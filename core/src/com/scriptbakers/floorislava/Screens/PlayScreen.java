package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.scriptbakers.floorislava.FloorIsLava;

/**
 * Created by inesc on 04/11/2016.
 */

public class PlayScreen implements Screen{

    private FloorIsLava game;
    private ImageButton pauseButton;
    private Skin skin;
    private TextureAtlas buttonsAtlas;
    private Stage stage;


    public PlayScreen(FloorIsLava floorIsLava){
        game= floorIsLava;

        skin = new Skin();
        stage= new Stage();
    }

    @Override
    public void show() {

        buttonsAtlas = new TextureAtlas("buttons.pack");
        skin = new Skin();
        skin.addRegions(buttonsAtlas);

        Gdx.input.setInputProcessor(stage);

        //PAUSE BUTTON
        pauseButton= new ImageButton(skin.getDrawable("pauseButton"));
        pauseButton.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        pauseButton.setPosition((Gdx.graphics.getWidth()/2 - pauseButton.getWidth()/2), Gdx.graphics.getHeight()/2);
        stage.addActor(pauseButton);

        // ***** //

        /*pauseButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        }); */

        // ***** //

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        //game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();

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
        game.batch.dispose();
        stage.dispose();
    }

    @Override
    public void dispose() {

    }
}
