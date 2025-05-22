package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Player;

public class PlayerController {
    private Player player;

    public PlayerController() {
        this.player = App.getCurrentGame().getPlayer();
    }

    public void update() {
        handlePlayerInput();

        player.getSprite().draw(Main.getBatch());

        if(player.isIdle()) {
            animation(0);
        }
        if(player.isWalking()) {
            animation(1);
        }
        if(player.isRunning()) {
            animation(2);
        }

    }

    private void handlePlayerInput() {
        boolean flag = false;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            flag = true;
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) || App.getCurrentGame().getPreGame().isAutoReload())
                player.getPosition().set(player.getPosition().x, player.getPosition().y - player.getSpeed());
            else
                player.getPosition().set(player.getPosition().x, player.getPosition().y - 2 * player.getSpeed());

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            flag = true;
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) || App.getCurrentGame().getPreGame().isAutoReload())
                player.getPosition().set(player.getPosition().x, player.getPosition().y + player.getSpeed());
            else
                player.getPosition().set(player.getPosition().x, player.getPosition().y + 2 * player.getSpeed());

        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) || App.getCurrentGame().getPreGame().isAutoReload())
                player.getPosition().set(player.getPosition().x + player.getSpeed(), player.getPosition().y);
            else
                player.getPosition().set(player.getPosition().x + 2 * player.getSpeed(), player.getPosition().y);

            player.getSprite().flip(true, false);
            flag = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) || App.getCurrentGame().getPreGame().isAutoReload())
                player.getPosition().set(player.getPosition().x - player.getSpeed(), player.getPosition().y);
            else
                player.getPosition().set(player.getPosition().x - 2 * player.getSpeed(), player.getPosition().y);

            flag = true;
        }

        if(flag && (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || App.getCurrentGame().getPreGame().isAutoReload())) {
            player.setIdle(false);
            player.setWalking(true);
            player.setRunning(false);
        }
        else if(flag){
            player.setIdle(false);
            player.setWalking(false);
            player.setRunning(true);
        }
        else {
            player.setIdle(true);
            player.setWalking(false);
            player.setRunning(false);
        }
    }

    private void animation(int i) {
        Array<Texture> regions = new Array<>(player.getAvatar().getTextures().get(0).size());
        player.getAvatar().getTextures().get(i).forEach(regions::add);
        Animation<Texture> animation = new Animation<>(0.1f, regions);

        player.getSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if(!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Player getPlayer() {
        return player;
    }
}
