package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.View.GameView;

import java.util.ArrayList;
import java.util.Random;

public class User {
    private Avatar avatar;
    private String username;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private GameView gameView;
    private int score;
    private Pair<String, Sprite> currentSprite;
    private ArrayList<Pair<String, Texture>> textures;
    private int killCount;
    private int maxTimeSpent;
    private PreGame preGame;

    public User(String username, String password, String securityQuestion, String securityAnswer) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.textures = new ArrayList<>();
        this.textures.add(new Pair<>("Iron_Man", GameAssetManager.getAvatars().get(0)));
        this.textures.add(new Pair<>("Capitan_America", GameAssetManager.getAvatars().get(1)));
        Pair<String, Texture> randomTexture = this.getTextures().get((new Random()).nextInt(this.getTextures().size()));
        this.currentSprite = new Pair<>(randomTexture.getKey(), new Sprite(randomTexture.getValue()));
        this.currentSprite.getValue().setSize(
            this.currentSprite.getValue().getWidth() / 30,
            this.currentSprite.getValue().getHeight() / 30);

        this.gameView = null;
        this.score = 0;
        this.killCount = 0;
        this.maxTimeSpent = 0;
        this.preGame = new PreGame();
        this.avatar = Avatar.SHANA;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public int getScore() {
        return score;
    }

    public Pair<String, Sprite> getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(Pair<String, Sprite> currentSprite) {
        this.currentSprite = currentSprite;
        this.currentSprite.getValue().setSize(
            App.getCurrentUser().getCurrentSprite().getValue().getWidth() / 30,
            App.getCurrentUser().getCurrentSprite().getValue().getHeight() / 30);
    }

    public int getKillCount() {
        return killCount;
    }

    public int getMaxTimeSpent() {
        return maxTimeSpent;
    }

    public PreGame getPreGame() {
        return preGame;
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public void updateMaxTimeSpent(int maxTimeSpent) {
        this.maxTimeSpent = Math.max(maxTimeSpent, this.maxTimeSpent);
    }

    public ArrayList<Pair<String, Texture>> getTextures() {
        return textures;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Texture findTextures(String selected) {
        for (Pair<String, Texture> texture : textures) {
            if(texture.getKey().equals(selected)) {
                return texture.getValue();
            }
        }
        return null;
    }
}
