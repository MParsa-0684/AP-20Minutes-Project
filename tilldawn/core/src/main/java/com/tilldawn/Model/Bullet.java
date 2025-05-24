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
    private CollisionRect collisionRect;

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
        collisionRect = new CollisionRect(this.position.x, this.position.y,
            this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);
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

    public void update() {
        this.getSprite().setX(this.getSprite().getX() + this.direction.x * this.speed);
        this.getSprite().setY(this.getSprite().getY() - this.direction.y * this.speed);
        this.position.set(this.getSprite().getX(), this.getSprite().getY());
        this.collisionRect.update(this.position.x, this.position.y,
            this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public int getDamage() {
        return damage;
    }
}
