package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player {
    private Avatar avatar;
    private Weapon weapon;
    private Sprite sprite;
    private CollisionRect collisionRect;
    private int health;
    private int speed;
    private boolean isIdle;
    private boolean isWalking;
    private boolean isRunning;
    Vector2 position;
    private ArrayList<Ability> abilities;
    private float time;

    public Player(Avatar avatar, Weapon weapon) {
        this.avatar = avatar;
        this.weapon = weapon;
        this.health = avatar.getHP();
        this.speed = avatar.getSpeed();
        this.sprite = App.getCurrentUser().getCurrentSprite();
        this.sprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        this.sprite.setSize(sprite.getWidth() * 2.5f, sprite.getHeight() * 2.5f);
        this.collisionRect = new CollisionRect(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        this.isIdle = true;
        this.isWalking = false;
        this.isRunning = false;
        this.position = new Vector2((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        this.abilities = new ArrayList<>();
        this.time = 0;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public boolean isWalking() {
        return isWalking;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setIdle(boolean idle) {
        isIdle = idle;
    }

    public void setWalking(boolean walking) {
        isWalking = walking;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
