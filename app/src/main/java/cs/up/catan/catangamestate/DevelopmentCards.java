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

    //The description given to each subclass on what the card does
    //MAY CHANGE LATER TO ASSIGN VALUE TO EACH CARD INSTEAD OF STRING
    HashMap<Integer, String> description = new HashMap<>();

    public DevelopmentCards(int totalKnights, int totalVictoryPoints, int totalRoadBuilding, int totalMonopoly, int yearOfPlenty, HashMap<String, Boolean> isPlayable, HashMap<String, Integer> typeOfDevelopment, HashMap<Integer, String> description) {
        this.totalKnights = totalKnights;
        this.totalVictoryPoints = totalVictoryPoints;
        this.totalRoadBuilding = totalRoadBuilding;
        this.totalMonopoly = totalMonopoly;
        this.yearOfPlenty = yearOfPlenty;
        this.isPlayable = isPlayable;
        this.typeOfDevelopment = typeOfDevelopment;
        this.description = description;
    }

    //default constructor
    public DevelopmentCards(){

    }

    @Override
    public String toString() {
        return "There are 5 types of Development Cards: Knight, Victory Points, Road Building, " +
                "Monopoly, and Year Of Plenty";
    }
}
