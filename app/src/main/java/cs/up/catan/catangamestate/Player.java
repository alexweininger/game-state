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
    }

    /*addBuilding
    *
    * Check if we can add a building to the map; returns if we cannot
    *
    * If we can, it will create the needed building, add it to the list of buildings that
    * this player owns. It will then be added to the board in the specified coordinate
    *
    */
    public boolean addBuilding(Building building)
    {

        if(!building.hasResources(this.resources)){
            return false;
        }
        buidlingsBuilt.add(building);

    /**
     *
     * @return the size of the player's army
     */
    public int getArmySize() {
        return armySize;
    }

    /**
     *
     * @param armySize the size of the player's army
     */
    public void setArmySize(int armySize) {
        this.armySize = armySize;
    }

    /**
     *
     * @return string representation of a Player
     */
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

    /**
     *
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
     *
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
     *
     * @param devCard  dev card to add
     */
    public void addDevCard(DevelopmentCards devCard) {
        developmentCards.add(devCard);
    }

    /**
     *
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
     *
     * @param devCard dev card to remove
     * @return if action was poissible
     */
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

    /**
     *
     * @return the players local score
     */
    public int getLocalScore() {
        return localScore;
    }

    /**
     *
     * @param localScore thre players local score
     */
    public void setLocalScore(int localScore) {
        this.localScore = localScore;
    }

    /**
     *
     * @return the player's public score
     */
    public int getPublicScore() {
        return publicScore;
    }

    /**
     *
     * @param publicScore the player's public score
     */
    public void setPublicScore(int publicScore) {
        this.publicScore = publicScore;
    }

    /**
     *
     * @return the player's id
     */
    public int getPlayerId() {
        return this.playerId;
    }

    /**
     *
     * @param id the player's id
     */
    public void setPlayerId(int id) {
        this.playerId = id;
    }

    /**
     *
     * @return number of players
     */
    public static int getPlayerCount() {
        return playerCount;
    }

    /**
     *
     * @param playerCount number of players
     */
    public static void setPlayerCount(int playerCount) {
        Player.playerCount = playerCount;
    }

    /**
     *
     * @return hashmap of resources
     */
    public HashMap<String, Integer> getResources() {
        return resources;
    }

    /**
     *
     * @param resource name of resource
     * @param value amount of resource
     */
    public void setResources(String resource, int value) {
        this.resources.put(resource,value);
    }
}

