package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.View.LoginMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.SignUpMenuView;

import java.util.Random;

public class SignUpMenuController extends Controller {
    private Pair checkPassword(String password){
        if(password.length() < 8)
            return new Pair<>(false, "Your password must be at least 8 characters");

        if(!password.matches(".*[@#$%&*()_].*"))
            return new Pair<>(false, "Your password must contain at least a letter from @#$%&*()_");

        if(!password.matches(".*\\d.*") || !password.matches(".*[A-Z].*"))
            return new Pair<>(false, "Your password must contain a capital letter and a number");

        return new Pair<>(true, "Your password is valid");
    }

    private Avatar getRandomAvatar(){
        Random random = new Random();
        int rand = random.nextInt(5);
        int ptr = 0;
        for (Avatars value : Avatars.values()) {
            if(ptr == rand)
                return value.getAvatar();
            ptr++;
        }
        return null;
    }

    private SignUpMenuView view;

    public void setView(SignUpMenuView view) {
        this.view = view;
    }

    public void handleSignUp() {
        if(view == null)
            return;

        if(view.getSignUpButton().isChecked()) {
            view.getSignUpButton().setChecked(false);
            if(checkUserExist(view.getUsernameTextField().getText()) != null) {
                view.getErrorLabel().setText("Your username is already exist!");
                return;
            }

            Pair<Boolean, String> pair = checkPassword(view.getPasswordTextField().getText());
            if(!pair.getKey()) {
                view.getErrorLabel().setText(pair.getValue());
                return;
            }

            App.getUsers().add(new User(
                view.getUsernameTextField().getText(),
                view.getPasswordTextField().getText(),
                view.getSecurityAnswerTextField().getSelection(),
                view.getSecurityAnswerTextField().getText(),
                getRandomAvatar()
            ));

            view.getErrorLabel().setText("Your account has been successfully registered!");
        }
        else if(view.getGuestButton().isChecked()) {
            view.getGuestButton().setChecked(false);
            App.setCurrentUser(new User("Guest", "Guest", "null", "null", null));
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getLoginButton().isChecked()) {
            view.getLoginButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }

    }
}
