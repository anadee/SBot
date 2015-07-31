package org.sbot.di

import com.google.inject.{Guice, AbstractModule}
import com.typesafe.config.{ConfigFactory, Config}
import net.codingwell.scalaguice.ScalaModule
import org.sbot.ui.controller.MainController
import org.sbot.ui.model.MainModel
import org.sbot.ui.view.MainView

/**
 * @author : const_
 */

object InstanceProvider {


  val injector = Guice.createInjector(new Injector)

  def instantiate(clazz: Class[Any]) = {
    injector.getInstance(clazz)
  }

  def injectMembers(instance: Any) = {
    injector.injectMembers(instance)
  }
}
class Injector extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    val config: Config = ConfigFactory.load()
    bind[Config].toInstance(config)
  }

}
