package com.scriptbakers.floorislava.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.scriptbakers.floorislava.Constants;
import com.scriptbakers.floorislava.logic.Game;

import javax.swing.text.View;

import static com.scriptbakers.floorislava.Constants.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;

/**
 * Created by epassos on 11/4/16.
 */

public class GameHud {

    Stage stage;
    Table table;
    Vector2 jumpOrigin;
    Vector2 jumpVector;
    SpriteBatch batch;
    InputListener inputListener;
    Game game;
    Sprite arrow; //TODO remove
    Label scoreLabel;


    public GameHud(final Game game, final SpriteBatch batch, Viewport viewport){
        this.batch = batch;
        this.game = game;

        stage = new Stage(viewport, batch);
        table = new Table();
        stage.addActor(table);
        stage.getCamera().position.set(WORLD_WIDTH/2, WORLD_HEIGHT/2, 0);


        Gdx.input.setInputProcessor(stage);
        arrow = new Sprite(new Texture("arrow.png"));
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
                arrow.setBounds(game.player.getPosition().x-8,game.player.getPosition().y ,15,ARROW_MAX_LEN*jumpVector.len()/Constants.MAX_JUMP_VECTOR_LENGTH);
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

        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        scoreLabel = new Label("Score: 0", labelStyle);
        table.add(scoreLabel).left().top();
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

        if (tempVec.len() <= Constants.MAX_JUMP_VECTOR_LENGTH)
            this.jumpVector.set(tempVec);
         else {
            this.jumpVector.setAngle(tempVec.angle());
            this.jumpVector.setLength(Constants.MAX_JUMP_VECTOR_LENGTH);
        }
    }

    public void deleteJumpVector(){
        if(jumpVector != null) {
            jumpVector = null;
            jumpOrigin = null;
        }
    }

    public void draw(){
        batch.begin();
        arrow.draw(batch);
        batch.end();

        scoreLabel.setText("Score: " + game.getScore());
        stage.draw();
    }

    public Stage getStage(){
        return stage;
    }
}
