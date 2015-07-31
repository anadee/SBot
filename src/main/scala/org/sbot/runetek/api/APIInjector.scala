package org.sbot.runetek.api

import com.google.inject.AbstractModule
import com.typesafe.config.{ConfigFactory, Config}
import net.codingwell.scalaguice.ScalaModule

/**
 * @author : const_
 */
class APIInjector extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    val config: Config = ConfigFactory.load()
    bind[Config].toInstance(config)
    bind[Bank]
  }

}
