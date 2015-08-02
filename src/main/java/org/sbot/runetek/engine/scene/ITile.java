package org.sbot.runetek.engine.scene;


import org.sbot.runetek.engine.collection.INode;
import org.sbot.runetek.engine.scene.tile.*;

/**
 * @author const_
 */
public interface ITile extends INode {


    IGroundLayer getGroundLayer();


    IInteractableObject[] getInteractableObjects();


    IBoundaryObject getBoundaryObject();


    IWallObject getWallObject();


    IFloorObject getFloorObject();
}
