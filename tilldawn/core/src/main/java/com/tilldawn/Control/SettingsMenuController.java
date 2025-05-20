package com.tilldawn.Control;

import com.tilldawn.View.SettingsMenuView;

public class SettingsMenuController {
    private SettingsMenuView view;

    public SettingsMenuController() {
        // Constructor for SettingsMenuController
    }

    public void setView(SettingsMenuView view) {
        this.view = view;
    }

    public void handleSettings() {
        if(view == null)
            return;


    }
}
