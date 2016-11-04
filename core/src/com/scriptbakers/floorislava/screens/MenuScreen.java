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

public class MenuScreen implements Screen{
    //private static MenuScreen ourInstance = new MenuScreen();

    private ImageButton playButton;
    private Skin skin;
    private TextureAtlas buttonsAtlas;
    private Stage stage;
    private SpriteBatch batch;
    private Game game;

    public MenuScreen(SpriteBatch batch) {
        this.batch = batch;

        //background image
        skin = new Skin();
        stage = new Stage();
    }

    public void show(){

        buttonsAtlas = new TextureAtlas("buttons.pack");
        skin = new Skin();
        skin.addRegions(buttonsAtlas);

        Gdx.input.setInputProcessor(stage);

        //PLAY BUTTON
        playButton= new ImageButton(skin.getDrawable("playButton"));
        playButton.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        playButton.setPosition((Gdx.graphics.getWidth()/2 - playButton.getWidth()/2), Gdx.graphics.getHeight()/2);
        stage.addActor(playButton);
        playButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
               ;
            }
        });

    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    public void hide() {
        stage.dispose();

    }

    public void dispose() {


    }
}
