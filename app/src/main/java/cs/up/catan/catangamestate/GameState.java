package cs.up.catan.catangamestate;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class GameState {

    private Dice dice;
    private int currentPlayer = -1;
    private int currentDiceSum =  -1;
    private ArrayList<Player> playerList = new ArrayList<>();
    private Board board = new Board();

    public GameState() {
       this.dice = new Dice();
        this.currentPlayer = -1;
        this.currentDiceSum = -1;
//        this.playerList.add(new Player()); // TODO what do we set this
        this.board = new Board();
    }


    public GameState(GameState gameState) {
        this.currentPlayer = gameState.currentPlayer;
        this.currentDiceSum = gameState.currentDiceSum;


        // TODO wait til all implemented
    }

    @Override
    public String toString() {
        String playerListString = "";

        for (int i = 0; i < playerList.size(); i++)
        {
            playerListString = playerList.get(i) + " "; // TODO
        }
        return "Current Player List: " + currentPlayer + "\n" +
                "Current Player:" + currentPlayer + "\n" +
                "Current Dice Sum: " + currentDiceSum;
    }

    /*rollDice() method
    *
    * Player sends action to game state and game state return number with
    * resources depending on settlements players own and where they're located.
    *
    * */
    public boolean rollDice(boolean move, EditText edit){
        if(move){
            edit.append("Player 1 rolled a 8!\n");
            return true;
        }
        edit.append("It is not Player 1's turn!\n");
        return false;
    }

    /*tradePort() method
    *
    * Player trades with ports, gives resources and receives a resource;
    * number depends on the resource
    *
    * */
	public boolean tradePort(boolean move, EditText edit){
        if(move){
            edit.append("Player 1 traded with a Port!\n");
            return true;
        }
        edit.append("Player 1 does that have enough resources to trade!\n");
        return false;
    }

    /*tradeBank() method
    *
    * Player trades with bank, gives resources and receives a resource;
    * number depends on the resource
    *
    * */
    public boolean tradeBank(boolean move, EditText edit){
        if(move){
            edit.append("Player 1 traded with the Bank!\n");
            return true;
        }
        edit.append("Player 1 does that have enough resources to trade!\n");
        return false;
    }

    /*buildRoad() method
    *
    * Player requests to build road ands Gamestate processes requests and returns true
    * if build was successful
    *
    * */
	public boolean buildRoad(boolean move, EditText edit){
        if(move){
            edit.append("Player 1 built 2 Roads!\n");
            return true;
        }
        edit.append("Player 1 cannot build a Road!");
        return false;
    }

    /*buildSettlement() method
     *
     * Player requests to build settlement and Gamestate processes requests and returns true
     * if build was successful
     *
     * */
    public boolean buildSettlement(boolean move, EditText edit){
            if(move){
                edit.append("Player 2 built a Settlement!\n");
                return true;
            }
            edit.append("Player 1 cannot build a Settlement!");
            return false;
    }

    /*buildCity() method
     *
     * Player requests to build city and Gamestate processes requests and returns true
     * if build was successful
     *
     * */
    public boolean buildCity(boolean move, EditText edit){
        if(move){
            edit.append("Player 2 built a City!\n");
            return true;
        }
        edit.append("Player 2 cannot build a City!");
        return false;
    }

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
