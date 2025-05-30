package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.GameView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.PauseMenuView;
import com.tilldawn.View.WinLoseView;

public class GameController {
    private Game game;
    private GameView view;
    private WorldController worldController;
    private PlayerController playerController;
    private EnemyController enemyController;
    private WeaponController weaponController;
    private SeedController seedController;
    private AbilityController abilityController;


    public void setView(GameView view) {
        this.game = view.getGame();
        this.view = view;
        worldController = new WorldController();
        playerController = new PlayerController();
        seedController = new SeedController();
        enemyController = new EnemyController(playerController);
        weaponController = new WeaponController(enemyController, seedController);
        abilityController = new AbilityController();
    }

    public void updateGame() {
        game.setCurrentTime(game.getCurrentTime() + Gdx.graphics.getDeltaTime());
        checkWinLose();

        if(view.getPauseButton().isChecked()) {
            view.getPauseButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PauseMenuView(new PauseMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }


        if(view != null) {
            worldController.update();
            playerController.update();
            weaponController.update();
            enemyController.update();
            seedController.update();
            abilityController.update();
        }
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    private void checkWinLose() {
        if(game.getPlayer().getHealth() <= 0) {
            if(App.getCurrentGame().getPreGame().isSfxMusic())
                GameAssetManager.getGameAssetManager().getPlayerKilled().play(1.0f);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new WinLoseView(new WinLoseController(), GameAssetManager.getGameAssetManager().getMenuSkin(), false));
            return;
        }
        if(game.getGameTime() * 60 - game.getCurrentTime() <= 0.017) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new WinLoseView(new WinLoseController(), GameAssetManager.getGameAssetManager().getMenuSkin(), true));
            return;
        }

    }

}
