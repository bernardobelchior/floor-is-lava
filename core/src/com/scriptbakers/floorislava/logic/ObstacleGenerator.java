package com.scriptbakers.floorislava.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.logic.gameentities.CircularObstacle;
import com.scriptbakers.floorislava.logic.gameentities.Obstacle;
import com.scriptbakers.floorislava.logic.gameentities.RectangularObstacle;

import static com.scriptbakers.floorislava.Constants.*;
import static com.scriptbakers.floorislava.Constants.Side.LEFT;
import static com.scriptbakers.floorislava.Constants.Side.RIGHT;

/**
 * Created by bernardo on 04-11-2016.
 */

public class ObstacleGenerator {
    World world;

    public ObstacleGenerator(World world) {
        this.world = world;
    }

    public Obstacle generateObstacle(int cameraY) {
        int y = cameraY+ WORLD_HEIGHT/2;
        y += Math.random()*WORLD_HEIGHT;

        Side side;

        if(Math.round(Math.random()) == 0)
            side = LEFT;
        else
            side = RIGHT;

        if(Math.round(Math.random()) == 0)
            return new RectangularObstacle(world, y, side);
        else
            return new CircularObstacle(world, y, side);
    }
}
