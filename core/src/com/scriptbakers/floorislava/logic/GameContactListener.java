package com.scriptbakers.floorislava.logic;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.scriptbakers.floorislava.logic.gameentities.Player;

import static com.scriptbakers.floorislava.Constants.CATEGORY_LAVA;
import static com.scriptbakers.floorislava.Constants.CATEGORY_FURNITURE;
import static com.scriptbakers.floorislava.Constants.CATEGORY_PLAYER;

/**
 * Created by bernardo on 04-11-2016.
 */

public class GameContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        short fixtureACategory = contact.getFixtureA().getFilterData().categoryBits;
        short fixtureBCategory = contact.getFixtureB().getFilterData().categoryBits;

        if(fixtureACategory == CATEGORY_PLAYER ) {
            Player player = (Player) contact.getFixtureA().getBody().getUserData();
            switch (fixtureBCategory) {
                case CATEGORY_FURNITURE:
                    player.onObstacle = true;
                    break;
                case CATEGORY_LAVA:
                    player.onLava = true;
                    break;
            }
        }
        //Delete overlapping furniture.
        /*else if(fixtureACategory == CATEGORY_FURNITURE) {
            switch (fixtureBCategory) {
                case CATEGORY_FURNITURE:
                    contact.setEnabled(false);
                    Fixture fixture = contact.getFixtureB();
                    fixture.getBody().destroyFixture(fixture);
                    break;
            }
        }*/

        if(fixtureBCategory == CATEGORY_PLAYER ) {
            Player player = (Player) contact.getFixtureB().getBody().getUserData();
            switch (fixtureACategory) {
                case CATEGORY_FURNITURE:
                    player.onObstacle = true;
                    break;
                case CATEGORY_LAVA:
                    player.onLava = true;
                    break;
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        short fixtureACategory = contact.getFixtureA().getFilterData().categoryBits;
        short fixtureBCategory = contact.getFixtureB().getFilterData().categoryBits;

        if(fixtureACategory == CATEGORY_PLAYER ) {
            Player player = (Player) contact.getFixtureA().getBody().getUserData();
            switch (fixtureBCategory) {
                case CATEGORY_FURNITURE:
                    player.onObstacle = false;
                    break;
                case CATEGORY_LAVA:
                    player.onLava = false;
                    break;
            }
        }


        if(fixtureBCategory == CATEGORY_PLAYER ) {
            Player player = (Player) contact.getFixtureB().getBody().getUserData();
            switch (fixtureACategory) {
                case CATEGORY_FURNITURE:
                    player.onObstacle = false;
                    break;
                case CATEGORY_LAVA:
                    player.onLava = false;
                    break;
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
