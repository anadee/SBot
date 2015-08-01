package org.sbot.loader

import org.objectweb.asm.tree.ClassNode
import org.sbot.core.Sessions
import org.sbot.loader.model.ClassDefinition

import scala.concurrent.ExecutionContext.Implicits.global
import java.applet.Applet
import java.io.File

import akka.actor.{Props, ActorSystem}
import akka.util.Timeout
import com.google.inject.Inject
import com.typesafe.config.Config
import org.sbot.loader.actor._
import org.sbot.core.ui.view.MainView
import org.slf4j.{LoggerFactory, Logger}

import akka.pattern.ask

import scala.concurrent.duration._
import scala.concurrent.{Future, Await}
import scala.util.{Failure, Success}

/**
 * @author : const_
 */
class ClientLoaderCoordinator @Inject()(config: Config, mainView: MainView) {

  val logger = LoggerFactory.getLogger(getClass)
  val system = ActorSystem(getClass.getSimpleName)

  val ConfigCrawlerTimeoutKey = "sbot.loader.crawler-timeout"
  val ConfigGlobalTimeoutKey = "sbot.loader.global-timeout"
  val ConfigHomeDirKey = "sbot.data.home-dir"
  val ConfigClientDirKey = "sbot.data.client-dir"
  val ConfigInjectedSuffixKey = "sbot.data.injected-suffix"

  val CrawlerTimeout = config.getInt(ConfigCrawlerTimeoutKey)

  implicit  val GlobalTimeout = Timeout(config.getInt(ConfigGlobalTimeoutKey) seconds)

  def getInjectedFile(version: Int) = new File(System.getProperty("user.home") +
    config.getString(ConfigHomeDirKey) + config.getString(ConfigClientDirKey)
    + version + config.getString(ConfigInjectedSuffixKey) +".jar")

  def load(): Unit = {
    logger.info(s"Beginning to load game...")
    val crawlerActor = system.actorOf(Props[CrawlerActor])
    val clientVersionActor = system.actorOf(Props[ClientVersionActor])
    val clientGrabberActor = system.actorOf(Props[ClientGrabberActor])
    val hookLoaderActor = system.actorOf(Props[HookLoaderActor])
    val bytecodeLoaderActor = system.actorOf(Props[BytecodeLoaderActor])
    val clientInjectorActor = system.actorOf(Props[ClientInjectorActor])
    val canvasOverrideActor = system.actorOf(Props[CanvasOverrideActor])
    val bytecodeDumperActor = system.actorOf(Props[BytecodeDumperActor])
    val appletInstantiatorActor = system.actorOf(Props[AppletInstantiatorActor])

    val applet = for {
      crawler <- (crawlerActor ? Crawl).mapTo[Crawler]
      version <- (clientVersionActor ? FindVersion(crawler.getParameter("codebase"))).mapTo[Int]
      pack <- (clientGrabberActor ? GrabClient(version, crawler.getParameter("codebase"), crawler.getParameter("initial_jar"))).mapTo[File]
      injectedFile = getInjectedFile(version)
      hooks <- (hookLoaderActor ? LoadHooks).mapTo[Map[String, ClassDefinition]]
      nodes <- (bytecodeLoaderActor ? LoadBytecode(pack)).mapTo[Map[String, ClassNode]]
      injectedNodes <- (clientInjectorActor ? InjectClient(nodes, hooks, injectedFile.exists())).mapTo[Seq[ClassNode]]
      injectedCanvasNode <- (canvasOverrideActor ? OverrideCanvas(injectedNodes)).mapTo[ClassNode]
      dumpedPack <- (bytecodeDumperActor ? DumpBytecode(getInjectedFile(version), injectedNodes)).mapTo[File]
      applet <- (appletInstantiatorActor ? LoadApplet(injectedFile, crawler.getParameter("initial_class"), crawler)).mapTo[Applet]
    } yield applet

    applet onComplete {
      case Success(loadedApplet) =>
        Sessions.addSession(loadedApplet)
        mainView.setApplet(loadedApplet)
      case Failure(ex)           => logger.error(s"Error loading applet", ex)
    }

  }
}
