package com.tilldawn.View;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.ProfileMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Avatar;
import com.tilldawn.Model.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ProfileMenuView implements Screen {
    private Stage stage;
    private ProfileMenuController controller;
    private final Label titleLabel;
    private final Label usernameLabel;
    private final TextField usernameField;
    private final TextButton changeUsernameButton;

    private final Label passwordLabel;
    private final TextField passwordField;
    private final TextButton changePasswordButton;

    private final TextButton deleteAccountButton;

    private final Label avatarLabel;
    private final SelectBox<String> avatarSelectBox;

    private final TextButton chooseFileButton;
    private Image avatarPreviewImage;

    private final Label dragDropLabel;
    private final Label errorLabel;
    private final TextButton backButton;


    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.titleLabel = new Label("Profile Menu", skin);
        titleLabel.setFontScale(3);
        usernameLabel = new Label("Username:", skin);
        usernameField = new TextField(App.getCurrentUser().getUsername(), skin);
        changeUsernameButton = new TextButton("Change Username", skin);

        // Password
        passwordLabel = new Label("Password:", skin);
        passwordField = new TextField(App.getCurrentUser().getPassword(), skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        changePasswordButton = new TextButton("Change Password", skin);

        // Delete account
        deleteAccountButton = new TextButton("Delete Account", skin);

        // Avatar selection from existing list
        avatarLabel = new Label("Choose Avatar:", skin);
        avatarSelectBox = new SelectBox<>(skin);
        avatarSelectBox.setItems(App.getCurrentUser().getTextures().stream().map(Pair::getKey).toArray(String[]::new));
        avatarSelectBox.setSelected(App.getCurrentUser().getCurrentSprite().getKey());
        avatarPreviewImage = new Image(new TextureRegionDrawable(new TextureRegion(App.getCurrentUser().getCurrentSprite().getValue())));
        avatarSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                String selected = avatarSelectBox.getSelected();
                App.getCurrentUser().setCurrentSprite(new Pair<>(selected,
                    new Sprite(App.getCurrentUser().findTextures(selected))));
                avatarPreviewImage.setDrawable(new TextureRegionDrawable(new TextureRegion(
                    App.getCurrentUser().getCurrentSprite().getValue())));
            }
        });

        // File chooser for custom avatar
        chooseFileButton = new TextButton("Upload Avatar", skin);
        chooseFileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                    java.awt.EventQueue.invokeLater(() -> {
                        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
                        int result = chooser.showOpenDialog(null);
                        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
                            File file = chooser.getSelectedFile();
                            Gdx.app.postRunnable(() -> {
                                Texture texture = new Texture(Gdx.files.absolute(file.getAbsolutePath()));
                                App.getCurrentUser().getTextures().add(new Pair<>(file.getName(), texture));
                                App.getCurrentUser().setCurrentSprite(
                                    new Pair<>(file.getName(), new Sprite(texture)));
                                avatarPreviewImage.setDrawable(new TextureRegionDrawable(new TextureRegion(
                                    App.getCurrentUser().getCurrentSprite().getValue()
                                )));
                            });
                        }
                    });
                }
            }
        });

            /*

             */
        dragDropLabel = new Label("Drag & Drop your Image here", skin);
        errorLabel = new Label("", skin);
        errorLabel.setColor(Color.WHITE);
        backButton = new TextButton("Back", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.top().padTop(40); // add top padding
        stage.addActor(table);
        table.defaults().pad(5).space(5);

        table.add(titleLabel).colspan(3).center().row();
        // Username section
        table.add(usernameLabel).left().padBottom(5);
        table.row();
        table.add(usernameField).width(300).padBottom(5);
        table.row();
        table.add(changeUsernameButton).padBottom(5);
        table.row();


        // Password section
        table.add(passwordLabel).left().padBottom(5);
        table.row();
        table.add(passwordField).width(300).padBottom(5);
        table.row();
        table.add(changePasswordButton).padBottom(5);
        table.row();

        // Delete account
        table.add(deleteAccountButton).padBottom(25);
        table.row();

        // Avatar selection from existing list
        table.add(avatarLabel).left().padBottom(5);
        table.row();
        table.add(avatarSelectBox).width(300).padBottom(25);
        table.row();

        // Custom avatar upload
        table.add(backButton).padBottom(10);
        table.row();
        table.add(chooseFileButton).padBottom(10);
        table.row();
        table.add(dragDropLabel).center().padBottom(10);
        table.row();

        table.add(avatarPreviewImage).size(App.getCurrentUser().getAvatar().getSprites().get(0).get(0).getWidth() * 3,
            App.getCurrentUser().getAvatar().getSprites().get(0).get(0).getHeight() * 3);
        table.row();
        table.add(errorLabel).padBottom(10).center();

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleProfile();
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

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextButton getChangeUsernameButton() {
        return changeUsernameButton;
    }

    public Label getPasswordLabel() {
        return passwordLabel;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public TextButton getDeleteAccountButton() {
        return deleteAccountButton;
    }

    public Label getAvatarLabel() {
        return avatarLabel;
    }

    public SelectBox<String> getAvatarSelectBox() {
        return avatarSelectBox;
    }

    public TextButton getChooseFileButton() {
        return chooseFileButton;
    }

    public Image getAvatarPreviewImage() {
        return avatarPreviewImage;
    }

    public Label getDragDropLabel() {
        return dragDropLabel;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
