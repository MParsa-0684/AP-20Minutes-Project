package com.tilldawn.Model;

public class Enemy {
    private EnemyType enemyType;

    public Enemy(EnemyType enemyType) {
        this.enemyType = enemyType;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }
}
