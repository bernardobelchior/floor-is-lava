package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.Constants;

import static com.scriptbakers.floorislava.Constants.OBSTACLE_RADIUS;

/**
 * Created by bernardo on 05-11-2016.
 */

public class RectangularObstacle extends Obstacle {
    Vector2 dimensions;

    public RectangularObstacle(World world, int y, Constants.Side side) {
        super(world);

        dimensions = new Vector2(OBSTACLE_RADIUS, OBSTACLE_RADIUS);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(dimensions.x, dimensions.y);

        createBody(polygonShape, y, side, dimensions.x);
    }

    public Vector2 getDimensions() {
        return dimensions;
    }
}
