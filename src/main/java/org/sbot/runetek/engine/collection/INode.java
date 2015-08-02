package org.sbot.runetek.engine.collection;

/**
 * @author const_
 */
public interface INode {

    INode getNext();

    INode getPrev();

    long getUid();

}
