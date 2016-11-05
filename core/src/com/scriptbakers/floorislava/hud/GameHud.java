package com.scriptbakers.floorislava.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.scriptbakers.floorislava.logic.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.scriptbakers.floorislava.Constants;

/**
 * Created by epassos on 11/4/16.
 */

public class GameHud {

    Stage stage;
    Table table;
    Viewport vport;
    Vector2 jumpOrigin;
    Vector2 jumpVector;
    SpriteBatch batch;
    //TODO display score
    InputListener inputListener;
    Game game;
    Sprite arrow; //TODO remove


    public GameHud(final Game game, final SpriteBatch batch){
        this.batch = batch;
        this.vport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, new OrthographicCamera());
        this.table = new Table();
        this.game = game;
        this.stage = new Stage(this.vport, this.batch);
        this.stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        this.arrow = new Sprite(new Texture("arrow.png"));
        final int ARROW_MAX_LEN = 100;

        this.inputListener = new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO check if not pressing any button
                createJumpVector(x,y);
                arrow.setBounds(game.player.getPosition().x-8,game.player.getPosition().y,0,0);
                arrow.setOriginCenter();
                arrow.setOrigin(arrow.getOriginX(), arrow.getOriginY() - arrow.getHeight()/2);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                updateJumpVector(x,y);
                arrow.setOrigin(arrow.getWidth()/2, 0);
                arrow.setSize(15,ARROW_MAX_LEN*jumpVector.len()/Constants.MAX_JUMPVEC_LEN);
                arrow.setRotation(90 + jumpVector.angle());
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                arrow.setBounds(0,0,0,0);
                endJumpVector();
                deleteJumpVector();
            }
        };

        this.stage.addListener(inputListener);

    }

    public void endJumpVector(){
        game.player.jump(new Vector2(-jumpVector.x,-jumpVector.y));
    }

    public void createJumpVector(float x, float y){
        jumpOrigin = new Vector2(x,y);
        jumpVector = new Vector2(0,0);
    }

    public void updateJumpVector(float x, float y){
        Vector2 tempVec = new Vector2(x - jumpOrigin.x, y - jumpOrigin.y);

        if(tempVec.angle() > 0 && tempVec.angle() < 90)
            tempVec.setAngle(0);

        else if(tempVec.angle() > 90 && tempVec.angle() < 180)
            tempVec.setAngle(180);


        if (tempVec.len() <= Constants.MAX_JUMPVEC_LEN) {
            jumpVector.set(tempVec);
        }

        else {
            jumpVector.setAngle(tempVec.angle());
            jumpVector.setLength(Constants.MAX_JUMPVEC_LEN);
        }

    }

    public void deleteJumpVector(){
        if(jumpVector != null) {
            jumpVector = null;
            jumpOrigin = null;
        }
    }

    public void draw(){
        arrow.draw(batch);
    }

    public Stage getStage(){
        return stage;
    }


}
