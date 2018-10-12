package cs.up.catan.catangamestate;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

	//instance variables
	private int localScore; // score of player that accounts for the players dev cards, must greater or equal to global score
	private int publicScore;
	private HashMap<String, Integer> resources = new HashMap<String, Integer>(); // k: resource id, v: resource count
	private ArrayList<Integer> developmentCards = new ArrayList<Integer>(); // ArrayList of the development card IDs the player owns

	// constructor
	public Player() {
		this.localScore = 2;
		this.publicScore = 4;
		this.resources.put("Logs", 3);
        this.resources.put("Bricks", 1);
        this.resources.put("Ore", 1);
        this.resources.put("Wool", 2);
		this.developmentCards.add(1);
	}

	public Player(Player player) {
		this.publicScore = player.publicScore;
		this.localScore = player.localScore;
        this.developmentCards = player.developmentCards;
        this.resources = player.resources;
    }

	@Override
	public String toString(){
		return "Player: \nLocal Score = " + this.localScore + "\nPublic score = " + this.publicScore + "\nResources = " + this.resources + "\nDevelopment Cards = " + this.developmentCards + "\n";
	}
}
