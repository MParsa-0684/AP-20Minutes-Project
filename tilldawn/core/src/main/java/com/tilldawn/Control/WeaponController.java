package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.tilldawn.Main;
import com.tilldawn.Model.*;

import java.util.ArrayList;

public class WeaponController {
    private Weapon weapon;
    private ArrayList<Bullet> playerBullets;
    private EnemyController enemyController;

    public WeaponController(EnemyController enemyController) {
        this.weapon = App.getCurrentGame().getPlayer().getWeapon();
        this.playerBullets = new ArrayList<>();
        this.enemyController = enemyController;
    }

    public void update() {
        weapon.getSprite().setPosition(App.getCurrentGame().getPlayer().getPosition().x + 12,
            App.getCurrentGame().getPlayer().getPosition().y + 10);
        weapon.getSprite().draw(Main.getBatch());

        handlePlayerInput();

        if(weapon.isReloading() && weapon.getReloadTimer() < weapon.getType().getTimeReload()) {
            reloadAnimation();
            checkNeedReload();
        }

        if(weapon.isAutoAim()) {
            handleWeaponAutoAim();
        }

        handleWeaponBullets();

    }

    public void handlePlayerInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.R) || (weapon.isNeedsReload() && App.getCurrentGame().getPreGame().isAutoReload())) {
            weapon.setReloading(true);
            weapon.setNeedsReload(false);
            weapon.setReloadTimer(0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            weapon.setAutoAim(!weapon.isAutoAim());
        }


    }

    public void handleWeaponRotation(int x, int y) {
        float weaponCenterX = App.getCurrentGame().getPlayer().getPosition().x;
        float weaponCenterY = App.getCurrentGame().getPlayer().getPosition().y;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);
        weapon.getSprite().setRotation((float) (Math.PI - angle * MathUtils.radiansToDegrees));
    }

    public void reloadAnimation() {
        Array<Texture> regions = new Array<>();
        for (int i = 1; i < weapon.getType().getTextures().size(); i++) {
            regions.add(weapon.getType().getTextures().get(i));
        }

        Animation<Texture> animation = new Animation<>(0.1f, regions);

        weapon.getSprite().setRegion(animation.getKeyFrame(weapon.getReloadTime()));

        if(!animation.isAnimationFinished(weapon.getReloadTimer())) {
            weapon.setReloadTime(weapon.getReloadTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            weapon.setReloadTime(0);
            weapon.getSprite().setRegion(weapon.getType().getTextures().get(0));
            weapon.setAmmo(weapon.getType().getAmmoMax());
        }

        animation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    private void checkNeedReload() {
        weapon.setReloadTimer(weapon.getReloadTimer() + Gdx.graphics.getDeltaTime());
        if(weapon.getReloadTimer() == weapon.getType().getTimeReload()) {
            weapon.setReloading(false);
            weapon.setReloadTimer(0);
            weapon.setReloadTime(0);
        }
    }

    public void handleWeaponShoot(int x, int y) {
        if(weapon.isNeedsReload())
            return;

        if(weapon.getAmmo() == 0) {
            weapon.setNeedsReload(true);
            return;
        }


        playerBullets.add(new Bullet(new Vector2(x, y), App.getCurrentGame().getPlayer().getPosition(),
            weapon.getType().getDamage(), GameAssetManager.getGameAssetManager().getBullet()));
        weapon.decreaseAmmo();
    }

    public void handleWeaponAutoAim() {
        ArrayList<Enemy> enemies = enemyController.getEnemies();
        ArrayList<Vector2> vectors = new ArrayList<>();
        Vector2 minVector = new Vector2(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Enemy closestEnemy = null;

        for (Enemy enemy : enemies) {
            if(enemy.getEnemyType() == EnemyType.TREE)
                continue;

            Vector2 vector = new Vector2(enemy.getPosition().x - App.getCurrentGame().getPlayer().getPosition().x
                , enemy.getPosition().y - App.getCurrentGame().getPlayer().getPosition().y);

            vectors.add(vector);
            if(vector.len() < minVector.len()) {
                minVector = vector;
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            if(minVector.len() == vectors.get(i).len()) {
                closestEnemy = enemies.get(i);
                break;
            }
        }

        if(closestEnemy != null) {
            handleWeaponShoot((int) closestEnemy.getPosition().x, (int) closestEnemy.getPosition().y);
        }
    }

    public void handleWeaponBullets() {
        for(Bullet b : playerBullets) {
            b.getSprite().draw(Main.getBatch());

            b.getSprite().setX(b.getSprite().getX() + b.getDirection().x * 5);
            b.getSprite().setY(b.getSprite().getY() - b.getDirection().y * 5);
        }
    }


}
