package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.physics.box2d.World;
<<<<<<< HEAD:core/src/com/scriptbakers/floorislava/logic/Game.java
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.gameentities.Player;
=======
import com.scriptbakers.floorislava.logic.gameentities.Player;
>>>>>>> origin/player_jump:core/src/com/scriptbakers/floorislava/logic/gameentities/Game.java

import static com.scriptbakers.floorislava.Constants.*;
import static com.scriptbakers.floorislava.Constants.GameState.OVER;
import static com.scriptbakers.floorislava.Constants.GameState.PAUSED;
import static com.scriptbakers.floorislava.Constants.GameState.RUNNING;

/**
 * Created by bernardo on 04-11-2016.
 */

public class Game {
    public final World world;
    public final Player player;

    private int noUpdates;
    private ObstacleGenerator obstacleGenerator;
    private GameState gameState;

    public Game() {
        world = new World(Constants.INITIAL_GRAVITY, true);
        player = new Player(world, PLAYER_INITIAL_X, PLAYER_INITIAL_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
        obstacleGenerator = new ObstacleGenerator(world);
        noUpdates = 0;
        gameState = PAUSED;
    }

    public void update(float delta) {
        if(gameState != RUNNING)
            return;

        world.step(1/delta, 6, 2);
        noUpdates++;

        if(noUpdates % (60/ OBSTACLE_GENENATION_PER_SECOND) == 0)
            obstacleGenerator.generateObstacle(Math.round(player.getPosition().y));
    }

    public void run() {
        gameState = RUNNING;
    }

    public void pause() {
        gameState = PAUSED;
    }

<<<<<<< HEAD:core/src/com/scriptbakers/floorislava/logic/Game.java
    public GameState getGameState(){
        return gameState;
=======
    public void update(float delta) {
        world.step(1/delta, 6, 2);
        player.update(delta);
>>>>>>> origin/player_jump:core/src/com/scriptbakers/floorislava/logic/gameentities/Game.java
    }
}
