package com.tilldawn.Control;

import com.tilldawn.Model.App;
import com.tilldawn.Model.User;

public class Controller {
    protected User checkUserExist(String username){
        for (User user : App.getUsers()) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

}
