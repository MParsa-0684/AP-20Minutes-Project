package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.tilldawn.Main;
import com.tilldawn.Model.*;

import java.util.ArrayList;

public class WeaponController {
    private Weapon weapon;
    private ArrayList<Bullet> playerBullets;
    private SeedController seedController;
    private EnemyController enemyController;

    public WeaponController(EnemyController enemyController, SeedController seedController) {
        this.weapon = App.getCurrentGame().getPlayer().getWeapon();
        this.seedController = seedController;
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
        else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            weapon.setAutoAim(!weapon.isAutoAim());
        }


    }

    public void handleWeaponRotation(int screenX, int screenY) {
        Vector3 world = new Vector3(screenX, screenY, 0);
        App.getCurrentUser().getGameView().getCamera().unproject(world);
        float mouseX = world.x, mouseY = world.y;

        Sprite spr = weapon.getSprite();
        float cx = spr.getX() + spr.getWidth()  * 0.5f;
        float cy = spr.getY() + spr.getHeight() * 0.5f;

        float dx = mouseX - cx;
        float dy = mouseY - cy;
        float angleDeg = MathUtils.atan2(dy, dx) * MathUtils.radiansToDegrees;
        weapon.getSprite().setSize(50, 50);

        spr.setOriginCenter();
        spr.setRotation(angleDeg);
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

        Vector3 world = new Vector3(x, Gdx.graphics.getHeight() - y, 0);
        App.getCurrentUser().getGameView().getCamera().unproject(world);
        playerBullets.add(new Bullet(new Vector2(world.x, world.y), App.getCurrentGame().getPlayer().getPosition(),
            weapon.getType().getDamage(), GameAssetManager.getGameAssetManager().getBullet()));
        weapon.decreaseAmmo();
    }

    public void handleWeaponAutoAim() {
        ArrayList<Enemy> enemies = enemyController.getEnemies();
        ArrayList<Vector2> vectors = new ArrayList<>();
        Vector2 minVector = new Vector2(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Enemy closestEnemy = null;

        for (Enemy enemy : enemies) {
            Vector2 vector = new Vector2(enemy.getPosition().x - App.getCurrentGame().getPlayer().getPosition().x
                , enemy.getPosition().y - App.getCurrentGame().getPlayer().getPosition().y);

            vectors.add(vector);
            if(vector.len() < minVector.len() && enemy.getEnemyType() != EnemyType.TREE) {
                minVector = vector;
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            if(enemies.get(i).getEnemyType() == EnemyType.TREE)
                continue;

            if(minVector.len() == vectors.get(i).len()) {
                closestEnemy = enemies.get(i);
                break;
            }
        }

        if(closestEnemy != null) {
            handleWeaponShoot((int) closestEnemy.getPosition().x, (int) (Gdx.graphics.getHeight() - closestEnemy.getPosition().y));
        }
    }

    public void handleWeaponBullets() {
        ArrayList<Bullet> tempBullets = new ArrayList<>();
        for(Bullet b : playerBullets) {
            b.getSprite().draw(Main.getBatch());

            boolean flag = false;
            b.update();
            ArrayList<Enemy> tempEnemies = new ArrayList<>();
            for (Enemy enemy : enemyController.getEnemies()) {
                if(b.getCollisionRect().hasIntersect(enemy.getCollisionRect()) && enemy.getEnemyType() != EnemyType.TREE) {
                    flag = true;
                    enemy.decreaseHP(b.getDamage());
                    if(enemy.getHP() <= 0) {
                        tempEnemies.add(enemy);
                        App.getCurrentGame().getPlayer().increaseKilled();
                        seedController.getSeeds().add(new Seed(enemy.getPosition(),
                            enemy.getEnemyType().getTextures().get(enemy.getEnemyType().getTextures().size() - 1)));
                    }
                }
            }

            for (Enemy tempEnemy : tempEnemies) {
                enemyController.getEnemies().remove(tempEnemy);
            }

            if(flag) {
                tempBullets.add(b);
            }
        }

        for (Bullet tempBullet : tempBullets) {
            this.playerBullets.remove(tempBullet);
        }
    }


}
