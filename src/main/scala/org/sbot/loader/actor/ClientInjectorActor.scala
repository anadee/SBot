package org.sbot.loader.actor

import java.io.{FileOutputStream, File}
import java.util.jar.{JarOutputStream, JarFile, JarEntry}

import akka.actor.Actor
import com.typesafe.config.{ConfigFactory, Config}
import org.objectweb.asm.{Opcodes, ClassReader, ClassWriter}
import org.objectweb.asm.tree._
import org.sbot.loader.exception.{ClassNodeNotFoundException}
import org.sbot.loader.model.{GetterDefinition, ClassDefinition}
import org.slf4j.LoggerFactory

/**
 * @author : const_
 */

case class InjectClient(nodes: Map[String, ClassNode], hooks: Map[String, ClassDefinition], alreadyInjected: Boolean)

case class ClientInjector(config: Config) {

  val logger = LoggerFactory.getLogger(getClass)

  def inject(nodes: Map[String, ClassNode], hooks: Map[String, ClassDefinition]): Seq[ClassNode] = {
    logger.info(s"Injecting accessors and interfaces into classes")
    hooks.foreach { entry =>
      injectNode(nodes.getOrElse(entry._1, throw new ClassNodeNotFoundException(s"Unable to find ClassNode: ${entry._1}")), entry._2)
    }
    logger.info(s"Finished injecting ${hooks.size} classes")
    nodes.values.toSeq
  }

  private def injectNode(node: ClassNode, definition: ClassDefinition): Unit = {
    node.interfaces.add(definition.identifiedName.replaceAll("\\.", "/"))
    definition.getters.foreach { getter =>
      node.methods.add(createGetter(getter))
    }
  }

  private def createGetter(getter: GetterDefinition): MethodNode = {
    val accessor = new MethodNode(Opcodes.ACC_PUBLIC, getter.name, "()" + getter.signature, null, null)
    if (getter.member) {
      accessor.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0))
    }
    accessor.instructions.add(new FieldInsnNode(if (getter.member) Opcodes.GETFIELD else Opcodes.GETSTATIC, getter.fieldClass, getter.fieldName, getter.actualSig))
    if (getter.multiplier != 0) {
      accessor.instructions.add(new LdcInsnNode(getter.multiplier))
      accessor.instructions.add(new InsnNode(multiplyOpcode(getter.actualSig)))
    }
    accessor.instructions.add(new InsnNode(returnOpcode(getter.actualSig)))
    accessor.visitMaxs(1, 1)
    accessor.visitEnd()
    accessor
  }

  private def returnOpcode(desc: String): Int = {
    desc match {
      case "D" => Opcodes.DRETURN
      case "F" => Opcodes.FRETURN
      case "J" => Opcodes.LRETURN
      case "I" => Opcodes.IRETURN
      case "Z" => Opcodes.IRETURN
      case _ => Opcodes.ARETURN
    }
  }

  private def multiplyOpcode(desc: String): Int = {
    desc match {
      case "D" => Opcodes.DMUL
      case "F" => Opcodes.FMUL
      case "J" => Opcodes.LMUL
      case _ => Opcodes.IMUL
    }
  }
}
class ClientInjectorActor extends Actor {

  val clientInjector = ClientInjector(ConfigFactory.load)

  override def receive: Receive = {
    case InjectClient(nodes, hooks, alreadyInjected) =>
      if (alreadyInjected) {
        sender ! clientInjector.inject(nodes, hooks)
      } else {
        sender ! nodes.values.toSeq
      }
      context.stop(self)
  }
}
