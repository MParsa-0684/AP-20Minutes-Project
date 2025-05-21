package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

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

    public Player(Avatar avatar, Weapon weapon) {
        this.avatar = avatar;
        this.weapon = weapon;
        this.health = avatar.getHP();
        this.speed = avatar.getSpeed();
        this.sprite = App.getCurrentUser().getCurrentSprite();
        this.sprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        this.sprite.setSize(sprite.getWidth() * 2, sprite.getHeight() * 2);
        this.collisionRect = new CollisionRect(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        this.isIdle = true;
        this.isWalking = false;
        this.isRunning = false;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
