package com.tilldawn.Model;

import com.badlogic.gdx.audio.Music;

public class Game {
    private PreGame preGame;
    private int gameTime;
    private Player player;

    public Game(PreGame preGame, int gameTime, Player player) {
        this.preGame = preGame;
        this.gameTime = gameTime;
        this.player = player;
    }

    public PreGame getPreGame() {
        return preGame;
    }

    public int getGameTime() {
        return gameTime;
    }

    public Player getPlayer() {
        return player;
    }
}
