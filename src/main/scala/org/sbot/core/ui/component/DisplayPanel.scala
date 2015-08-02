package org.sbot.core.ui.component

import java.awt.Dimension
import javax.swing.{JPanel, JTabbedPane}

import com.typesafe.config.{ConfigFactory, Config}
import org.sbot.core.ui.component.tab.{GameTab, AddTab, Tab}
import org.sbot.core.ui.controller.MainController
import org.sbot.core.ui.view.MainView

/**
 * @author : const_
 */
case class DisplayPanel (config: Config) extends JPanel {

  val ConfigMinWidthKey = "sbot.displaypanel.min-width"
  val ConfigMinHeightKey = "sbot.displaypanel.min-height"

  setMinimumSize(new Dimension(config.getInt(ConfigMinWidthKey), config.getInt(ConfigMinHeightKey)))
  setSize(new Dimension(config.getInt(ConfigMinWidthKey), config.getInt(ConfigMinHeightKey)))
}
