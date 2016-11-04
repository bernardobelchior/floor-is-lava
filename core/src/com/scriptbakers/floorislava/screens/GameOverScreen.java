package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scriptbakers.floorislava.logic.Game;

/**
 * Created by inesc on 04/11/2016.
 */

public class GameOverScreen implements Screen{
    private ImageButton replayButton;
    private Skin skin;
    private TextureAtlas buttonsAtlas;
    private Stage stage;
    private SpriteBatch batch;
    private Game game;

    public GameOverScreen(Game game, SpriteBatch batch){
        this.game = game;
        this.batch = batch;

        //backgroud image
        skin = new Skin();
        stage= new Stage();
    }

    @Override
    public void show() {
        buttonsAtlas = new TextureAtlas("buttons.pack");
        skin = new Skin();
        skin.addRegions(buttonsAtlas);

        Gdx.input.setInputProcessor(stage);

        //REPLAY BUTTON
        replayButton= new ImageButton(skin.getDrawable("playButton"));
        replayButton.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        replayButton.setPosition((Gdx.graphics.getWidth()/2 - replayButton.getWidth()/2), Gdx.graphics.getHeight()/2);
        stage.addActor(replayButton);
        replayButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
               game.run();
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {

    }
}
