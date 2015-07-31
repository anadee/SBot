package org.sbot.ui.controller

import java.awt.event.{ActionEvent, ActionListener}

import com.google.inject.{Singleton, Inject}
import org.sbot.ui.model.MainModel
import org.sbot.ui.view.MainView
import org.slf4j.LoggerFactory

/**
 * @author : const_
 */
@Singleton
@Inject
class MainController extends ActionListener {

  val logger = LoggerFactory.getLogger(getClass)
  @Inject
  var model: MainModel = _
  @Inject
  var view: MainView = _

  override def actionPerformed(e: ActionEvent): Unit = {
    logger.info(s"$e")
  }
}
