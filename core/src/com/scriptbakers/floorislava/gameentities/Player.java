package com.scriptbakers.floorislava.gameentities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by epassos on 11/4/16.
 * This is the class that represents the player
 */


public class Player {
    Vector2 position;
    int score;
    boolean jumping;
    public final Body body;

    public Player(World world, int x, int y, int width, int height){
        this.position = new Vector2(x,y);
        this.jumping = false;

        /* Creates the player in the world. */
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        PolygonShape player = new PolygonShape();
        player.setAsBox(width, height);
        body.createFixture(player, 1);
        body.setGravityScale(0);

        player.dispose();
    }

    public void jump(Vector2 jumpVector){
        this.jumping = true;
    }

    public Vector2 getPosition() {
        return position;
    }
}
