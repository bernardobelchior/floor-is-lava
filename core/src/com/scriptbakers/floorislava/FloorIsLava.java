package com.scriptbakers.floorislava;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.scriptbakers.floorislava.logic.Game;
import com.scriptbakers.floorislava.screens.GameScreen;
import com.scriptbakers.floorislava.screens.MenuScreen;

/**
 * Created by epassos on 11/5/16.
 */

public class FloorIsLava extends ApplicationAdapter {
    public GameStateManager gsm;
    public SpriteBatch batch;


    @Override
    public void create () {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        State menuState = new State(new GameScreen(new Game(gsm),batch),gsm);//new MenuScreen(this),gsm);
        gsm.push(menuState);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.render(1/60f);
    }
}
