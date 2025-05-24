package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    private WeaponType type;
    private Sprite sprite;
    private int ammo;
    private int maxAmmo;
    private int projectile;
    private boolean needsReload;
    private boolean reloading;
    private float reloadTimer;
    private float reloadTime;
    private boolean autoAim;

    public Weapon(WeaponType type) {
        this.type = type;
        this.sprite = new Sprite(type.getTextures().get(0));
        this.sprite.setPosition((float) Gdx.graphics.getWidth() / 2 + 12, (float) Gdx.graphics.getHeight() / 2 + 10);
        this.sprite.setSize(50, 50);
        this.maxAmmo = type.getAmmoMax();
        this.ammo = type.getAmmoMax();
        this.projectile = type.getProjectile();
        this.needsReload = false;
        this.reloadTimer = 0;
        this.reloading = false;
        this.reloadTime = 0;
        this.autoAim = false;
    }

    public WeaponType getType() {
        return type;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void decreaseAmmo() {
        ammo--;
    }

    public boolean isNeedsReload() {
        return needsReload;
    }

    public void setNeedsReload(boolean needsReload) {
        this.needsReload = needsReload;
    }


    public float getReloadTimer() {
        return reloadTimer;
    }

    public void setReloadTimer(float reloadTimer) {
        this.reloadTimer = reloadTimer;
    }

    public boolean isReloading() {
        return reloading;
    }

    public void setReloading(boolean reloading) {
        this.reloading = reloading;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public boolean isAutoAim() {
        return autoAim;
    }

    public void setAutoAim(boolean autoAim) {
        this.autoAim = autoAim;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public int getProjectile() {
        return projectile;
    }

    public void setProjectile(int projectile) {
        this.projectile = projectile;
    }
}
