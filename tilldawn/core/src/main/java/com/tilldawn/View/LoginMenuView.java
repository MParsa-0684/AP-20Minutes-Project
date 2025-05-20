package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.LoginMenuController;
import com.tilldawn.Main;

public class LoginMenuView implements Screen {
    private Stage stage;
    private LoginMenuController controller;
    private Table table;
    private final Label viewTitle;
    private final Label usernameLable;
    private final Label passwordLable;
    private final TextField usernameTextField;
    private final TextField passwordTextField;
    private final TextButton forgetPasswordButton;
    private final TextButton loginButton;
    private final Label errorLabel;

    public LoginMenuView(LoginMenuController controller, Skin skin) {
        this.table = new Table();
        this.controller = controller;
        this.viewTitle = new Label("Login Menu", skin);
        this.usernameLable = new Label("Username:", skin);
        this.passwordLable = new Label("Password:", skin);
        this.usernameTextField = new TextField("", skin);
        this.passwordTextField = new TextField("", skin);
        this.forgetPasswordButton = new TextButton("Forgot Password", skin);
        this.loginButton = new TextButton("Login Menu", skin);
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

        table.defaults().pad(5).space(20);
        viewTitle.setFontScale(3);
        table.add(viewTitle).colspan(2).center();
        table.row().space(20);

        usernameLable.setFontScale(1.5f);
        passwordLable.setFontScale(1.5f);
        table.add(usernameLable).pad(0, 0, 0, 50);
        table.add(passwordLable).pad(0, -170, 0, 0);
        table.row();

        table.add(usernameTextField).pad(0, 0, 0, 50);
        table.add(passwordTextField).pad(0, -150, 0, 0);
        table.row();

        table.add(loginButton).pad(0, 110, 0, 200);
        table.add(forgetPasswordButton).pad(0, -100, 0, 50);
        table.row();
        table.add(errorLabel).colspan(2).center();

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleLogin();
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

    public Label getViewTitle() {
        return viewTitle;
    }

    public Label getUsernameLable() {
        return usernameLable;
    }

    public Label getPasswordLable() {
        return passwordLable;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public TextButton getForgetPasswordButton() {
        return forgetPasswordButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}
