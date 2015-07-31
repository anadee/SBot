package org.sbot.loader.actor

import scala.concurrent.ExecutionContext.Implicits.global
import java.io.{InputStream, OutputStream}
import java.net.Socket

import akka.actor.Actor
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

import scala.concurrent.Future

/**
 * @author : const_
 */

case class FindVersion(codebase: String)

class ClientVersionFinder (config: Config) {

  val ConfigLastKnownVersionKey = "sbot.loader.last-known-version"

  val logger = LoggerFactory.getLogger(getClass)
  val LastKnownVersion = config.getInt(ConfigLastKnownVersionKey)

  def findCurrentVersion(codebase: String): Int = {
    logger.info(s"Looking for current client version, last known version: $LastKnownVersion")
    var version: Int = LastKnownVersion
    var finished = false
    while (version < 200 && !finished) {
      val socket: Socket = new Socket(codebase.replaceAll("http://", "").replaceAll("/", ""), 43594)
      val socketInput: InputStream = socket.getInputStream
      val socketOutput: OutputStream = socket.getOutputStream
      socketOutput.write(15)
      socketOutput.write(0)
      socketOutput.write(0)
      socketOutput.write(version >> 8)
      socketOutput.write(version)
      socketOutput.flush
      val response: Int = socketInput.read
      if (response == 0) {
        socket.close
        logger.info(s"Found current version: $version")
        finished = true
        version
      } else {
        socket.close
        version += 1
      }
    }
    version
  }
}
class ClientVersionActor extends Actor {

  val clientVersionFinder = new ClientVersionFinder(ConfigFactory.load)

  override def receive: Receive = {
    case FindVersion(codebase) =>
      sender ! clientVersionFinder.findCurrentVersion(codebase)
      context.stop(self)
  }
}
