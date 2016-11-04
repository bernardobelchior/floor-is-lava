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

    public static final Vector2 INITIAL_GRAVITY = new Vector2(0, -9.8f);

    public static final float PLAYER_INITIAL_X = WORLD_WIDTH/2;
    public static final float PLAYER_INITIAL_Y = WORLD_HEIGHT/5;
    public static final float PLAYER_WIDTH = WORLD_WIDTH/12;
    public static final float PLAYER_HEIGHT = WORLD_HEIGHT/30;

    public static final float OBSTACLE_RADIUS = PLAYER_WIDTH;
    public static final float OBSTACLE_GENENATION_PER_SECOND = 0.5f;

    public static final int MAX_JUMPVEC_LEN = 200;
}
