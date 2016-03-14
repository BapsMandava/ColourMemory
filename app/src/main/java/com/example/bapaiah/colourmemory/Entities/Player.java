package com.example.bapaiah.colourmemory.Entities;

import io.realm.RealmObject;

/**
 * Created by bapaiah on 8/3/2016.
 */
public class Player extends RealmObject {

    private String playerName;
    private int playerScore;



    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
