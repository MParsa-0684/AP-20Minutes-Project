package com.tilldawn.Control;

import com.tilldawn.Model.Ability;
import com.tilldawn.Model.AbilityType;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AbilityController {
    HashMap<AbilityType, ArrayList<Ability>> abilities;

    public AbilityController() {
        this.abilities = App.getCurrentGame().getPlayer().getAbilities();
    }

    public void update() {
        updateLevel();
        for (AbilityType value : AbilityType.values()) {
            for (Ability ability : abilities.get(value)) {
                ability.useAbility();
            }
        }
    }

    private void updateLevel() {
        int currentLevelXP = 10 * (App.getCurrentGame().getPlayer().getLevel() - 1) * App.getCurrentGame().getPlayer().getLevel();
        if(App.getCurrentGame().getPlayer().getXp() - currentLevelXP >= App.getCurrentGame().getPlayer().getLevel() * 20) {
            if(App.getCurrentGame().getPreGame().isSfxMusic())
                GameAssetManager.getGameAssetManager().getLevelUp().play(1.0f);

            App.getCurrentGame().getPlayer().setLevel(App.getCurrentGame().getPlayer().getLevel() + 1);
            App.getCurrentUser().getGameView().getLevelProgressBar().setRange(0, App.getCurrentGame().getPlayer().getLevel() * 20);
            App.getCurrentUser().getGameView().getLevelProgressBar().setValue(0);
            giveRandomAbility();
        }
    }

    private void giveRandomAbility() {
        Random random = new Random();
        int index = random.nextInt(5);
        App.getCurrentGame().getPlayer().getAbilities().get(AbilityType.values()[index]).add(new Ability(AbilityType.values()[index]));
        if(AbilityType.values()[index] == AbilityType.VITALITY) {
            App.getCurrentGame().getPlayer().setHealth(App.getCurrentGame().getPlayer().getHealth() + 1);
        }
        if(AbilityType.values()[index] == AbilityType.PROCREASE) {
            App.getCurrentGame().getPlayer().getWeapon().setProjectile(
                App.getCurrentGame().getPlayer().getWeapon().getProjectile() + 1
            );
        }
        if(AbilityType.values()[index] == AbilityType.AMOCREASE) {
            App.getCurrentGame().getPlayer().getWeapon().setMaxAmmo(
                App.getCurrentGame().getPlayer().getWeapon().getMaxAmmo() + 5
            );
        }
    }
}
