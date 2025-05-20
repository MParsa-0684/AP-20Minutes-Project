package com.tilldawn.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class App {
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
}
