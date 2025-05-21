package com.tilldawn.Model;

public enum Ability {
    VITALITY("VITALITY"),
    DAMAGER("DAMAGER"),
    PROCREASE("PROCREASE"),
    AMOCREASE("AMOCREASE"),
    SPEEDY("SPEEDY");

    private final String name;

    Ability(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
