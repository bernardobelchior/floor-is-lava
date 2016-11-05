package com.scriptbakers.floorislava;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.scriptbakers.floorislava.logic.Game;
import com.scriptbakers.floorislava.screens.GameOverScreen;
import com.scriptbakers.floorislava.screens.GameScreen;
import com.scriptbakers.floorislava.screens.MenuScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.scriptbakers.floorislava.Constants.GameState.OVER;
import static com.scriptbakers.floorislava.Constants.GameState.PAUSED;
import static com.scriptbakers.floorislava.Constants.GameState.RUNNING;

public class GameStateManager extends ApplicationAdapter {
	ArrayList<Screen> screens;
	Game game;
	GameOverScreen gameOverScreen;
	SpriteBatch batch;



	@Override
	public void create () {
        batch = new SpriteBatch();
        game = new Game(this);
        screens = new ArrayList<Screen>();

        screens.add(new GameScreen(game, batch));
        screens.add(new MenuScreen(game, batch));

    }

	@Override
	public void render() {
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
				if(screens.size() < 2) {
                    System.out.println("Adding game over screen");
                    screens.add(new GameOverScreen(game, batch));
                }
                else{
                    System.out.println("DEAD");
                    System.out.println(screens.size());
                }
				break;
			case PAUSED:
				//TODO: Implement
				break;
			case RUNNING:
				if(screens.size() > 1) {
                    screens.remove(screens.size() - 1);
                    System.out.println("ESTA A CORRER");
                }
				//	screens.remove(0);
				break;
		}
	}

	@Override
	public void dispose () {

	}

}
