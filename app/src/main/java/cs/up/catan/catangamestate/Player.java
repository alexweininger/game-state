package cs.up.catan.catangamestate;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends GameState {

    //instance variables

	int localScore; // score of player that accounts for the players dev cards, must greater or eqaul to global score
	HashMap<Integer, Integer> resources = new HashMap<Integer, Integer>();
	ArrayList<DevelopmentCards> developmentCards = new ArrayList<DevelopmentCards>();

    public Player(int settlementCount, int cityCount, int roadCount, boolean isTurn, int woodCount, int oreCount, int brickCount, int wheatCount, int woolCount) {

    }

    @Override
    public String toString() {
        return "The player has "+ settlementCount + " Settlements, " + cityCount + " Cities, " +
                roadCount + " Roads." + "Is it their turn: " + isTurn + ". In their inventory" +
                "they have:" + brickCount + " Brick, " + oreCount + " Ore, " + woolCount +
                " Sheep, " + wheatCount + " Wheat, and " + woodCount + " wood.";
    }
}
