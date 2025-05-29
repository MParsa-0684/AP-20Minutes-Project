package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class World {
    private Texture texture = GameAssetManager.getGameAssetManager().getMap();
    private Sprite sprite = new Sprite(texture);
    private Vector2 pos = new Vector2(-4264, -2936);

    public World() {

    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPos() {
        return pos;
    }
}
