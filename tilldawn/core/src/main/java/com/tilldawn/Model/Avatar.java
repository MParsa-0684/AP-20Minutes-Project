package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class Avatar {
    private String name;
    private ArrayList<ArrayList<Texture>> textures;
    private ArrayList<ArrayList<Sprite>> sprites;
    private int HP;
    private int speed;

    public Avatar(String name, ArrayList<ArrayList<Texture>> textures, int HP, int speed) {
        this.name = name;
        this.textures = textures;
        this.HP = HP;
        this.speed = speed;
        this.sprites = getTextureSprites(textures);
    }

    private ArrayList<ArrayList<Sprite>> getTextureSprites(ArrayList<ArrayList<Texture>> textures) {
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

    public ArrayList<ArrayList<Sprite>> getSprites() {
        return sprites;
    }
}
