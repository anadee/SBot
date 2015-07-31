package org.sbot.runetek.engine.cache.composite;

import org.sbot.runetek.engine.collection.INode;

/**
 * @author const_
 */
public interface IObjectComposite extends INode {

    String[] actions();

    short[] originalModelColors();

    String name();

    short[] modifiedModelColors();

}
