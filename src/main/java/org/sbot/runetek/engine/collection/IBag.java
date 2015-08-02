package org.sbot.runetek.engine.collection;

/**
 * @author const_
 */
public interface IBag {

    INode[] getCache();

    int getSize();

    int getCurrentIndex();

    INode getCurrent();

    INode getHead();

}
