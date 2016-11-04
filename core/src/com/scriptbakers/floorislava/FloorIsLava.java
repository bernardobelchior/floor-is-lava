package com.scriptbakers.floorislava;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class FloorIsLava extends ApplicationAdapter {
	public static int WORLD_WIDTH = 1000;
	public static int WORLD_HEIGHT = 2000;
	public static Vector2 INITIAL_GRAVITY = new Vector2(0, -50);

	World world;
	GameScreen screen;

	@Override
	public void create () {
		world = new World(INITIAL_GRAVITY, true);
		screen = new GameScreen(world);

		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/5);
		bodyDef.type = BodyDef.BodyType.KinematicBody;
		Body body = world.createBody(bodyDef);

		CircleShape player = new CircleShape();
		player.setRadius(WORLD_WIDTH/10);
		body.createFixture(player, 1);

		player.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.step(1/60f, 6, 2);
		screen.render(1/60f);
	}
	
	@Override
	public void dispose () {

	}
}
