package org.sbot.core.ui.component.tab

import org.sbot.core.Session
import org.sbot.core.ui.ActionCommands
import org.sbot.core.ui.component.{AppletContainer, Icons}
import org.sbot.core.ui.controller.MainController

/**
 * @author : const_
 */
case class GameTab(name: String, appletContainer: AppletContainer, session: Session, mainController: MainController) extends Tab {

  setActionCommand(ActionCommands.SelectTab)
  addActionListener(mainController)
  setIcon(Icons.User)
  setToolTipText(name)

}
