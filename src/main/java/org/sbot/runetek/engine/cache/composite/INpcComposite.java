package org.sbot.runetek.engine.cache.composite;

import org.sbot.runetek.engine.collection.INode;

/**
 * @author const_
 */
public interface INpcComposite extends INode {

    String name();

    String[] actions();

    int[] modelIds();

    int realId();

    int[] transformIds();

    int id();

    int combatLevel();

}
