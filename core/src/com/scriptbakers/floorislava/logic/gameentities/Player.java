package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.FloorIsLava;

import java.awt.geom.Point2D;

import static com.scriptbakers.floorislava.FloorIsLava.INITIAL_GRAVITY;
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
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        CircleShape player = new CircleShape();
        player.setRadius(WORLD_WIDTH/10);
        body.createFixture(player, 1);
        body.setGravityScale(0);
        player.dispose();
    }

    public void update(float dt){
        //FIXME debug prints
        System.out.println("Player x: " + body.getWorldCenter().x);
        System.out.println("Player y: " + body.getWorldCenter().y);
        position.set(body.getWorldCenter());

    }

    public void jump(Vector2 jumpVector){

        jumping = true;
        //body.applyLinearImpulse(jumpVector.scl(50), body.getWorldCenter(),true);
        body.applyForceToCenter(jumpVector.scl(50),true);

    }
}
