package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
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
                App.getCurrentUser().getPreGame().copy(),
                Integer.parseInt(view.getTimeSelectBox().getSelected()),
                new Player(
                    App.getCurrentUser().getAvatar(),
                    new Weapon(WeaponType.valueOf(view.getWeaponSelectBox().getSelected()))
                )
            );

            float volume = App.getBackgroundMusic().getVolume();
            if(App.getBackgroundMusic() != null)
                App.getBackgroundMusic().dispose();
            App.setBackgroundMusic(Gdx.audio.newMusic(Gdx.files.internal("AudioClip/Wasteland Combat Loop.wav")));
            App.getBackgroundMusic().setLooping(true);
            App.getBackgroundMusic().setVolume(volume);
            App.getBackgroundMusic().play();

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(GameAssetManager.getGameAssetManager().getMenuSkin(), game));
        }
    }


}
