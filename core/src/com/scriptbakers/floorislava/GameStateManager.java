package com.scriptbakers.floorislava;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.scriptbakers.floorislava.logic.Game;
import com.scriptbakers.floorislava.screens.GameOverScreen;
import com.scriptbakers.floorislava.screens.GameScreen;

import java.util.ArrayList;

public class GameStateManager extends ApplicationAdapter {
	ArrayList<Screen> screens;
	Game game;
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		game = new Game();
		screens = new ArrayList<Screen>();

		screens.add(new GameScreen(game, batch));
		//screens.add(new MenuScreen(this, batch));
		game.run();
		}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.update(1/60f);
		updateGameState();

		for (Screen screen:	screens) {
			screen.render(1/60f);
		}
	}

	public void updateGameState(){
		switch (game.getGameState()) {
			case OVER:
				if(screens.size() < 2)
					screens.add(new GameOverScreen(game, batch));
				break;
			case PAUSED:
				//TODO: Implement
				break;
			case RUNNING:
				if(screens.size() > 1)
					screens.remove(screens.size()-1);
				break;
		}
	}

	@Override
	public void dispose () {

	}
}