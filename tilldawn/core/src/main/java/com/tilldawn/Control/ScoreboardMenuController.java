package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.PreGameMenuView;
import com.tilldawn.View.ScoreboardMenuView;

public class ScoreboardMenuController {
    private ScoreboardMenuView view;

    public ScoreboardMenuController() {
        // Constructor for ScoreboardMenuController
    }

    public void setView(ScoreboardMenuView view) {
        this.view = view;
    }

    public void handleScoreBoard() {
        if(view == null)
            return;

        if(view.getBackButton().isChecked()) {
            view.getBackButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
    }
}
