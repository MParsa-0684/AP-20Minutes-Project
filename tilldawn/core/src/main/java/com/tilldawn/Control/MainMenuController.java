package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.*;

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

        if(view.getSettingsButton().isChecked()) {
            view.getSettingsButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SettingsMenuView(new SettingsMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getProfileButton().isChecked()) {
            view.getProfileButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getPreGameButton().isChecked()) {
            view.getPreGameButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getScoreButton().isChecked()) {
            view.getScoreButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ScoreboardMenuView(new ScoreboardMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getHintButton().isChecked()) {
            view.getHintButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new HintTalentMenuView(new HintTalentMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getLoadGameButton().isChecked()) {
            view.getHintButton().setChecked(false);
            if(App.getCurrentUser().getGameView() != null) {
                App.setCurrentGame(App.getCurrentUser().getGameView());
                Main.getMain().getScreen().dispose();
//                Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getMenuSkin(),
//                    App.getCurrentUser().getGameView()));
            }
            else {
                view.getErrorLabel().setText("You don't have any saved game!");
            }
        }
        else if(view.getExitButton().isChecked()) {
            view.getExitButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SignUpMenuView(new SignUpMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
    }
}
