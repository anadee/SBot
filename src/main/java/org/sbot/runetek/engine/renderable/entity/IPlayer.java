package org.sbot.runetek.engine.renderable.entity;

import org.sbot.runetek.engine.cache.composite.IPlayerComposite;

/**
 * @author const_
 */
public interface IPlayer extends IEntity {


    String getName();


    IPlayerComposite getComposite();


    int getCombatLevel();


    boolean getVisible();
}
