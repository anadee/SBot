package org.sbot

import java.io.File

import com.google.inject.{Inject, Guice}
import com.typesafe.config.Config
import org.sbot.di.{InstanceProvider, Injector}
import org.sbot.loader.ClientLoaderCoordinator
import org.sbot.loader.actor.Crawler
import org.sbot.ui.view.MainView
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
    InstanceProvider.injectMembers(this)
    mkdirs()
    boot.loadFrame()
    boot.loadClient()
  }

  private def mkdirs(): Unit = {
    val homeDir = System.getProperty("user.home") + config.getString(ConfigHomeDirKey)
    val clientDir = homeDir + config.getString(ConfigClientDirKey)
    IndexedSeq(new File(homeDir), new File(clientDir)).foreach(_.mkdir())
  }
}

class Boot @Inject()(mainView: MainView, clientLoaderCoordinator: ClientLoaderCoordinator) {

  def loadFrame(): Unit = {
    mainView.init()
  }

  def loadClient(): Unit = {
    clientLoaderCoordinator.load()
  }
}

