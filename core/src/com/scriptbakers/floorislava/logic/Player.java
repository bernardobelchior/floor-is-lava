package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Point2D;


/**
 * Created by epassos on 11/4/16.
 * This is the class that represents the player
 */


public class Player {
    Point2D.Float position;
    int score;
    boolean jumping;

    public Player(float x, float y){
        this.position = new Point2D.Float(x,y);
        this.jumping = false;
    }

    public void jump(Vector2 jumpVector){
        this.jumping = true;
    }
}
