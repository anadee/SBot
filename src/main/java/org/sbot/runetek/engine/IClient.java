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

    ICache getNpcCompositeCache();


    ICache getObjectCompositeCache();


    ICache getItemCompositeCache();


    int[] getSineTable();


    void setWorld(IWorld world);


    int[] getCosineTable();


    ICollisionMap[] getMaps();

    IWorld[] getWorlds();

    IDeque getProjectiles();


    IDeque[][][] getLoot();


    IPlayer[] getPlayers();


    IPlayer getLocalPlayer();


    int getCameraX();


    int getCameraY();


    int getCameraZ();


    int getCameraPitch();


    int getCameraYaw();


    int getPlane();


    INpc[] getNpcs();


    int getBaseX();


    int getBaseY();


    int[] getSkillLevels();


    int[] getSkillBases();


    int[] getSkillExperiences();


    boolean isMenuOpen();


    int getMenuCount();


    String[] getMenuActions();


    String[] getMenuOptions();


    int getMenuX();


    int getMenuY();


    int getMenuWidth();


    int getMenuHeight();


    int getLoopCycle();


    IScene getScene();


    byte[][][] getTileSettings();


    int[][][] getTileHeights();


    int getMinimapAngle();


    int getMinimapScale();


    int getMinimapOffset();


    IBag getWidgetNodeBag();


    Canvas getCanvas();


    int getLoginIndex();


    int[] getWidgetBoundsX();


    int[] getWidgetBoundsY();


    int[] getWidgetBoundsWidth();


    int[] getWidgetBoundsHeight();


    int[] getWidgetSettings();


    int[] getPlayerSettings();

    IWidget[][] getWidgets();

    ICache getNpcModelCache();
}
