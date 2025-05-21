package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Arrays;

public enum Weapon {
    RESOLVER("Revolver", 20, 1, 1, 6,
            GameAssetManager.getGameAssetManager().getRevolver()),
    SHOTGUN("Shotgun", 10, 4, 1, 2,
            GameAssetManager.getGameAssetManager().getShotgun()),
    SMG_DUAL("SMGs Dual", 8, 1, 2, 24,
            GameAssetManager.getGameAssetManager().getSmgDual()),;
;
    private final String name;
    private final int damage;
    private final int projectile;
    private final int timeReload;
    private final int ammoMax;
    private final ArrayList<Texture> textures;


    Weapon(String name, int damage, int projectile, int timeReload, int ammoMax, ArrayList<Texture> textures) {
        this.name = name;
        this.damage = damage;
        this.projectile = projectile;
        this.timeReload = timeReload;
        this.ammoMax = ammoMax;
        this.textures = textures;
    }


    private ArrayList<Sprite> getSprites() {
        ArrayList<Sprite> sprites = new ArrayList<>();
        for (Texture texture : textures) {
            sprites.add(new Sprite(texture));
        }
        return sprites;
    }


}
