package org.sbot.runetek.engine.renderable.entity;

import org.sbot.runetek.engine.renderable.IRenderable;

/**
 * @author const_
 */
public interface IEntity extends IRenderable {

    String message();

    int localX();

    int localY();

    int animationId();

    int orientation();

    int currentHealth();

    int maxHealth();

    int interactingEntity();

    int queueSize();

    int loopCycleStatus();
}
