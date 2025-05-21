package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.View.GameView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.PreGameMenuView;
import com.tilldawn.View.SettingsMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;

    public PreGameMenuController() {
        // Constructor for PreGameMenuController
    }

    public void setView(PreGameMenuView view) {
        this.view = view;
    }

    public void handlePreGame() {
        if(view == null)
            return;

        if(view.getBackButton().isChecked()) {
            view.getBackButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getSettingsButton().isChecked()) {
            view.getSettingsButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SettingsMenuView(new SettingsMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getGameButton().isChecked()) {
            view.getGameButton().setChecked(false);
            Game game = new Game(
                App.getCurrentUser().getPreGame(),
                Integer.parseInt(view.getTimeSelectBox().getSelected()),
                new Player(
                    App.getCurrentUser().getAvatar(),
                    Weapon.valueOf(view.getWeaponSelectBox().getSelected())
                )
            );

            App.setCurrentGame(game);
            App.getCurrentUser().setGame(game);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
    }

}
