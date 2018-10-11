package cs.up.catan.catangamestate;

import java.util.ArrayList;

public class GameState {

    Dice dice = new Dice();
    int currentPlayer;
    int currentDiceSum;
    ArrayList<Player> playerList;


    public GameState() {

    }
    @Override
    public String toString() {
        return "";
    }
}
