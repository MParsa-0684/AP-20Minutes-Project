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

    public void setView(GameView view) {
        this.view = view;
        worldController = new WorldController();
        playerController = new PlayerController();
        enemyController = new EnemyController();
        weaponController = new WeaponController();
    }

    public void updateGame() {
        if(view != null) {
            worldController.update();
            playerController.update();
            enemyController.update();
            weaponController.update();
        }
    }
}
