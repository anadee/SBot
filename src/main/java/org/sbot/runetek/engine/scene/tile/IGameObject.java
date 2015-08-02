package org.sbot.runetek.engine.scene.tile;

import org.sbot.runetek.engine.renderable.IRenderable;

/**
 * @author const_
 */
public interface IGameObject {

    int getHash();

    int getX();

    int getY();

    int getOrientation();

    int getFlags();

    IRenderable getRenderable();

}
