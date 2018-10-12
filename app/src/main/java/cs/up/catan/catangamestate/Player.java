package cs.up.catan.catangamestate;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends GameState {

	//instance variables
	private int localScore; // score of player that accounts for the players dev cards, must greater or equal to global score
	private HashMap<Integer, Integer> resources = new HashMap<Integer, Integer>(); // k: resource id, v: resource count
	private ArrayList<Integer> developmentCards = new ArrayList<Integer>(); // ArrayList of the development card IDs the player owns
	private ArrayList<Building> buildings = new ArrayList<Building>();

	// constructor
	public Player() {
	//	this.localScore = 0;
	//	this.resources.put(22, 33);
	//	this.developmentCards.add(10);
	//	this.buildings.add(new Building());
	}

	@Override
	public String toString() {
		return "Player[" + "localScore:" + localScore + ", resources:" + resources +
				", developmentCards:" + developmentCards + "]\n";
	}



}
