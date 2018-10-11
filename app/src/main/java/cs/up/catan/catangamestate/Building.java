package cs.up.catan.catangamestate;

import java.util.HashMap;

public class Building extends GameState {

    //ensures the player has enough resources to build the requested building
    public String buildingName = "";
    private HashMap<String, Integer> checkResources = new HashMap<>();
    private int victoryPoints = 0;

    private int intersectionId = 0;

    //default constructor for subclasses
    public Building(){

    }

    public Building(String buildingName, HashMap<String, Integer> checkResources, int victoryPoints) {
        this.buildingName = buildingName;
        this.checkResources = checkResources;
        this.victoryPoints = victoryPoints;
    }

    @Override
    public String toString() {
        return "Building{" + "buildingName='" + buildingName + '\'' + ", checkResources=" + checkResources +
                ", victoryPoints=" + victoryPoints + ", intersectionId=" + intersectionId + '}';
    }
}
