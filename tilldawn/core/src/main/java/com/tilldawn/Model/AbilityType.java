package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public enum AbilityType {
    VITALITY("VITALITY", GameAssetManager.getGameAssetManager().getAbilities().get(0)),
    DAMAGER("DAMAGER", GameAssetManager.getGameAssetManager().getAbilities().get(1)),
    PROCREASE("PROCREASE", GameAssetManager.getGameAssetManager().getAbilities().get(2)),
    AMOCREASE("AMOCREASE", GameAssetManager.getGameAssetManager().getAbilities().get(3)),
    SPEEDY("SPEEDY", GameAssetManager.getGameAssetManager().getAbilities().get(4));

    private final String name;
    private final Texture texture;
    private final Sprite sprite;

    AbilityType(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
        this.sprite = new Sprite(texture);
    }

    public String getName() {
        return name;
    }

    public Texture getTexture() {
        return texture;
    }
}
