package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.scriptbakers.floorislava.Main;
import com.scriptbakers.floorislava.logic.Game;

import static com.scriptbakers.floorislava.Constants.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;

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
        Table table = new Table();

        stage = new Stage(new ExtendViewport(WORLD_WIDTH, 0), batch);
        stage.getCamera().position.set(WORLD_WIDTH/2, WORLD_HEIGHT/4, 0);

        replayButton= new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("replay.png"))));
        replayButton.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        replayButton.setPosition((Gdx.graphics.getWidth()/2 - replayButton.getWidth()/2), Gdx.graphics.getHeight()/2);

        Image skull = new Image(new Texture("squareSkull.png"));
        skull.setScale(1,0.5f);

        table.center().top();
        table.add(skull).expandX();
        table.row();
        table.add(replayButton);
        table.setFillParent(true);

        stage.addActor(table);
        replayButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                Main.stateManager.restartGame();
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        stage.act(delta);

        //batch.begin();
        stage.draw();
        //batch.end();


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);

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
