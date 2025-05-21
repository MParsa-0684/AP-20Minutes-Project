package com.tilldawn.Control;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Avatar;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Pair;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.ProfileMenuView;
import com.tilldawn.View.SignUpMenuView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ProfileMenuController extends Controller {
    private ProfileMenuView view;

    public ProfileMenuController() {
        // Constructor for ProfileMenuController
    }

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void handleProfile() {
        if(view == null)
            return;

        if(view.getChangeUsernameButton().isChecked()) {
            view.getChangeUsernameButton().setChecked(false);
            if(checkUserExist(view.getUsernameField().getText()) != null) {
                view.getErrorLabel().setText("This username is already taken!");
                return;
            }

            App.getCurrentUser().setUsername(view.getUsernameField().getText());
            view.getErrorLabel().setText("Your username is changed!");
        }
        else if(view.getChangePasswordButton().isChecked()) {
            view.getChangePasswordButton().setChecked(false);
            Pair<Boolean, String> pair = checkPassword(view.getPasswordField().getText());
            if(!pair.getKey()) {
                view.getErrorLabel().setText(pair.getValue());
                return;
            }

            App.getCurrentUser().setPassword(view.getPasswordField().getText());
            view.getErrorLabel().setText("Your password is changed!");
        }
        else if(view.getDeleteAccountButton().isChecked()) {
            view.getDeleteAccountButton().setChecked(false);

            App.getUsers().remove(App.getCurrentUser());
            App.setCurrentUser(null);
            view.getErrorLabel().setText("Your account has been deleted!");
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SignUpMenuView(new SignUpMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getBackButton().isChecked()) {
            view.getBackButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
    }
}
