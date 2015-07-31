package org.sbot.runetek.engine.cache.composite;

import org.sbot.runetek.engine.collection.INode;

/**
 * @author const_
 */
public interface IItemComposite extends INode {

    String name();

    String[] inventoryActions();

    String[] groundActions();

    int modelId();

}
