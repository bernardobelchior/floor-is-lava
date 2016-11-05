package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.Constants.Side;

import java.util.Random;

import static com.scriptbakers.floorislava.Constants.CATEGORY_OBSTACLE;
import static com.scriptbakers.floorislava.Constants.MASK_OBSTACLE;
import static com.scriptbakers.floorislava.Constants.OBSTACLE_MARGIN;
import static com.scriptbakers.floorislava.Constants.SCROLL_VELOCITY;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;


/**
 * Created by bernardo on 04-11-2016.
 */

public class Obstacle {
    Body body;
    Random rnd = new Random();
    float radius;
    int obstacleType;

    public Obstacle(World world, Shape shape, int y, Side side) {
        BodyDef bodyDef = new BodyDef();

        radius = shape.getRadius();
        float x = shape.getRadius() + OBSTACLE_MARGIN;

        Random rnd = new Random();
        obstacleType = rnd.nextInt(3)+1;

        if(side == Side.RIGHT)
            x = WORLD_WIDTH - x;

        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = CATEGORY_OBSTACLE;
        fixtureDef.filter.maskBits = MASK_OBSTACLE;
        body.createFixture(fixtureDef);

        body.setLinearVelocity(0,SCROLL_VELOCITY);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public int getObstacleType(){
        return obstacleType;
    }

    public float getRadius() {
return radius;
    }
}
