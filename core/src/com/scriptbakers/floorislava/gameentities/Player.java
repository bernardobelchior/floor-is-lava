package com.scriptbakers.floorislava.gameentities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.Point;

import static com.scriptbakers.floorislava.FloorIsLava.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.FloorIsLava.WORLD_WIDTH;


/**
 * Created by epassos on 11/4/16.
 * This is the class that represents the player
 */


public class Player {
    Vector2 position;
    int score;
    boolean jumping;
    Body body;

    public Player(World world, int x, int y){
        this.position = new Vector2(x,y);
        this.jumping = false;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/5);
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        Body body = world.createBody(bodyDef);

        PolygonShape player = new PolygonShape();
        player.setRadius(WORLD_WIDTH/10);
        body.createFixture(player, 1);

        player.dispose();
    }

    public void jump(Vector2 jumpVector){
        this.jumping = true;
    }
}
