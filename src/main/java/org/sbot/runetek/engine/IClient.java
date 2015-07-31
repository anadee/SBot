package org.sbot.runetek.engine;

import org.sbot.runetek.engine.collection.IBag;
import org.sbot.runetek.engine.collection.ICache;
import org.sbot.runetek.engine.collection.IDeque;
import org.sbot.runetek.engine.media.IWidget;
import org.sbot.runetek.engine.renderable.entity.INpc;
import org.sbot.runetek.engine.renderable.entity.IPlayer;
import org.sbot.runetek.engine.scene.ICollisionMap;
import org.sbot.runetek.engine.scene.IScene;

import java.awt.*;

/**
 * @author const_
 */
public interface IClient {

    ICache npcCompositeCache();


    ICache objectCompositeCache();


    ICache itemCompositeCache();


    int[] sineTable();


    void setWorld(IWorld world);


    int[] cosineTable();


    ICollisionMap[] maps();

    IWorld[] worlds();

    IDeque projectiles();


    IDeque[][][] loot();


    IPlayer[] players();


    IPlayer localPlayer();


    int cameraX();


    int cameraY();


    int cameraZ();


    int cameraPitch();


    int cameraYaw();


    int plane();


    INpc[] npcs();


    int baseX();


    int baseY();


    int[] skillLevels();


    int[] skillBases();


    int[] skillExperiences();


    boolean menuOpen();


    int menuCount();


    String[] menuActions();


    String[] menuOptions();


    int menuX();


    int menuY();


    int menuWidth();


    int menuHeight();


    int loopCycle();


    IScene scene();


    byte[][][] tileSettings();


    int[][][] tileHeights();


    int minimapAngle();


    int minimapScale();


    int minimapOffset();


    IBag widgetNodeBag();


    Canvas canvas();


    int loginIndex();


    int[] widgetBoundsX();


    int[] widgetBoundsY();


    int[] widgetBoundsWidth();


    int[] widgetBoundsHeight();


    int[] widgetSettings();


    int[] playerSettings();

    IWidget[][] widgets();

    ICache npcModelCache();
}
