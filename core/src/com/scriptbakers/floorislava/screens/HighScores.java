package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.logic.Game;

import java.util.Collections;
import java.util.Locale;

import static com.scriptbakers.floorislava.Constants.GameState.PAUSED;
import static com.scriptbakers.floorislava.Constants.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;

/**
 * Created by inesc on 05/11/2016.
 */

public class HighScores implements Screen{

    Stage stage;

    Texture background;
    Sprite highscore;
    Sprite firstPos, secondPos, thirdPos;

    Game game;
    SpriteBatch batch;
    OrthographicCamera camera;
    boolean renderedOnce;
    Viewport viewport;
    private Label labelScore1;
    private Label labelScore2;
    private Label labelScore3;

    public void HighScores(Game game, SpriteBatch batch){
        this.game = game;
        this.batch=batch;
        background = new Texture("HSbackground.jpg");

        stage= new Stage();

        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new ExtendViewport(WORLD_WIDTH, 0, camera);
        // Needed in order to make the game full screen.
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(WORLD_WIDTH/2, Constants.WORLD_HEIGHT/2, 0);
        renderedOnce = false;


        Collections.sort(game.scores);
        Collections.reverse(game.scores);

        labelScore1 = new Label(String.format("1-     %05d", game.scores.get(0)), new Label.LabelStyle(new BitmapFont(), Color.RED));
        labelScore1.setFontScale(3f);
        labelScore2 = new Label(String.format("2-     %05d", game.scores.get(1)), new Label.LabelStyle(new BitmapFont(), Color.RED));
        labelScore2.setFontScale(3f);
        labelScore3 = new Label(String.format("3-     %05d", game.scores.get(2)), new Label.LabelStyle(new BitmapFont(), Color.RED));
        labelScore3.setFontScale(3f);

        Table table = new Table();
        table.left();
        table.setFillParent(true);

        table.padTop(60);
        table.add(labelScore1).height(100).padLeft(viewport.getWorldWidth()/4);
        table.row();
        table.add(labelScore2).height(100).padLeft(viewport.getWorldWidth()/4);
        table.row();
        table.add(labelScore3).height(100).padLeft(viewport.getWorldWidth()/4);
        table.row();

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);

    }

    public void handleInput() {
        //TODO: create 'back tom main menu' button

    }

    public void update(float dt) {
        handleInput();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        if(game.getGameState() != PAUSED && renderedOnce) //TODO: confirmar estado do jogo
            return;

        renderedOnce = true;

        camera.update();
        game.update(delta);
        batch.begin();
        //TODO: draw background, button and other sprites
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
