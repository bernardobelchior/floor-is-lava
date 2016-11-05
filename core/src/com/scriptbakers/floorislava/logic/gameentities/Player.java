package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.Constants;

import static com.scriptbakers.floorislava.Constants.CATEGORY_PLAYER;
import static com.scriptbakers.floorislava.Constants.LEFT_LAVA_THRESHOLD;
import static com.scriptbakers.floorislava.Constants.MASK_PLAYER;
import static com.scriptbakers.floorislava.Constants.PLAYER_HEIGHT;
import static com.scriptbakers.floorislava.Constants.PLAYER_INITIAL_Y;
import static com.scriptbakers.floorislava.Constants.PLAYER_WIDTH;
import static com.scriptbakers.floorislava.Constants.RIGHT_LAVA_THRESHOLD;
import static com.scriptbakers.floorislava.Constants.SCROLL_VELOCITY;


/**
 * Created by epassos on 11/4/16.
 * This is the class that represents the player
 */


public class Player {
    int score;
    public boolean onObstacle;
    public final Body body;
    int jumpTime;
    boolean alive;


    public Player(World world, float x, float y, float width, float height) {
        onObstacle = false;
        alive = true;

        /* Creates the player in the world. */
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        PolygonShape player = new PolygonShape();
        player.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = player;
        fixtureDef.restitution = 1;
        fixtureDef.filter.categoryBits = CATEGORY_PLAYER;
        fixtureDef.filter.maskBits = MASK_PLAYER;
        fixtureDef.density = 1;

        body.createFixture(fixtureDef);
        body.setGravityScale(0);
        body.setUserData(this);

        jumpTime = 0;
        player.dispose();
    }

    public void update(float dt) {
        if(body.getPosition().y - PLAYER_HEIGHT/2 <= 0)
            alive = false;

        if((body.getPosition().x - PLAYER_WIDTH/2 < LEFT_LAVA_THRESHOLD) && !onObstacle && jumpTime == 0)
            alive = false;

        if((body.getPosition().x + PLAYER_WIDTH/2 > RIGHT_LAVA_THRESHOLD) && !onObstacle  && jumpTime == 0)
            alive = false;

        if (jumpTime > 0)
            jumpTime--;
        else {
            if(onObstacle || body.getWorldCenter().y > PLAYER_INITIAL_Y) {
                body.setLinearVelocity(0f, SCROLL_VELOCITY);
            } else {
                body.setLinearVelocity(0f, 0f);
            }
        }
    }

    public void jump(Vector2 jumpVector){
        body.setLinearVelocity(jumpVector.cpy().scl(50));
        jumpTime = Math.round(Constants.JUMP_TIME_MULTIPLIER *jumpVector.len()/ Constants.MAX_JUMP_VECTOR_LENGTH);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isJumping() {
        return jumpTime > 0;
    }
}
