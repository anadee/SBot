package org.sbot.loader.actor

import java.io.File
import java.util.jar.JarFile

import akka.actor.Actor
import akka.actor.Actor.Receive
import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import org.slf4j.LoggerFactory

/**
 * @author : const_
 */

case class LoadBytecode(file: File)

case class BytecodeLoader() {

  val logger = LoggerFactory.getLogger(getClass)

  def load(file: File): Map[String, ClassNode] = {
    import scala.collection.JavaConversions._
    logger.info(s"Loading bytecode for client: ${file.getName}")
    val jar = new JarFile(file)
    val map = jar.entries().filter(_.getName.endsWith(".class")).map { entry =>
      val reader: ClassReader = new ClassReader(jar.getInputStream(entry))
      val node: ClassNode = new ClassNode
      reader.accept(node, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES)
      node.name -> node
    }.toMap
    logger.info(s"Successfully loaded ${map.size} classes into bytecode form")
    map
  }
}
class BytecodeLoaderActor extends Actor {

  val bytecodeLoader = BytecodeLoader()

  override def receive: Receive = {
    case LoadBytecode(file) =>
      sender ! bytecodeLoader.load(file)
      context.stop(self)
  }
}
