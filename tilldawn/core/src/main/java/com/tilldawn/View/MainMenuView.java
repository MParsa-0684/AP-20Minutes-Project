package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;

public class MainMenuView implements Screen {
    private Stage stage;
    private MainMenuController controller;
    private final Label titleLabel;
    private final TextButton settingsButton;
    private final TextButton profileButton;
    private final TextButton preGameButton;
    private final TextButton scoreButton;
    private final TextButton hintButton;
    private final TextButton loadGameButton;
    private final TextButton exitButton;
    private final Label userNameLabel;
    private final Label scoreLabel;
    private final Label errorLabel;
    private Table table;


    public MainMenuView(MainMenuController controller, Skin skin) {
        this.table = new Table();
        this.controller = controller;
        this.titleLabel = new Label("Main Menu", skin);
        this.settingsButton = new TextButton("Settings", skin);
        this.profileButton = new TextButton("Profile", skin);
        this.preGameButton = new TextButton("Pre-Game", skin);
        this.scoreButton = new TextButton("Score Board", skin);
        this.hintButton = new TextButton("Hint Menu", skin);
        this.loadGameButton = new TextButton("Load Game", skin);
        this.exitButton = new TextButton("Exit Account", skin);
        this.userNameLabel = new Label("Username : " + App.getCurrentUser().getUsername(), skin);
        this.scoreLabel = new Label("Score : " + String.valueOf(App.getCurrentUser().getScore()), skin);
        this.errorLabel = new Label("", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        Table leftColumn = new Table();
        leftColumn.defaults().pad(10).fillX();

        leftColumn.add(settingsButton).row();
        leftColumn.add(profileButton).row();
        leftColumn.add(preGameButton).row();
        leftColumn.add(scoreButton).row();
        leftColumn.add(hintButton).row();

        Table rightColumn = new Table();
        rightColumn.defaults().pad(10).fillX();

        rightColumn.row();
        Sprite sprite = App.getCurrentUser().getCurrentSprite().getValue();
        Image avatarImage = new Image(new TextureRegionDrawable(new TextureRegion(sprite)));

        rightColumn.add(avatarImage).size(sprite.getWidth() * 2, sprite.getHeight() * 2).center().row();
        rightColumn.add(userNameLabel).row();
        rightColumn.add(scoreLabel).row();
        rightColumn.add(loadGameButton).row();
        rightColumn.add(exitButton).row();

        titleLabel.setFontScale(3);
        table.add(titleLabel).colspan(2).row();
        table.add(leftColumn).pad(20).top().left();
        table.add(rightColumn).pad(20).top().right();
        table.row();
        table.add(errorLabel).colspan(2);

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);
        Main.getBatch().begin();
        GameAssetManager.getGameAssetManager().getBackground().draw(Main.getBatch());
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleMainMenu();
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

    public Label getTitleLabel() {
        return titleLabel;
    }

    public TextButton getSettingsButton() {
        return settingsButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getPreGameButton() {
        return preGameButton;
    }

    public TextButton getScoreButton() {
        return scoreButton;
    }

    public TextButton getHintButton() {
        return hintButton;
    }

    public TextButton getLoadGameButton() {
        return loadGameButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    public Label getUserNameLabel() {
        return userNameLabel;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}
