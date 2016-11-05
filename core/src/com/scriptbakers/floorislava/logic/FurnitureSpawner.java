package com.scriptbakers.floorislava.logic;

import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.logic.gameentities.furniture.CircularFurniture;
import com.scriptbakers.floorislava.logic.gameentities.furniture.Furniture;
import com.scriptbakers.floorislava.logic.gameentities.furniture.RectangularFurniture;

import static com.scriptbakers.floorislava.Constants.LAVA_PATCH_MIN_LENGTH;
import static com.scriptbakers.floorislava.Constants.LEFT_LAVA_THRESHOLD;
import static com.scriptbakers.floorislava.Constants.OBSTACLE_RADIUS;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;
import static com.scriptbakers.floorislava.Graphics.bedTexture;
import static com.scriptbakers.floorislava.Graphics.pianoTexture;
import static com.scriptbakers.floorislava.Graphics.tableTexture;

/**
 * Created by bernardo on 04-11-2016.
 */

public class FurnitureSpawner {
    World world;

    public FurnitureSpawner(World world) {
        this.world = world;
    }

    public Furniture generateObstacle(float y, float patchLength) {
        y += Math.random()* patchLength;

        float x = LEFT_LAVA_THRESHOLD/2;

        //Place obstacle on the right side.
        if(Math.round(Math.random()) == 0)
            x = WORLD_WIDTH - x;

        Furniture furniture = null;
        switch ((short) Math.round(Math.random()*2)) {
            case 0:
                furniture = new RectangularFurniture(world, x, y, 0.75f*LEFT_LAVA_THRESHOLD, 0.75f*LEFT_LAVA_THRESHOLD) ;
                furniture.setTexture(pianoTexture);
                break;
            case 1:
                furniture = new RectangularFurniture(world, x, y, 0.8f*LEFT_LAVA_THRESHOLD, 0.8f*LEFT_LAVA_THRESHOLD*3/2);
                furniture.setTexture(bedTexture);
                break;
            case 2:
                furniture = new CircularFurniture(world, x, y, OBSTACLE_RADIUS);
                furniture.setTexture(tableTexture);
                break;
        }

        return furniture;
    }
}
