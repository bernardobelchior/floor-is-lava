package com.scriptbakers.floorislava;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by bernardo on 04-11-2016.
 */


public class Constants {
    public enum Side {
        RIGHT, LEFT
    }

    public enum GameState {
        OVER, PAUSED, RUNNING
    }

    public static final short CATEGORY_PLAYER = 0x0001;
    public static final short CATEGORY_OBSTACLE = 0x0002;
    public static final short CATEGORY_WALL = 0x0004;

    public static final short MASK_PLAYER = CATEGORY_WALL;
    public static final short MASK_OBSTACLE = 0;
    public static final short MASK_WALL = CATEGORY_PLAYER;

    public static final int WORLD_WIDTH = 512;
    public static final int WORLD_HEIGHT = 1024;

    public static final Vector2 INITIAL_GRAVITY = new Vector2(0, -WORLD_HEIGHT/1024f);

    public static final float PLAYER_INITIAL_X = WORLD_WIDTH/2;
    public static final float PLAYER_INITIAL_Y = WORLD_HEIGHT/5;
    public static final float PLAYER_WIDTH = WORLD_WIDTH/12;
    public static final float PLAYER_HEIGHT = WORLD_HEIGHT/30;

    public static final float OBSTACLE_RADIUS = PLAYER_WIDTH;
    public static final float OBSTACLE_GENENATION_PER_SECOND = 0.5f;

    public static final int MAX_JUMPVEC_LEN = 10;
}
