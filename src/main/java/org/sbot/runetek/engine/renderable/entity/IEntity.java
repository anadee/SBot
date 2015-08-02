package org.sbot.runetek.engine.renderable.entity;

import org.sbot.runetek.engine.renderable.IRenderable;

/**
 * @author const_
 */
public interface IEntity extends IRenderable {

    String getMessage();

    int getLocalX();

    int getLocalY();

    int getAnimationId();

    int getOrientation();

    int getCurrentHealth();

    int getMaxHealth();

    int getInteractingEntity();

    int getQueueSize();

    int getLoopCycleStatus();
}
