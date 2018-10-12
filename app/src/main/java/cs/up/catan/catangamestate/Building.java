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

    private int intersectionId = 0;

    //default constructor for subclasses
    public Building() {

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
