package cs.up.catan.catangamestate;

import java.util.ArrayList;

public class GameState {

    private Dice dice;
    private int currentPlayer;
    private int currentDiceSum;
    private ArrayList<Player> playerList;

    public GameState() {
        this.dice = new Dice();
        this.currentPlayer = -1;
        this.currentDiceSum = -1;
        this.playerList = null; // TODO what do we set this
    }

    // TODO wait til all implemented
    public GameState(GameState gameState) {

    }

    @Override
    public String toString() {
        String playerListString = "";

        for (int i = 0; i < playerList.size(); i++)
        {
            playerListString = playerList.get(i) + " ";
        }
        return "Current Player List: " + currentPlayer + "\n" +
                "Current Player:" + currentPlayer + "\n" +
                "Current Dide Sum: " + currentDiceSum;
    }

    public int rollDice(){
        return dice.rollDice();
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

	/*moveRobber();
	    --Called dice value is a 7
	TODO
	 */
}
