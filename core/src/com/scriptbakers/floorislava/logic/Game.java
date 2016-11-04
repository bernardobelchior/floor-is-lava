package com.scriptbakers.floorislava.logic;

import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.gameentities.Player;

import static com.scriptbakers.floorislava.FloorIsLava.*;

/**
 * Created by bernardo on 04-11-2016.
 */

public class Game {
    public final World world;
    public final Player player;

    public Game() {
        world = new World(INITIAL_GRAVITY, true);
        player = new Player(world, WORLD_WIDTH/2, WORLD_HEIGHT/5, WORLD_WIDTH/12, WORLD_HEIGHT/30); //FIXME: fix x and y;
    }

    public void update(float delta) {
        world.step(1/delta, 6, 2);
    }
}
