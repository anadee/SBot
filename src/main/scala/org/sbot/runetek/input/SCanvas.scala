package org.sbot.runetek.input

import java.awt.{Graphics, GraphicsEnvironment, GraphicsConfiguration, Canvas}
import java.awt.image.BufferedImage

import akka.actor.{Props, ActorSystem}
import org.sbot.framework.actor.{EventWrapper, EventActor}
import org.sbot.framework.evt.PaintEvent

/**
 * @author : const_
 */
class SCanvas extends Canvas {

  lazy val config: GraphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment.getDefaultScreenDevice.getDefaultConfiguration
  lazy val backBuffer: BufferedImage = config.createCompatibleImage(765, 503)
  lazy val gameBuffer: BufferedImage =  config.createCompatibleImage(765, 503)
  val system = ActorSystem(getClass.getSimpleName)
  val eventActor = system.actorOf(Props[EventActor])

  override def getGraphics: Graphics = {
    val gameGraphics: Graphics = gameBuffer.getGraphics
    val bufferGraphics: Graphics = backBuffer.getGraphics
    bufferGraphics.drawImage(gameBuffer, 0, 0, null)
    val base: Graphics = super.getGraphics
    eventActor ! EventWrapper(PaintEvent(bufferGraphics), hashCode())
    if (base != null) {
      base.drawImage(backBuffer, 0, 0, this)
    }
    gameGraphics
  }

  override def setBounds(x: Int, y: Int, width: Int, height: Int) {
    super.setBounds(0, 0, width, height)
  }
}
