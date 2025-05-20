package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;
import com.tilldawn.View.ForgetPasswordMenu;
import com.tilldawn.View.LoginMenuView;
import com.tilldawn.View.MainMenuView;

public class LoginMenuController extends Controller {


    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void handleLogin() {
        if(view == null)
            return;

        if(view.getLoginButton().isChecked()) {
            view.getLoginButton().setChecked(false);
            User user = checkUserExist(view.getUsernameTextField().getText());
            if(user == null)
                view.getErrorLabel().setText("Your username doesn't exist!");

            if(!user.getPassword().equals(view.getPasswordTextField().getText()))
                view.getErrorLabel().setText("Wrong password!");

            App.setCurrentUser(user);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getForgetPasswordButton().isChecked()) {
            view.getForgetPasswordButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ForgetPasswordMenu(new ForgetPasswordController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }

    }
}
