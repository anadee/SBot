package org.sbot.runetek.engine.collection;

/**
 * @author const_
 */
public interface IBag {

    INode[] cache();

    int size();

    int currentIndex();

    INode current();

    INode head();

}
