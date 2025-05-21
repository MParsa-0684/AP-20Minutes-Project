package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {
    private Texture texture = GameAssetManager.getGameAssetManager().getBullet();
    private Sprite sprite = new Sprite(texture);
    private int x, y;
    private int speed;
    private int damage;

    public Bullet(int x, int y, int speed, int damage) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
    }
}
