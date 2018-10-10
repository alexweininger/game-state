package cs.up.catan.catangamestate;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends GameState {

	//instance variables

	private int localScore; // score of player that accounts for the players dev cards, must greater or eqaul to global score
	private HashMap<Integer, Integer> resources;
	private ArrayList<DevelopmentCards> developmentCards;

	public Player(int localScore, HashMap<Integer, Integer> resources, ArrayList<DevelopmentCards> developmentCards) {
		this.localScore = localScore;
		this.resources = resources;
		this.developmentCards = developmentCards;
	}

	@Override
	public String toString() {
		return "Player{" +
				"localScore=" + localScore +
				", resources=" + resources +
				", developmentCards=" + developmentCards +
				'}';
	}

	/*rollDice()
	    --Player will roll dice and send integer to game state
	TODO
	 */

	/*tradePort()
	    --Player will ask to trade with a port and send info to game state
	TODO
	 */

	/*build()
	    --Player selects item to build and sends to gameState which will process selection
	TODO
	 */

	/*useDevCard()
	    --Player will select a development card they own and use it; gamestate will
	    --determine legality and then carry out development cards function
	TODO
	 */

	/*robberDiscard()
	    --Player chooses cards to discard if they own more than 7 cards and robber is activated
	TODO
	 */

	/*confirmAction();
	    --Gamestate will ask player to confirm action; based on player's decision, gamestate decides
	    --On next steps
	TODO
	 */

	/*rollDice();
	    --Player will send action to gamestate to roll dice
	TODO
	 */

	/*moveRobber();
	    --Called dice value is a 7
	TODO
	 */

}
