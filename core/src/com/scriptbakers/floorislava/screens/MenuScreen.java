package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.hud.GameHud;
import com.scriptbakers.floorislava.logic.Game;

import static com.scriptbakers.floorislava.Constants.GameState.PAUSED;
import static com.scriptbakers.floorislava.Constants.GameState.RUNNING;

/**
 * Created by inesc on 04/11/2016.
 */

public class MenuScreen implements Screen{
    //private static MenuScreen ourInstance = new MenuScreen();
    SpriteBatch batch;
    final Game game;
    Box2DDebugRenderer debugRenderer;
    OrthographicCamera camera;
    ExtendViewport viewport;
    boolean renderedOnce;
    Sprite gameTitle, teamTitle;
    Stage stage;

    public MenuScreen(final Game game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;

        //debugRenderer = new Box2DDebugRenderer();

        gameTitle = new Sprite(new Texture("gameTitle.png"));
        teamTitle = new Sprite(new Texture("teamTitle.png"));
        gameTitle.scale(0.2f);
        camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
        camera.position.set(Constants.WORLD_WIDTH/2, Constants.WORLD_HEIGHT/2, 0);
        stage = new Stage(viewport, batch);
        renderedOnce = false;
       
        Gdx.input.setInputProcessor(stage);
    }


    public void show(){

    }

    public void resize(int width, int height) {
        camera = new OrthographicCamera(width, height);
    }

    public void pause() {

    }

    

    public void resume() {

    }

    public void render(float delta) {
        if(game.getGameState() != PAUSED && renderedOnce)
            return;

        renderedOnce = true;

        camera.update();

        batch.begin();
        batch.draw(gameTitle, viewport.getWorldWidth()/4,viewport.getWorldHeight()/2, viewport.getWorldWidth()/2, viewport.getWorldHeight()/3);
        batch.draw(teamTitle,  viewport.getWorldWidth()/4,0, viewport.getWorldWidth()/2, viewport.getWorldHeight()/4);
        stage.act(delta);
        batch.end();
        if(Gdx.input.isTouched()){
            game.run();
        }

        batch.setProjectionMatrix(stage.getCamera().combined);


        //debugRenderer.render(game.world, camera.combined);
    }

    public void hide() {
        stage.dispose();

    }

    public void dispose() {
        batch.dispose();
       // gameTitle.dispose();
    }
}
