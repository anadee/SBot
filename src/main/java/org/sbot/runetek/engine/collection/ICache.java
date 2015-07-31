package org.sbot.runetek.engine.collection;

/**
 * @author const_
 */
public interface ICache {

    IBag bag();

    IQueue queue();

    ICacheableNode emptyCacheableNode();

}
