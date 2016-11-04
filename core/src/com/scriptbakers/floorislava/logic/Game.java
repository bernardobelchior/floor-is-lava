package com.scriptbakers.floorislava.logic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.scriptbakers.floorislava.FloorIsLava.INITIAL_GRAVITY;
import static com.scriptbakers.floorislava.FloorIsLava.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.FloorIsLava.WORLD_WIDTH;

/**
 * Created by bernardo on 04-11-2016.
 */

public class Game {
    World world;

    public Game() {
        world = new World(INITIAL_GRAVITY, true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/5);
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        Body body = world.createBody(bodyDef);

        CircleShape player = new CircleShape();
        player.setRadius(WORLD_WIDTH/10);
        body.createFixture(player, 1);

        player.dispose();
    }

    public World getWorld() {
        return world;
    }

    public void update(float delta) {
        world.step(1/delta, 6, 2);
    }
}
