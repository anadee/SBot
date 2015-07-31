package org.sbot.runetek.input

import java.awt.{Graphics, GraphicsEnvironment, GraphicsConfiguration, Canvas}
import java.awt.image.BufferedImage

/**
 * @author : const_
 */
class SCanvas extends Canvas {

  lazy val config: GraphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment.getDefaultScreenDevice.getDefaultConfiguration
  lazy val backBuffer: BufferedImage = config.createCompatibleImage(765, 503)
  lazy val gameBuffer: BufferedImage =  config.createCompatibleImage(765, 503)

  override def getGraphics: Graphics = {
    val gameGraphics: Graphics = gameBuffer.getGraphics
    val bufferGraphics: Graphics = backBuffer.getGraphics
    bufferGraphics.drawImage(gameBuffer, 0, 0, null)
    val base: Graphics = super.getGraphics
//    if (LemonBuddy.environment != null) {
//      LemonBuddy.environment.eventDelegate.onPaintEvent(new PaintEvent(bufferGraphics))
//    }
    bufferGraphics.drawString("SBot", 100, 100)
    if (base != null) {
      base.drawImage(backBuffer, 0, 0, this)
    }
    gameGraphics
  }

  override def setBounds(x: Int, y: Int, width: Int, height: Int) {
    super.setBounds(0, 0, width, height)
  }
}
