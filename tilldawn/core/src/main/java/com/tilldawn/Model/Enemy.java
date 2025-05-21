package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.util.ArrayList;

public class Enemy {
    private EnemyType enemyType;
    private CollisionRect collisionRect;
    private int x;
    private int y;

    public Enemy(EnemyType enemyType, CollisionRect collisionRect, int x, int y) {
        this.enemyType = enemyType;
        this.collisionRect = collisionRect;
        this.x = x;
        this.y = y;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }
}
