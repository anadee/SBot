package org.sbot.loader.actor

import java.awt.Canvas

import akka.actor.Actor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.{ClassNode, MethodInsnNode}
import org.sbot.loader.exception.CanvasClassNotOverriddenException
import org.sbot.runetek.input.SCanvas

/**
 * @author : const_
 */

case class OverrideCanvas(nodes: Seq[ClassNode])

case class CanvasOverrider() {

  val CanvasName = classOf[SCanvas].getName.replaceAll("\\.", "/")

  def overrideCanvas(nodes: Seq[ClassNode]): ClassNode = {
    import scala.collection.JavaConversions._
    val canvasNode = nodes.find(_.superName == classOf[Canvas].getName.replaceAll("\\.", "/")).map {node =>
      node.superName = CanvasName
      node.methods.toSeq.find(_.name == "<init>").headOption.map { method =>
        method.instructions.toArray.toSeq
          .filter(ain => ain.getOpcode == Opcodes.INVOKESPECIAL && ((ain.asInstanceOf[MethodInsnNode]).owner == "java/awt/Canvas"))
          .map(insn => insn.asInstanceOf[MethodInsnNode].owner = CanvasName)
        method.visitMaxs(1, 1)
        method.visitEnd()
      }
      node.visitEnd()
      node
    }
    canvasNode.getOrElse(throw new CanvasClassNotOverriddenException(s"Unable to override canvas class"))
  }
}
class CanvasOverrideActor extends Actor {

  val canvasOverrider = CanvasOverrider()

  override def receive: Receive = {
    case OverrideCanvas(nodes) =>
      sender ! canvasOverrider.overrideCanvas(nodes)
      context.stop(self)
  }
}
