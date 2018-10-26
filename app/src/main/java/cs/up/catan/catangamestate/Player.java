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
    private HashMap<String, Integer> resources = new HashMap<>(); // k: resource id, v: resource count
    private ArrayList<DevelopmentCard> developmentCards = new ArrayList<>(); // ArrayList of the development cards the player owns
    private HashMap<String, Integer> availableBuildings = new HashMap<>(); // // k: resource id, v: buildings available
    private int armySize; // for the knight trophy and dev card
    private final int playerId;   // player Id
    private static int playerCount = 1; // TODO what is this???

    /**
     * Player constructor
     */
    public Player() {
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
     *
     * @param player -
     */
    public Player(Player player) {
        this.developmentCards = player.developmentCards;
        this.armySize = player.armySize;
        this.resources = player.resources;
        this.playerId = player.playerId;
    } // end deepCopy player cons.

    /**
     * @return the size of the player's army
     */
    public int getArmySize() {
        return armySize;
    }

    /**
     * @param armySize the size of the player's army
     */
    public void setArmySize(int armySize) {
        this.armySize = armySize;
    }

    /**
     * @return string representation of a Player
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Player ");
        sb.append(playerId);
        sb.append("\nResources = ");
        sb.append(this.resources);
        sb.append("\nDevelopment Cards = ");
        sb.append(this.developmentCards);
        sb.append("\n");

        return sb.toString();
    }

    /**
     * @param res name of resource
     * @param num amount to add
     * @return if action was possible
     */
    public boolean addResources(String res, int num) {
        if (this.resources.containsKey(res)) {
            this.resources.put(res, this.resources.get(res) + num);
            return true;
        }
        return false;
    }

    /**
     * @param res name of resource
     * @param num amount to add
     * @return if action was possible
     */
    public boolean removeResources(String res, int num) {
        if (this.resources.containsKey(res)) {
            this.resources.put(res, this.resources.get(res) - num);
            return true;
        }
        return false;
    }

    /**
     * @param devCard dev card to add
     */
    public void addDevCard(DevelopmentCard devCard) {
        developmentCards.add(devCard);
    }

    /**
     * @param res name of resource
     * @param num amount to add
     * @return if action was possible
     */
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

    /**
     * @param devCard dev card to remove
     * @return if action was poissible
     */
    public boolean useDevCard(DevelopmentCard devCard) {
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

    /**
     * @return the player's id
     */
    public int getPlayerId() {
        return this.playerId;
    }

    /**
     * @return number of players
     */
    public static int getPlayerCount() {
        return playerCount;
    }

    /**
     * @return hashmap of resources
     */
    public HashMap<String, Integer> getResources() {
        return resources;
    }

    /**
     * @param resource name of resource
     * @param value    amount of resource
     */
    public void setResources(String resource, int value) {
        this.resources.put(resource, value);
    }
}

