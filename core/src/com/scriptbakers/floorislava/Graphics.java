package com.scriptbakers.floorislava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by bernardo on 05-11-2016.
 */

public class Graphics {
    public static final Animation runningAnimation = new Animation(1/10f, new TextureAtlas(Gdx.files.internal("boy.atlas")).getRegions());
    public static final Animation jumpingAnimation = new Animation(1/10f, new TextureAtlas(Gdx.files.internal("boyJumping.atlas")).getRegions());
    public static final Animation lavaAnimation = new Animation(1/10f, new TextureAtlas(Gdx.files.internal("lava.pack")).getRegions());

    public static final Texture floorTexture = new Texture(Gdx.files.internal("floor.png"));
    public static final Texture tableTexture = new Texture(Gdx.files.internal("squareTable.png"));
    public static final Texture pianoTexture = new Texture(Gdx.files.internal("squarePiano.png"));
    public static final Texture bedTexture = new Texture(Gdx.files.internal("squareBed.png"));
}
