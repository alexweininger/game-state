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

    // resourceCard index values: 0 = Brick, 1 = Ore, 2 = Wool, 3 = Wheat, 4 = Wood
    private int[] resourceCards = new int[5]; // array for number of each resource card a player has

    // array for relating resource card names to resource card ids in the resourceCards array above
    private String[] resourceCardIds = {"Brick", "Ore", "Wool", "Wheat", "Wood"};

    private ArrayList<DevelopmentCard> developmentCards = new ArrayList<>(); // ArrayList of the development cards the player owns
    private HashMap<String, Integer> availableBuildings = new HashMap<>(); // // k: resource id, v: buildings available TODO change data type to better one
    private int armySize; // determined by how many knight dev cards the player has played, used for determining who currently has the largest army trophy
    private int playerId;   // playerId

    /**
     * Player constructor
     */
    Player(int id) {
        // initialize all resource card counts to 0
        for (int i = 0; i < this.resourceCards.length; i++) {
            this.resourceCards[i] = 0;
        }
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
    Player(Player player) {
        this.developmentCards = player.getDevelopmentCards();
        this.armySize = player.getArmySize();
        this.resources = player.getResources();
        this.availableBuildings = player.getAvailableBuildings();
        this.playerId = player.getPlayerId();
        this.resourceCards = player.getResourceCards();
    }

    /**
     * error checking:
     * - checks for valid resourceCardId
     *
     * @param resourceCardId - index value of resource to add (0-4) defined above
     * @param numToAdd       - number of resource cards of this type to add to the players inventory AW
     */
    public void addResourceCard(int resourceCardId, int numToAdd) {
        if (resourceCardId < 0 || resourceCardId >= 5) { // check for a valid resourceCardId
            Log.d("devError", "ERROR addResourceCard: given resourceCardId: " + resourceCardId + " is invalid. Must be an integer (0-4).");
        } else {
            Log.d("devInfo", "INFO addResourceCard: added numToAdd: " + numToAdd + " resourceCardId: " + resourceCardId + " to playerId: " + this.playerId + " resourceCards.");
            this.resourceCards[resourceCardId] += numToAdd; // increase count of the resource card
        }
    }

    /**
     * error checking:
     * - error checks for valid resourceCardId
     * - error checks for preventing negative resource card counts
     *
     * @param resourceCardId - id of resource card to remove from players inventory
     * @param numToRemove    - number of resource cards of this type to remove
     * @return - if numToRemove resource card(s) have been removed from the players inventory
     */
    public boolean removeResourceCard(int resourceCardId, int numToRemove) {
        if (resourceCardId < 0 || resourceCardId >= 5) { // check for valid resourceCardId
            Log.d("devError", "ERROR removeResourceCard: given resourceCardId: " + resourceCardId + " is invalid. Must be an integer (0-4).");
            return false; // did not remove resource cards to players inventory
        } else {
            if (this.resourceCards[resourceCardId] >= numToRemove) { // check to prevent negative card counts
                Log.d("devInfo", "INFO removeResourceCard: removed numToRemove: " + numToRemove + " resourceCardId: " + resourceCardId + " from playerId: " + this.playerId + " resourceCards.");
                this.resourceCards[resourceCardId] -= numToRemove; // remove cards
                return true; // removed cards to players inventory
            } else {
                Log.d("devError", "ERROR removeResourceCard: cannot remove numToRemove: " + numToRemove + " resourceCardId: " + resourceCardId + " from playerId: " + this.playerId + ". Player currently has " + this.resourceCards[resourceCardId] + " cards of this resource.");
                return false; // did not remove resource cards to players inventory
            }
        }
    }

    /**
     * @return String showing the number of each resource card the player has
     */
    public String printResourceCards() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.resourceCards.length; i++) {
            str.append(this.resourceCardIds[i]).append(": ").append(this.resourceCards[i]).append(", ");
        }
        return str.toString();
    }

    public int[] getResourceCards() {
        return this.resourceCards;
    }

    public void setResourceCards(int[] resourceCards) {
        this.resourceCards = resourceCards;
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

    public boolean hasResources(String key, int amount) {
        return resources.get(key).intValue() >= amount;
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

    public String getRandomCard() {
        ArrayList<String> resourceNames = new ArrayList<>();
        String[] baseResources = {"Brick", "Wool", "Grain", "Ore", "Wood"};
        for (int n = 0; n < resources.size(); n++) {
            for (int x = 0; x < baseResources.length; x++) {
                if (resources.containsKey(baseResources[n])) {
                    resourceNames.add(baseResources[n]);
                }
            }
        }
        if (resourceNames.size() == 0) {
            return "No Cards in this person's hands!";
        }
        String stolenResource = resourceNames.get((int) (Math.random() * resourceNames.size()));
        this.removeResources(stolenResource, 1);
        return stolenResource;
    }

    /**
     * @return string representation of a Player
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
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
}


