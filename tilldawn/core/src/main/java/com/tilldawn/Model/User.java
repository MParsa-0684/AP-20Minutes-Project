package com.tilldawn.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class User {
    private String username;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private Avatar avatar;
    private Game game;
    private int score;
    private Sprite currentSprite;
    private int killCount;
    private int maxTimeSpent;

    public User(String username, String password, String securityQuestion, String securityAnswer, Avatar avatar) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.avatar = avatar;
        this.currentSprite = avatar.getSprites().get(0).get(0);
        this.game = null;
        this.score = 0;
        this.killCount = 0;
        this.maxTimeSpent = 0;
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

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getScore() {
        return score;
    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(Sprite currentSprite) {
        this.currentSprite = currentSprite;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getMaxTimeSpent() {
        return maxTimeSpent;
    }
}
