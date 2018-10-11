package cs.up.catan.catangamestate;

import android.widget.TextView;

import java.util.ArrayList;

public class GameState {

    Dice dice = new Dice();
    int currentPlayer;
    int currentDiceSum;
    ArrayList<Player> playerList;

  
  public GameState () {
    
  }

    public GameState(GameState gameState)
    {

    }

    }
    @Override
    public String toString() {
        return "";
    }

    public boolean rollDice(boolean move, TextView edit){
        if(move){
            edit.append("Player 1 rolled a 8!\n");
            return true;
        }
        edit.append("It is not Player 1's turn!\n");
        return false;
    }

	public boolean tradePort(boolean move, TextView edit){
        if(move){
            edit.append();
            return true;
        }
        return false;
    }

    public boolean tradePort(boolean move){
        if(move){
            System.out.println("");
            return true;
        }
        return false;
    }

	public boolean buildRoad(){
        if(move){
            System.out.println("Player 1 !\n");
            return true;
        }
        System.out.println("It is not Player 1's turn!");
        return false;
    }

    public boolean buildSettlement(){
        if(move){
            System.out.println("Player 1 !\n");
            return true;
        }
        System.out.println("It is not Player 1's turn!");
        return false;
    }
    public boolean buildCity(){
        if(move){
            System.out.println("Player 1 !\n");
            return true;
        }
        System.out.println("It is not Player 1's turn!");
        return false;
    }
    public boolean buildRoad(){
        if(move){
            System.out.println("Player 1 !\n");
            return true;
        }
        System.out.println("It is not Player 1's turn!");
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
