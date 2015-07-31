package org.sbot.loader.actor

import scala.concurrent.ExecutionContext.Implicits.global
import java.io.File

import akka.actor.Actor
import com.typesafe.config.{Config, ConfigFactory}
import org.sbot.loader.exception.UnableToDownloadClientException
import org.sbot.util.{Disk, Internet}
import org.slf4j.LoggerFactory

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
 * @author : const_
 */

case class GrabClient(version: Int, codebase: String, initialJar: String)

case class ClientGrabber(config: Config) {

  val logger = LoggerFactory.getLogger(getClass)

  val ConfigHomeDirKey = "sbot.data.home-dir"
  val ConfigClientDirKey = "sbot.data.client-dir"
  val ConfigDownloadTimeoutKey = "sbot.loader.download-timeout"

  def grabClient(version: Int, codebase: String, initialJar: String): File = {
    val file = new File(System.getProperty("user.home") +
      config.getString(ConfigHomeDirKey) + config.getString(ConfigClientDirKey) + version + ".jar")
    if(file.exists()) {
      file
    } else {
      val packUrl: String = codebase + initialJar
      val client = Internet.downloadBytes(packUrl) map {
        case bytes =>
          Disk.write(file, bytes)
          file
        case _ => throw new UnableToDownloadClientException(s"Unable to download gamepack: $packUrl")
      }
      Await.result(client, config.getInt(ConfigDownloadTimeoutKey) seconds)
    }
  }
}
class ClientGrabberActor extends Actor {

  val clientGrabber = ClientGrabber(ConfigFactory.load)

  override def receive: Receive = {
    case GrabClient(version, codebase, initialJar) =>
      sender ! clientGrabber.grabClient(version, codebase, initialJar)
      context.stop(self)
  }
}
