package org.sbot.runetek.api.functions.impl

import org.sbot.runetek.api.ContextProvider

/**
 * @author : const_
 */
class Camera() extends ContextProvider {

  def getX: Int = context.getClient.cameraX()

  def getY: Int = context.getClient.cameraY()

  def getZ: Int = context.getClient.cameraZ()

  def getYaw: Int = context.getClient.cameraYaw()

  def getPitch: Int = context.getClient.cameraPitch()

}
