package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Seed {
    private Texture texture;
    private Sprite sprite;
    private Vector2 position;
    private CollisionRect collisionRect;

    public Seed(Vector2 position) {
        texture = GameAssetManager.getGameAssetManager().getSeed();
        sprite = new Sprite(texture);
        this.position = position.cpy();
        this.sprite.setSize(sprite.getWidth() * 2, sprite.getHeight() * 2);
        this.collisionRect = new CollisionRect(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
    }

}
