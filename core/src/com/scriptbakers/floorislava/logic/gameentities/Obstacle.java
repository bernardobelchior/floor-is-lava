package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.Constants.Side;

import static com.scriptbakers.floorislava.Constants.CATEGORY_OBSTACLE;
import static com.scriptbakers.floorislava.Constants.MASK_OBSTACLE;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;


/**
 * Created by bernardo on 04-11-2016.
 */

public class Obstacle {
    Body body;

    public Obstacle(World world, Shape shape, int y, Side side) {
        BodyDef bodyDef = new BodyDef();

        float x = 0;
        switch (side) {
            case LEFT:
                x = shape.getRadius();
                break;
            case RIGHT:
                x = WORLD_WIDTH - shape.getRadius();
                break;
        }

        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = CATEGORY_OBSTACLE;
        fixtureDef.filter.maskBits = MASK_OBSTACLE;
        body.createFixture(fixtureDef);


        body.setLinearVelocity(0,-10f);
    }

}
