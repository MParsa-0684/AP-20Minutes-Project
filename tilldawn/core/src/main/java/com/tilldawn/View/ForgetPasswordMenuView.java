package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.ForgetPasswordController;
import com.tilldawn.Main;

public class ForgetPasswordMenuView implements Screen {
    private Stage stage;
    private ForgetPasswordController controller;
    private Table table;
    private final Label viewTitle;
    private final Label usernameLabel;
    private final Label newPasswordLabel;
    private final TextField usernameTextField;
    private final TextField newPasswordTextField;
    private final Label securityQuestion;
    private final TextField securityAnswerTextField;
    private final TextButton checkUsernameButton;
    private final TextButton setNewPasswordButton;
    private final TextButton loginButton;
    private final Label errorLabel;


    public ForgetPasswordMenuView(ForgetPasswordController controller, Skin skin) {
        this.table = new Table();
        this.controller = controller;
        this.viewTitle = new Label("ForgetPassword Menu", skin);
        this.usernameLabel = new Label("Username:", skin);
        this.newPasswordLabel = new Label("New password:", skin);
        this.usernameTextField = new TextField("", skin);
        this.newPasswordTextField = new TextField("", skin);
        this.securityQuestion = new Label("Security Question: ", skin);
        this.securityAnswerTextField = new TextField("", skin);
        this.checkUsernameButton = new TextButton("Find username", skin);
        this.setNewPasswordButton = new TextButton("Reset Password", skin);
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

// Set default spacing and padding
        table.defaults().pad(10).space(20);

// Title
        viewTitle.setFontScale(3f);
        table.add(viewTitle).colspan(3).center().padBottom(40); // nicely spaced below title
        table.row();

// Username
        usernameLabel.setFontScale(1.5f);
        table.add(usernameLabel).right().width(200).padRight(10);
        table.add(usernameTextField).width(300);
        table.add(checkUsernameButton).width(150).left();
        table.row();

// Security Question
        securityQuestion.setFontScale(1.5f);
        table.add(securityQuestion).colspan(3).left().padTop(10);
        table.row();

// Security Answer + New Password
        table.add(securityAnswerTextField).width(200).left();

        newPasswordLabel.setFontScale(1.5f);
        table.add(newPasswordLabel).left().width(150);
        table.add(newPasswordTextField).width(300).pad(0, -100, 0, 0);
        table.row();

// Buttons
        table.add(setNewPasswordButton).width(200).padTop(20);
        table.add(loginButton).width(200).padTop(20).colspan(2);
        table.row();

// Error Label
        table.add(errorLabel).colspan(3).center().padTop(15);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleForgetPassword();
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

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Label getNewPasswordLabel() {
        return newPasswordLabel;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public TextField getNewPasswordTextField() {
        return newPasswordTextField;
    }

    public Label getSecurityQuestion() {
        return securityQuestion;
    }

    public TextField getSecurityAnswerTextField() {
        return securityAnswerTextField;
    }

    public TextButton getCheckUsernameButton() {
        return checkUsernameButton;
    }

    public TextButton getSetNewPasswordButton() {
        return setNewPasswordButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}
