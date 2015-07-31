package org.sbot.loader.actor

import java.io.{FileOutputStream, File}
import java.util.jar.{JarEntry, JarOutputStream}

import akka.actor.Actor
import akka.actor.Actor.Receive
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.tree.ClassNode
import org.slf4j.LoggerFactory

/**
 * @author : const_
 */

case class DumpBytecode(outputFile: File, nodes: Seq[ClassNode])

case class BytecodeDumper() {

  val logger = LoggerFactory.getLogger(getClass)

  def save(output: File, nodes: Seq[ClassNode]) = {
    logger.info(s"Dumping ${nodes.size} nodes to ${output.getName}")
    output.createNewFile()
    val outStream = new JarOutputStream(new FileOutputStream(output))
    nodes.foreach { node =>
      val writer: ClassWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS)
      outStream.putNextEntry(new JarEntry(node.name.replaceAll("\\.", "/") + ".class"))
      node.accept(writer)
      outStream.write(writer.toByteArray)
      outStream.closeEntry()
    }
    outStream.close()
    logger.info(s"Finished dumping nodes to file")
    output
  }
}
class BytecodeDumperActor extends Actor {

  val bytecodeDumper = BytecodeDumper()

  override def receive: Receive = {
    case DumpBytecode(outputFile, nodes) =>
      sender ! bytecodeDumper.save(outputFile, nodes)
      context.stop(self)
  }
}
