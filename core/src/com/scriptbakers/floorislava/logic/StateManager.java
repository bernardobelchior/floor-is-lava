package com.scriptbakers.floorislava.logic;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.screens.GameOverScreen;
import com.scriptbakers.floorislava.screens.GameScreen;
import com.scriptbakers.floorislava.screens.MenuScreen;

import java.util.ArrayList;

/**
 * Created by bernardo on 05-11-2016.
 */

public class StateManager {
    private Game game;
    private ArrayList<Screen> screens;
    private SpriteBatch batch;
    private Constants.GameState gameState;

    public StateManager() {
        screens = new ArrayList<Screen>();
        gameState = Constants.GameState.PAUSED;
    }

    public void create() {
        batch = new SpriteBatch();
        game = new Game();
        screens.add(new GameScreen(game, batch));
        screens.add(new MenuScreen(batch));
        screens.get(screens.size()-1).show();
    }

    public void update(float delta) {
        if(game == null)
            return;

        if(gameState == Constants.GameState.RUNNING)
            game.update(1/60f);

        for (Screen screen:	screens) {
            screen.render(1/60f);
        }
    }

    public void restartGame() {
        game = new Game();
        screens.clear();
        screens.add(new GameScreen(game, batch));
        startGame();
    }

    public void startGame() {
        if(screens.size() > 1)
            screens.remove(screens.size()-1);
        screens.get(screens.size()-1).show();
        gameState = Constants.GameState.RUNNING;
    }

    public void gameOver() {
        gameState = Constants.GameState.OVER;
        screens.add(new GameOverScreen(game, batch));
        screens.get(screens.size()-1).show();
    }
}
