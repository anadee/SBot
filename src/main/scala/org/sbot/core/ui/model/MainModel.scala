package org.sbot.core.ui.model

import java.applet.Applet
import java.awt.{Dimension, BorderLayout}

import com.google.inject.{Singleton, Inject}
import com.typesafe.config.Config
import org.sbot.core.ui.exception.SessionNotFoundException
import org.sbot.core.{Sessions, Session}
import org.sbot.core.ui.component.AppletContainer
import org.sbot.core.ui.component.tab.GameTab
import org.sbot.core.ui.controller.MainController
import org.sbot.core.ui.view.MainView
import org.sbot.runetek.engine.IClient
import org.slf4j.LoggerFactory

/**
 * @author : const_
 */
@Singleton
class MainModel @Inject()(config: Config) {

  @Inject
  val view: MainView = null
  @Inject
  val controller: MainController = null

  val logger = LoggerFactory.getLogger(getClass)

  var selectedTab: Option[GameTab] = None
  val tabs: IndexedSeq[GameTab] = scala.collection.mutable.IndexedSeq()

  val ConfigMinWidthKey = "sbot.displaypanel.min-width"
  val ConfigMinHeightKey = "sbot.displaypanel.min-height"

  def addTab(applet: Applet) = {
    logger.info(s"Adding applet to main view: $applet")
    applet.setSize(new Dimension(config.getInt(ConfigMinWidthKey), config.getInt(ConfigMinHeightKey)))
    Sessions.addSession(applet)
    println(applet)
    val gameTab = new GameTab("GameTab", AppletContainer(applet),
      Sessions.getSession(applet.asInstanceOf[IClient].getCanvas().hashCode())
        .getOrElse(throw SessionNotFoundException(s"Unable to find session")), controller)
    view.addTab(gameTab)
    setSelectedTab(gameTab)
  }

  def setSelectedTab(gameTab: GameTab) = {
    selectedTab = Option(gameTab)
    view.setSelectedTab(gameTab)
  }
}
