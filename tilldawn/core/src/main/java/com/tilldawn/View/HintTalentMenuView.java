package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.HintTalentMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.AbilityType;
import com.tilldawn.Model.App;
import com.tilldawn.Model.Avatar;
import com.tilldawn.Model.GameAssetManager;

import java.util.ArrayList;
import java.util.Arrays;

public class HintTalentMenuView implements Screen {
    private HintTalentMenuController controller;
    private Skin skin;
    private Stage stage;
    private Label titleLabel;
    private Label heroLabel;
    private Label keysLabel;
    private Label cheatLabel;
    private Label abilityLabel;
    private Table titleTable;
    private Table heroTable;
    private Table abilityTable;
    private ScrollPane heroScrollPane;
    private ScrollPane keysScrollPane;
    private ScrollPane cheatScrollPane;
    private ScrollPane abilityScrollPane;
    private TextButton backButton;

    public HintTalentMenuView(HintTalentMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;

        // Labels
        titleLabel = new Label("", skin);
        heroLabel = new Label("", skin);
        keysLabel = new Label("", skin);
        cheatLabel = new Label("", skin);
        abilityLabel = new Label("", skin);
        titleLabel.setScale(2f);
        heroLabel.setScale(2f);
        keysLabel.setScale(2f);
        cheatLabel.setScale(2f);
        abilityLabel.setScale(2f);

        // Tables
        titleTable = new Table();
        heroTable = new Table();
        abilityTable = new Table();

        // ScrollPanes
        heroScrollPane = new ScrollPane(null, skin);
        keysScrollPane = new ScrollPane(null, skin);
        cheatScrollPane = new ScrollPane(null, skin);
        abilityScrollPane = new ScrollPane(null, skin);
        backButton = new TextButton("Back", skin);



        controller.setView(this);
    }

    @Override
    public void show() {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        titleLabel.setText("Hint Menu (Talent)");
        titleTable.add(titleLabel).padTop(30).colspan(3).row();

        heroLabel.setText("Heroes");
        for (int i = 0; i < 5; i++) {
            Sprite sprite = new Sprite(GameAssetManager.getGameAssetManager().getHeros().get(i));
            ImageButton icon = new ImageButton(new TextureRegionDrawable(sprite));
            icon.setSize(sprite.getWidth() / 2f, sprite.getHeight() / 2f);
            heroTable.add(icon);
        }

        heroTable.row();
        for (int i = 0; i < 5; i++) {
            Label properties = new Label("Name : " + Avatar.values()[i].getName() + "\n" +
                "HP : " + Avatar.values()[i].getHP() + "\n" +
                "Speed: " + Avatar.values()[i].getSpeed(), skin);
            heroTable.add(properties);
        }
        heroScrollPane.setActor(heroTable);

        ArrayList<String> directions = new ArrayList<>(Arrays.asList("Up", "Left", "Down", "Right"));
        keysLabel.setText("Display game keys");
        List<String> keysList = new List<>(skin);
        Array<String> list = new Array<>();
        for (int i = 0; i < 4; i++) {
            list.add(directions.get(i) + ": " +
                App.getCurrentUser().getPreGame().getGameKeys().split("-")[i]);
        }
        keysList.setItems(list);
        keysScrollPane.setActor(keysList);

        cheatLabel.setText("Display cheat codes and their effects");
        List<String> cheatList = new List<>(skin);
        cheatList.setItems("Decrease Time: 1","Level Up : 2","Increase Health : 3", "Boss fight: 4", "Infinite Ammo : 5");
        this.cheatScrollPane = new ScrollPane(cheatList, skin);

        abilityLabel.setText("Display the effectiveness of game abilities");
        abilityTable.defaults().pad(4).size(50, 50);
        List<String> abilityList = new List<>(skin);
        Array<String> list2 = new Array<>();
        for (AbilityType value : AbilityType.values()) {
            list2.add(value.name() + ": " + value.getProperties());
        }
        abilityList.setItems(list2);
        abilityScrollPane.setActor(abilityList);

        titleTable.setFillParent(true);
        titleTable.top().left();

        titleTable.add(heroLabel).padTop(10).center().colspan(3).row();
        titleTable.add(heroScrollPane).padTop(10).padBottom(20).center().height(400).colspan(3).row();
        titleTable.add(keysLabel).padTop(20).padLeft(150).left();
        titleTable.add(cheatLabel).padTop(20).left();
        titleTable.add(abilityLabel).padTop(20).left().row();
        titleTable.add(keysScrollPane).padTop(20).left().height(100).padLeft(150).expandX();
        titleTable.add(cheatScrollPane).padTop(20).left().height(100).expandX();
        titleTable.add(abilityScrollPane).padTop(20).left().height(100).expandX().row();
        titleTable.add(backButton).padTop(30).center().colspan(3).row();

        stage.addActor(titleTable);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(35 / 255f, 29 / 255f, 42 / 255f, 1);

        Main.getBatch().begin();
        Main.getBatch().end();

        GameAssetManager.getGameAssetManager().setColorFunction();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleHintTalent();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public TextButton getBackButton() {
        return backButton;
    }
}
