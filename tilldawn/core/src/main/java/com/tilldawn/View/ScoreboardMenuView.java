package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.ScoreboardMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.User;

import java.util.ArrayList;

public class ScoreboardMenuView implements Screen {
    private Stage stage;
    private ScoreboardMenuController controller;
    private Skin skin;
    private Table table;
    private Label titleLabel;
    private Label usernameLabel;
    private Label scoreLabel;
    private Label killLabel;
    private Label maxTimeLabel;
    private TextButton backButton;

    public ScoreboardMenuView(ScoreboardMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.titleLabel = new Label("Scoreboard", skin);
        this.titleLabel.setFontScale(3);
        this.usernameLabel = new Label("Username", skin);
        this.usernameLabel.setFontScale(1.5f);
        this.usernameLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.getUsers().sort((a, b) -> a.getUsername().compareToIgnoreCase(b.getUsername()));
            }
        });

        this.scoreLabel = new Label("Score", skin);
        this.scoreLabel.setFontScale(1.5f);
        this.scoreLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.getUsers().sort((a, b) -> a.getScore() - b.getScore());
            }
        });

        this.killLabel = new Label("Kill Count", skin);
        this.killLabel.setFontScale(1.5f);
        this.killLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.getUsers().sort((a, b) -> a.getKillCount() - b.getKillCount());
            }
        });

        this.maxTimeLabel = new Label("Max Time", skin);
        this.maxTimeLabel.setFontScale(1.5f);
        this.maxTimeLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.getUsers().sort((a, b) -> a.getMaxTimeSpent() - b.getMaxTimeSpent());
            }
        });

        this.backButton = new TextButton("Back", skin);

        App.getUsers().sort((a, b) -> a.getScore() - b.getScore());
        controller.setView(this);
    }

    @Override
    public void show() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.defaults().pad(5).space(5);
        table.add(titleLabel).colspan(4).center().padBottom(20).row();

        table.add(usernameLabel).pad(10);
        table.add(scoreLabel).pad(10);
        table.add(killLabel).pad(10);
        table.add(maxTimeLabel).pad(10);
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
        Main.getBatch().end();
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
