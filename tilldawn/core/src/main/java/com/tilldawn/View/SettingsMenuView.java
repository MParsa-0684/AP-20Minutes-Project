package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.SettingsMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;

public class SettingsMenuView implements Screen {
    private Stage stage;
    private Table table;
    private SettingsMenuController controller;
    private Label titleLabel;
    private Label volumeLabel;
    private Slider volumeSlider;
    private Label musicLabel;
    private SelectBox<String> musicSelectBox;
    private Label sfxLabel;
    private CheckBox sfxButton;
    private Label gameKeysLabel;
    private TextField gameKeysButtons;
    private Label autoReloadLabel;
    private CheckBox autoReloadCheckBox;
    private Label gameThemeLabel;
    private CheckBox gameThemeCheckBox;
    private TextButton backButton;


    public SettingsMenuView(SettingsMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        titleLabel = new Label("Settings", skin);

        volumeLabel = new Label("Volume", skin);
        volumeSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeSlider.setValue(0.5f);

        musicLabel = new Label("Music Track", skin);
        musicSelectBox = new SelectBox<>(skin);
        musicSelectBox.setItems("Pretty Dungeon LOOP", "Wasteland Combat Loop");
        musicSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                String selected = musicSelectBox.getSelected();
                float volume = App.getBackgroundMusic().getVolume();
                if(App.getBackgroundMusic() != null)
                    App.getBackgroundMusic().dispose();
                App.setBackgroundMusic(Gdx.audio.newMusic(Gdx.files.internal("AudioClip/" + selected + ".wav")));
                App.getBackgroundMusic().setLooping(true);
                App.getBackgroundMusic().setVolume(volume);
                App.getBackgroundMusic().play();
            }
        });

        sfxLabel = new Label("Sound Effects", skin);
        sfxButton = new CheckBox("", skin);
        if(App.getCurrentUser().getPreGame().isSfxMusic())
            sfxButton.setChecked(true);

        gameKeysLabel = new Label("Key Bindings", skin);
        gameKeysButtons = new TextField(App.getCurrentUser().getPreGame().getGameKeys(), skin);

        autoReloadLabel = new Label("Auto Reload", skin);
        autoReloadCheckBox = new CheckBox("", skin);
        if(App.getCurrentUser().getPreGame().isAutoReload())
            autoReloadCheckBox.setChecked(true);

        gameThemeLabel = new Label("Black & White Mode", skin);
        gameThemeCheckBox = new CheckBox("", skin);
        if(App.getCurrentUser().getPreGame().getGameColor() == Color.BLACK)
            gameThemeCheckBox.setChecked(true);

        backButton = new TextButton("Back", skin);
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.top().padTop(200);
        stage.addActor(table);

        // Title
        titleLabel.setFontScale(3f);
        table.add(titleLabel).colspan(2).center().padBottom(30);
        table.row().padTop(30);

        // Volume Control
        volumeLabel.setFontScale(1.5f);
        table.add(volumeLabel).left().padRight(10);
        table.add(volumeSlider).width(250);
        table.row().padTop(30);;

        // Music Selector
        musicLabel.setFontScale(1.5f);
        table.add(musicLabel).left().padRight(10);
        table.add(musicSelectBox).width(250);
        table.row().padTop(30);;

        // SFX Toggle
        sfxLabel.setFontScale(1.5f);
        table.add(sfxLabel).left().padRight(10);
        table.add(sfxButton).left();
        table.row().padTop(30);;

        // Game Key Bindings
        gameKeysLabel.setFontScale(1.5f);
        table.add(gameKeysLabel).left().padRight(10);
        table.add(gameKeysButtons).width(250);
        table.row().padTop(30);;

        // Auto Reload
        autoReloadLabel.setFontScale(1.5f);
        table.add(autoReloadLabel).left().padRight(10);
        table.add(autoReloadCheckBox).left();
        table.row().padTop(30);;

        // Game Theme
        gameThemeLabel.setFontScale(1.5f);
        table.add(gameThemeLabel).left().padRight(10);
        table.add(gameThemeCheckBox).left();
        table.row().padTop(30);
        table.add(backButton).colspan(2).width(250);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleSettings();
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

    public SelectBox<String> getMusicSelectBox() {
        return musicSelectBox;
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public CheckBox getSfxButton() {
        return sfxButton;
    }

    public TextField getGameKeysButtons() {
        return gameKeysButtons;
    }

    public CheckBox getAutoReloadCheckBox() {
        return autoReloadCheckBox;
    }

    public CheckBox getGameThemeCheckBox() {
        return gameThemeCheckBox;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
