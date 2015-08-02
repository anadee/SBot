package org.sbot.loader.actor

import java.applet.Applet
import java.io.File
import java.net.{URLClassLoader, URL}

import akka.actor.Actor
import akka.actor.Actor.Receive
import com.typesafe.config.{ConfigFactory, Config}
import org.sbot.loader.{ClientStub, ClientClassLoader}
import org.sbot.runetek.engine.IClient
import org.slf4j.LoggerFactory
import sun.applet.AppletClassLoader

/**
 * @author : const_
 */

case class LoadApplet(client: File, mainClass: String, crawler: Crawler)

class AppletInstantiator(client: File, mainClass: String, crawler: Crawler, config: Config) {

  val logger = LoggerFactory.getLogger(getClass)

  def loadApplet(): Applet = {
    logger.info(s"Loading applet...")
    val jarURL: URL = client.toURI.toURL
    val classLoader: ClientClassLoader = ClientClassLoader(Array(jarURL))
    val clientClass: Class[_] = classLoader.loadClass(mainClass)
    val applet = clientClass.newInstance.asInstanceOf[Applet]
    val appletStub = ClientStub(crawler)
    appletStub.getAppletContext.setApplet(applet)
    applet.setStub(appletStub)
    applet.init
    applet.start
    logger.info(s"Applet has been started")
    applet
  }
}
class AppletInstantiatorActor extends Actor {

  override def receive: Receive = {
    case LoadApplet(client, mainClass, crawler) =>
      sender ! new AppletInstantiator(client, mainClass.split(".class")(0), crawler, ConfigFactory.load).loadApplet()
      context.stop(self)
  }

}
