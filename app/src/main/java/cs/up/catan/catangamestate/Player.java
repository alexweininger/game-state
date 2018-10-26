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
    private int playerId;   // player Id
    /**
     * Player constructor
     */
    public Player(int id) {
        this.armySize = 0;
        this.resources.put("Brick", 20);
        this.resources.put("Ore", 20);
        this.resources.put("Sheep", 20);
        this.resources.put("Wheat", 20);
        this.resources.put("Wood", 20);
        this.playerId = id;
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
    }

    /*addBuilding
    *
    * Check if we can add a building to the map; returns if we cannot
    *
    * If we can, it will create the needed building, add it to the list of buildings that
    * this player owns. It will then be added to the board in the specified coordinate
    *
    */
    public boolean addBuilding(Building building) {

        if (!building.hasResources(this.resources)) {
            return false;
        }
        return true;
    }

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
    /*
    public boolean useResource(String res, int num) {
        if (this.resources.containsKey(res)) {
            if (this.resources.get(res) >= num) {
                this.resources.put(res, this.resources.get(res) - num);
                return true;
            }
            return false;
        }
        return false;
    }*/

    /**
     * @param devCard dev card to remove
     * @return if action was possible
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

    public String getRandomCard(){
        ArrayList<String> resourceNames = new ArrayList<>();
        String[] baseResources = {"Brick", "Wool", "Grain", "Ore", "Wood"};
        for (int n = 0; n < resources.size(); n++){
            for (int x = 0; x < baseResources.length; x++) {
                if (resources.containsKey(baseResources[n])){
                    resourceNames.add(baseResources[n]);
                }
            }
        }
        if (resourceNames.size() == 0){
            return "No Cards in this person's hands!";
        }

        String stolenResource = resourceNames.get((int) (Math.random() * resourceNames.size()));
        this.removeResources(stolenResource, 1);
        return stolenResource;
    }



}


