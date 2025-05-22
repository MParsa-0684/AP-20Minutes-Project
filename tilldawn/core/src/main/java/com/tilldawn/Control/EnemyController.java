package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Enemy;
import com.tilldawn.Model.EnemyType;

import java.util.ArrayList;
import java.util.Random;

public class EnemyController {
    private ArrayList<Enemy> enemies;
    private PlayerController playerController;

    public EnemyController(PlayerController playerController) {
        this.enemies = new ArrayList<>();
        this.playerController = playerController;

        int ctr = 0;
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
                    ctr++;
                    enemies.add(enemy);
                    break;
                }
            }
        }
//        System.out.println(ctr);
    }

    public void update() {
        for (Enemy enemy : enemies) {
            enemy.getSprite().draw(Main.getBatch());
            updateEnemy(enemy);
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

    }

    private void handleEyebat(Enemy enemy) {

    }

    private void handleElder(Enemy enemy) {

    }


    private void enemyAnimation(Enemy enemy) {
        Array<Texture> regions = new Array<>(enemy.getEnemyType().getTextures().size());
        enemy.getEnemyType().getTextures().forEach(regions::add);
        Animation<Texture> animation = new Animation<>(0.5f, regions);

        enemy.getSprite().setRegion(animation.getKeyFrame(enemy.getTime()));

        if(!animation.isAnimationFinished(enemy.getTime())) {
            enemy.setTime(enemy.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            enemy.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
