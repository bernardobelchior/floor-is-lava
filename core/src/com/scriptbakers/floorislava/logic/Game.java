package com.scriptbakers.floorislava.logic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.logic.gameentities.Player;
import com.scriptbakers.floorislava.Constants;

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
        world.setContactListener(new GameContactListener());
        player = new Player(world, PLAYER_INITIAL_X, PLAYER_INITIAL_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
        obstacleGenerator = new ObstacleGenerator(world);
        createWalls();

        noUpdates = 0;
        gameState = PAUSED;
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

        if(noUpdates % (60/ OBSTACLE_GENENATION_PER_SECOND) == 0)
            obstacleGenerator.generateObstacle(Math.round(player.getPosition().y));
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
}
