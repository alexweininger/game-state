package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 25th, 2018
 * https://github.com/alexweininger/game-state
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class DevelopmentCards {

    private ArrayList<Integer> developmentCards = new ArrayList<Integer>(); // ArrayList of the development card in the deck
    private HashMap<String, Integer> checkResources = new HashMap<>();

    //default instance variable
    private String name;
    private boolean isPlayable;

    // hashmap connecting ids to a specifc card

    public DevelopmentCards(String name) {
        this.name = name;
        this.isPlayable = false;
    }

    /**
     * creates a deck of int representing the exact number each type of card
     */
    public void generateDevCardDeck() {
        checkResources.put("Ore", 1);
        checkResources.put("Wheat", 1);
        checkResources.put("Sheep", 1);

        int[] devCardCounts = {14, 5, 2, 2, 2};
        for (int i = 0; i < devCardCounts.length; i++) {
            for (int j = 0; j < devCardCounts[i]; j++) {
                developmentCards.add(i);
            }
        }
    }

    //default use method
    public void useCard(Player player) {
        player.useDevCard(this);
    }

    /**
     * @param player player who is building a dev card
     */
    public void build(Player player) {
        player.removeResources("Ore", 1);
        player.removeResources("Sheep", 1);
        player.removeResources("Wheat", 1);

        //adds the building to the player's array list of built buildings
        player.addDevCard(getRandomCard());
    }

    /**
     * @return the random dev card the player drew
     */
    public DevelopmentCards getRandomCard() {
        int random = (int) Math.random() * 25;
        int cardChosen = developmentCards.get(random);
        developmentCards.remove(random);
        switch (cardChosen) {
            case 0:
                Knight knight = new Knight();
                return knight;
            case 1:
                VictoryPoints vicortyPoints = new VictoryPoints();
                return vicortyPoints;
            case 2:
                RoadDevCard roadBuilding = new RoadDevCard();
                return roadBuilding;
            case 3:
                Monopoly monopoly = new Monopoly();
                return monopoly;
            case 4:
                YearOfPlenty yearOfPlenty = new YearOfPlenty();
                return yearOfPlenty;
        }
        return null;
    }

    /**
     * @param playable allows the player to play the card or not
     */
    public void setPlayable(boolean playable) {
        isPlayable = playable;
    }

    // play card based on given dev card id

    //Step 1: Create developmentCards object somewhere
    //Step 2: Call toString on the object
    //Step 3: For one of the players, have this toString printed to the console
    //as if they selected to play this card (may need a boolean to see if they have
    //the card)

    /**
     * @return string representation of a DevelopmnentCard
     */
    @Override
    public String toString() {
        return "You played + " + name;
    }
}
