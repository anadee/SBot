package org.sbot.runetek.engine.scene;


import org.sbot.runetek.engine.collection.INode;
import org.sbot.runetek.engine.scene.tile.*;

/**
 * @author const_
 */
public interface ITile extends INode {


    IGroundLayer groundLayer();


    IInteractableObject[] interactableObjects();


    IBoundaryObject boundaryObject();


    IWallObject wallObject();


    IFloorObject floorObject();
}
