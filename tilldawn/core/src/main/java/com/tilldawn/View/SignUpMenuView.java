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
import com.tilldawn.Model.Avatar;

import java.util.Arrays;

public class SignUpMenuView implements Screen {
    private Stage stage;
    private final SignUpMenuController controller;
    private Table table;
    private final Label viewTitle;
    private final Label usernameLable;
    private final Label passwordLable;
    private final TextField usernameTextField;
    private final TextField passwordTextField;
    private final TextButton GuestButton;
    private final SelectBox<String> securityQuestions;
    private final TextField securityAnswerTextField;
    private final TextButton signUpButton;
    private final Label errorLabel;

    public SignUpMenuView(SignUpMenuController controller, Skin skin) {
        this.table = new Table();
        this.controller = controller;
        this.viewTitle = new Label("Sign Up Menu", skin);
        this.usernameLable = new Label("Username:", skin);
        this.passwordLable = new Label("Password:", skin);
        this.usernameTextField = new TextField("", skin);
        this.passwordTextField = new TextField("", skin);
        this.GuestButton = new TextButton("Guest User", skin);
        this.securityQuestions = new SelectBox<>(skin);
        this.securityQuestions.setItems(new Array<>(new String[]{"1.What was the name of your first car?",
            "2.What city were you born in?",
            "3.What is your favorite teacher’s name?"}));
        this.securityAnswerTextField = new TextField("Your answer", skin);
        this.signUpButton = new TextButton("Sign Up", skin);
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
        table.add(usernameLable).right().pad(0, 0, 0, 220);
        table.add(passwordLable).left();
        table.row();

        table.add(usernameTextField).right().pad(0, 0, 0, 200);
        table.add(passwordTextField).left();
        table.row();

        table.add(securityQuestions).right().pad(0, 50, 0, 0);
        table.add(securityAnswerTextField).left();
        table.row();

        table.add(signUpButton).right().pad(0, 0, 0, 150);
        table.add(GuestButton).pad(0, 0, 0, 100);
        table.row().pad(50);
        errorLabel.setFontScale(1.5f);
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
}
