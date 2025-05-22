package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Texture texture = GameAssetManager.getGameAssetManager().getBullet();
    private Sprite sprite = new Sprite(texture);
    private Vector2 position = new Vector2();
    private int speed;
    private int damage;

    public Bullet(Vector2 position, int damage) {
        this.sprite.setPosition((float) Gdx.graphics.getWidth() / 2 + 12, (float) Gdx.graphics.getHeight() / 2 + 30);
        this.position = position.cpy();
        this.speed = 5;
        this.damage = damage;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPosition() {
        return position;
    }
}
