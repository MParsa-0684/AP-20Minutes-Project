package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;

public class Ability {
    private AbilityType type;
    private float time;

    public Ability(AbilityType type) {
        this.type = type;
        if(type == AbilityType.DAMAGER || type == AbilityType.SPEEDY)
            this.time = 10;
    }

    public void useAbility() {
        switch (type){
            case DAMAGER:
            case SPEEDY:
                if(time > 0)
                    time -= Gdx.graphics.getDeltaTime();
                break;
            default :
                break;
        }
    }

    public AbilityType getType() {
        return type;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

}
