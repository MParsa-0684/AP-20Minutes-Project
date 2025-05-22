package com.tilldawn.Model;

import com.badlogic.gdx.audio.Music;
import com.tilldawn.View.GameView;

public class Game {
    private World world;
    private PreGame preGame;
    private float gameTime;
    private Player player;
    private float currentTime;


    public Game(PreGame preGame, int gameTime, Player player) {
        this.preGame = preGame;
        this.gameTime = gameTime;
        this.player = player;
        this.world = new World();
        this.currentTime = 0.0f;
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

}
