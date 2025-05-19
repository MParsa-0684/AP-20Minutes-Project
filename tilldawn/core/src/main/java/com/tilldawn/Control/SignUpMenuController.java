package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.View.LoginMenuView;
import com.tilldawn.View.SignUpMenuView;

import java.util.Random;

public class SignUpMenuController {
    private boolean checkUserExist(String username){
        for (User user : App.getUsers()) {
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    private Pair checkPassword(String password){
        if(password.length() < 8)
            return new Pair<>(false, "Your password must be at least 8 characters");

        if(!password.contains("[@#$%&*()_]"))
            return new Pair<>(false, "Your password must contain at least a letter from @#$%&*()_");

        if(!password.contains("\\d") || !password.contains("[A-Z]"))
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

    public SignUpMenuController() {
        // Constructor for SignUpMenuController
    }

    public void setView(SignUpMenuView view) {
        this.view = view;
    }

    public void handleSignUp() {
        if(view == null)
            return;

        if(view.getSignUpButton().isChecked()) {
            if(checkUserExist(view.getUsernameTextField().getText())) {
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

        }
        else if(view.getGuestButton().isChecked()) {
            App.setCurrentUser(new User("Guest", "Guest", "null", "null", null));
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }

    }
}
