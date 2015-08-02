package org.sbot.core.ui.component.tab

import org.sbot.core.ui.ActionCommands
import org.sbot.core.ui.component.Icons
import org.sbot.core.ui.controller.MainController

/**
 * @author : const_
 */

case class AddTab(mainController: MainController) extends Tab {

  setActionCommand(ActionCommands.AddTab)
  addActionListener(mainController)
  setIcon(Icons.Plus)
  setToolTipText("Add")

}
