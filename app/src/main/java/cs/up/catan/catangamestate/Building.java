package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 10th, 2018
 * https://github.com/alexweininger/game-state
 **/

import java.util.HashMap;
import java.util.Iterator;

public class Building {

    //ensures the player has enough resources to build the requested building
    public String buildingName = "";
    private HashMap<String, Integer> reqResources = new HashMap<>();
    private int victoryPoints = 0;
    private int ownerId = 0;
    private int intersectionId = 0;

    //default constructor for subclasses
    //No values initialized; this is to be overrided by subclasses
    public Building() {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Building{");
        sb.append("buildingName='");
        sb.append(buildingName);
        sb.append('\'');
        sb.append(", reqResources=");
        sb.append(reqResources);
        sb.append(", victoryPoints=");
        sb.append(victoryPoints);
        sb.append(", intersectionId=");
        sb.append(intersectionId);
        sb.append('}');

        return sb.toString();
    }

    /*build
     *
     * Build a building; overrided by subclasses to specify which resources to take. It will be
     * Iterating through the reqResources HashMap to taking the needed values from the player
     * resources HashMap.
     *
     * Once resources are taken
     *
     */
    public void build(Player player)
    {
        for(HashMap.Entry<String, Integer> entry: reqResources.entrySet()){
            if(entry.getValue() < reqResources.get(entry.getKey())){
                player.removeResources(entry.getKey(), entry.getValue());
            }
        }

        //assigns the player's id the building signifying who owns it
        setOwnerId(player.getPlayerId());
        //adds the building to the player's array list of built buildings
        player.addBuilding(this);

        //TODO: mark when a location is taken on the board (Use setIntersectionId(int intersectionId))
    }

    /*hasResources
     *
     * Iterates through reqResources HashMap and checks the Key values in the resources
     * HashMap to see if there is a sufficient amount of resources to build the called building.
     *
     * Return true if there are enough resources; return false if otherwise
     *
     */
    public boolean hasResources(HashMap<String, Integer> resources){    //TODO Logic needs to be tested
        for(HashMap.Entry<String, Integer> entry: reqResources.entrySet()){
            if(entry.getValue() < reqResources.get(entry.getKey())){
                return false;
            }
        }
        return true;
    }


    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getIntersectionId() {
        return intersectionId;
    }

    public void setIntersectionId(int intersectionId) {
        this.intersectionId = intersectionId;
    }
}
