package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.scriptbakers.floorislava.logic.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.hud.GameHud;
import com.scriptbakers.floorislava.logic.gameentities.Obstacle;

import java.util.ArrayList;
import java.util.Iterator;

import static com.scriptbakers.floorislava.Constants.GameState.RUNNING;
import static com.scriptbakers.floorislava.Constants.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;


/**
 * Created by bernardo on 04-11-2016.
 */

public class GameScreen implements Screen {
    SpriteBatch batch;
    Game game;
    Box2DDebugRenderer debugRenderer;
    OrthographicCamera camera;
    Viewport viewport;
    GameHud hud;
    boolean renderedOnce;
    float timePassed=0;
    TextureAtlas playerRunning;
    TextureAtlas playerJumping;
    Texture table;
    Texture piano;
    Texture bed;
    Animation runningAnimation;
    Animation jumpingAnimation;
    ArrayList<Obstacle> obstacles;

    public GameScreen(Game game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        this.hud = new GameHud(game, batch);

        debugRenderer = new Box2DDebugRenderer();

        table = new Texture(Gdx.files.internal("squareTable.png"));
        piano = new Texture(Gdx.files.internal("squarePiano.png"));
        bed = new Texture(Gdx.files.internal("squareBed.png"));

        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new ExtendViewport(WORLD_WIDTH, 0, camera);
        // Needed in order to make the game full screen.
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(WORLD_WIDTH/2, Constants.WORLD_HEIGHT/2, 0);
        renderedOnce = false;

        playerRunning = new TextureAtlas(Gdx.files.internal("boy.atlas"));
        playerJumping = new TextureAtlas(Gdx.files.internal("boyJumping.atlas"));

        runningAnimation = new Animation(1/10f, playerRunning.getRegions());
        jumpingAnimation = new Animation(1/10f, playerJumping.getRegions());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(game.getGameState() != RUNNING && renderedOnce)
            return;

        renderedOnce = true;

        camera.update();
        game.update(delta);

        batch.begin();
        timePassed += Gdx.graphics.getDeltaTime();

        obstacles=game.getObstacles();
        Iterator<Obstacle> obstacleIterator = obstacles.iterator();
        while (obstacleIterator.hasNext()){
            Obstacle tempObstacle=obstacleIterator.next();
            switch (tempObstacle.getObstacleType()) {
                case 1:
                    batch.draw(table, tempObstacle.getPosition().x*Gdx.graphics.getWidth()/viewport.getWorldWidth(), tempObstacle.getPosition().y*Gdx.graphics.getHeight()/viewport.getWorldHeight(), viewport.getWorldWidth(), viewport.getWorldHeight()/2);
                    break;
                case 2:
                    batch.draw(piano, tempObstacle.getPosition().x*Gdx.graphics.getWidth()/viewport.getWorldWidth(), tempObstacle.getPosition().y*Gdx.graphics.getHeight()/viewport.getWorldHeight(), viewport.getWorldWidth(), viewport.getWorldHeight()/2);
                    break;
                case 3:
                    batch.draw(bed, tempObstacle.getPosition().x*Gdx.graphics.getWidth()/viewport.getWorldWidth(), tempObstacle.getPosition().y*Gdx.graphics.getHeight()/viewport.getWorldHeight(), viewport.getWorldWidth(), viewport.getWorldHeight()/2);
                    break;
            }
        }

        if(game.player.isJumping()==true)
            batch.draw(jumpingAnimation.getKeyFrames()[2],game.player.getPosition().x*(Gdx.graphics.getWidth()/viewport.getWorldWidth()),game.player.getPosition().y*(Gdx.graphics.getHeight()/viewport.getWorldHeight()));
        else
            batch.draw(runningAnimation.getKeyFrame(timePassed,true),game.player.getPosition().x*(Gdx.graphics.getWidth()/viewport.getWorldWidth()),game.player.getPosition().y*(Gdx.graphics.getHeight()/viewport.getWorldHeight()));

        //batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        debugRenderer.render(game.world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
