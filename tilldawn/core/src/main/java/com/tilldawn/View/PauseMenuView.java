package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tilldawn.Control.PauseMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.Ability;
import com.tilldawn.Model.App;

import java.util.ArrayList;

public class PauseMenuView implements Screen {
    private Stage stage;
    private Label titleLabel;
    private Window pauseWindow;
    private PauseMenuController controller;
    private TextButton resumeButton;
    private Label cheatLabel;
    private Label abilitiesLabel;
    private ScrollPane cheatScrollPane;
    private ScrollPane abilitiesScrollPane;
    private CheckBox gameThemeCheckBox;
    private TextButton quitButton;
    private TextButton saveQuitButton;

    public PauseMenuView(PauseMenuController controller, Skin skin) {
        this.controller = controller;
        this.pauseWindow = new Window("", skin);
        pauseWindow.setModal(true);
        pauseWindow.setMovable(false);
        pauseWindow.defaults().pad(8).fillX();

        this.titleLabel = new Label("Game Paused", skin);
        pauseWindow.add(titleLabel).center().colspan(2).expandX().center().padBottom(10).row();
        resumeButton = new TextButton("Resume", skin);
        pauseWindow.add(resumeButton).row();
        cheatLabel = new Label("", skin); //TODO
        pauseWindow.add(cheatLabel).row();
        abilitiesLabel = new Label("", skin); //TODO
        pauseWindow.add(cheatLabel).row();

        List<String> cheatList = new List<>(skin);
        cheatList.setItems("GODMODE","INFINITE_AMMO","NOCLIP"); // example
        this.cheatScrollPane = new ScrollPane(cheatList, skin);
        pauseWindow.add(cheatLabel).left().colspan(2).row();
        pauseWindow.add(cheatScrollPane).colspan(2).height(100).row();

        this.abilitiesLabel = new Label("All Abilities Gained", skin);
        Table allAbilTable = new Table(skin);
        allAbilTable.defaults().pad(4).size(50, 50);
        App.getCurrentGame().getPlayer().getAbilities().forEach((key, value) -> {
            for (Ability ability : value) {
                Sprite sprite = new Sprite(ability.getType().getTexture());
                ImageButton icon = new ImageButton(new TextureRegionDrawable(sprite));
                allAbilTable.add(icon);
            }
            allAbilTable.row();
        });

        this.abilitiesScrollPane = new ScrollPane(allAbilTable, skin);
        pauseWindow.add(this.abilitiesLabel).left().colspan(2).row();
        pauseWindow.add(this.abilitiesScrollPane).colspan(2).height(100).row();

        this.gameThemeCheckBox = new CheckBox("Black & White Mode", skin);
        if(App.getCurrentUser().getPreGame().getGameColor() == Color.BLACK)
            gameThemeCheckBox.setChecked(true);
        pauseWindow.add(gameThemeCheckBox).row();

        this.quitButton = new TextButton("Quit", skin);
        pauseWindow.add(quitButton).colspan(2).row();

        this.saveQuitButton = new TextButton("Save&Quit", skin);
        pauseWindow.add(saveQuitButton).colspan(2).row();

        pauseWindow.pack();
        pauseWindow.setPosition(
            (Gdx.graphics.getWidth()  - pauseWindow.getWidth())  / 2,
            (Gdx.graphics.getHeight() - pauseWindow.getHeight()) / 2
        );
        pauseWindow.setColor(35 / 255f, 46 / 255f, 62 / 255f, 1);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        stage.addActor(pauseWindow);
    }

    @Override
    public void render(float delta) {
//        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handlePause();
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

    public TextButton getResumeButton() {
        return resumeButton;
    }

    public CheckBox getGameThemeCheckBox() {
        return gameThemeCheckBox;
    }

    public TextButton getQuitButton() {
        return quitButton;
    }

    public TextButton getSaveQuitButton() {
        return saveQuitButton;
    }
}
