package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;

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
    private Vector2 position;
    private HashMap<AbilityType, ArrayList<Ability>> abilities;
    private float time;
    private int level;
    private int killed;
    private boolean invincible;
    private float invincibleTime;
    private int xp;

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
        this.abilities = new HashMap<>();
        for (AbilityType value : AbilityType.values()) {
            this.abilities.put(value, new ArrayList<>());
        }

        this.time = 0;
        this.level = 1;
        this.invincible = false;
        this.invincibleTime = 0;
        this.xp = 0;
        this.killed = 0;
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
        int sp = speed;
        for (Ability ability : abilities.get(AbilityType.SPEEDY)) {
            if(ability.getTime() > 0) {
                sp *= 2;
                break;
            }
        }

        return sp;
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

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void update(Vector2 difference) {
        this.getPosition().set(this.getPosition().x + difference.x, this.getPosition().y + difference.y);
        this.sprite.setPosition(this.getPosition().x, this.getPosition().y);
        this.getCollisionRect().update(this.getSprite().getX(),
            this.getSprite().getY(), this.getSprite().getWidth(), this.getSprite().getHeight());
    }

    public void decreaseHealth(int amount) {
        health -= amount;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public float getInvincibleTime() {
        return invincibleTime;
    }

    public void setInvincibleTime(float invincibleTime) {
        this.invincibleTime = invincibleTime;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public HashMap<AbilityType, ArrayList<Ability>> getAbilities() {
        return abilities;
    }

    public void increaseKilled() {
        this.killed++;
    }

    public int getKilled() {
        return killed;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
