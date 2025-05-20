package com.tilldawn.Control;

import com.tilldawn.View.MainMenuView;

public class MainMenuController {
    private MainMenuView view;

    public MainMenuController() {
        // Constructor for MainMenuController
    }

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenu() {
        if(view == null)
            return;


    }
}
