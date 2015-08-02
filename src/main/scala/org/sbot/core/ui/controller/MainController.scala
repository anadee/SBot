package org.sbot.core.ui.controller

import java.awt.event.{ActionEvent, ActionListener}

import com.google.inject.{Singleton, Inject}
import com.typesafe.config.Config
import org.sbot.core.ui.ActionCommands
import org.sbot.core.ui.component.tab.GameTab
import org.sbot.core.ui.model.MainModel
import org.sbot.core.ui.view.MainView
import org.sbot.loader.ClientLoaderCoordinator
import org.slf4j.LoggerFactory

/**
 * @author : const_
 */
@Singleton
class MainController @Inject()(config: Config) extends ActionListener {

  @Inject
  val model: MainModel = null
  @Inject
  val view: MainView = null

  lazy val clientLoaderCoordinator: ClientLoaderCoordinator = ClientLoaderCoordinator(config, view, model)

  val logger = LoggerFactory.getLogger(getClass)

  override def actionPerformed(evt: ActionEvent): Unit = {
    evt.getActionCommand match {
      case ActionCommands.AddTab =>
        println(clientLoaderCoordinator)
        clientLoaderCoordinator.load()
      case ActionCommands.SelectTab =>
        val gameTab = evt.getSource.asInstanceOf[GameTab]
      case _ => logger.error(s"Unhandled event $evt")

    }
  }
}
