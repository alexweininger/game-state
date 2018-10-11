package cs.up.catan.catangamestate;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends GameState {

	//instance variables
	private int localScore; // score of player that accounts for the players dev cards, must greater or equal to global score
	private HashMap<Integer, Integer> resources; // k: resource id, v: resource count
	private ArrayList<Integer> developmentCards; // ArrayList of the development card IDs the player owns
	private ArrayList<Building> buildings;

	// constructor
	public Player(int localScore, HashMap<Integer, Integer> resources, ArrayList<Integer> developmentCards, ArrayList<Building> buildings) {
		this.localScore = localScore;
		this.resources = resources;
		this.developmentCards = developmentCards;
		this.buildings = buildings;
	}



	@Override
	public String toString() {
		return "Player[" + "localScore:" + localScore + ", resources:" + resources +
				", developmentCards:" + developmentCards +
				"]\n";
	}



}
