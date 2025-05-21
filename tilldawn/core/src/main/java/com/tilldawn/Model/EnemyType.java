package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public enum EnemyType {
    TREE("Tree", 0, 1, 0,
        GameAssetManager.getGameAssetManager().getTree()),
    TENTACLE_MONSTER("Tentacle Monster", 0, 25, 3,
        GameAssetManager.getGameAssetManager().getTentacle_monster()),
    EYEBAT("Eyebat", 0, 50, 3,
        GameAssetManager.getGameAssetManager().getEyebat()),
    ELDER("Elder", 0, 400, 5,
        GameAssetManager.getGameAssetManager().getElder());

    private String name;
    private int triggerTime;
    private int HP;
    private int spawnRate;
    private ArrayList<Texture> Textures;

    EnemyType(String name, int triggerTime, int HP, int spawnRate, ArrayList<Texture> textures) {
        this.name = name;
        this.triggerTime = triggerTime;
        this.HP = HP;
        this.spawnRate = spawnRate;
        Textures = textures;
    }
}
