package org.sbot.runetek.engine.media;

/**
 * @author const_
 */
public interface IWidget {

    int scrollX();

    int scrollY();

    int uid();

    IWidget[] children();

    int loopCycleStatus();

    int modelId();

    String[] actions();

    int width();

    int height();

    int parentId();

    String text();

    int scrollWidth();

    int scrollHeight();

    int itemId();

    int itemStackSize();

    int textureId();

    int x();

    int y();

    int boundsIndex();

    int[] contentIds();

    int[] contentStackSizes();

}
