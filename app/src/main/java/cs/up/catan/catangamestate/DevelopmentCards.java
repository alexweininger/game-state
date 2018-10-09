package cs.up.catan.catangamestate;

import java.util.HashMap;

public class DevelopmentCards extends GameState {

    private int totalKnights;
    private int totalVictoryPoints;
    private int totalRoadBuilding;
    private int totalMonopoly;
    private int yearOfPlenty;

    //checks to see if the subclasses are playable
    HashMap<String, Boolean> isPlayable = new HashMap<>();

    //sees the type of development card and the amount of that card available
    HashMap<String, Integer> typeOfDevelopment = new HashMap<>();


    public DevelopmentCards(){

    }
}
