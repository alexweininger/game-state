package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 10th, 2018
 * <p>
 * https://github.com/alexweininger/game-state
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    //instance variables
    private int localScore; // score of player that accounts for the players dev cards, must greater or equal to global score
    private int publicScore;
    private HashMap<String, Integer> resources = new HashMap<String, Integer>(); // k: resource id, v: resource count
    private ArrayList<Integer> developmentCards = new ArrayList<Integer>(); // ArrayList of the development card IDs the player owns
    private int playerId;   //Player ID
    private static int playerCount = 1;

    // constructor
    public Player() {
        this.localScore = 2;
        this.publicScore = 4;
        this.resources.put("Logs", 3);
        this.resources.put("Bricks", 1);
        this.resources.put("Ore", 1);
        this.resources.put("Wool", 2);
        this.developmentCards.add(1);
        this.playerId = playerCount;
        playerCount++;
    }

    public Player(Player player) {
        this.publicScore = player.publicScore;
        this.localScore = player.localScore;
        this.developmentCards = player.developmentCards;
        this.resources = player.resources;
        this.playerId = player.playerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Player ");
        sb.append(playerId);
        sb.append(": \nPrivate Score = ");
        sb.append(this.localScore);
        sb.append("\nPublic score = ");
        sb.append(this.publicScore);
        sb.append("\nResources = ");
        sb.append(this.resources);
        sb.append("\nDevelopment Cards = ");
        sb.append(this.developmentCards);
        sb.append("\n");

        return sb.toString();
    }
}
