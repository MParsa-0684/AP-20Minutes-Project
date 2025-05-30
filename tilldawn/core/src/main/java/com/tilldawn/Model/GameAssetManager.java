package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private static final Skin menuSkin = new Skin(Gdx.files.internal("skin/tracer-ui.json"));
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final ArrayList<ArrayList<Texture>> shana = createHeroTextures(new String[][]{
        {
            "Heros/Shana/idle/Idle_0 #8330.png",
            "Heros/Shana/idle/Idle_1 #8360.png",
            "Heros/Shana/idle/Idle_2 #8819.png",
            "Heros/Shana/idle/Idle_3 #8457.png",
            "Heros/Shana/idle/Idle_4 #8318.png",
            "Heros/Shana/idle/Idle_5 #8307.png"
        },
        {
            "Heros/Shana/walk/Walk_0 #8278.png",
            "Heros/Shana/walk/Walk_1 #8632.png",
            "Heros/Shana/walk/Walk_2 #8711.png",
            "Heros/Shana/walk/Walk_3 #8769.png",
            "Heros/Shana/walk/Walk_5 #8688.png",
            "Heros/Shana/walk/Walk_6 #8442.png",
            "Heros/Shana/walk/Walk_7 #8214.png"
        },
        {
            "Heros/Shana/run/Run_0 #8762.png",
            "Heros/Shana/run/Run_1 #8778.png",
            "Heros/Shana/run/Run_2 #8286.png",
            "Heros/Shana/run/Run_3 #8349.png"
        }
    });


    private final ArrayList<ArrayList<Texture>> diamond = createHeroTextures(new String[][]{
        {
            "Heros/Diamond/idle/Idle_0 #8328.png",
            "Heros/Diamond/idle/Idle_1 #8358.png",
            "Heros/Diamond/idle/Idle_2 #8817.png",
            "Heros/Diamond/idle/Idle_3 #8455.png",
            "Heros/Diamond/idle/Idle_4 #8316.png",
            "Heros/Diamond/idle/Idle_5 #8305.png"
        },
        {
            "Heros/Diamond/walk/Walk_0 #8277.png",
            "Heros/Diamond/walk/Walk_1 #8631.png",
            "Heros/Diamond/walk/Walk_2 #8710.png",
            "Heros/Diamond/walk/Walk_3 #8768.png",
            "Heros/Diamond/walk/Walk_4 #8464.png",
            "Heros/Diamond/walk/Walk_5 #8687.png",
            "Heros/Diamond/walk/Walk_6 #8441.png",
            "Heros/Diamond/walk/Walk_7 #8213.png"
        },
        {
            "Heros/Diamond/run/Run_0 #8760.png",
            "Heros/Diamond/run/Run_1 #8776.png",
            "Heros/Diamond/run/Run_2 #8284.png",
            "Heros/Diamond/run/Run_3 #8347.png"
        }
    });




    private final ArrayList<ArrayList<Texture>> scarlet =     createHeroTextures(new String[][]{
        {
            "Heros/Scarlet/idle/Idle_0 #8327.png",
            "Heros/Scarlet/idle/Idle_1 #8357.png",
            "Heros/Scarlet/idle/Idle_2 #8816.png",
            "Heros/Scarlet/idle/Idle_3 #8454.png",
            "Heros/Scarlet/idle/Idle_4 #8315.png",
            "Heros/Scarlet/idle/Idle_5 #8304.png"
        },
        {
            "Heros/Scarlet/run/Run_0 #8759.png",
            "Heros/Scarlet/run/Run_1 #8775.png",
            "Heros/Scarlet/run/Run_2 #8283.png",
            "Heros/Scarlet/run/Run_3 #8346.png"
        },
        {
            "Heros/Scarlet/run/Run_0 #8759.png",
            "Heros/Scarlet/run/Run_1 #8775.png",
            "Heros/Scarlet/run/Run_2 #8283.png",
            "Heros/Scarlet/run/Run_3 #8346.png"
        }
    });



    private final ArrayList<ArrayList<Texture>> lilith = createHeroTextures(new String[][]{
        {
            "Heros/Lilith/idle/Idle_0 #8333.png",
            "Heros/Lilith/idle/Idle_1 #8363.png",
            "Heros/Lilith/idle/Idle_2 #8822.png",
            "Heros/Lilith/idle/Idle_3 #8460.png",
            "Heros/Lilith/idle/Idle_4 #8321.png",
            "Heros/Lilith/idle/Idle_5 #8310.png"
        },
        {
            "Heros/Lilith/walk/Walk_0 #8279.png",
            "Heros/Lilith/walk/Walk_1 #8633.png",
            "Heros/Lilith/walk/Walk_2 #8712.png",
            "Heros/Lilith/walk/Walk_3 #8770.png",
            "Heros/Lilith/walk/Walk_4 #8465.png",
            "Heros/Lilith/walk/Walk_5 #8689.png",
            "Heros/Lilith/walk/Walk_6 #8443.png",
            "Heros/Lilith/walk/Walk_7 #8215.png"
        },
        {
            "Heros/Lilith/run/Run_0 #8765.png",
            "Heros/Lilith/run/Run_1 #8781.png",
            "Heros/Lilith/run/Run_2 #8289.png",
            "Heros/Lilith/run/Run_3 #8352.png"
        }
    });

    private final ArrayList<ArrayList<Texture>> dasher =     createHeroTextures(new String[][]{
        {
            "Heros/Dasher/idle/Idle_0 #8325.png",
            "Heros/Dasher/idle/Idle_1 #8355.png",
            "Heros/Dasher/idle/Idle_2 #8814.png",
            "Heros/Dasher/idle/Idle_3 #8452.png",
            "Heros/Dasher/idle/Idle_4 #8313.png",
            "Heros/Dasher/idle/Idle_5 #8302.png"
        },
        {
            "Heros/Dasher/run/Run_0 #8757.png",
            "Heros/Dasher/run/Run_1 #8773.png",
            "Heros/Dasher/run/Run_2 #8281.png",
            "Heros/Dasher/run/Run_3 #8344.png"
        },
        {
            "Heros/Dasher/run/Run_0 #8757.png",
            "Heros/Dasher/run/Run_1 #8773.png",
            "Heros/Dasher/run/Run_2 #8281.png",
            "Heros/Dasher/run/Run_3 #8344.png"
        }
    });

    private final ArrayList<Texture> revolver = new ArrayList<>(Arrays.asList(
        new Texture("Sprite/RevolverStill/RevolverStill.png"),
        new Texture("Sprite/RevolverReload/RevolverReload_0.png"),
        new Texture("Sprite/RevolverReload/RevolverReload_1.png"),
        new Texture("Sprite/RevolverReload/RevolverReload_2.png"),
        new Texture("Sprite/RevolverReload/RevolverReload_3.png")
    ));

    private final ArrayList<Texture> shotgun = new ArrayList<>(Arrays.asList(
        new Texture("Sprite/T/T_Shotgun_SS_0.png"),
        new Texture("Sprite/T/T_Shotgun_SS_1.png"),
        new Texture("Sprite/T/T_Shotgun_SS_2.png"),
        new Texture("Sprite/T/T_Shotgun_SS_3.png")
    ));

    private final ArrayList<Texture> smgDual = new ArrayList<>(Arrays.asList(
        new Texture("Sprite/T/T_DualSMGs_Icon.png"),
        new Texture("Sprite/SMGReload/SMGReload_0.png"),
        new Texture("Sprite/SMGReload/SMGReload_1.png"),
        new Texture("Sprite/SMGReload/SMGReload_2.png"),
        new Texture("Sprite/SMGReload/SMGReload_3.png")
    ));

    private final ArrayList<Texture> tree = new ArrayList<>(Arrays.asList(
        new Texture("Sprite/T/T_TreeMonster_0.png"),
        new Texture("Sprite/T/T_TreeMonster_1.png"),
        new Texture("Sprite/T/T_TreeMonster_2.png"),
        new Texture("Sprite/T/T_TreeMonster_2.png")
    ));

    private final ArrayList<Texture> tentacle_monster = new ArrayList<>(Arrays.asList(
        new Texture("Sprite/TentacleIdle/TentacleIdle0.png"),
        new Texture("Sprite/TentacleIdle/TentacleIdle1.png"),
        new Texture("Sprite/TentacleIdle/TentacleIdle2.png"),
        new Texture("Sprite/TentacleIdle/TentacleIdle3.png"),
        new Texture("Sprite/TentacleSpawn/TentacleSpawn0.png"),
        new Texture("Sprite/TentacleSpawn/TentacleSpawn1.png"),
        new Texture("Sprite/TentacleSpawn/TentacleSpawn2.png"),
        new Texture("Sprite/TentacleAttack/TentacleAttack.png"),
        new Texture("Sprite/T/T_Cultist_EM.png")
    ));

    private final ArrayList<Texture> eyebat = new ArrayList<>(Arrays.asList(
        new Texture("Sprite/T/T_EyeBat_0.png"),
        new Texture("Sprite/T/T_EyeBat_1.png"),
        new Texture("Sprite/T/T_EyeBat_2.png"),
        new Texture("Sprite/T/T_EyeBat_3.png"),
        new Texture("Sprite/T/T_EyeBat_EM.png")
    ));

    private final ArrayList<Texture> elder = new ArrayList<>(Arrays.asList(
        new Texture("Sprite/ElderBrain/ElderBrain.png")
    ));

    private final Texture map = new Texture("Sprite/Map/map.png");

    private final Texture bullet = new Texture("Sprite/Icon/Icon_Bullet_Storm.png");

    private final Texture seed = new Texture("Sprite/Seed/xp.png");

    private final Texture enemyBullet = new Texture("Sprite/Icon/Icon_LightBullet.png");

    private final ArrayList<Texture> abilities = new ArrayList<>(Arrays.asList(
        new Texture("Sprite/Ability/Vitality.png"),
        new Texture("Sprite/Ability/Damager.png"),
        new Texture("Sprite/Ability/Procrease.png"),
        new Texture("Sprite/Ability/Amocrease.png"),
        new Texture("Sprite/Ability/Speedy.png")
    ));

    private static final ArrayList<Texture> avatars = new ArrayList<>(Arrays.asList(
        new Texture("Sprite/Avatar/IronMan_Scaled.png"),
        new Texture("Sprite/Avatar/CaptainAmerica_Scaled.png")
    ));

    private static final Texture cursor = new Texture("Sprite/T/T_Cursor.png");

    private static final Texture shadow = new Texture("Sprite/Shader/Untitled-1.png");

    private final ArrayList<Texture> death = new ArrayList<>(Arrays.asList(
        new Texture("Sprite/DeathFX/DeathFX_0.png"),
        new Texture("Sprite/DeathFX/DeathFX_1.png"),
        new Texture("Sprite/DeathFX/DeathFX_2.png"),
        new Texture("Sprite/DeathFX/DeathFX_3.png")
    ));

    private final Texture playerDeath = new Texture("Sprite/Death/Icon_DeathPlague.png");

    private final Sprite background = new Sprite(new Texture("Sprite/Background/background.png"));

    private final Sound enemyKilled = Gdx.audio.newSound(Gdx.files.internal("AudioClip/Blood_Splash_Quick_01.wav"));

    private final Sound playerKilled = Gdx.audio.newSound(Gdx.files.internal("AudioClip/Explosion_Blood_01.wav"));

    private final Sound coins = Gdx.audio.newSound(Gdx.files.internal("AudioClip/Coins (10).wav"));

    private final Sound levelUp = Gdx.audio.newSound(Gdx.files.internal("AudioClip/Special & Powerup (8).wav"));

    private final Sound walk1 = Gdx.audio.newSound(Gdx.files.internal("AudioClip/Footsteps_Casual_Grass_01.wav"));

    private final Sound walk2 = Gdx.audio.newSound(Gdx.files.internal("AudioClip/Footsteps_Casual_Grass_02.wav"));

    private final Sound walk3 = Gdx.audio.newSound(Gdx.files.internal("AudioClip/Footsteps_Casual_Grass_03.wav"));

    private final Sound shoot = Gdx.audio.newSound(Gdx.files.internal("AudioClip/single_shot.wav"));

    private final ArrayList<Texture> heros = new ArrayList<>(Arrays.asList(
        new Texture("Sprite/T/T_Shana_Portrait.png"),
        new Texture("Sprite/T/T_Diamond_Portrait.png"),
        new Texture("Sprite/T/T_Scarlett_Portrait.png"),
        new Texture("Sprite/T/T_Lilith_Portrait.png"),
        new Texture("Sprite/T/T_Dasher_Portrait.png")
    ));

    public static GameAssetManager getGameAssetManager() {
        if(gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public void setColorFunction() {
        if(App.getCurrentUser().getPreGame().getGameColor() == Color.BLACK) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, 0.5f);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
    }

    public Skin getMenuSkin() {
        return menuSkin;
    }

    private static ArrayList<ArrayList<Texture>> createHeroTextures(String[][] paths) {
        ArrayList<ArrayList<Texture>> textures = new ArrayList<>();
        for (String[] group : paths) {
            ArrayList<Texture> animationFrames = new ArrayList<>();
            for (String path : group) {
                animationFrames.add(new Texture(path));
            }
            textures.add(animationFrames);
        }
        return textures;
    }

    public ArrayList<ArrayList<Texture>> getShananaTextures() {
        return shana;
    }

    public ArrayList<ArrayList<Texture>> getDiamondTextures() {
        return diamond;
    }

    public ArrayList<ArrayList<Texture>> getScarletTextures() {
        return scarlet;
    }

    public ArrayList<ArrayList<Texture>> getLilithTextures() {
        return lilith;
    }

    public ArrayList<ArrayList<Texture>> getDasherTextures() {
        return dasher;
    }

    public ArrayList<Texture> getTree() {
        return tree;
    }

    public ArrayList<Texture> getRevolver() {
        return revolver;
    }

    public ArrayList<Texture> getShotgun() {
        return shotgun;
    }

    public ArrayList<Texture> getSmgDual() {
        return smgDual;
    }

    public ArrayList<Texture> getTentacle_monster() {
        return tentacle_monster;
    }

    public ArrayList<Texture> getEyebat() {
        return eyebat;
    }

    public ArrayList<Texture> getElder() {
        return elder;
    }

    public Texture getMap() {
        return map;
    }

    public Texture getBullet() {
        return bullet;
    }

    public Texture getSeed() {
        return seed;
    }

    public Texture getEnemyBullet() {
        return enemyBullet;
    }

    public ArrayList<Texture> getAbilities() {
        return abilities;
    }

    public static ArrayList<Texture> getAvatars() {
        return avatars;
    }

    public static Texture getCursor() {
        return cursor;
    }

    public static Texture getShadow() {
        return shadow;
    }

    public ArrayList<Texture> getDeath() {
        return death;
    }

    public Texture getPlayerDeath() {
        return playerDeath;
    }

    public Sprite getBackground() {
        return background;
    }

    public Sound getEnemyKilled() {
        return enemyKilled;
    }

    public Sound getPlayerKilled() {
        return playerKilled;
    }

    public Sound getCoins() {
        return coins;
    }

    public Sound getLevelUp() {
        return levelUp;
    }

    public Sound getWalk1() {
        return walk1;
    }

    public Sound getWalk2() {
        return walk2;
    }

    public Sound getWalk3() {
        return walk3;
    }

    public Sound getShoot() {
        return shoot;
    }

    public ArrayList<Texture> getHeros() {
        return heros;
    }
}

