package com.tilldawn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.SignUpMenuController;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.SignUpMenuView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        App.setBackgroundMusic(Gdx.audio.newMusic(Gdx.files.internal("AudioClip/Pretty Dungeon LOOP.wav")));
        App.getBackgroundMusic().setLooping(true);
        App.getBackgroundMusic().setVolume(0.5f);
        App.getBackgroundMusic().play();

        Pixmap pixmap = new Pixmap(Gdx.files.internal("Sprite/T/T_Cursor.png"));
        int xHotspot = 16;
        int yHotspot = 16;

        Cursor cursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);

        Gdx.graphics.setCursor(cursor);

        pixmap.dispose();
        main.setScreen(new SignUpMenuView(new SignUpMenuController(), GameAssetManager.getGameAssetManager().getMenuSkin()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        if(App.getBackgroundMusic() != null) {
            App.getBackgroundMusic().dispose();
        }

        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }
}
