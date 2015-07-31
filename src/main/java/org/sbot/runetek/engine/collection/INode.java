package org.sbot.runetek.engine.collection;

/**
 * @author const_
 */
public interface INode {

    INode next();

    INode prev();

    long uid();

}
