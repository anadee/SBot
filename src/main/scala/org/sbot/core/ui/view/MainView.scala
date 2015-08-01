package org.sbot.core.ui.view

import java.applet.Applet
import java.awt.{BorderLayout, Dimension}
import javax.swing.JFrame

import com.google.inject.{Singleton, Inject}
import com.typesafe.config.Config
import org.sbot.loader.actor.{ClientGrabber, Crawler}
import org.sbot.core.ui.component.{ClientMenuBar, Frame}
import org.sbot.core.ui.controller.MainController
import org.sbot.core.ui.model.MainModel
import org.slf4j.LoggerFactory


/**
 * @author : const_
 */
@Singleton
@Inject
class MainView @Inject()(config: Config) extends Frame {

  val logger = LoggerFactory.getLogger(getClass)
  @Inject
  var model: MainModel = _
  @Inject
  var controller: MainController = _

  val ConfigNameKey = "sbot.mainframe.name"
  val ConfigWidthKey = "sbot.mainframe.width"
  val ConfigHeightKey = "sbot.mainframe.height"

  val clientMenuBar = ClientMenuBar()


  def init(): Unit = {
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
  }

  def setApplet(applet: Applet) = {
    logger.info(s"Adding applet to main view: $applet")
    add(applet, BorderLayout.CENTER)
    revalidate()
  }
}
