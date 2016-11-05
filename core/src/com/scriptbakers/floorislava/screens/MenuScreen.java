package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.Main;

import static com.scriptbakers.floorislava.Constants.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;

/**
 * Created by inesc on 04/11/2016.
 */

public class MenuScreen implements Screen {
    SpriteBatch batch;
    Sprite gameTitle, teamTitle;
    Stage stage;

    public MenuScreen(SpriteBatch batch) {
        this.batch = batch;

        gameTitle = new Sprite(new Texture("gameTitle.png"));
        teamTitle = new Sprite(new Texture("teamTitle.png"));
        gameTitle.scale(0.2f);
        stage = new Stage(new ExtendViewport(WORLD_WIDTH, 0), batch);
        stage.getCamera().position.set(WORLD_WIDTH/2, WORLD_HEIGHT/2, 0);
    }

    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    public void pause() {

    }

    public void resume() {

    }

    public void render(float delta) {
        Viewport viewport = stage.getViewport();

        batch.setProjectionMatrix(stage.getCamera().combined);
        batch.begin();
        //batch.draw(gameTitle, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight()/ 2, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);
        ///batch.draw(teamTitle, Gdx.graphics.getWidth() / 4, 0, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 4);
        batch.draw(gameTitle, viewport.getWorldWidth()/4,viewport.getWorldHeight()/2, viewport.getWorldWidth()/2, viewport.getWorldHeight()/3);
        batch.draw(teamTitle,  viewport.getWorldWidth()/4,0, viewport.getWorldWidth()/2, viewport.getWorldHeight()/4);
        batch.end();

        if (Gdx.input.isTouched())
            Main.stateManager.startGame();
    }

    public void hide() {
        stage.dispose();
    }

    public void dispose() {

    }
}