package cs.up.catan.catangamestate;

import java.util.ArrayList;
import java.util.HashMap;

public class DevelopmentCards extends GameState {

    private int totalKnights;
    private int totalVictoryPoints;
    private int totalRoadBuilding;
    private int totalMonopoly;
    private int totalYearOfPlenty;

    //default instance variable
    private String knightName = "Knight";
    private String victoryPointsName = "Victory Points";
    private String roadBuildingName = "Road Building";
    private String monopolyName = "Monopoly";
    private String yearOfPlentyName = "Year of Plenty";

    //checks to see if the subclasses are playable
    HashMap<String, Boolean> isPlayable = new HashMap<>();

    //sees the type of development card and the amount of that card available
    HashMap<String, Integer> typeOfDevelopment = new HashMap<>();

    //The description given to each subclass on what the card does
    //MAY CHANGE LATER TO ASSIGN VALUE TO EACH CARD INSTEAD OF STRING
    HashMap<Integer, String> description = new HashMap<>();

    // hashmap connecting ids to a specifc card

    public DevelopmentCards(int totalKnights, int totalVictoryPoints, int totalRoadBuilding, int totalMonopoly, int totalYearOfPlenty, HashMap<String, Boolean> isPlayable, HashMap<String, Integer> typeOfDevelopment, HashMap<Integer, String> description) {
        this.totalKnights = totalKnights;
        this.totalVictoryPoints = totalVictoryPoints;
        this.totalRoadBuilding = totalRoadBuilding;
        this.totalMonopoly = totalMonopoly;
        this.totalYearOfPlenty = totalYearOfPlenty;
        this.isPlayable = isPlayable;
        this.typeOfDevelopment = typeOfDevelopment;
        this.description = description;
        this.typeOfDevelopment = typeOfDevelopment;
    }

    //default constructor
    public DevelopmentCards(){
    }

    public int getRandomCard(){
        //probabilities
        int countKnight = 14;
        int countVicPoints = 5;
        int countRoad = 2;
        int countMonopoly = 2;
        int countYOP = 2;

        return 0;
    }

    public String pickDescriptions(){
        description.put(1, this.knightName);
        description.put(2, this.victoryPointsName);
        description.put(3, this.roadBuildingName);
        description.put(4, this.monopolyName);
        description.put(5, this.yearOfPlentyName);

        int pick = (int) (Math.random() * 5);

        return description.get(pick);
    }

    // play card based on given dev card id

    //Step 1: Create developmentCards object somewhere
    //Step 2: Call toString on the object
    //Step 3: For one of the players, have this toString printed to the console
    //as if they selected to play this card (may need a boolean to see if they have
    //the card)

    @Override
    public String toString() {
        return "You played + " + pickDescriptions();
    }
}
