package com.tilldawn.View;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.Ability;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Game;

import java.util.ArrayList;

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
    private ProgressBar levelProgressBar;
    private ArrayList<Texture> abilities;
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
        levelProgressBar = new ProgressBar(0, App.getCurrentGame().getPlayer().getLevel() * 20, 1, false, skin);
        this.skin = skin;

        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
    }

    @Override
    public void show() {
        stage = new Stage();
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        Table root = new Table();
        root.setFillParent(true);
        root.top().padTop(10);
        stage.addActor(root);

        // 2) add the progress bar spanning both columns, centered
        root.add(levelProgressBar)
            .colspan(2)
            .expandX()
            .left()
            .padBottom(20);
        root.row();

        // 3) build left‐column table (stats + pause button)
        Table left = new Table(skin);
        left.defaults().pad(5).left();
        left.add(healthLabel).row();
        left.add(levelLabel) .row();
        left.add(xpLabel)    .row();
        left.add(ammoLabel)  .row();
        left.add(timeLabel)  .row();
        left.add(pauseButton);

        // 4) build right‐column table (ability icons)
        Table right = new Table(skin);
        right.defaults().pad(4);
        for (Ability ability : App.getCurrentGame().getPlayer().getAbilities()) {
            Image icon = new Image(new TextureRegionDrawable(new TextureRegion(ability.getTexture())));
            right.add(icon).size(32).row();    // one per row; use .row() or remove if you want in a grid
        }

        // 5) add both to the root: left on left, right on right
        root.add(left).expandY().top().left();
        root.add(right).expandY().top().right();
    }

    private void updateCamera() {
        Vector2 p = App.getCurrentGame().getPlayer().getPosition();
        camera.position.set(p.x, p.y, 0);
        camera.update();

    }

    @Override
    public void render(float v) {
//        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);



        Main.getBatch().begin();
        updateCamera();
        Main.getBatch().setProjectionMatrix(camera.combined);

        controller.updateGame();


        Main.getBatch().end();



        healthLabel.setText("HP : " + App.getCurrentGame().getPlayer().getHealth());
        levelLabel.setText("Level : " + App.getCurrentGame().getPlayer().getLevel());
        xpLabel.setText("XP : " + App.getCurrentGame().getPlayer().getXp());
        ammoLabel.setText("Ammo : " + App.getCurrentGame().getPlayer().getWeapon().getAmmo());
        timeLabel.setText("Time : " + String.format("%02d:%02d",
            ((int) App.getCurrentGame().getCurrentTime() / 60),
            ((int) App.getCurrentGame().getCurrentTime() % 60)));
        int currentLevelXP = 10 * (App.getCurrentGame().getPlayer().getLevel() - 1) * App.getCurrentGame().getPlayer().getLevel();
        levelProgressBar.setValue(App.getCurrentGame().getPlayer().getXp() - currentLevelXP);
//        levelProgressBar.

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
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

    public TextButton getPauseButton() {
        return pauseButton;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
