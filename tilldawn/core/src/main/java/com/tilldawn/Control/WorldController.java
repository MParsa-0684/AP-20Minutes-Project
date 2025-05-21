package com.tilldawn.Control;

import com.tilldawn.Model.App;
import com.tilldawn.Model.World;

public class WorldController {
    private World world;

    public WorldController() {
        world = App.getCurrentGame().getWorld();
    }

    public void update() {

    }
}
