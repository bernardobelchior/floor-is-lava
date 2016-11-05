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
import com.scriptbakers.floorislava.logic.gameentities.Lava;
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

    float timeElapsed;
    ArrayList<Texture> randomTextures;
    Animation runningAnimation;
    Animation jumpingAnimation;
    Animation lavaAnimation;
    Texture floorTexture;


    public GameScreen(Game game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;

        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new ExtendViewport(WORLD_WIDTH, 0, camera);
        this.hud = new GameHud(game, batch, viewport);
        randomTextures = new ArrayList<Texture>();
        timeElapsed = 0;

        // Needed in order to make the game full screen.
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(WORLD_WIDTH/2, Constants.WORLD_HEIGHT/2, 0);

        randomTextures.add(new Texture(Gdx.files.internal("squareTable.png")));
        randomTextures.add(new Texture(Gdx.files.internal("squarePiano.png")));
        randomTextures.add(new Texture(Gdx.files.internal("squareBed.png")));
        floorTexture = new Texture(Gdx.files.internal("floor.png"));
        floorTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        runningAnimation = new Animation(1/10f, new TextureAtlas(Gdx.files.internal("boy.atlas")).getRegions());
        jumpingAnimation = new Animation(1/10f, new TextureAtlas(Gdx.files.internal("boyJumping.atlas")).getRegions());
        lavaAnimation = new Animation(1/10f, new TextureAtlas(Gdx.files.internal("lava.pack")).getRegions());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        timeElapsed += Gdx.graphics.getDeltaTime();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for(int i = 0; i < WORLD_WIDTH; i+=WORLD_WIDTH/2) {
            for(int j = 0; j < WORLD_HEIGHT; j+=WORLD_HEIGHT/4) {
                batch.draw(floorTexture, 0, 0, i, j, i+WORLD_WIDTH/2, j+WORLD_HEIGHT/4);
            }
        }


        for (Obstacle obstacle : game.getObstacles()) {
            obstacle.setTexture(randomTextures.get((int) (Math.random()*randomTextures.size())));
            obstacle.draw(batch);
        }

        for (Lava lava: game.getLavaPatches()) {
            lava.setAnimation(lavaAnimation);
            lava.draw(batch, timeElapsed);
        }

        TextureRegion frame = runningAnimation.getKeyFrame(timeElapsed,true);
        if(game.player.isJumping())
            frame = jumpingAnimation.getKeyFrames()[2];

        float x = game.getPlayerPosition().x-PLAYER_WIDTH/2;
        float y = game.getPlayerPosition().y-PLAYER_HEIGHT/2;
        float width = PLAYER_WIDTH*PIXELS_PER_METER;
        float height = PLAYER_HEIGHT*PIXELS_PER_METER;

        batch.draw(frame, x, y, width, height);
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
