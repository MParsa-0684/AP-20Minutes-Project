package com.tilldawn.Model;

import com.badlogic.gdx.audio.Music;

public class Game {
    private World world;
    private PreGame preGame;
    private int gameTime;
    private Player player;

    public Game(PreGame preGame, int gameTime, Player player) {
        this.preGame = preGame;
        this.gameTime = gameTime;
        this.player = player;
        this.world = new World();
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

    public World getWorld() {
        return world;
    }
}
