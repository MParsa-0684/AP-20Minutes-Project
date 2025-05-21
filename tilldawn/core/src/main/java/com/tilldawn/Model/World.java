package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class World {
    private Texture texture = GameAssetManager.getGameAssetManager().getMap();
    private Sprite sprite = new Sprite(texture);

    public World() {

    }
}
