package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.tilldawn.Main;
import com.tilldawn.Model.*;

import java.util.ArrayList;
import java.util.Random;

public class EnemyController {
    private ArrayList<Enemy> enemies;
    private PlayerController playerController;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Enemy> killedEnemies;

    public EnemyController(PlayerController playerController) {
        this.enemies = new ArrayList<>();
        this.playerController = playerController;
        this.enemyBullets = new ArrayList<>();
        this.killedEnemies = new ArrayList<>();

        Random rand = new Random();
        for (int i = 1; i < 101; i++) {
            for (int j = 0; j < 1000; j++) {
                Vector2 position = new Vector2(rand.nextInt(-i * 50, i * 50), rand.nextInt(-i * 50, i * 50));
                Enemy enemy = new Enemy(EnemyType.TREE, position);
                boolean flag = true;
                for (int k = 0; k < enemies.size(); k++) {
                    if(enemies.get(k).getCollisionRect().hasIntersect(enemy.getCollisionRect())) {
                        flag = false;
                        break;
                    }
                }
                if(App.getCurrentGame().getPlayer().getCollisionRect().hasIntersect(enemy.getCollisionRect()))
                    flag = false;

                if(flag) {
                    enemies.add(enemy);
                    break;
                }
            }
        }
    }

    public void update() {
        ArrayList<Enemy> tempEnemies = new ArrayList<>();
        for (Enemy killedEnemy : killedEnemies) {
            boolean flag = animation(killedEnemy);
            killedEnemy.getSprite().setScale(2f);
            killedEnemy.getSprite().draw(Main.getBatch());
            if(flag) {
                tempEnemies.add(killedEnemy);
            }
        }

        for (Enemy tempEnemy : tempEnemies) {
            killedEnemies.remove(tempEnemy);
        }
        tempEnemies.clear();

        generateRandomEnemies();

        for (Enemy enemy : enemies) {
            enemy.getSprite().draw(Main.getBatch());
            updateEnemy(enemy);
        }

        handleEnemyBullets();
    }

    private void generateRandomEnemies() {
        float time = App.getCurrentGame().getCurrentTime();
        if(3 - time % 3 <= 0.017) {
            int number = (int) Math.ceil(time / 30);
            for (int i = 0; i < number; i++) {
                Vector2 position = getRandomPosition();

                Enemy enemy = new Enemy(EnemyType.TENTACLE_MONSTER, position);
                enemies.add(enemy);
            }
        }

        if(time >= App.getCurrentGame().getGameTime() / 4 && (10 - time % 10) <= 0.017) {
            int number = (int) Math.ceil((4 * time - App.getCurrentGame().getGameTime() + 30) / 30);
            for (int i = 0; i < number; i++) {
                Vector2 position = getRandomPosition();
                Enemy enemy = new Enemy(EnemyType.EYEBAT, position);
                enemies.add(enemy);
            }
        }

        //ELDER enemy
    }

    private Vector2 getRandomPosition() {
        Random rand = new Random();
        float rand1 = rand.nextFloat();
        float rand2 = rand.nextFloat();
        if(rand1 > 0.5) {
            Vector2 position = new Vector2(
                App.getCurrentGame().getPlayer().getPosition().x + (float) Gdx.graphics.getWidth() / 2 * rand2,
                App.getCurrentGame().getPlayer().getPosition().y + (float) Gdx.graphics.getHeight() / 2 * (rand1 > 0.7 ? 1 : -1));
            return position;
        }
        else {
            Vector2 position = new Vector2(
                App.getCurrentGame().getPlayer().getPosition().x + (float) Gdx.graphics.getWidth() / 2 * (rand1 > 0.3 ? 1 : -1),
                App.getCurrentGame().getPlayer().getPosition().y + (float) Gdx.graphics.getHeight() / 2 * rand2);
            return position;
        }
    }

