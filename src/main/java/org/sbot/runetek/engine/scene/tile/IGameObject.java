package org.sbot.runetek.engine.scene.tile;

import org.sbot.runetek.engine.renderable.IRenderable;

/**
 * @author const_
 */
public interface IGameObject {

    int hash();

    int x();

    int y();

    int orientation();

    int flags();

    IRenderable renderable();

}
