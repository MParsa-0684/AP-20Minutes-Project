package com.tilldawn.Control;

import com.tilldawn.Model.App;
import com.tilldawn.Model.Bullet;
import com.tilldawn.Model.Weapon;

import java.util.ArrayList;

public class WeaponController {
    private Weapon weapon;
    private ArrayList<Bullet> bullets;

    public WeaponController() {
        this.weapon = App.getCurrentGame().getPlayer().getWeapon();
        this.bullets = new ArrayList<>();
    }

    public void update() {

    }
}
