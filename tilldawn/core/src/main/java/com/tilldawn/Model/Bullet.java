package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Texture texture;
    private Sprite sprite;
    private Vector2 position;
    private Vector2 direction;
    private int speed;
    private int damage;

    public Bullet(Vector2 position, Vector2 ourPosition, int damage, Texture texture) {
        this.texture = texture;
        sprite = new Sprite(texture);
        this.position = ourPosition.cpy();
        this.sprite.setPosition(this.position.x, this.position.y);
        this.direction = new Vector2(position.x - this.position.x, position.y - this.position.y).nor();

        float angle = (float) Math.atan2(position.y - this.position.y, position.x - this.position.x);
        sprite.setRotation((float) (Math.PI - angle * MathUtils.radiansToDegrees));
        this.speed = 5;
        this.damage = damage;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDirection() {
        return direction;
    }
}
