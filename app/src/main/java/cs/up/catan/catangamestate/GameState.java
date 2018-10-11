package cs.up.catan.catangamestate;

public class GameState {

    Dice dice = new Dice();

    public GameState() {

    }
    @Override
    public String toString() {
        return "";
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
