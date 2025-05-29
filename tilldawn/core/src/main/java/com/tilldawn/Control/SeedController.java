package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Seed;

import java.util.ArrayList;

public class SeedController {
    private ArrayList<Seed> seeds;

    public SeedController() {
        this.seeds = new ArrayList<>();
    }

    public void update() {
        ArrayList<Seed> tempSeeds = new ArrayList<>();
        for (Seed seed : this.seeds) {
            seed.getSprite().draw(Main.getBatch());

            if(seed.getCollisionRect().hasIntersect(App.getCurrentGame().getPlayer().getCollisionRect())) {
                if(App.getCurrentGame().getPreGame().isSfxMusic())
                    GameAssetManager.getGameAssetManager().getCoins().play(1.0f);
                App.getCurrentGame().getPlayer().setXp(App.getCurrentGame().getPlayer().getXp() + 3);
                tempSeeds.add(seed);
            }
        }

        for (Seed seed : tempSeeds) {
            seeds.remove(seed);
        }
    }

    public ArrayList<Seed> getSeeds() {
        return seeds;
    }
}
