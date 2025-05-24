package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.PreGameMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Avatar;
import org.w3c.dom.Text;

public class PreGameMenuView implements Screen {
    private PreGameMenuController controller;
    private Stage stage;
    private Label titleLabel;
    private Label heroLabel;
    private SelectBox<String> heroSelectBox;
    private Label weaponLabel;
    private SelectBox<String> weaponSelectBox;
    private Label timeLabel;
    private SelectBox<String> timeSelectBox;
    private TextButton backButton;
    private TextButton gameButton;
    private TextButton settingsButton;
    private Label errorLabel;
    private Table table;


    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.controller = controller;
        titleLabel    = new Label("Pre-Game Menu", skin);
        titleLabel.setFontScale(3);
        heroLabel     = new Label("Select Hero:",     skin);
        weaponLabel   = new Label("Select Weapon:",   skin);
        timeLabel     = new Label("Game Duration:",   skin);

        heroSelectBox   = new SelectBox<>(skin);
        heroSelectBox.setItems("SHANA", "DIAMOND", "SCARLET", "LILITH", "DASHER");
        if(!Avatar.CUSTOM.getTextures().isEmpty())
            heroSelectBox.setItems("CUSTOM");
        heroSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                String selected = heroSelectBox.getSelected();
                App.getCurrentUser().setAvatar(Avatar.getAvatar(selected));
                App.getCurrentUser().setCurrentSprite(Avatar.getAvatar(selected).getSprites().get(0).get(0));
            }
        });

        weaponSelectBox = new SelectBox<>(skin);
        weaponSelectBox.setItems("REVOLVER", "SHOTGUN", "SMG_DUAL");

        timeSelectBox   = new SelectBox<>(skin);
        timeSelectBox.setItems("2", "5", "10", "20");

        backButton     = new TextButton("Back",        skin);
        gameButton     = new TextButton("Start Game", skin);
        settingsButton = new TextButton("Settings",    skin);

        heroSelectBox.setSelected(App.getCurrentUser().getAvatar().getName());
        weaponSelectBox.setSelected("Revolver");
        timeSelectBox.setSelected("2");
        errorLabel = new Label((App.getCurrentUser().getGameView() != null) ? "Your saved game will be eliminated!" : "", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.top().padTop(30);
        stage.addActor(table);
        table.defaults().pad(5).space(5);


        table.add(titleLabel).colspan(2).center().padBottom(20).padTop(200);
        table.row();

        table.add(heroLabel).left().pad(5);
        table.add(heroSelectBox).width(200).pad(5);
        table.row();

        table.add(weaponLabel).left().pad(5);
        table.add(weaponSelectBox).width(200).pad(5);
        table.row();

        table.add(timeLabel).left().pad(5);
        table.add(timeSelectBox).width(200).pad(5);
        table.row();

        table.add(gameButton).colspan(2).center().padTop(20).padBottom(5);
        table.row();

        table.add(backButton).left().pad(5);
        table.add(settingsButton).right().pad(5);
        table.row();
        table.add(errorLabel).center().pad(5);
        table.row();

        stage.addActor(table);
    }


    @Override
    public void render(float v) {
        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handlePreGame();
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

    public SelectBox<String> getWeaponSelectBox() {
        return weaponSelectBox;
    }

    public SelectBox<String> getTimeSelectBox() {
        return timeSelectBox;
    }

    public SelectBox<String> getHeroSelectBox() {
        return heroSelectBox;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextButton getGameButton() {
        return gameButton;
    }

    public TextButton getSettingsButton() {
        return settingsButton;
    }
}
