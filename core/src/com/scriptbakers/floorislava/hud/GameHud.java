package com.scriptbakers.floorislava.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.scriptbakers.floorislava.logic.Game;

import java.awt.geom.Point2D;

/**
 * Created by epassos on 11/4/16.
 */

public class GameHud {

    Stage stage;
    Table table;
    Viewport vport;
    Point2D.Float jumpOrigin;
    Vector2 jumpVector;
    SpriteBatch batch;
    //TODO display score
    InputListener inputListener;

    public GameHud(final Game game, SpriteBatch batch){
        this.batch = batch;


        this.stage = new Stage(); //TODO add viewport and batch
        this.inputListener = new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touchDown"); //FIXME debug only
                //TODO insert arrow
                //TODO check if not pressing any button
                createJumpVector(x,y);

                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                System.out.println("dragging..."); //FIXME debug only
                //TODO scale arrow
                updateJumpVector(x,y);

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touchUp"); //FIXME debug only
                //TODO remove arrow
                game.player.jump(getJumpVector());
                deleteJumpVector();
            }
        };

    }

    public void createJumpVector(float x, float y){
        this.jumpOrigin = new Point2D.Float(x,y);
        this.jumpVector = new Vector2(0,0);

        //FIXME debug
        System.out.println("new vector2: " + x + y);
    }

    public void updateJumpVector(float x, float y){
        this.jumpVector.set(x - jumpOrigin.x, y - jumpOrigin.y);
        System.out.println("dragging: " + jumpVector.x + jumpVector.y);
    }

    public void deleteJumpVector(){
        if(this.jumpVector != null) {
            this.jumpVector = null;
            this.jumpOrigin = null;
        }
    }

    public Vector2 getJumpVector(){
        return jumpVector;
    }


}