package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.Main;

/**
 * Created by inesc on 04/11/2016.
 */

public class MenuScreen implements Screen {
    SpriteBatch batch;
    OrthographicCamera camera;
    FitViewport viewport;
    Sprite gameTitle, teamTitle;
    Stage stage;

    public MenuScreen(SpriteBatch batch) {
        this.batch = batch;

        gameTitle = new Sprite(new Texture("gameTitle.png"));
        teamTitle = new Sprite(new Texture("teamTitle.png"));
        gameTitle.scale(0.2f);
        camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
        camera.position.set(Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT / 2, 0);
        stage = new Stage(viewport, batch);
    }

    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    public void resize(int width, int height) {
        camera = new OrthographicCamera(width, height);
    }

    public void pause() {

    }

    public void resume() {

    }

    public void render(float delta) {
        batch.begin();
        batch.draw(gameTitle, viewport.getWorldWidth() / 4, viewport.getWorldHeight() / 2, viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 3);
        batch.draw(teamTitle, viewport.getWorldWidth() / 4, 0, viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 4);
        if (Gdx.input.isTouched()) {
            Main.stateManager.startGame();
        }
        batch.end();
    }

    public void hide() {
        stage.dispose();
    }

    public void dispose() {

    }
}