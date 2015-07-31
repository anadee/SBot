package org.sbot.ui.component

import java.awt.event.ActionListener
import javax.swing.{JCheckBoxMenuItem, JMenuItem, JMenu, JMenuBar}

/**
 * @author : const_
 */
case class ClientMenuBar() extends JMenuBar {

  def init(actionListener: ActionListener): Unit = {
    val mnFile = new JMenu("File")
    val mnAccounts = new JMenuItem("Accounts")
    mnAccounts.setActionCommand("file:accounts")
    mnAccounts.addActionListener(actionListener)
    mnFile.add(mnAccounts)

    val mnDebug = new JMenu("Debug")
    val debugCamera = new JCheckBoxMenuItem("Camera")
    debugCamera.setActionCommand("debug:camera")
    debugCamera.addActionListener(actionListener)
    mnDebug.add(debugCamera)

    add(mnFile)
    add(mnDebug)
  }
}
