package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.logic.Game;

/**
 * Created by inesc on 04/11/2016.
 */

public class GameOverScreen implements Screen{
    private ImageButton replayButton;
    private Sprite skull;
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
        skull = new Sprite(new Texture("squareSkull.png"));
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
        batch.begin();
        batch.draw(skull, Constants.WORLD_WIDTH/2 - skull.getWidth()/2,
                Constants.WORLD_HEIGHT/2 + skull.getHeight()/2,skull.getWidth(), skull.getHeight() );
        stage.draw();
        batch.end();
        stage.act(delta);

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
