package org.sbot.core.ui.component

import javax.imageio.ImageIO
import javax.swing.ImageIcon

/**
 * @author : const_
 */
object Icons {

  val Plus = new ImageIcon(ImageIO.read(getClass.getResourceAsStream("/icon/plus.png")))
  val Minus = new ImageIcon(ImageIO.read(getClass.getResourceAsStream("/icon/minus.png")))
  val User = new ImageIcon(ImageIO.read(getClass.getResourceAsStream("/icon/user.png")))
  val Stop = new ImageIcon(ImageIO.read(getClass.getResourceAsStream("/icon/stop.png")))
  val Start = new ImageIcon(ImageIO.read(getClass.getResourceAsStream("/icon/play.png")))
  val Enabled = new ImageIcon(ImageIO.read(getClass.getResourceAsStream("/icon/enabled.png")))
  val Disabled = new ImageIcon(ImageIO.read(getClass.getResourceAsStream("/icon/disabled.png")))

}
