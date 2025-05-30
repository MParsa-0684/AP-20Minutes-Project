package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.ScoreboardMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreboardMenuView implements Screen {
    private Stage stage;
    private ScoreboardMenuController controller;
    private Skin skin;
    private Table table;
    private Label titleLabel;
    private TextButton usernameTextButton;
    private TextButton scoreTextButton;
    private TextButton killTextButton;
    private TextButton maxTimeTextButton;
    private TextButton backButton;

    public ScoreboardMenuView(ScoreboardMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.titleLabel = new Label("Scoreboard", skin);
        this.titleLabel.setFontScale(3);
        this.usernameTextButton = new TextButton("Username", skin);
        this.usernameTextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.getUsers().sort((a, b) -> a.getUsername().compareToIgnoreCase(b.getUsername()));
                extractedTable();
            }
        });

        this.scoreTextButton = new TextButton("Score", skin);
        this.scoreTextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.getUsers().sort(Comparator.comparingInt(User::getScore).reversed());
                extractedTable();
            }
        });

        this.killTextButton = new TextButton("Kill Count", skin);
        this.killTextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.getUsers().sort(Comparator.comparingInt(User::getKillCount).reversed());
                extractedTable();
            }
        });

        this.maxTimeTextButton = new TextButton("Max Time", skin);
        this.maxTimeTextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.getUsers().sort(Comparator.comparingInt(User::getMaxTimeSpent).reversed());
                extractedTable();
            }
        });

        this.backButton = new TextButton("Back", skin);

        App.getUsers().sort(Comparator.comparingInt(User::getScore).reversed());
        controller.setView(this);
    }

    @Override
    public void show() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        extractedTable();

        stage.addActor(table);
    }

    private void extractedTable() {
        stage.clear();
        table.clear();
        table.defaults().pad(5).space(5);
        table.setFillParent(true);
        table.add(titleLabel).colspan(4).center().padBottom(20).row();

        table.add(usernameTextButton).pad(10);
        table.add(scoreTextButton).pad(10);
        table.add(killTextButton).pad(10);
        table.add(maxTimeTextButton).pad(10);
        table.row();

        ArrayList<User> users = App.getUsers();

        // Show only top 10
        for (int i = 0; i < Math.min(users.size(), 10); i++) {
            User u = users.get(i);
            Label usernameLabel = new Label(u.getUsername(), skin);
            Label scoreLabel = new Label(String.valueOf(u.getScore()), skin);
            Label killLabel = new Label(String.valueOf(u.getKillCount()), skin);
            Label maxTimeLabel = new Label(String.valueOf(u.getMaxTimeSpent()), skin);

            if(i == 0) {
                usernameLabel.setColor(Color.GOLD);
                scoreLabel.setColor(Color.GOLD);
                killLabel.setColor(Color.GOLD);
                maxTimeLabel.setColor(Color.GOLD);
            }
            if(i == 1) {
                usernameLabel.setColor(Color.GRAY);
                scoreLabel.setColor(Color.GRAY);
                killLabel.setColor(Color.GRAY);
                maxTimeLabel.setColor(Color.GRAY);
            }
            if(i == 2) {
                usernameLabel.setColor(Color.BROWN);
                scoreLabel.setColor(Color.BROWN);
                killLabel.setColor(Color.BROWN);
                maxTimeLabel.setColor(Color.BROWN);
            }

            if(u == App.getCurrentUser()) {
                usernameLabel.setColor(Color.BLUE);
                scoreLabel.setColor(Color.BLUE);
                killLabel.setColor(Color.BLUE);
                maxTimeLabel.setColor(Color.BLUE);
            }

            table.add(usernameLabel).pad(5);
            table.add(scoreLabel).pad(5);
            table.add(killLabel).pad(5);
            table.add(maxTimeLabel).pad(5);
            table.row();
        }
        table.add(backButton).colspan(4).center().padTop(50).pad(5).row();
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);
        Main.getBatch().begin();
        GameAssetManager.getGameAssetManager().getBackground().draw(Main.getBatch());
        Main.getBatch().end();

        GameAssetManager.getGameAssetManager().setColorFunction();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleScoreBoard();
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

    public TextButton getBackButton() {
        return backButton;
    }
}
