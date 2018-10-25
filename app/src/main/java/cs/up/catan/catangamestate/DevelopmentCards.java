package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 24th, 2018
 * https://github.com/alexweininger/game-state
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class DevelopmentCards{

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

    public void generateDevCardDeck()
    {
        checkResources.put("Ore", 1);
        checkResources.put("Wheat", 1);
        checkResources.put("Sheep", 1);


        //add Knights
        for(int i = 0; i < 14; i++)
        {
            developmentCards.add(0);
        }

        //add VictoryPoints
        for(int i = 0; i < 5; i++)
        {
            developmentCards.add(1);
        }

        //add RoadBuilding
        for(int i = 0; i < 2; i++)
        {
            developmentCards.add(2);
        }

        //add Monopoly
        for(int i = 0; i < 2; i++)
        {
            developmentCards.add(3);
        }

        //add Year Of Plenty
        for(int i = 0; i < 2; i++)
        {
            developmentCards.add(4);
        }
    }

    //default constructor
    public DevelopmentCards() {
    }

    //default use method
    public void useCard()
    {

    }

    public void build(Player player)
    {
        player.removeResources("Ore",1);
        player.removeResources("Sheep",1);
        player.removeResources("Wheat",1);

        //adds the building to the player's array list of built buildings
        player.addDevCard(getRandomCard());
    }

    public DevelopmentCards getRandomCard() {
        int random = (int) Math.random() * 25;
        int cardChosen = developmentCards.get(random);
        developmentCards.remove(random);
        switch (cardChosen)
        {
            case 0: // Knight
                DevelopmentCards knight = new DevelopmentCards("Knight");
                return knight;
            case 1:
                DevelopmentCards vicortyPoints = new DevelopmentCards("Vicorty Points");
                return vicortyPoints;
            case 2:
                DevelopmentCards roadBuilding = new DevelopmentCards("Road Building");
                return roadBuilding;
            case 3:
                DevelopmentCards monopoly = new DevelopmentCards("Monopoly");
                return monopoly;
            case 4:
                DevelopmentCards yearOfPlenty = new DevelopmentCards("Year Of Plenty");
                return yearOfPlenty;
        }



        return null;
    }

    // play card based on given dev card id

    //Step 1: Create developmentCards object somewhere
    //Step 2: Call toString on the object
    //Step 3: For one of the players, have this toString printed to the console
    //as if they selected to play this card (may need a boolean to see if they have
    //the card)

    @Override
    public String toString() {
        return "You played + " + name;
    }
}
