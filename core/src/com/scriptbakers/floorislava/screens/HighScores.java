package com.scriptbakers.floorislava.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.scriptbakers.floorislava.logic.Game;

import java.util.Collections;

/**
 * Created by inesc on 05/11/2016.
 */

public class HighScores implements Screen{

    Stage stage;

    Texture background;
    Sprite highscore;
    Sprite firstPos, secondPos, thirdPos;

    private Label labelScore1;
    private Label labelScore2;
    private Label labelScore3;

    public void HighScores(Game game, SpriteBatch batch){

        stage= new Stage();
        background = new Texture("HSbackground.jpg");

        //Collections.sort(game.scores);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    }

    @Override
    public void dispose() {

    }
}
