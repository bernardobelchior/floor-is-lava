package com.scriptbakers.floorislava;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.scriptbakers.floorislava.logic.StateManager;
import com.scriptbakers.floorislava.screens.GameOverScreen;

public class Main extends ApplicationAdapter {
	public static final StateManager stateManager = new StateManager();

	@Override
	public void create () {
		stateManager.create();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateManager.update(1/60f);
	}

	@Override
	public void dispose () {

	}
}