    public void updateEnemy(Enemy enemy) {
        if(enemy.getEnemyType() == EnemyType.TREE) {
            if(App.getCurrentGame().getPlayer().getPosition().dst(enemy.getPosition()) < 300)
                enemyAnimation(enemy);
        }
        else
            enemyAnimation(enemy);

        if(enemy.getCollisionRect().hasIntersect(App.getCurrentGame().getPlayer().getCollisionRect())) {
            if(!App.getCurrentGame().getPlayer().isInvincible()) {
                App.getCurrentGame().getPlayer().decreaseHealth(1);
                App.getCurrentGame().getPlayer().update(new Vector2(30, 30));
                App.getCurrentGame().getPlayer().setInvincible(true);
            }
        }

        switch (enemy.getEnemyType()) {
            case TENTACLE_MONSTER:
                handleTentacle(enemy);
                break;
            case EYEBAT:
                handleEyebat(enemy);
                break;
            case ELDER:
                handleElder(enemy);
                break;
            default:
                break;
        }

    }

    private void handleTentacle(Enemy enemy) {
        Vector2 direction = new Vector2(App.getCurrentGame().getPlayer().getPosition().x - enemy.getPosition().x,
            App.getCurrentGame().getPlayer().getPosition().y - enemy.getPosition().y).nor();

        enemy.update(direction);
    }

    private void handleEyebat(Enemy enemy) {
        Vector2 direction = new Vector2(App.getCurrentGame().getPlayer().getPosition().x - enemy.getPosition().x,
            App.getCurrentGame().getPlayer().getPosition().y - enemy.getPosition().y).nor();

        enemy.update(direction);

        float time = App.getCurrentGame().getCurrentTime();
        if(3 - time % 3 <= 0.017) {
            enemyBullets.add(new Bullet(App.getCurrentGame().getPlayer().getPosition(), enemy.getPosition(), 1,
                GameAssetManager.getGameAssetManager().getEnemyBullet()));
        }
    }

    private void handleElder(Enemy enemy) {
        //TODO
    }


    private void enemyAnimation(Enemy enemy) {
        Array<Texture> regions = new Array<>(enemy.getEnemyType().getTextures().size());
        for (int i = 0; i < enemy.getEnemyType().getTextures().size(); i++) {
            if(i != enemy.getEnemyType().getTextures().size() - 1)
                regions.add(enemy.getEnemyType().getTextures().get(i));
        }

        Animation<Texture> animation;
        if(enemy.getEnemyType() == EnemyType.TREE)
            animation = new Animation<>(0.5f, regions);
        else
            animation = new Animation<>(0.1f, regions);

        enemy.getSprite().setRegion(animation.getKeyFrame(enemy.getTime()));

        if(!animation.isAnimationFinished(enemy.getTime())) {
            enemy.setTime(enemy.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            enemy.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    private void handleEnemyBullets() {
        ArrayList<Bullet> tempBullets = new ArrayList<>();
        for(Bullet b : enemyBullets) {
            b.getSprite().draw(Main.getBatch());

            b.getSprite().setX(b.getSprite().getX() + b.getDirection().x * 3);
            b.getSprite().setY(b.getSprite().getY() + b.getDirection().y * 3);
            if(App.getCurrentGame().getPlayer().getCollisionRect().hasIntersect(
                new CollisionRect(b.getSprite().getX(), b.getSprite().getY(), b.getSprite().getWidth(), b.getSprite().getHeight())
            )) {
                App.getCurrentGame().getPlayer().decreaseHealth(1);
                tempBullets.add(b);
            }
        }

        for (Bullet b : tempBullets) {
            enemyBullets.remove(b);
        }
    }

    private boolean animation(Enemy enemy) {
        Array<Texture> regions = new Array<>(GameAssetManager.getGameAssetManager().getDeath().size());
        GameAssetManager.getGameAssetManager().getDeath().forEach(regions::add);
        Animation<Texture> animation = new Animation<>(0.1f, regions);

        enemy.getSprite().setRegion(animation.getKeyFrame(enemy.getKilledTime()));

        if(!animation.isAnimationFinished(enemy.getKilledTime())) {
            enemy.setKilledTime(enemy.getKilledTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            enemy.setKilledTime(0);
            return true;
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
        return false;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Enemy> getKilledEnemies() {
        return killedEnemies;
    }
}
