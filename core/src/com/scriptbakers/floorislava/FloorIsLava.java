package com.scriptbakers.floorislava;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.scriptbakers.floorislava.logic.gameentities.Game;
import com.scriptbakers.floorislava.screens.GameScreen;


public class FloorIsLava extends ApplicationAdapter {
	public static int WORLD_WIDTH = 1000;
	public static int WORLD_HEIGHT = 2000;
	public static Vector2 INITIAL_GRAVITY = new Vector2(0, -50);

	GameScreen screen;
	Game game;

	@Override
	public void create () {
		game = new Game();
		screen = new GameScreen(game);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.update(1/60f);
		screen.render(1/60f);
	}
	
	@Override
	public void dispose () {

	}
}
