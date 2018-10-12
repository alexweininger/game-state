package cs.up.catan.catangamestate;

import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class GameState {

    private Dice dice;
    private int currentPlayer = -1;
    private int currentDiceSum =  -1;
    private ArrayList<Player> playerList = new ArrayList<>();

    public GameState() {
       // this.dice = new Dice();
        this.currentPlayer = -1;
        this.currentDiceSum = -1;
//        this.playerList.add(new Player()); // TODO what do we set this
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
                "Current Dice Sum: " + currentDiceSum + "\n";
    }

    /*initBuildings() method
    *
    * Method for the very first turn for each player; player will select coordinates for
    * two roads and two settlements at the beginning of the game
    *
     */
    public boolean initBuilding(boolean move, EditText edit){
        if(move){
            edit.append("Player 1 placed their settlements and roads!\n");
            edit.append("Player 2 placed their settlements and roads!\n");
            edit.append("Player 3 placed their settlements and roads!\n");
            return true;
        }

        return false;
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
        edit.append("Player 1 cannot build a Road!\n");
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
            edit.append("Player 1 cannot build a Settlement!\n");
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
        edit.append("Player 2 cannot build a City!\n");
        return false;
    }

    /*buyDevCard() method
     *
     * Player will choose "Development Card" from the build menu, confirm, and then add a
     * random development card to their development card inventory
     *
     */
    public boolean buyDevCard(boolean move, EditText edit){
        if(move){
            edit.append("Player 3 built a Development Card!\n");
            return true;
        }
        edit.append("Player 3 cannot build a Development Card!\n");
        return false;
    }

	/*useDevCard() method
	 *
	 * Player will select a development card they own and use it; gamestate will determine
	 * legality and then carry out development cards function
	 *
	 */
    public boolean useDevCard(boolean move, EditText edit){
        if(move){
            edit.append("Player 3 used their Knight Card!\n");
            return true;
        }
        edit.append("Player 3 cannon use their Development Card!\n");
        return false;
    }

	/*robberDiscard() method
	 *
	 * Player chooses cards to discard if they own more than 7 cards and robber is activated
	 *
	 */
	public boolean robberDiscard(boolean move, EditText edit){
	    if(move){
            edit.append("Player 2 lost half their cards from the Robber!\n");
            return true;
	    }
	    edit.append("Player 2 owns 7 or less cards!\n");
	    return false;
    }

    /*robberMove() method
     *
     * If the player has rolled a 7, player will move the robber to another Hexagon that
     * has settlements nearby
     *
	 */
    public boolean robberMove(boolean move, EditText edit){
        if(move){
            edit.append("Player 1 moved the Robber to Hexagon 4!\n");
            return true;
        }
        edit.append("Player 1 cannot move the Robber there!\n");
        return false;
    }

    /*robberSteal() method
     *
     * After the player has moved the Robber, the player will choose a player to steal from and
     * receive a random card from their hand
     *
     */
    public boolean robberSteal(boolean move, EditText edit){
        if(move){
            edit.append("Player 1 stole a card from Player 3!\n");
            return true;
        }
        edit.append("Player 1 cannot steal a card!\n");
        return false;
    }

	/*confirmAction();
	    --Gamestate will ask player to confirm action; based on player's decision, gamestate decides
	    --On next steps
	TODO
	 */
}
