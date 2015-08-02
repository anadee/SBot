package org.sbot.runetek.engine.collection;

/**
 * @author const_
 */
public interface ICache {

    IBag getBag();

    IQueue getQueue();

    ICacheableNode getEmptyCacheableNode();

}
