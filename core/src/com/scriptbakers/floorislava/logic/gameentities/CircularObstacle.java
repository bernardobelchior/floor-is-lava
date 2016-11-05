package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.Constants;

import static com.scriptbakers.floorislava.Constants.OBSTACLE_RADIUS;

/**
 * Created by bernardo on 05-11-2016.
 */

public class CircularObstacle extends Obstacle {
    Vector2 dimensions;

    public CircularObstacle(World world, float x, float y, float radius) {
        super(world);

        dimensions = new Vector2(radius, radius);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        createBody(circleShape, x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(texture == null)
            return;

        float x = getPosition().x-dimensions.x;
        float y = getPosition().y-dimensions.y;
        float width = dimensions.x*2;
        float height = dimensions.y*2;

        batch.draw(texture, x, y, width, height);
    }

    @Override
    public Vector2 getDimensions() {
        return dimensions;
    }
}
