package org.sbot.core.ui.view

import java.applet.Applet
import java.awt.{BorderLayout, Dimension}
import javafx.geometry.VerticalDirection
import javax.swing._

import com.google.inject.{Singleton, Inject}
import com.typesafe.config.Config
import org.sbot.core.ui.ActionCommands
import org.sbot.core.ui.component.tab.{AddTab, GameTab}
import org.sbot.loader.actor.{ClientGrabber, Crawler}
import org.sbot.core.ui.component._
import org.sbot.core.ui.controller.MainController
import org.sbot.core.ui.model.MainModel
import org.slf4j.LoggerFactory


/**
 * @author : const_
 */
@Singleton
class MainView @Inject()(config: Config) extends Frame {

  @Inject
  val model: MainModel = null
  @Inject
  val controller: MainController = null

  val logger = LoggerFactory.getLogger(getClass)

  val ConfigNameKey = "sbot.mainframe.name"
  val ConfigWidthKey = "sbot.mainframe.width"
  val ConfigHeightKey = "sbot.mainframe.height"

  val clientMenuBar = ClientMenuBar()
  val clientTabToolBar = ClientTabToolBar()
  val displayPanel = DisplayPanel(config)
  val btnRunScript = new JButton()
  val btnStopScript = new JButton()
  val btnStartScript = new JButton()
  val btnRender = new JButton("Render")
  val btnInput = new JButton("Input")

  def setSelectedTab(gameTab: GameTab) = {
    if(displayPanel.getComponents.length > 0) {
      displayPanel.remove(0)
    }
    model.tabs.foreach(_.setEnabled(true))
    gameTab.setEnabled(false)
    displayPanel.add(gameTab.appletContainer)
    revalidate()
  }

  def addTab(gameTab: GameTab) = {
    val addTabIndex = clientTabToolBar.getComponentIndex(clientTabToolBar.getComponents.find(_.isInstanceOf[AddTab]).head)
    clientTabToolBar.remove(addTabIndex)
    clientTabToolBar.add(gameTab, addTabIndex)
    clientTabToolBar.add(AddTab(controller), addTabIndex + 1)
    model.tabs.foreach(_.setEnabled(true))
    gameTab.setEnabled(false)
    val separatorIndex = clientTabToolBar.getComponentIndex(clientTabToolBar.getComponents.find(_.isInstanceOf[JSeparator]).head)
    clientTabToolBar.remove(separatorIndex)
    clientTabToolBar.add(Box.createHorizontalGlue(), separatorIndex - model.tabs.size)
  }

  def init(): Unit = {
    setLayout(new BorderLayout())
    setTitle(config.getString(ConfigNameKey))
    val width = config.getInt(ConfigWidthKey)
    val height = config.getInt(ConfigHeightKey)

    val dimensions = new Dimension(width, height)
    setPreferredSize(dimensions)
    setMinimumSize(dimensions)

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setVisible(true)
    setLocationRelativeTo(null)

    clientMenuBar.init(controller)
    setJMenuBar(clientMenuBar)


    clientTabToolBar.setFloatable(false)
    getContentPane.add(clientTabToolBar, BorderLayout.NORTH)

    getContentPane.add(displayPanel, BorderLayout.CENTER)

    val addTab: AddTab = new AddTab(controller)
    clientTabToolBar.add(addTab)

    clientTabToolBar.add(Box.createHorizontalGlue())

    btnRunScript.setIcon(Icons.Start)
    btnRunScript.setActionCommand(ActionCommands.RunScript)
    btnRunScript.addActionListener(controller)
    clientTabToolBar.add(btnRunScript)
    clientTabToolBar.addSeparator(new Dimension(5, clientTabToolBar.getHeight))

    btnStopScript.setIcon(Icons.Stop)
    btnStopScript.setEnabled(false)
    btnStopScript.setActionCommand(ActionCommands.StopScript)
    btnStopScript.addActionListener(controller)
    clientTabToolBar.add(btnStopScript)
    clientTabToolBar.addSeparator(new Dimension(5, clientTabToolBar.getHeight))

    btnInput.setIcon(Icons.Enabled)
    btnInput.setActionCommand(ActionCommands.InputToggle)
    btnInput.addActionListener(controller)
    clientTabToolBar.add(btnInput)
    clientTabToolBar.addSeparator(new Dimension(5, clientTabToolBar.getHeight))

    btnRender.setIcon(Icons.Enabled)
    btnRender.setActionCommand(ActionCommands.RenderToggle)
    btnRender.addActionListener(controller)
    clientTabToolBar.add(btnRender)
  }
}
