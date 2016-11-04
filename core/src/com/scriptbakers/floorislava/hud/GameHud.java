package com.scriptbakers.floorislava.hud;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by epassos on 11/4/16.
 */

public class GameHud {
    Stage stage;
    Table table;
    Viewport vport;
    //TODO display score
    InputListener inputListener;

    public GameHud(){
        this.stage = new Stage(); //TODO add viewport and batch
        this.inputListener = new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touchDown"); //FIXME debug only
                //TODO insert arrow and start registering movement vector

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touchUp"); //FIXME debug only
                //TODO remove arrow, register movement vector and jump!
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                System.out.println("dragging..."); //FIXME debug only
                //TODO scale arrow and update movement vector

                
            }
        }

    }

}
