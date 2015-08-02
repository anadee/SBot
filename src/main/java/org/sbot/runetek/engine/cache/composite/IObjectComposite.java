package org.sbot.runetek.engine.cache.composite;

import org.sbot.runetek.engine.collection.INode;

/**
 * @author const_
 */
public interface IObjectComposite extends INode {

    String[] getActions();

    short[] getOriginalModelColors();

    String getName();

    short[] getModifiedModelColors();

}
