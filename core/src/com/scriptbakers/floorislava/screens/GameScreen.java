package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.scriptbakers.floorislava.logic.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.hud.GameHud;
import com.scriptbakers.floorislava.logic.gameentities.Lava;
import com.scriptbakers.floorislava.logic.gameentities.furniture.Furniture;

import static com.scriptbakers.floorislava.Constants.LEFT_LAVA_THRESHOLD;
import static com.scriptbakers.floorislava.Constants.PIXELS_PER_METER;
import static com.scriptbakers.floorislava.Constants.PLAYER_HEIGHT;
import static com.scriptbakers.floorislava.Constants.PLAYER_WIDTH;
import static com.scriptbakers.floorislava.Constants.RIGHT_LAVA_THRESHOLD;
import static com.scriptbakers.floorislava.Constants.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;
import static com.scriptbakers.floorislava.Graphics.floorTexture;
import static com.scriptbakers.floorislava.Graphics.jumpingAnimation;
import static com.scriptbakers.floorislava.Graphics.lavaAnimation;
import static com.scriptbakers.floorislava.Graphics.runningAnimation;


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

    public GameScreen(Game game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;

        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new ExtendViewport(WORLD_WIDTH, 0, camera);
        this.hud = new GameHud(game, batch, viewport);
        timeElapsed = 0;

        // Needed in order to make the game full screen.
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(WORLD_WIDTH/2, Constants.WORLD_HEIGHT/2, 0);

        floorTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(hud.getStage());
    }

    @Override
    public void render(float delta) {
        camera.update();
        timeElapsed += Gdx.graphics.getDeltaTime();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for(int i = 0; i < WORLD_WIDTH; i+=WORLD_WIDTH) {
            for(int j = 0; j < WORLD_HEIGHT; j+=WORLD_HEIGHT/4) {
                batch.draw(floorTexture, LEFT_LAVA_THRESHOLD, 0, i, j, i+WORLD_WIDTH, j+WORLD_HEIGHT/4);
            }
        }

        TextureRegion frame = lavaAnimation.getKeyFrame(timeElapsed, true);
        batch.draw(frame, 0, 0, LEFT_LAVA_THRESHOLD, WORLD_HEIGHT);
        batch.draw(frame, RIGHT_LAVA_THRESHOLD, 0, LEFT_LAVA_THRESHOLD, WORLD_HEIGHT);


        for (Furniture furniture : game.getFurnitures()) {
            furniture.draw(batch);
        }

        for (Lava lava: game.getLavaPatches()) {
            lava.setAnimation(lavaAnimation);
            lava.draw(batch, timeElapsed);
        }

        frame = runningAnimation.getKeyFrame(timeElapsed,true);
        if(game.player.isJumping())
            frame = jumpingAnimation.getKeyFrames()[2];

        float x = game.getPlayerPosition().x-PLAYER_WIDTH;
        float y = game.getPlayerPosition().y-PLAYER_HEIGHT;
        float width = PLAYER_WIDTH*PIXELS_PER_METER/2;
        float height = PLAYER_HEIGHT*PIXELS_PER_METER/2;

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
