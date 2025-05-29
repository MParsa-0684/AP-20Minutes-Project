package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.HintTalentMenuView;
import com.tilldawn.View.MainMenuView;

public class HintTalentMenuController {
    private HintTalentMenuView view;

    public HintTalentMenuController() {
        // Constructor for HintTalentMenuController
    }

    public void setView(HintTalentMenuView hintTalentMenuView) {
        this.view = hintTalentMenuView;
    }

    public void handleHintTalent() {
        if(view.getBackButton().isChecked()) {
            view.getBackButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
    }
}
