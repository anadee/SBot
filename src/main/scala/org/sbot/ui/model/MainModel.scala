package org.sbot.ui.model

import com.google.inject.{Singleton, Inject}
import org.sbot.ui.controller.MainController
import org.sbot.ui.view.MainView

/**
 * @author : const_
 */
@Singleton
@Inject
class MainModel {

  @Inject
  var controller: MainController = _
  @Inject
  var view: MainView = _
}
