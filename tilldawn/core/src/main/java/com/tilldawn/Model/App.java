package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.tilldawn.View.GameView;

import java.util.ArrayList;
import java.util.Arrays;

public class App {
    private static Music backgroundMusic;
    private static final ArrayList<User> users = new ArrayList<>();
    private static User currentUser = null;
    private static GameView currentGameView = null;


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static Game getCurrentGame() {
        return currentGameView.getGame();
    }

    public static void setCurrentGame(GameView currentGameView) {
        App.currentGameView = currentGameView;
    }

    public static Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public static void setBackgroundMusic(Music backgroundMusic) {
        App.backgroundMusic = backgroundMusic;
    }
}
