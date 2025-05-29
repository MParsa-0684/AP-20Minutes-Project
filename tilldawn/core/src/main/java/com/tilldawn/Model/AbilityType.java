package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public enum AbilityType {
    VITALITY("VITALITY", GameAssetManager.getGameAssetManager().getAbilities().get(0), "HP increase by 1"),
    DAMAGER("DAMAGER", GameAssetManager.getGameAssetManager().getAbilities().get(1), "25% Weapon Damage within 10 seconds"),
    PROCREASE("PROCREASE", GameAssetManager.getGameAssetManager().getAbilities().get(2), "Projectile increase by 1"),
    AMOCREASE("AMOCREASE", GameAssetManager.getGameAssetManager().getAbilities().get(3), "Max ammo increase by 5"),
    SPEEDY("SPEEDY", GameAssetManager.getGameAssetManager().getAbilities().get(4), "Double Speed within 10 seconds");

    private final String name;
    private final Texture texture;
    private final Sprite sprite;
    private final String properties;

    AbilityType(String name, Texture texture, String properties) {
        this.name = name;
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public Texture getTexture() {
        return texture;
    }

    public String getProperties() {
        return properties;
    }
}
