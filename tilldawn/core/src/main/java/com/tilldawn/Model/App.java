package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;
import java.util.Arrays;

public class App {
    private static Music backgroundMusic;
    private static final ArrayList<User> users = new ArrayList<>();
    private static User currentUser = null;
    private static Game currentGame = null;


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
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }

    public static Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public static void setBackgroundMusic(Music backgroundMusic) {
        App.backgroundMusic = backgroundMusic;
    }
}
