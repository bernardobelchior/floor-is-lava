package com.scriptbakers.floorislava;

import com.badlogic.gdx.Screen;

/**
 * Created by epassos on 11/5/16.
 */

public class State {

    private Screen screen;
    private GameStateManager gsm;

    public State(Screen screen, GameStateManager gsm){
        this.screen = screen;
        this.gsm = gsm;
    }

    public void render(float delta){
        screen.render(delta);
    }

    public void dispose(){
        screen.dispose();
    }

}
