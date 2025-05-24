package com.tilldawn.View;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.Ability;
import com.tilldawn.Model.AbilityType;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Game;


public class GameView implements Screen, InputProcessor {
    private final com.tilldawn.Model.Game game;
    private final GameController controller;
    private Stage stage;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Label healthLabel;
    private final Label levelLabel;
    private final Label xpLabel;
    private final Label ammoLabel;
    private final Label timeLabel;
    private final TextButton pauseButton;
    private final ProgressBar levelProgressBar;
    private Table right;
    private final Skin skin;

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
        levelProgressBar = new ProgressBar(0, App.getCurrentGame().getPlayer().getLevel() * 20, 3, false, skin);
        this.skin = skin;

        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
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

        root.add(levelProgressBar)
            .colspan(2)
            .growX()
            .height(20)
            .pad(5)
            .row();

        Table left = new Table(skin);
        left.defaults().pad(5).left();
        left.add(healthLabel).row();
        left.add(levelLabel) .row();
        left.add(xpLabel)    .row();
        left.add(ammoLabel)  .row();
        left.add(timeLabel)  .row();
        left.add(pauseButton);

        right = new Table(skin);
        abilityShow();
        root.add(left).expandY().top().left();
        root.add(right).expandY().top().right();
    }

    private void abilityShow() {
        right.defaults().pad(4);
        for (AbilityType value : AbilityType.values()) {
            for (Ability ability : App.getCurrentGame().getPlayer().getAbilities().get(value)) {
                Image icon = new Image(new TextureRegionDrawable(new TextureRegion(ability.getType().getTexture())));
                right.add(icon).size(45);
            }
            right.row();
        }
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

        right.clear();
        abilityShow();

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

    public ProgressBar getLevelProgressBar() {
        return levelProgressBar;
    }
}
