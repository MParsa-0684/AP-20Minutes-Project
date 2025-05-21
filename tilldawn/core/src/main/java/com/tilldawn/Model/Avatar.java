package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public enum Avatar {

    SHANA("SHANA", GameAssetManager.getGameAssetManager().getShananaTextures(), 4, 4),
    DIAMOND("DIAMOND", GameAssetManager.getGameAssetManager().getDiamondTextures(), 7, 1),
    SCARLET("SCARLET", GameAssetManager.getGameAssetManager().getScarletTextures(), 3, 5),
    LILITH("LILITH", GameAssetManager.getGameAssetManager().getLilithTextures(), 5, 3),
    DASHER("DASHER", GameAssetManager.getGameAssetManager().getDasherTextures(), 2, 10),
    CUSTOM("CUSTOM", new ArrayList<>(), 4, 4);

    private String name;
    private ArrayList<ArrayList<Texture>> textures;
    private int HP;
    private int speed;

    Avatar(String name, ArrayList<ArrayList<Texture>> textures, int HP, int speed) {
        this.name = name;
        this.textures = textures;
        this.HP = HP;
        this.speed = speed;
    }

    public static Avatar getAvatar(String name) {
        for (Avatar value : Avatar.values()) {
            if(value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }

    public ArrayList<ArrayList<Sprite>> getSprites() {
        ArrayList<ArrayList<Sprite>> sprites = new ArrayList<>();
        for (ArrayList<Texture> texture : textures) {
            ArrayList<Sprite> sprite = new ArrayList<>();
            for (Texture t : texture) {
                sprite.add(new Sprite(t));
            }
            sprites.add(sprite);
        }
        return sprites;
    }

    public String getName() {
        return name;
    }

    public void setTextures(ArrayList<ArrayList<Texture>> textures) {
        this.textures = textures;
    }

    @Override
    public String toString() {
        return name;
    }

    public ArrayList<ArrayList<Texture>> getTextures() {
        return textures;
    }
}
