package org.sbot.runetek.api.functions

import org.sbot.runetek.api.wrapper.Player

/**
 * @author : const_
 */
trait Players {

  def getLoaded: Option[Seq[Player]]

  def getMyPlayer: Option[Player]
}
