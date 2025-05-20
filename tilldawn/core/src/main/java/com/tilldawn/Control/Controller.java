package com.tilldawn.Control;

import com.tilldawn.Model.App;
import com.tilldawn.Model.Pair;
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

    protected Pair checkPassword(String password){
        if(password.length() < 8)
            return new Pair<>(false, "Your password must be at least 8 characters");

        if(!password.matches(".*[@#$%&*()_].*"))
            return new Pair<>(false, "Your password must contain at least a letter from @#$%&*()_");

        if(!password.matches(".*\\d.*") || !password.matches(".*[A-Z].*"))
            return new Pair<>(false, "Your password must contain a capital letter and a number");

        return new Pair<>(true, "Your password is valid");
    }

}
