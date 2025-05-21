package com.tilldawn.Control;

import com.tilldawn.Model.App;
import com.tilldawn.Model.Player;

public class PlayerController {
    private Player player;

    public PlayerController() {
        this.player = App.getCurrentGame().getPlayer();
    }

    public void update() {

    }
}
