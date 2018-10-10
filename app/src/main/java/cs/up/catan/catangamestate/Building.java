package cs.up.catan.catangamestate;

import java.util.HashMap;

public class Building extends GameState {

    //ensures the player has enough resources to build the requested building
    String name;
    HashMap<String, Integer> checkResources = new HashMap<>();
    int victoryPoints;

    public Building(){

    }

    public Building(int vicPoints){
        this.victoryPoints = vicPoints;
    }

    @Override
    public String toString() {
        return "The " + name + " gives " + victoryPoints + " Victory Points.\n";
    }
}
