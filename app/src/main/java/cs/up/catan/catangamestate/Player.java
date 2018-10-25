package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 25th, 2018
 * <p>
 * https://github.com/alexweininger/game-state
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    /* Player instance variables */
    private int localScore; // score of player that accounts for the players dev cards, must greater or equal to global score
    private int publicScore;
    private HashMap<String, Integer> resources = new HashMap<String, Integer>(); // k: resource id, v: resource count
    private ArrayList<DevelopmentCards> developmentCards = new ArrayList<DevelopmentCards>(); // ArrayList of the development cards the player owns
    private HashMap<String, Integer> availableBuildings = new HashMap<>(); // // k: resource id, v: buildings available
    private int armySize; // for the knight card
    private int playerId;   // player ID
    private static int playerCount = 1;

    /** Player constructor
     *
     */
    public Player() {
        this.localScore = 2;
        this.publicScore = 4;
        this.armySize = 0;
        this.resources.put("Brick", 1);
        this.resources.put("Ore", 1);
        this.resources.put("Sheep", 2);
        this.resources.put("Wheat", 0);
        this.resources.put("Wood", 3);
        this.playerId = playerCount;
        playerCount++;
    }

    /**
     * deepCopy constructor
     * @param player -
     */
    public Player(Player player) {
        this.publicScore = player.publicScore;
        this.localScore = player.localScore;
        this.developmentCards = player.developmentCards;
        this.armySize = player.armySize;
        this.resources = player.resources;
        this.playerId = player.playerId;
    } // end deepCopy player cons.

    public int getArmySize() {
        return armySize;
    }

    public void setArmySize(int armySize) {
        this.armySize = armySize;
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

    public boolean addResources(String res, int num) {
        if (this.resources.containsKey(res)) {
            this.resources.put(res, this.resources.get(res) + num);
            return true;
        }
        return false;
    }

    public boolean removeResources(String res, int num) {
        if (this.resources.containsKey(res)) {
            this.resources.put(res, this.resources.get(res) - num);
            return true;
        }
        return false;
    }

    public void addDevCard(DevelopmentCards devCard) {
        developmentCards.add(devCard);
    }

    public boolean useResource(String res, int num) {
        if (this.resources.containsKey(res)) {
            if (this.resources.get(res) >= num) {
                this.resources.put(res, this.resources.get(res) - num);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean useDevCard(DevelopmentCards devCard) {
        if (developmentCards.contains(devCard)) {
            developmentCards.remove(devCard);
            return true;
        }
        return false;
    }

    //use to allow the player to use the dev card they built the turn prior
    public void setDevelopmentCardsAsPlayable() {
        for (int i = 0; i < developmentCards.size(); i++) {
            developmentCards.get(i).setPlayable(true);
        }
    }

    public int getLocalScore() {
        return localScore;
    }

    public void setLocalScore(int localScore) {
        this.localScore = localScore;
    }

    public int getPublicScore() {
        return publicScore;
    }

    public void setPublicScore(int publicScore) {
        this.publicScore = publicScore;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(int id) {
        this.playerId = id;
    }

    public static int getPlayerCount() {
        return playerCount;
    }

    public static void setPlayerCount(int playerCount) {
        Player.playerCount = playerCount;
    }
}

