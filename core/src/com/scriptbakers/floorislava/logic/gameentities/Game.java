package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.logic.gameentities.Player;

import static com.scriptbakers.floorislava.FloorIsLava.INITIAL_GRAVITY;
import static com.scriptbakers.floorislava.FloorIsLava.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.FloorIsLava.WORLD_WIDTH;

/**
 * Created by bernardo on 04-11-2016.
 */

public class Game {
    World world;
    Player player;

    public Game() {
        world = new World(INITIAL_GRAVITY, true);

        player = new Player(world, WORLD_WIDTH/2, WORLD_HEIGHT/5); //FIXME: fix x and y;


    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    public void update(float delta) {
        world.step(1/delta, 6, 2);
    }
}
