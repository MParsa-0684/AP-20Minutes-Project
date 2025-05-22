package com.tilldawn.Control;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.tilldawn.View.GameView;

public class GameController {
    private GameView view;
    private WorldController worldController;
    private PlayerController playerController;
    private EnemyController enemyController;
    private WeaponController weaponController;
    private SeedController seedController;
    private AbilityController abilityController;

    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController();
        worldController = new WorldController(playerController);
        enemyController = new EnemyController();
        weaponController = new WeaponController(enemyController);
        seedController = new SeedController();
        abilityController = new AbilityController();
    }

    public void updateGame() {
        if(view != null) {
            worldController.update();
            playerController.update();
            enemyController.update();
            weaponController.update();
            seedController.update();
            abilityController.update();
        }
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }
}
