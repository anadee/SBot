package org.sbot.runetek.api.functions.impl

import org.sbot.runetek.api.ContextProvider
import org.sbot.runetek.api.wrapper.Player

/**
 * @author : const_
 */
class Players() extends ContextProvider {

  def getLoaded: Option[Seq[Player]] = Option(context.getClient.players().filter(_ != null).map(Player))

  def getMyPlayer: Option[Player] = Option(context.getClient.localPlayer()) match {
    case Some (player) => Option(Player(player))
  }
}
