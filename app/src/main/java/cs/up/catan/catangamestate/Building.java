package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 10th, 2018
 * https://github.com/alexweininger/game-state
 **/

import java.util.HashMap;

public class Building {

    //ensures the player has enough resources to build the requested building
    public String buildingName = "";
    private HashMap<String, Integer> checkResources = new HashMap<>();
    private int victoryPoints = 0;
    private int ownerId = 0;
    private int intersectionId = 0;

    //default constructor for subclasses
    public Building() {

    }

    public Building(String buildingName, HashMap<String, Integer> checkResources, int victoryPoints) {
        this.buildingName = buildingName;
        this.checkResources = checkResources;
        this.victoryPoints = victoryPoints;
    }

    public void build(Player player)
    {
        player.removeResources("Brick",this.checkResources.get("Brick"));
        player.removeResources("Ore",this.checkResources.get("Ore"));
        player.removeResources("Sheep",this.checkResources.get("Sheep"));
        player.removeResources("Wheat",this.checkResources.get("Wheat"));
        player.removeResources("Wood",this.checkResources.get("Wood"));

        //assigns the player's id the building signifying who owns it
        setOwnerId(player.getPlayerId());
        //adds the building to the player's array list of built buildings
        player.addBuilding(this);

        //TODO: mark when a location is taken on the board (Use setIntersectionId(int intersectionId))
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Building{");
        sb.append("buildingName='");
        sb.append(buildingName);
        sb.append('\'');
        sb.append(", checkResources=");
        sb.append(checkResources);
        sb.append(", victoryPoints=");
        sb.append(victoryPoints);
        sb.append(", intersectionId=");
        sb.append(intersectionId);
        sb.append('}');

        return sb.toString();
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
