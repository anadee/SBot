package org.sbot.runetek.engine.media;

/**
 * @author const_
 */
public interface IWidget {

    int getScrollX();

    int getScrollY();

    int getUid();

    IWidget[] getChildren();

    int getLoopCycleStatus();

    int getModelId();

    String[] getActions();

    int getWidth();

    int getHeight();

    int getParentId();

    String getText();

    int getScrollWidth();

    int getScrollHeight();

    int getItemId();

    int getItemStackSize();

    int getTextureId();

    int getX();

    int getY();

    int getBoundsIndex();

    int[] getContentIds();

    int[] getContentStackSizes();

}
