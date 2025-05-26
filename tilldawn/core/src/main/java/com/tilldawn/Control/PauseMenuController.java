package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.PauseMenuView;
import com.tilldawn.View.WinLoseView;

public class PauseMenuController{
    private PauseMenuView view;

    public void setView(PauseMenuView view) {
        this.view = view;
    }

    public void handlePause() {
        if(view == null)
            return;

        App.getCurrentGame().getPreGame().
            setGameColor((view.getGameThemeCheckBox().isChecked()) ? Color.BLACK : Color.WHITE);

        if(view.getResumeButton().isChecked()) {
            view.getResumeButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(App.getCurrentUser().getGameView());
        }
        else if(view.getQuitButton().isChecked() || view.getSaveQuitButton().isChecked()) {
            float volume = App.getBackgroundMusic().getVolume();
            if(App.getBackgroundMusic() != null)
                App.getBackgroundMusic().dispose();


            if(view.getSaveQuitButton().isChecked()) {
                App.setBackgroundMusic(Gdx.audio.newMusic(Gdx.files.internal("AudioClip/Pretty Dungeon LOOP.wav")));
                App.getBackgroundMusic().setLooping(true);
                App.getBackgroundMusic().setVolume(volume);
                App.getBackgroundMusic().play();

                view.getSaveQuitButton().setChecked(false);
                App.setCurrentGame(null);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
            }
            else if(view.getQuitButton().isChecked()) {
                view.getQuitButton().setChecked(false);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new WinLoseView(new WinLoseController(), GameAssetManager.getGameAssetManager().getMenuSkin(), false));
            }

        }

    }
}
