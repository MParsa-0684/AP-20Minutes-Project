package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.View.GameView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.WinLoseView;

public class WinLoseController {
    private WinLoseView view;

    public void setView(WinLoseView view) {
        this.view = view;
    }

    public void handleWinLose() {
        if(view.getMainMenuButton().isChecked()) {
            view.getMainMenuButton().setVisible(false);

            float volume = App.getBackgroundMusic().getVolume();
            if(App.getBackgroundMusic() != null)
                App.getBackgroundMusic().dispose();
            App.setBackgroundMusic(Gdx.audio.newMusic(Gdx.files.internal("AudioClip/Pretty Dungeon LOOP.wav")));
            App.getBackgroundMusic().setLooping(true);
            App.getBackgroundMusic().setVolume(volume);
            App.getBackgroundMusic().play();

            App.getCurrentGame().getPlayer().getSprite().setSize(App.getCurrentGame().getPlayer().getSprite().getWidth() / 2.5f,
                App.getCurrentGame().getPlayer().getSprite().getHeight() / 2.5f);

            App.getCurrentUser().setGameView(null);
            App.setCurrentGame(null);
            App.getCurrentUser().increaseScore(view.getScore());
            App.getCurrentUser().updateMaxTimeSpent((int) view.getTimeSurvived());

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getTryAgainButton().isChecked()) {
            Game game = new Game(
                App.getCurrentUser().getPreGame().copy(),
                (int) App.getCurrentGame().getGameTime(),
                new Player(
                    App.getCurrentUser().getAvatar(),
                    new Weapon(App.getCurrentGame().getPlayer().getWeapon().getType())
                )
            );

            float volume = App.getBackgroundMusic().getVolume();
            if(App.getBackgroundMusic() != null)
                App.getBackgroundMusic().dispose();
            App.setBackgroundMusic(Gdx.audio.newMusic(Gdx.files.internal("AudioClip/Pretty Dungeon LOOP.wav")));
            App.getBackgroundMusic().setLooping(true);
            App.getBackgroundMusic().setVolume(volume);
            App.getBackgroundMusic().play();

            App.getCurrentGame().getPlayer().getSprite().setSize(App.getCurrentGame().getPlayer().getSprite().getWidth() / 2.5f,
                App.getCurrentGame().getPlayer().getSprite().getHeight() / 2.5f);

            App.getCurrentUser().setGameView(null);
            App.setCurrentGame(null);
            App.getCurrentUser().increaseScore(view.getScore());
            App.getCurrentUser().updateMaxTimeSpent((int) view.getTimeSurvived());

            volume = App.getBackgroundMusic().getVolume();
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
