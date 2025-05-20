package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;
import com.tilldawn.View.ForgetPasswordMenuView;
import com.tilldawn.View.LoginMenuView;

public class ForgetPasswordController extends Controller{
    private ForgetPasswordMenuView view;

    public void setView(ForgetPasswordMenuView view) {
        this.view = view;
    }

    public void handleForgetPassword() {
        if(view == null)
            return;

        if(view.getLoginButton().isChecked()) {
            view.getLoginButton().setChecked(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
        }
        else if(view.getCheckUsernameButton().isChecked()) {
            view.getCheckUsernameButton().setChecked(false);
            User user = checkUserExist(view.getUsernameTextField().getText());
            if(user == null) {
                view.getErrorLabel().setText("User not found!");
                return;
            }

            view.getSecurityQuestion().setText(view.getSecurityQuestion().getText() + " " + user.getSecurityQuestion());
        }
        else if(view.getSetNewPasswordButton().isChecked()) {
            view.getSetNewPasswordButton().setChecked(false);
            User user = checkUserExist(view.getUsernameTextField().getText());
            if(user == null) {
                view.getErrorLabel().setText("User not found!");
                return;
            }

            if(!view.getSecurityAnswerTextField().getText().equals(user.getSecurityAnswer())) {
                view.getErrorLabel().setText("Security answer mismatched!");
                return;
            }

            user.setPassword(view.getNewPasswordTextField().getText());
            view.getErrorLabel().setText("Your password has been changed to !" + user.getPassword());
        }
    }
}
