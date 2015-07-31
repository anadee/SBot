package org.sbot.core

import java.applet.Applet

import org.sbot.runetek.api.Context
import org.sbot.runetek.engine.IClient
import org.sbot.runetek.evt.PaintEvent

import scala.collection.mutable

/**
 * @author : const_
 */

object Sessions {

  val sessions: scala.collection.mutable.Map[Int, Session] = new mutable.HashMap[Int, Session]()

  def addSession(applet: Applet) = {
    val session = Session(applet)
    sessions.put(session.getClient().canvas().hashCode(), session)
  }

  def getSession(hashCode: Int): Option[Session] = {
    sessions.get(hashCode)
  }
}

case class Session(applet: Applet) {

  val context: Context = Context(this)

  def onPaintEvent(paintEvent: PaintEvent) = {

  }
  def getClient(): IClient = applet.asInstanceOf[IClient]

}
