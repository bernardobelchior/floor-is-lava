package com.scriptbakers.floorislava.logic;

import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.gameentities.Player;

import static com.scriptbakers.floorislava.Constants.*;

/**
 * Created by bernardo on 04-11-2016.
 */

public class Game {
    public final World world;
    public final Player player;

    private int noUpdates;
    private ObstacleGenerator obstacleGenerator;

    public Game() {
        world = new World(Constants.INITIAL_GRAVITY, true);
        player = new Player(world, PLAYER_INITIAL_X, PLAYER_INITIAL_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
        obstacleGenerator = new ObstacleGenerator(world);
        noUpdates = 0;
    }

    public void update(float delta) {
        world.step(1/delta, 6, 2);
        noUpdates++;

        if(noUpdates % (60/ OBSTACLE_GENENATION_PER_SECOND) == 0)
            obstacleGenerator.generateObstacle(Math.round(player.getPosition().y));
    }
}
