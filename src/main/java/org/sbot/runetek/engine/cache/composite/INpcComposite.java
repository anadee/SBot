package org.sbot.runetek.engine.cache.composite;

import org.sbot.runetek.engine.collection.INode;

/**
 * @author const_
 */
public interface INpcComposite extends INode {

    String getName();

    String[] getActions();

    int[] getModelIds();

    int getRealId();

    int[] getTransformIds();

    int getId();

    int getCombatLevel();

}
