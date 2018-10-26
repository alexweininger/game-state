package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 10th, 2018
 * https://github.com/alexweininger/game-state
 **/

import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class GameState {

    // GameState instance variables
    private Dice dice; // dice object
    private int currentDiceSum = -1;

    private int currentPlayerId = -1; // id of player who is the current playing player

    private ArrayList<Player> playerList = new ArrayList<>(); // list of players in game

    private Board board = new Board();

    private int currentLargestArmyPlayerId; // player who currently has the largest army

    // victory points of each player
    private int[] playerVictoryPoints = new int[4];
    private int[] playerPrivateVictoryPoints = new int[4];

    // GameState constructor
    public GameState() {
        this.dice = new Dice();
        this.currentPlayerId = 0;
        this.currentDiceSum = 3;

        this.playerList.add(new Player());
        this.playerList.add(new Player());
        this.playerList.add(new Player());
        this.playerList.add(new Player());

        this.board.toString();

        // set all vic points to 0 to start
        for (int i = 0; i < playerVictoryPoints.length; i++) {
            playerVictoryPoints[i] = 0;
            playerPrivateVictoryPoints[i] = 0;
        }
    } // end GameState constructor

    // GameState deep copy constructor TODO
    public GameState(GameState gameState) {
        this.dice = gameState.dice;
        this.currentPlayerId = gameState.currentPlayerId;
        this.currentDiceSum = gameState.currentDiceSum;

        for (int i = 0; i < gameState.playerList.size(); i++) {
            this.playerList.add(new Player(gameState.playerList.get(i)));
        }
    } // end deep copy constructor

    /**
     * checkArmySize - after each turn checks who has the largest army (amount of played knight cards) with a minimum of 3 knight cards played.
     */
    public void checkArmySize() {
        // stuff... TODO
    }

    /**
     * checkRoadLength - after each turn check if any player has longest road, with a min of 5 road segments
     * probably just calls a method in board?
     * recursion???
     */
    public void checkRoadLength() {

    }

    // TODO move vic points to GameState
    public void updateVictoryPoints() {

    }

    // turn method TODO Niraj

    /**
     * within this method if they build a building we add it to the board
     *
     * @return
     */
    private boolean turnHandler() {
        // board.add()()()
        return false;

        /*
        1. roll dice

        2. get action
        while (!over) {
           getAction() // get players action

           3. carry out action
        }
        */

    }

    /**
     * AW
     *
     * @param diceSum - dice sum
     */
    public void produceResources(int diceSum, EditText edit) {
        ArrayList<Integer> productionHexagonIds = board.getHexagonsFromChitValue(diceSum);
        for (Integer i: productionHexagonIds) {
            /* for each producing hexagon tile TODO
             *   1. find adjacent intersections
             *   2. check if intersections have buildings
             *   3. give the owner of each building the corresponding
             *       amount of the hexagon resource type
             */
            Hexagon hex = board.getHexagonFromId(i);
            edit.append("producing " + hex.getResourceType());

            ArrayList<Integer> receivingPlayerIds = new ArrayList<>();

        }
    }


    /* initBuildings method
     *
     * Method for the very first turn for each player; player will select coordinates for
     * two roads and two settlements at the beginning of the game
     *
     *
     */
    public boolean initBuilding(boolean move, EditText edit) {


        if (move) {
            edit.append("Player 1 placed their settlements and roads!\n");
            edit.append("Player 2 placed their settlements and roads!\n");
            edit.append("Player 3 placed their settlements and roads!\n");
            return true;
        }

        return false;
    } // end initBuilding action method

    /*rollDice() method
     *
     * Player sends action to game state and game state return number with
     * resources depending on settlements players own and where they're located.
     *
     * */
    public boolean rollDice(int playerId, EditText edit) {
        if(playerId != currentPlayerId){
            return false;
        }
        int rollNum = dice.roll();
        produceResources(rollNum, edit);

        StringBuilder str = new StringBuilder();
        str.append("Player " + playerId + " rolled a " + rollNum + "\n");
        edit.append(str);
        return true;
    } // end rollDice action method

    /*tradePort() method
     *
     * Player trades with ports, gives resources and receives a resource;
     * number depends on the resourc
     *
     * TODO Implement method
     *
     * */
    public boolean tradePort(boolean move, EditText edit) {
        if (move) {
            edit.append("Player 1 traded with a Port!\n");
            return true;
        }
        edit.append("Player 1 does that have enough resources to trade!\n");
        return false;
    } // end tradePort action method

    /*tradeBank() method
     *
     * Player trades with bank, gives resources and receives a resource;
     * number depends on the resource
     *
     * TODO Implement method
     * */
    public boolean tradeBank(boolean move, EditText edit) {
        if (move) {
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
     * TODO Implement method
     * */
    public boolean buildRoad(boolean move, EditText edit) {
        if (move) {
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
     * TODO Implement method
     * */
    public boolean buildSettlement(boolean move, EditText edit) {
        if (move) {
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
     * TODO Implement method
     * */
    public boolean buildCity(boolean move, EditText edit) {
        if (move) {
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
     * TODO Implement method
     */
    public boolean buyDevCard(boolean move, EditText edit) {
        if (move) {
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
     * TODO Implement method
     */
    public boolean useDevCard(boolean move, EditText edit) {
        if (move) {
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
     * TODO Implement method
     */
    public boolean robberDiscard(boolean move, EditText edit) {
        if (move) {
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
     * TODO Implement method
     */
    public boolean robberMove(boolean move, EditText edit) {
        if (move) {
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
     * TODO Implement method
     */
    public boolean robberSteal(boolean move, EditText edit) {
        if (move) {
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


    // toString() TODO
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String str = "";

        result.append("GameState:\n");
        result.append("Current Player: ").append(this.currentPlayerId).append("\n");
        str += "GameState:\n";
        str += "Current Player:" + currentPlayerId + "\n";
        str += "Current Dice Sum: " + currentDiceSum + "\n";

        for (int i = 0; i < playerList.size(); i++) {
            str += playerList.get(i).toString() + " "; // TODO
            str += "\n\n";
        }


        str += this.board.toString();
        return str;
    } // end GameState toString()
}
