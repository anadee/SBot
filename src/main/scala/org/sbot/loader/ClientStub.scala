package org.sbot.loader

import java.applet.{Applet, AppletContext, AppletStub}
import java.net.URL

import org.sbot.loader.actor.Crawler

/**
 * @author : const_
 */
case class ClientStub (crawler: Crawler) extends AppletStub {

  val clientAppletContext = new ClientAppletContext

  override def isActive: Boolean = true

  override def getParameter(name: String): String = crawler.parameters.getOrElse(name, "null")

  override def getDocumentBase: URL = new URL(getParameter("codebase"))

  override def getAppletContext: ClientAppletContext = {
    clientAppletContext
  }

  override def appletResize(width: Int, height: Int): Unit = {
    val applet: Applet = clientAppletContext.getApplet("main")
    if (applet != null) {
      applet.setSize(width, height)
    }
  }

  override def getCodeBase: URL = new URL(getParameter("codebase"))
}
