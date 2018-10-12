package cs.up.catan.catangamestate;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

	//instance variables
	private int localScore; // score of player that accounts for the players dev cards, must greater or equal to global score
	private HashMap<Integer, Integer> resources = new HashMap<Integer, Integer>(); // k: resource id, v: resource count
	private ArrayList<Integer> developmentCards = new ArrayList<Integer>(); // ArrayList of the development card IDs the player owns

	// constructor
	public Player() {
		this.localScore = 2;
		this.resources.put(22, 33);
		this.developmentCards.add(10);
	}

	@Override
	public String toString(){
		return "Player: \nLocal Score = " + localScore + "\nResources = " + resources + "\nDevelopment Cards = " + developmentCards;
	}



}
