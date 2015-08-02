package org.sbot.core.ui.component

import java.applet.Applet
import java.awt.BorderLayout
import javax.swing.JPanel

/**
 * @author : const_
 */
case class AppletContainer(applet: Applet) extends JPanel {

  add(applet, BorderLayout.CENTER)

}
