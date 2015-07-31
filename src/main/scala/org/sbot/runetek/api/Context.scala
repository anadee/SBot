package org.sbot.runetek.api

import org.sbot.core.Session
import org.sbot.runetek.engine.IClient


/**
 * @author : const_
 */
case class Context(session: Session) {

  def getClient: IClient = session.getClient()
}
