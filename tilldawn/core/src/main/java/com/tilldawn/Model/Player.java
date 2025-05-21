package com.tilldawn.Model;

public class Player {
    private Avatar avatar;
    private Weapon weapon;

    public Player(Avatar avatar, Weapon weapon) {
        this.avatar = avatar;
        this.weapon = weapon;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
