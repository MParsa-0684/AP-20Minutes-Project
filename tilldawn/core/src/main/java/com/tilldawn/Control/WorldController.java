package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.World;

public class WorldController {
    private PlayerController playerController;
    private World world;

    public WorldController(PlayerController playerController) {
        world = App.getCurrentGame().getWorld();
        this.playerController = playerController;
    }

    public void update() {
        world.setPos(playerController.getPlayer().getPosition().cpy());
        Main.getBatch().draw(world.getTexture(), world.getPos().x -3104, world.getPos().y - 1992);
    }
}
