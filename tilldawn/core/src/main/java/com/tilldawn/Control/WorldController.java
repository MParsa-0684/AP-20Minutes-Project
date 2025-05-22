package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.World;

public class WorldController {
    private World world;

    public WorldController() {
        world = new World();
    }

    public void update() {
        Main.getBatch().draw(world.getTexture(), world.getPos().x, world.getPos().y);
    }
}
