package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class Enemy {
    private EnemyType enemyType;
    private CollisionRect collisionRect;
    Vector2 position;;

    public Enemy(EnemyType enemyType, CollisionRect collisionRect, Vector2 position) {
        this.enemyType = enemyType;
        this.collisionRect = collisionRect;
        this.position = position.cpy();
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public Vector2 getPosition() {
        return position;
    }
}
