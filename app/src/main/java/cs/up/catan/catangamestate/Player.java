package cs.up.catan.catangamestate;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 25th, 2018
 * <p>
 * https://github.com/alexweininger/game-state
 **/

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    /* Player instance variables */
    private HashMap<String, Integer> resources = new HashMap<>(); // k: resource id, v: resource count
    private int[] resourceCards = new int[5]; // array for number of each resource card a player has

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
        this.resources.put("Wool", 20);
        this.resources.put("Wheat", 20);
        this.resources.put("Wood", 20);
        this.playerId = id;
    }

    /**
     * deepCopy constructor
     *
     * @param player - Player object to copy
     */
    public Player(Player player) {
        this.developmentCards = player.getDevelopmentCards();
        this.armySize = player.getArmySize();
        this.resources = player.getResources();
        this.availableBuildings = player.getAvailableBuildings();
        this.playerId = player.getPlayerId();
    }

    public void addResourceCard(int resourceCardId, int numToAdd) {
        if (resourceCardId < 0 || resourceCardId >= 5) {
            Log.d("devError", "ERROR addResourceCard: given resourceCardId: " + resourceCardId + " is invalid. Must be an integer (0-4).");
        }
        this.resourceCards[resourceCardId] += numToAdd;
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
        sb.append("\navailableBuildings = ");
        sb.append(availableBuildings);
        sb.append("\narmySize = ");
        sb.append(armySize);
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

    public boolean hasResources(String key, int amount){
        if(resources.get(key).intValue() < amount) {
            return false;
        }
        return true;
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

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }

    public HashMap<String, Integer> getAvailableBuildings() {
        return availableBuildings;
    }

    public void setAvailableBuildings(HashMap<String, Integer> availableBuildings) {
        this.availableBuildings = availableBuildings;
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


