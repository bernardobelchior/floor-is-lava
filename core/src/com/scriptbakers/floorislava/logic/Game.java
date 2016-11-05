package com.scriptbakers.floorislava.logic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.logic.gameentities.Lava;
import com.scriptbakers.floorislava.logic.gameentities.Obstacle;
import com.scriptbakers.floorislava.logic.gameentities.Player;

import java.util.ArrayList;
import java.util.Iterator;

import static com.scriptbakers.floorislava.Constants.*;
import static com.scriptbakers.floorislava.Constants.GameState.*;

/**
 * Created by bernardo on 04-11-2016.
 */

public class Game {
    public final World world;
    public final Player player;
    private ArrayList<Lava> lavaPatches;


    private int noUpdates;
    private ObstacleGenerator obstacleGenerator;
    private GameState gameState;
    private ArrayList<Obstacle> obstacles;
    public Game() {
        world = new World(Constants.INITIAL_GRAVITY, true);
        player = new Player(world, PLAYER_INITIAL_X, PLAYER_INITIAL_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
        obstacleGenerator = new ObstacleGenerator(world);
        lavaPatches = new ArrayList<Lava>();
        obstacles =  new ArrayList<Obstacle>();

        noUpdates = 0;
        gameState = PAUSED;

        world.setContactListener(new GameContactListener());
        createWalls();
    }

    private void createWalls() {
        //Left wall creation
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0, 0);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body body = world.createBody(bodyDef);
        body.setGravityScale(0);

        EdgeShape shape = new EdgeShape();
        shape.set(0, 0, 0, WORLD_HEIGHT);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = -1;
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = CATEGORY_WALL;
        fixtureDef.filter.maskBits = MASK_WALL;
        body.createFixture(fixtureDef);

        //Right wall creation
        bodyDef = new BodyDef();
        bodyDef.position.set(WORLD_WIDTH+1, 0);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(bodyDef);
        body.setGravityScale(0);

        shape = new EdgeShape();
        shape.set(0, 0, 0, WORLD_HEIGHT);

        fixtureDef = new FixtureDef();
        fixtureDef.density = -1;
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = CATEGORY_WALL;
        fixtureDef.filter.maskBits = MASK_WALL;
        body.createFixture(fixtureDef);
    }

    public void update(float delta) {
        if(gameState != RUNNING)
            return;

        world.step(delta, 6, 2);
        player.update(delta);

        if(!player.isAlive())
            gameState=OVER;

        noUpdates++;

        for (int i = obstacles.size() - 1; i >= 0; i--) {
            Obstacle obstacle = obstacles.get(i);
            if(obstacle.getPosition().y - obstacle.getRadius() < 0)
                obstacles.remove(i);
        }

        for (int i = lavaPatches.size() - 1; i >= 0; i--) {
            Lava lava = lavaPatches.get(i);
            if (lava.getPosition().y + lava.getLength() / 2 < 0)
                lavaPatches.remove(i);
        }

    if(noUpdates % (60/ OBSTACLE_GENERATION_PER_SECOND) == 0)
        obstacles.add(obstacleGenerator.generateObstacle(Math.round(player.getPosition().y)));


        if(noUpdates % (60/LAVA_GENERATION_PER_SECOND) == 0)
            lavaPatches.add(new Lava(world, (float) Math.random()* LAVA_PATCH_MAX_LENGTH));




    }

    public void run() {
        gameState = RUNNING;
    }

    public void pause() {
        gameState = PAUSED;
    }

    public GameState getGameState(){
        return gameState;
    }

    public ArrayList<Obstacle> getObstacles(){
        return this.obstacles;
    }
}
