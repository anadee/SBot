package org.sbot.core.ui.model

import com.google.inject.{Singleton, Inject}
import org.sbot.core.ui.controller.MainController
import org.sbot.core.ui.view.MainView

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
