package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.WinLoseController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;

public class WinLoseView implements Screen {
    private Stage stage;
    private final Label winLoseLabel;
    private final Label timeSurvivedTitleLabel;
    private final Label killedNumberTitleLabel;
    private final Label levelNumberTitleLabel;
    private final Label scoreTitleLabel;
    private final Label timeSurvivedLabel;
    private final Label killedNumberLabel;
    private final Label levelNumberLabel;
    private final Label scoreLabel;
    private final TextButton tryAgainButton;
    private final TextButton mainMenuButton;
    private Window winLoseWindow;
    private WinLoseController controller;
    private Skin skin;
    private int score;
    private float timeSurvived;


    public WinLoseView(WinLoseController controller, Skin skin, Boolean win) {
        this.controller = controller;
        this.skin = skin;
        this.winLoseWindow = new Window("", skin);
        winLoseWindow.setModal(true);
        winLoseWindow.setMovable(false);
        winLoseWindow.defaults().pad(8).fillX();

        this.winLoseLabel = new Label((win ? "YOU WON " : "YOU LOST ") + App.getCurrentUser().getUsername() + "!", skin);
        this.winLoseLabel.setFontScale(2f);
        this.timeSurvivedTitleLabel = new Label("Time Survived:", skin);
        this.timeSurvivedTitleLabel.setColor(124,153,118, 1);
        this.timeSurvivedLabel = new Label(String.format("%02d:%02d",
            ((int) App.getCurrentGame().getCurrentTime() / 60),
            ((int) App.getCurrentGame().getCurrentTime() % 60)), skin);
        this.timeSurvivedLabel.setColor(244,234,211, 1);

        this.killedNumberTitleLabel = new Label("Enemies Killed", skin);
        this.killedNumberTitleLabel.setColor(124,153,118, 1);
        this.killedNumberLabel = new Label(String.valueOf(App.getCurrentGame().getPlayer().getKilled()), skin);
        this.killedNumberLabel.setColor(244,234,211, 1);

        this.levelNumberTitleLabel = new Label("Levels Earned", skin);
        this.levelNumberTitleLabel.setColor(124,153,118, 1);
        this.levelNumberLabel = new Label(String.valueOf(App.getCurrentGame().getPlayer().getLevel()), skin);
        this.levelNumberLabel.setColor(244,234,211, 1);

        score = ((int) App.getCurrentGame().getCurrentTime()) * App.getCurrentGame().getPlayer().getKilled();
        timeSurvived = App.getCurrentGame().getCurrentTime();

        this.scoreTitleLabel = new Label("Score Earned", skin);
        this.scoreLabel = new Label(String.valueOf(score), skin);

        this.tryAgainButton = new TextButton("Try Again", skin);
        this.mainMenuButton = new TextButton("Main Menu", skin);

        winLoseWindow.add(winLoseLabel).
            center().colspan(2).expandX().center().padBottom(10).row();

        winLoseWindow.add(timeSurvivedTitleLabel).left();
        winLoseWindow.add(timeSurvivedLabel)     .right();
        winLoseWindow.row();

        winLoseWindow.add(killedNumberTitleLabel).left();
        winLoseWindow.add(killedNumberLabel)     .right();
        winLoseWindow.row();

        winLoseWindow.add(levelNumberTitleLabel).left();
        winLoseWindow.add(levelNumberLabel)     .right();
        winLoseWindow.row();

        winLoseWindow.add().colspan(2).height(1).fillX().padTop(10).row();

        winLoseWindow.add(scoreTitleLabel).left();
        winLoseWindow.add(scoreLabel)       .right();
        winLoseWindow.row();

        winLoseWindow.add(tryAgainButton)
            .colspan(2)
            .fillX()
            .padTop(20)
            .row();

        winLoseWindow.add(mainMenuButton)
            .colspan(2)
            .fillX()
            .padTop(5)
            .row();

        winLoseWindow.pack();
        winLoseWindow.setPosition(
            (Gdx.graphics.getWidth()  - winLoseWindow.getWidth())  / 2,
            (Gdx.graphics.getHeight() - winLoseWindow.getHeight()) / 2
        );

        this.controller.setView(this);

    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        stage.addActor(winLoseWindow);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleWinLose();
    }

    @Override
    public void resize(int width, int height) {

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

    public TextButton getTryAgainButton() {
        return tryAgainButton;
    }

    public TextButton getMainMenuButton() {
        return mainMenuButton;
    }

    public int getScore() {
        return score;
    }

    public float getTimeSurvived() {
        return timeSurvived;
    }
}
