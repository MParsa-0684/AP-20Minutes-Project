package com.tilldawn.View;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Game;

public class GameView implements Screen, InputProcessor {
    private com.tilldawn.Model.Game game;
    private GameController controller;
    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Label healthLabel;
    private Label levelLabel;
    private Label xpLabel;
    private Label ammoLabel;
    private Label timeLabel;
    private TextButton pauseButton;
    private Skin skin;

    public GameView(Skin skin, Game game) {

        this.game = game;
        App.getCurrentUser().setGameView(this);
        App.setCurrentGame(this);
        this.controller = new GameController();
        controller.setView(this);

        healthLabel = new Label("HP : " + App.getCurrentGame().getPlayer().getHealth(), skin);
        levelLabel = new Label("Level : " + App.getCurrentGame().getPlayer().getLevel(), skin);
        xpLabel = new Label("XP : " + App.getCurrentGame().getPlayer().getXp(), skin);
        ammoLabel = new Label("Ammo : " + App.getCurrentGame().getPlayer().getWeapon().getAmmo(), skin);
        timeLabel = new Label("Time : " + String.format("%02d:%02d",
            ((int) App.getCurrentGame().getCurrentTime() / 60),
            ((int) App.getCurrentGame().getCurrentTime() % 60)), skin);
        pauseButton = new TextButton("Pause", skin);
        this.skin = skin;

        camera = new OrthographicCamera();
        viewport = new FitViewport(7000, 5000, camera);
    }

    @Override
    public void show() {
        stage = new Stage();
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        Table ui = new Table();
        ui.setFillParent(true);
        ui.top().left().pad(10);
        ui.defaults().pad(5).space(5);
        stage.addActor(ui);

        ui.add(healthLabel).left().row();
        ui.add(levelLabel ).left().row();
        ui.add(xpLabel).left().row();
        ui.add(ammoLabel  ).left().row();
        ui.add(timeLabel  ).left().row();
        ui.add(pauseButton).left();
    }

    private void updateCamera() {
        Vector2 p = App.getCurrentGame().getPlayer().getPosition();
        camera.position.set(p.x, p.y, 0);
//        Main.getBatch().setProjectionMatrix(camera.combined);
        camera.update();

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);

        updateCamera();
        Main.getBatch().begin();
        controller.updateGame();
        Main.getBatch().end();

        healthLabel.setText("HP : " + App.getCurrentGame().getPlayer().getHealth());
        levelLabel.setText("Level : " + App.getCurrentGame().getPlayer().getLevel());
        xpLabel.setText("XP : " + App.getCurrentGame().getPlayer().getXp());
        ammoLabel.setText("Ammo : " + App.getCurrentGame().getPlayer().getWeapon().getAmmo());
        timeLabel.setText("Time : " + String.format("%02d:%02d",
            ((int) App.getCurrentGame().getCurrentTime() / 60),
            ((int) App.getCurrentGame().getCurrentTime() % 60)));

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }


    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        controller.getWeaponController().handleWeaponShoot(i, i1);
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    public Stage getStage() {
        return stage;
    }

    public Game getGame() {
        return game;
    }
}
