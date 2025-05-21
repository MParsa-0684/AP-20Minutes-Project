package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public enum Avatars {
    SHANA(new Avatar("SHANAN", GameAssetManager.getGameAssetManager().getShananaTextures(), 4, 4)),
    DIAMOND(new Avatar("DIAMOND", GameAssetManager.getGameAssetManager().getDiamondTextures(), 7, 1)),
    SCARLET(new Avatar("SCARLET", GameAssetManager.getGameAssetManager().getScarletTextures(), 3, 5)),
    LILITH(new Avatar("LILITH", GameAssetManager.getGameAssetManager().getLilithTextures(), 5, 3)),
    DASHER(new Avatar("DASHER", GameAssetManager.getGameAssetManager().getDasherTextures(), 2, 10));

    private Avatar avatar;

    Avatars(Avatar avatar) {
        this.avatar = avatar;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public static Avatar getAvatar(String name) {
        for (Avatars value : Avatars.values()) {
            if(value.getAvatar().getName().equals(name)) {
                return value.getAvatar();
            }
        }
        return null;
    }
}
