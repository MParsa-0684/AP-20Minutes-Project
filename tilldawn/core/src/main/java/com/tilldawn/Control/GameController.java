package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Game;
import com.tilldawn.View.GameView;

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
        weaponController = new WeaponController(enemyController);
        abilityController = new AbilityController();
    }

    public void updateGame() {
        game.setCurrentTime(game.getCurrentTime() + Gdx.graphics.getDeltaTime());

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
}
