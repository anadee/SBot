package org.sbot.runetek.api.functions.impl

import org.sbot.runetek.api.ContextProvider
import org.sbot.runetek.api.functions.Camera

/**
 * @author : const_
 */
class CameraImpl() extends ContextProvider with Camera {

  override def getX: Int = context.getClient.getCameraX()

  override def getY: Int = context.getClient.getCameraY()

  override def getZ: Int = context.getClient.getCameraZ()

  override def getYaw: Int = context.getClient.getCameraYaw()

  override def getPitch: Int = context.getClient.getCameraPitch()

}
