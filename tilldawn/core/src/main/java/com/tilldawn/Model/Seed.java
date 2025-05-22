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
        sprite.setPosition(this.position.x, this.position.y);
        this.sprite.setSize(sprite.getWidth() * 2, sprite.getHeight() * 2);
        this.collisionRect = new CollisionRect(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }
}
