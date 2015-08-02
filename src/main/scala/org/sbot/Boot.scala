package org.sbot

import java.io.File
import javax.swing.UIManager

import ch.randelshofer.quaqua.QuaquaLookAndFeel
import com.google.inject.{Inject, Guice}
import com.typesafe.config.Config
import org.sbot.core.di.{InstanceProvider, Injector}
import org.sbot.loader.ClientLoaderCoordinator
import org.sbot.loader.actor.Crawler
import org.sbot.core.ui.view.MainView
/**
 * @author : const_
 */
object Boot {

  @Inject
  var boot: Boot = _
  @Inject
  var config: Config = _

  val ConfigHomeDirKey = "sbot.data.home-dir"
  val ConfigClientDirKey = "sbot.data.client-dir"

  def main(args: Array[String]) {
    //UIManager.setLookAndFeel(new QuaquaLookAndFeel)
    InstanceProvider.injectMembers(this)
    mkdirs()
    boot.loadFrame()
  }

  private def mkdirs(): Unit = {
    val homeDir = System.getProperty("user.home") + config.getString(ConfigHomeDirKey)
    val clientDir = homeDir + config.getString(ConfigClientDirKey)
    IndexedSeq(new File(homeDir), new File(clientDir)).foreach(_.mkdir())
  }
}

class Boot @Inject()(mainView: MainView) {

  def loadFrame(): Unit = {
    mainView.init()
  }
}

