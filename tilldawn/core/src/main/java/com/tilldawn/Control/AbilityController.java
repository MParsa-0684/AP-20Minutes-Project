package com.tilldawn.Control;

import com.tilldawn.Model.Ability;
import com.tilldawn.Model.App;

import java.util.ArrayList;
import java.util.Random;

public class AbilityController {
    ArrayList<Ability> abilities;

    public AbilityController() {
        this.abilities = App.getCurrentGame().getPlayer().getAbilities();
    }

    public void update() {
        updateLevel();
    }

    private void updateLevel() {
        int currentLevelXP = 10 * (App.getCurrentGame().getPlayer().getLevel() - 1) * App.getCurrentGame().getPlayer().getLevel();
        if(App.getCurrentGame().getPlayer().getXp() - currentLevelXP >= App.getCurrentGame().getPlayer().getLevel() * 20) {
            App.getCurrentGame().getPlayer().setLevel(App.getCurrentGame().getPlayer().getLevel() + 1);
            giveRandomAbility();
        }
    }

    private void giveRandomAbility() {
        Random random = new Random();
        int index = random.nextInt(5);
        Ability ability = abilities.get(index);
        App.getCurrentGame().getPlayer().getAbilities().add(ability);
    }
}
