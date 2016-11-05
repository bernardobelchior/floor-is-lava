package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.scriptbakers.floorislava.Constants.CATEGORY_LAVA;
import static com.scriptbakers.floorislava.Constants.CATEGORY_PLAYER;
import static com.scriptbakers.floorislava.Constants.LAVA_PATCH_MIN_LENGTH;
import static com.scriptbakers.floorislava.Constants.LEFT_LAVA_THRESHOLD;
import static com.scriptbakers.floorislava.Constants.MASK_LAVA;
import static com.scriptbakers.floorislava.Constants.MASK_PLAYER;
import static com.scriptbakers.floorislava.Constants.PIXELS_PER_METER;
import static com.scriptbakers.floorislava.Constants.SCROLL_VELOCITY;
import static com.scriptbakers.floorislava.Constants.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;
import static com.scriptbakers.floorislava.Graphics.lavaAnimation;

/**
 * Created by epassos on 11/4/16.
 */

public class Lava {
    Body body;
    float length;

    public Lava(World world, float y, float length){
        this.length = length;
        createBody(world, y);
    }

    private void createBody(World world, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(WORLD_WIDTH/2, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        PolygonShape lava = new PolygonShape();
        lava.setAsBox(WORLD_WIDTH/2-LEFT_LAVA_THRESHOLD, length);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = lava;
        fixtureDef.restitution = 1;
        fixtureDef.filter.categoryBits = CATEGORY_LAVA;
        fixtureDef.filter.maskBits = MASK_LAVA;
        fixtureDef.isSensor = true;
        fixtureDef.density = 1;

        body.createFixture(fixtureDef);
        body.setGravityScale(0);
        body.setLinearVelocity(0, SCROLL_VELOCITY);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public float getLength() {
        return length;
    }

    public void draw(SpriteBatch batch, float timeElapsed) {
        float x = body.getPosition().x - (WORLD_WIDTH/2-LEFT_LAVA_THRESHOLD);
        //FIXME: Workaround due to the texture
        float y = body.getPosition().y - length*1.7f;
        float width =(WORLD_WIDTH/2-LEFT_LAVA_THRESHOLD)/2*PIXELS_PER_METER;
        float height = 1.5f*length/2*PIXELS_PER_METER;

        batch.draw(lavaAnimation.getKeyFrame(timeElapsed,true), x, y, width, height);

        /*float x = body.getPosition().x - (WORLD_WIDTH/2-LEFT_LAVA_THRESHOLD);
        float y = body.getPosition().y - length;
        float width =(WORLD_WIDTH/2-LEFT_LAVA_THRESHOLD)/2*PIXELS_PER_METER/3;
        float height = length/2*PIXELS_PER_METER/LAVA_PATCH_MIN_LENGTH;

        for(int i = 0; i < 3; i++) {
            x += WORLD_WIDTH/(2*LEFT_LAVA_THRESHOLD)*i;
            for(int j = 0; i < length/LAVA_PATCH_MIN_LENGTH; i++) {
                y += length/LAVA_PATCH_MIN_LENGTH*j;
                batch.draw(lavaAnimation.getKeyFrame(timeElapsed,true), x, y, width, height);
            }
        }*/
    }
}
