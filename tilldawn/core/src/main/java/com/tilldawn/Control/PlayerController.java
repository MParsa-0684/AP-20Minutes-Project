package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.tilldawn.Main;
import com.tilldawn.Model.*;

public class PlayerController {
    private Player player;
    private Game game;

    public PlayerController() {
        this.player = App.getCurrentGame().getPlayer();
        this.game = App.getCurrentGame();
    }

    public void update() {
        if(player.isInvincible()) {
            player.setInvincibleTime(player.getInvincibleTime() + Gdx.graphics.getDeltaTime());
            if(player.getInvincibleTime() >= 1){
                player.setInvincible(false);
                player.setInvincibleTime(0);
            }
        }
//        System.out.println(player.getInvincibleTime());

        handlePlayerInput();

        player.getShadow().draw(Main.getBatch());
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
        Vector2 difference = new Vector2(0, 0);
        if(Gdx.input.isKeyPressed(game.getKeys()[0])) {
            flag = true;
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) || App.getCurrentGame().getPreGame().isAutoReload())
                difference.add(new Vector2(0, player.getSpeed()));
            else
                difference.add(new Vector2(0,  2 * player.getSpeed()));
        }
        if(Gdx.input.isKeyPressed(game.getKeys()[2])) {
            flag = true;
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) || App.getCurrentGame().getPreGame().isAutoReload())
                difference.add(new Vector2(0, -player.getSpeed()));
            else
                difference.add(new Vector2(0, -2 * player.getSpeed()));
        }
        if(Gdx.input.isKeyPressed(game.getKeys()[1])) {
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) || App.getCurrentGame().getPreGame().isAutoReload())
                difference.add(new Vector2(-player.getSpeed(), 0));
            else
                difference.add(new Vector2(-2 * player.getSpeed(), 0));

            player.getSprite().flip(true, false);
            flag = true;
        }
        if(Gdx.input.isKeyPressed(game.getKeys()[3])) {
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) || App.getCurrentGame().getPreGame().isAutoReload())
                difference.add(new Vector2(player.getSpeed(), 0));
            else
                difference.add(new Vector2(2 * player.getSpeed(), 0));

            flag = true;
        }
        // Cheat Codes
        // Decrease Time: T
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            App.getCurrentGame().setCurrentTime(Math.max(0, App.getCurrentGame().getCurrentTime() - 60));
        }
        // Level Up : L
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            App.getCurrentGame().getPlayer().setXp(
                player.getXp() +
                    10 * (App.getCurrentGame().getPlayer().getLevel() + 1) * App.getCurrentGame().getPlayer().getLevel()
            );
        }
        // Increase Health : H
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            player.setHealth(player.getHealth() + 1);
        }
        // Boss fight: B
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            //TODO: Boss Fight
        }
        // Infinite Ammo : G
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            player.getWeapon().setMaxAmmo(Integer.MAX_VALUE);
            player.getWeapon().setAmmo(Integer.MAX_VALUE);
        }

        player.update(difference);

        if(flag && App.getCurrentGame().getPreGame().isSfxMusic()) {
            GameAssetManager.getGameAssetManager().getWalk1().play(0.2f);
            GameAssetManager.getGameAssetManager().getWalk2().play(0.2f);
            GameAssetManager.getGameAssetManager().getWalk3().play(0.2f);
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
