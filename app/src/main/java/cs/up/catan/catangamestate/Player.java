package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 24th, 2018
 * <p>
 * https://github.com/alexweininger/game-state
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    //instance variables
    private int localScore; // score of player that accounts for the players dev cards, must greater or equal to global score
    private int publicScore;
    private HashMap<String, Integer> resources = new HashMap<String, Integer>(); // k: resource id, v: resource count
    private ArrayList<DevelopmentCards> developmentCards = new ArrayList<DevelopmentCards>(); // ArrayList of the development cards the player owns
    private ArrayList<Building> buidlingsBuilt = new ArrayList<Building>(); // ArrayList of the buildings the player has built
    private HashMap<String, Integer> availiableBuildings = new HashMap<>(); // // k: resource id, v: buildings available
    private int playerId;   //Player ID
    private static int playerCount = 1;

    // constructor
    public Player() {
        this.localScore = 2;
        this.publicScore = 4;
        this.resources.put("Bricks", 1);
        this.resources.put("Ore", 1);
        this.resources.put("Sheep", 2);
        this.resources.put("Wheat", 0);
        this.resources.put("Wood", 3);
        this.playerId = playerCount;
        playerCount++;
    }

    public Player(Player player) {
        this.publicScore = player.publicScore;
        this.localScore = player.localScore;
        this.developmentCards = player.developmentCards;
        this.resources = player.resources;
        this.playerId = player.playerId;
    }

    //adds buildings when the player builds them
    public void addBuilding(Building building)
    {

        buidlingsBuilt.add(building);

        //once the player chooses the build something there victory points are are added locally and publicly
        localScore += buidlingsBuilt.get(buidlingsBuilt.size()-1).getVictoryPoints();
        publicScore += buidlingsBuilt.get(buidlingsBuilt.size()-1).getVictoryPoints();
        // decreases the available buildings by one
        availiableBuildings.put(building.getBuildingName(),availiableBuildings.get(building.getBuildingName())-1);

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Player ");
        sb.append(playerId);
        sb.append(": \nPrivate Score = ");
        sb.append(this.localScore);
        sb.append("\nPublic score = ");
        sb.append(this.publicScore);
        sb.append("\nResources = ");
        sb.append(this.resources);
        sb.append("\nDevelopment Cards = ");
        sb.append(this.developmentCards);
        sb.append("\n");

        return sb.toString();
    }

    public boolean addResources(String res, int num){
        if(this.resources.containsKey(res)) {
            this.resources.put(res, this.resources.get(res) + num);
            return true;
        }
        return false;
    }

    public boolean removeResources(String res, int num){
        if(this.resources.containsKey(res)) {
            this.resources.put(res, this.resources.get(res) - num);
            return true;
        }
        return false;
    }

    public void addDevCard(DevelopmentCards devCard){
        developmentCards.add(devCard);
    }

    public boolean useResource(String res, int num){
        if(this.resources.containsKey(res)){
            if(this.resources.get(res) >= num){
                this.resources.put(res, this.resources.get(res) - num);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean useDevCard(DevelopmentCards devCard){
        if(developmentCards.contains(devCard)){
            developmentCards.remove(devCard);
            return true;
        }
        return false;
    }

    public int getLocalScore() {
        return localScore;
    }

    public void setLocalScore(int localScore) {
        this.localScore = localScore;
    }

    public int getPublicScore() {
        return publicScore;
    }

    public void setPublicScore(int publicScore) {
        this.publicScore = publicScore;
    }

    public int getPlayerId(){
        return this.playerId;
    }

    public void setPlayerId(int id){
        this.playerId = id;
    }

    public static int getPlayerCount() {
        return playerCount;
    }

    public static void setPlayerCount(int playerCount) {
        Player.playerCount = playerCount;
    }
}

