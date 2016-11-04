package com.scriptbakers.floorislava.gameentities;

import com.badlogic.gdx.math.Vector2;

import java.awt.Point;


/**
 * Created by epassos on 11/4/16.
 * This is the class that represents the player
 */


public class Player {
    Point position;
    int score;
    boolean jumping;

    public Player(int x, int y){
        this.position = new Point(x,y);
        this.jumping = false;
    }

    public void jump(Vector2 jumpVector){
        this.jumping = true;
    }
}
