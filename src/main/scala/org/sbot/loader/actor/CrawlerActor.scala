package org.sbot.loader.actor

import akka.actor.Actor
import com.google.inject.Inject
import com.typesafe.config.{Config, ConfigFactory}
import org.sbot.loader.exception.ParameterNotFoundException
import org.sbot.util.Internet
import org.slf4j.LoggerFactory

/**
 * @author : const_
 */

case class Crawl()

case class Crawler(config: Config) {

  val logger = LoggerFactory.getLogger(getClass)

  val ConfigGameBaseUrlKey = "sbot.game.base-url"
  val ConfigGameConfigFileKey = "sbot.game.config-file"

  def GameBaseUrl = config.getString(ConfigGameBaseUrlKey)

  def GameConfigFile = config.getString(ConfigGameConfigFileKey)

  val parameters = {
    val url = GameBaseUrl + GameConfigFile
    logger.info(s"Crawling $url")
    val body = Internet.downloadString(url)
    if (body == null) {
      logger.info(s"Error crawling $url")
      throw new RuntimeException("Error with params")
    } else {
      val map = body.replaceAll("param=", "").replaceAll("msg=", "").split("\n")
        .filter(line => line.lastIndexOf('=') < line.length - 1).map { line =>
        val split = line.split('=')
        split(0) -> split(1)
      }.toMap
      logger.info(s"Finished crawling $url -- Found ${map.size} parameters")
      map
    }
  }

  def getParameter(name: String) = parameters.getOrElse(name, throw new ParameterNotFoundException(s"Unable to find parameter: $name"))
}

class CrawlerActor extends Actor {

  override def receive: Receive = {
    case Crawl =>
      sender ! new Crawler(ConfigFactory.load)
      context.stop(self)
  }
}
