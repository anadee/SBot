package org.sbot.loader.actor

import java.io.File

import akka.actor.Actor
import org.json4s.native.Serialization
import org.sbot.loader.exception.ErrorParsingHooksException
import org.sbot.loader.model.{MethodDefinition, GetterDefinition, ClassDefinition}
import org.sbot.util.Disk
import org.slf4j.LoggerFactory

import org.json4s._

import scala.util.Try

/**
 * @author : const_
 */

case class LoadHooks()

object JSONUtil {

  implicit val formats = DefaultFormats + FieldSerializer[ClassDefinition]()

  def toJSON(objectToWrite: AnyRef): String = Serialization.write(objectToWrite)

  def fromJSONOption[T](jsonString: String)(implicit mf: Manifest[T]): Option[T] = Try(Serialization.read(jsonString)).toOption

}

case class HookLoader() {

  val logger = LoggerFactory.getLogger(getClass)

  def parseHooks(hooks: String): Map[String, ClassDefinition] = {
    logger.info(s"Parsing hooks")
    val classDefinitions = JSONUtil.fromJSONOption[Seq[ClassDefinition]](hooks)
    val map = classDefinitions.getOrElse(throw new ErrorParsingHooksException(s"Unable to parse hooks")).map { classDef =>
      classDef.originalName -> classDef
    }.toMap
    logger.info(s"Loaded ${map.size} class definitions")
    map
  }
}
class HookLoaderActor extends Actor {

  val hookLoader = HookLoader()

  override def receive: Receive = {
    case LoadHooks => sender ! hookLoader.parseHooks(new String(Disk.read(new File("./hooks.json"))))
  }

}
