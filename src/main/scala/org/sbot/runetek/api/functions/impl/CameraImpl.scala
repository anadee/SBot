package org.sbot.runetek.api.functions.impl

import org.sbot.runetek.api.ContextProvider
import org.sbot.runetek.api.functions.Camera

/**
 * @author : const_
 */
class CameraImpl() extends ContextProvider with Camera {

  override def getX: Int = context.getClient.cameraX()

  override def getY: Int = context.getClient.cameraY()

  override def getZ: Int = context.getClient.cameraZ()

  override def getYaw: Int = context.getClient.cameraYaw()

  override def getPitch: Int = context.getClient.cameraPitch()

}
