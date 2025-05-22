package com.tilldawn.Model;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;

public class PreGame {
    private boolean sfxMusic;
    private String gameKeys;
    private boolean autoReload;
    private Color gameColor;

    public PreGame() {
        sfxMusic = true;
        gameKeys = "w-a-s-d";
        autoReload = false;
        gameColor = Color.WHITE;
    }

    public boolean isSfxMusic() {
        return sfxMusic;
    }

    public void setSfxMusic(boolean sfxMusic) {
        this.sfxMusic = sfxMusic;
    }

    public String getGameKeys() {
        return gameKeys;
    }

    public void setGameKeys(String gameKeys) {
        this.gameKeys = gameKeys;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public Color getGameColor() {
        return gameColor;
    }

    public void setGameColor(Color gameColor) {
        this.gameColor = gameColor;
    }


}
