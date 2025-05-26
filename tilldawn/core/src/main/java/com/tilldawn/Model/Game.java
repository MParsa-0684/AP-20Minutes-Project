package com.tilldawn.Model;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.tilldawn.View.GameView;

import static jdk.internal.org.jline.terminal.spi.SystemStream.Input;

public class Game {
    private World world;
    private PreGame preGame;
    private float gameTime;
    private Player player;
    private float currentTime;
    private int[] keys;


    public Game(PreGame preGame, int gameTime, Player player) {
        this.preGame = preGame;
        this.gameTime = gameTime;
        this.player = player;
        this.world = new World();
        this.currentTime = 0.0f;
        this.keys = new int[4];
        int[] defaultKeys = new int[] {
            com.badlogic.gdx.Input.Keys.W,
            com.badlogic.gdx.Input.Keys.A,
            com.badlogic.gdx.Input.Keys.S,
            com.badlogic.gdx.Input.Keys.D
        };
        String[] inputStrings = this.preGame.getGameKeys().split("-");
        for (int i = 0; i < inputStrings.length; i++) {
            try {
                keys[i] = com.badlogic.gdx.Input.Keys.class.getField(inputStrings[i].toUpperCase()).getInt(null);
            }
            catch (Exception e) {
                keys[i] = defaultKeys[i];
            }
        }
    }

    public PreGame getPreGame() {
        return preGame;
    }

    public float getGameTime() {
        return gameTime;
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(float currentTime) {
        this.currentTime = currentTime;
    }

    public int[] getKeys() {
        return keys;
    }
}
