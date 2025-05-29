package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Enemy {
    private EnemyType enemyType;
    private CollisionRect collisionRect;
    Vector2 position;
    private Sprite sprite;
    private float time;
    private int HP;
    private float killedTime;

    public Enemy(EnemyType enemyType, Vector2 position) {
        this.enemyType = enemyType;
        this.sprite = new Sprite(enemyType.getTextures().get(0));
        this.position = position.cpy();
        sprite.setPosition(position.x, position.y);
        this.collisionRect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth() / 3, sprite.getHeight() / 3);
        this.HP = enemyType.getHP();
        this.killedTime = 0f;
        this.time = 0f;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public Vector2 getPosition() {
        return position;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void update(Vector2 direction) {
        this.getPosition().set(this.getPosition().x + direction.x, this.getPosition().y + direction.y);
        this.sprite.setPosition(this.getPosition().x, this.getPosition().y);
        this.getCollisionRect().update(this.getSprite().getX(),
            this.getSprite().getY(), this.getSprite().getWidth(), this.getSprite().getHeight());
    }

    public void decreaseHP(int HP) {
        this.HP -= HP;
    }

    public int getHP() {
        return HP;
    }

    public float getKilledTime() {
        return killedTime;
    }

    public void setKilledTime(float killedTime) {
        this.killedTime = killedTime;
    }
}
