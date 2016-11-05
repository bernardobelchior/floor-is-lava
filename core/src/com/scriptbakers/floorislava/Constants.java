package com.scriptbakers.floorislava;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by bernardo on 04-11-2016.
 */


public class Constants {
    public enum Side {
        RIGHT, LEFT
    }

    public static final int WORLD_WIDTH = 256;
    public static final int WORLD_HEIGHT = 512;

    public static final Vector2 INITIAL_GRAVITY = new Vector2(0, 0);

    public enum GameState {
        OVER, PAUSED, RUNNING
    }

    public static final short CATEGORY_PLAYER = 0x0001;
    public static final short CATEGORY_OBSTACLE = 0x0002;
    public static final short CATEGORY_WALL = 0x0004;

    public static final short MASK_PLAYER = CATEGORY_WALL | CATEGORY_OBSTACLE;
    public static final short MASK_OBSTACLE = CATEGORY_PLAYER;
    public static final short MASK_WALL = CATEGORY_PLAYER;

    public static final float PLAYER_INITIAL_X = WORLD_WIDTH/2;
    public static final float PLAYER_INITIAL_Y = WORLD_HEIGHT/4;
    public static final float PLAYER_WIDTH = WORLD_WIDTH/16;
    public static final float PLAYER_HEIGHT = WORLD_HEIGHT/32;

    public static final float SCROLL_VELOCITY = -WORLD_HEIGHT/32;

    public static final float LEFT_LAVA_THRESHOLD = WORLD_WIDTH/3;
    public static final float RIGHT_LAVA_THRESHOLD = WORLD_WIDTH - LEFT_LAVA_THRESHOLD;

    public static final float OBSTACLE_RADIUS = 1.5f*PLAYER_WIDTH;
    public static final float OBSTACLE_GENENATION_PER_SECOND = 0.5f;

    public static final int MAX_JUMPVEC_LEN = 200;
}
