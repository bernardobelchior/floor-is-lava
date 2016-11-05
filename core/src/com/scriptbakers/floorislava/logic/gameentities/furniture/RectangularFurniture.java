package com.scriptbakers.floorislava.logic.gameentities.furniture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by bernardo on 05-11-2016.
 */

public class RectangularFurniture extends Furniture {
    Vector2 dimensions;

    public RectangularFurniture(World world, float x, float y, float width, float height) {
        super(world);

        dimensions = new Vector2(width/2, height/2);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(dimensions.x, dimensions.y);

        createBody(polygonShape, x, y);
    }

    public Vector2 getDimensions() {
        return dimensions;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(texture == null)
            return;

        float x = getPosition().x-dimensions.x;
        float y = getPosition().y-dimensions.y;
        float width = dimensions.x*2;
        float height = dimensions.y*2;


        batch.draw(texture, x, y, width, height);
    }
}
