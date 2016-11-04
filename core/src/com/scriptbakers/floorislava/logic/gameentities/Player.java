package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.Constants;

import static com.scriptbakers.floorislava.Constants.CATEGORY_PLAYER;
import static com.scriptbakers.floorislava.Constants.MASK_PLAYER;


/**
 * Created by epassos on 11/4/16.
 * This is the class that represents the player
 */


public class Player {
    Vector2 position;
    int score;
    boolean jumping;
    public final Body body;
    int jumpTime;


    public Player(World world, float x, float y, float width, float height){
        this.position = new Vector2(x,y);
        this.jumping = false;

        /* Creates the player in the world. */
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        PolygonShape player = new PolygonShape();
        player.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = player;
        fixtureDef.filter.categoryBits = CATEGORY_PLAYER;
        fixtureDef.filter.categoryBits = MASK_PLAYER;
        fixtureDef.density = 1;

        body.createFixture(fixtureDef);
        body.setGravityScale(0);
        body.setUserData(this);
        body.setGravityScale(0);

        jumpTime = 0;

        player.dispose();
    }

    public void update(float dt){
        //FIXME debug prints
        System.out.println("Player x: " + body.getWorldCenter().x);
        System.out.println("Player y: " + body.getWorldCenter().y);
        position.set(body.getWorldCenter());

        if(jumpTime > 0)
            jumpTime--;

        else if(jumping) {
            jumping = false;
            body.setLinearVelocity(0f,0f);
        }
    }

    public void jump(Vector2 jumpVector){
        jumping = true;
        body.applyForceToCenter(jumpVector.scl(50),true);

        jumpTime = Math.round(jumpVector.len()/ Constants.MAX_JUMPVEC_LEN)*60;
    }

    public Vector2 getPosition() {
        return position;
    }
}
