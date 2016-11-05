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

public class GameStateManager extends ApplicationAdapter {
	ArrayList<Screen> screens;
	Game game;
	SpriteBatch batch;

	//scores
	public ArrayList<Integer> scores;
	private Preferences scoresPref;

	@Override
	public void create () {
		batch = new SpriteBatch();
		game = new Game();
		screens = new ArrayList<Screen>();

		screens.add(new MenuScreen(game, batch));
		screens.add(new GameScreen(game, batch));

		//scores
		scores = new ArrayList<Integer>();
		scores.add(0);
		scores.add(0);
		scores.add(0);

		scoresPref = Gdx.app.getPreferences("HighScores");
		String name = scoresPref.getString("name", "no name");
		if(name.equals("no name")){
			scoresPref.putInteger("scores1", 0);
			scoresPref.putInteger("scores2", 0);
			scoresPref.putInteger("scores3", 0);
		}
		else
			 readScores();

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
				if(screens.size() == 1)
					System.out.println("ESTA A CORRER");
					//creens.remove(screens.size());
				break;
		}
	}

	@Override
	public void dispose () {

	}

	public void readScores() {
		scores.add(scoresPref.getInteger("score1"));
		scores.add(scoresPref.getInteger("score2"));
		scores.add(scoresPref.getInteger("score3"));
	}

	public void writeScores(){
		Collections.sort(scores);
		Collections.reverse(scores);
		scoresPref.putString("name", "floor is lava");
		scoresPref.putInteger("score1", scores.get(0));
		scoresPref.putInteger("score2", scores.get(1));
		scoresPref.putInteger("score3", scores.get(2));

		scoresPref.flush();
	}
}
