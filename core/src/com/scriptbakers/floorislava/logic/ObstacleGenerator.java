package com.scriptbakers.floorislava.logic;

import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.logic.gameentities.CircularObstacle;
import com.scriptbakers.floorislava.logic.gameentities.Obstacle;
import com.scriptbakers.floorislava.logic.gameentities.RectangularObstacle;

import static com.scriptbakers.floorislava.Constants.LEFT_LAVA_THRESHOLD;
import static com.scriptbakers.floorislava.Constants.OBSTACLE_RADIUS;
import static com.scriptbakers.floorislava.Constants.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;

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

        float x = LEFT_LAVA_THRESHOLD/2;

        //Place obstacle on the right side.
        if(Math.round(Math.random()) == 0)
            x = WORLD_WIDTH - x;


        if(Math.round(Math.random()) == 0)
            return new RectangularObstacle(world, x, y, OBSTACLE_RADIUS*2, OBSTACLE_RADIUS*2);
        else
            return new CircularObstacle(world, x, y, OBSTACLE_RADIUS);
    }
}
