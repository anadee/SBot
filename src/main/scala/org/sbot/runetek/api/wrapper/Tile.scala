package org.sbot.runetek.api.wrapper

import org.sbot.runetek.engine.scene.ITile


/**
 * @author : const_
 */
case class Tile(x: Int, y: Int, plane: Int) extends Locatable {

  override def getX: Int = x

  override def getPlane: Int = y

  override def getY: Int = plane
}
