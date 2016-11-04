package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
<<<<<<< HEAD:core/src/com/scriptbakers/floorislava/gameentities/Player.java
import com.badlogic.gdx.physics.box2d.FixtureDef;
=======
import com.badlogic.gdx.physics.box2d.CircleShape;
>>>>>>> origin/player_jump:core/src/com/scriptbakers/floorislava/logic/gameentities/Player.java
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.scriptbakers.floorislava.FloorIsLava;

<<<<<<< HEAD:core/src/com/scriptbakers/floorislava/gameentities/Player.java
import static com.scriptbakers.floorislava.Constants.CATEGORY_OBSTACLE;
import static com.scriptbakers.floorislava.Constants.CATEGORY_PLAYER;
import static com.scriptbakers.floorislava.Constants.Entity.PLAYER;
import static com.scriptbakers.floorislava.Constants.MASK_PLAYER;
=======
import java.awt.geom.Point2D;

import static com.scriptbakers.floorislava.FloorIsLava.INITIAL_GRAVITY;
import static com.scriptbakers.floorislava.FloorIsLava.WORLD_HEIGHT;
import static com.scriptbakers.floorislava.FloorIsLava.WORLD_WIDTH;
>>>>>>> origin/player_jump:core/src/com/scriptbakers/floorislava/logic/gameentities/Player.java


/**
 * Created by epassos on 11/4/16.
 * This is the class that represents the player
 */


public class Player {

    Vector2 position;
    int score;
    boolean jumping;
    public final Body body;

<<<<<<< HEAD:core/src/com/scriptbakers/floorislava/gameentities/Player.java
    public Player(World world, int x, int y, int width, int height) {
=======

    public Player(World world, int x, int y){
>>>>>>> origin/player_jump:core/src/com/scriptbakers/floorislava/logic/gameentities/Player.java
        this.position = new Vector2(x,y);
        this.jumping = false;

        /* Creates the player in the world. */
        BodyDef bodyDef = new BodyDef();
<<<<<<< HEAD:core/src/com/scriptbakers/floorislava/gameentities/Player.java
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        PolygonShape player = new PolygonShape();
        player.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = player;
        fixtureDef.filter.categoryBits = CATEGORY_PLAYER;
        fixtureDef.filter.categoryBits = MASK_PLAYER;
        fixtureDef.density = 1;

        body.createFixture(fixtureDef);
        body.setGravityScale(0);
        body.setUserData(this);

=======
        bodyDef.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/5);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        CircleShape player = new CircleShape();
        player.setRadius(WORLD_WIDTH/10);
        body.createFixture(player, 1);
        body.setGravityScale(0);
>>>>>>> origin/player_jump:core/src/com/scriptbakers/floorislava/logic/gameentities/Player.java
        player.dispose();
    }

    public void update(float dt){
        //FIXME debug prints
        System.out.println("Player x: " + body.getWorldCenter().x);
        System.out.println("Player y: " + body.getWorldCenter().y);
        position.set(body.getWorldCenter());

    }

    public void jump(Vector2 jumpVector){

        jumping = true;
        //body.applyLinearImpulse(jumpVector.scl(50), body.getWorldCenter(),true);
        body.applyForceToCenter(jumpVector.scl(50),true);

    }

    public Vector2 getPosition() {
        return position;
    }
}
