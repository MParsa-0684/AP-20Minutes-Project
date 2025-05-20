package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.SignUpMenuController;
import com.tilldawn.Main;

public class SignUpMenuView implements Screen {
    private Stage stage;
    private final SignUpMenuController controller;
    private Table table;
    private final Label viewTitle;
    private final Label usernameLabel;
    private final Label passwordLabel;
    private final TextField usernameTextField;
    private final TextField passwordTextField;
    private final TextButton GuestButton;
    private final SelectBox<String> securityQuestions;
    private final TextField securityAnswerTextField;
    private final TextButton signUpButton;
    private final TextButton loginButton;
    private final Label errorLabel;

    public SignUpMenuView(SignUpMenuController controller, Skin skin) {
        this.table = new Table();
        this.controller = controller;
        this.viewTitle = new Label("SignUp Menu", skin);
        this.usernameLabel = new Label("Username:", skin);
        this.passwordLabel = new Label("Password:", skin);
        this.usernameTextField = new TextField("", skin);
        this.passwordTextField = new TextField("", skin);
        this.GuestButton = new TextButton("Guest User", skin);
        this.securityQuestions = new SelectBox<>(skin);
        this.securityQuestions.setItems(new Array<>(new String[]{"1.What was the name of your first car?",
            "2.What city were you born in?",
            "3.What is your favorite teacher’s name?"}));
        this.securityAnswerTextField = new TextField("", skin);
        this.signUpButton = new TextButton("Sign Up", skin);
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

        usernameLabel.setFontScale(1.5f);
        passwordLabel.setFontScale(1.5f);
        table.add(usernameLabel).pad(0, 0, 0, 220);
        table.add(passwordLabel).left();
        table.row();

        table.add(usernameTextField).width(200).pad(0, 0, 0, 150);
        table.add(passwordTextField).width(200).left();
        table.row();

        table.add(securityQuestions).pad(0, 0, 0, 0);
        table.add(securityAnswerTextField).left();
        table.row();

        table.add(signUpButton).pad(0, 0, 0, 220);
        table.add(GuestButton).pad(0, -400, 0, 0);
        table.add(loginButton).pad(0, -200, 0, 0);
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
        controller.handleSignUp();
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

    public TextButton getGuestButton() {
        return GuestButton;
    }

    public TextButton getSignUpButton() {
        return signUpButton;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public TextField getSecurityAnswerTextField() {
        return securityAnswerTextField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public SelectBox<String> getSecurityQuestions() {
        return securityQuestions;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }
}
