package org.sbot.framework.actor

import akka.actor.Actor
import org.sbot.core.{Sessions, Session}
import org.sbot.framework.Event
import org.sbot.framework.evt.PaintEvent

/**
 * @author : const_
 */

case class EventWrapper(evt: Event, hash: Int)

class EventActor  extends Actor {

  override def receive: Receive = {
    case EventWrapper(paintEvent: PaintEvent, hash) => Sessions.getSession(hash).map(_.onPaintEvent(paintEvent))
  }

}
