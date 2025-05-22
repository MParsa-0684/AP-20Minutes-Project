package com.tilldawn.Control;

import com.badlogic.gdx.graphics.Color;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.MainMenuView;
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

        App.getCurrentUser().getPreGame().setSfxMusic(view.getSfxButton().isChecked());
        App.getCurrentUser().getPreGame().setAutoReload(view.getAutoReloadCheckBox().isChecked());
        App.getCurrentUser().getPreGame().setGameColor((view.getGameThemeCheckBox().isChecked()) ? Color.BLACK : Color.WHITE);
        App.getCurrentUser().getPreGame().setGameKeys(view.getGameKeysButtons().getText());

        if(view.getBackButton().isChecked()) {
            view.getBackButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getVolumeSlider().isDragging()) {
            App.getBackgroundMusic().setVolume(view.getVolumeSlider().getValue());
        }

    }
}
