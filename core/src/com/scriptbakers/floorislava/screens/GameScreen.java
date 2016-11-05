package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.scriptbakers.floorislava.logic.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.hud.GameHud;
import com.scriptbakers.floorislava.logic.gameentities.Obstacle;

import java.util.ArrayList;
import java.util.Iterator;

import static com.scriptbakers.floorislava.Constants.PIXELS_PER_METER;
import static com.scriptbakers.floorislava.Constants.PLAYER_HEIGHT;
import static com.scriptbakers.floorislava.Constants.PLAYER_WIDTH;
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

    float timeElapsed =0;
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

        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new ExtendViewport(WORLD_WIDTH, 0, camera);
        this.hud = new GameHud(game, batch, viewport);

        // Needed in order to make the game full screen.
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(WORLD_WIDTH/2, Constants.WORLD_HEIGHT/2, 0);


        table = new Texture(Gdx.files.internal("squareTable.png"));
        piano = new Texture(Gdx.files.internal("squarePiano.png"));
        bed = new Texture(Gdx.files.internal("squareBed.png"));

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
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        timeElapsed += Gdx.graphics.getDeltaTime();

        obstacles=game.getObstacles();

        for (Obstacle obstacle : obstacles) {
            Texture texture = table;

            switch (obstacle.getObstacleType()) {
                case 1:
                    texture = table;
                    break;
                case 2:
                    texture = piano;
                    break;
                case 3:
                    texture = bed;
                    break;
            }

            float x = obstacle.getPosition().x-obstacle.getDimensions().x;
            float y = obstacle.getPosition().y-obstacle.getDimensions().y;
            float width = obstacle.getDimensions().x*2;
            float height = obstacle.getDimensions().y*2;

            batch.draw(texture, x, y, width, height);
        }

        TextureRegion frame = runningAnimation.getKeyFrame(timeElapsed,true);
        if(game.player.isJumping())
            frame = jumpingAnimation.getKeyFrames()[2];

        float x = game.getPlayerPosition().x-PLAYER_WIDTH/2;
        float y = game.getPlayerPosition().y-PLAYER_HEIGHT/2;
        float width = PLAYER_WIDTH*PIXELS_PER_METER/2;
        float height = PLAYER_HEIGHT*PIXELS_PER_METER/2;

        batch.draw(frame, x, y, width, height);

        //batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        debugRenderer.render(game.world, camera.combined);
        hud.draw();
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
