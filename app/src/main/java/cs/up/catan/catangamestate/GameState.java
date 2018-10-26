package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 10th, 2018
 * https://github.com/alexweininger/game-state
 **/

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;

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

        this.playerList.add(new Player(0));
        this.playerList.add(new Player(1));
        this.playerList.add(new Player(2));
        this.playerList.add(new Player(3));

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

    /**
     * AW
     *
     * @param diceSum - dice sum
     */
    public void produceResources(int diceSum, EditText edit) {
        ArrayList<Integer> productionHexagonIds = board.getHexagonsFromChitValue(diceSum);
        for (Integer i : productionHexagonIds) {
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

    /**
     * TODO HELP me do this
     * method for when a player must select a number resource cards from their inventory
     *
     * @param player        - player to select cards
     * @param numberOfCards - number of cards to select
     * @return - array list of card types that were selected: String
     */
    public ArrayList<String> selectResourceCards(Player player, int numberOfCards) {
        return null;
    }

    /* initBuildings method
     *
     * Method for the very first turn for each player; player will select coordinates for
     * two roads and two settlements at the beginning of the game
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
    public boolean rollDice(boolean move, EditText edit) {
        StringBuilder str = new StringBuilder();
        if (move) {
            this.dice.roll();
            str.append("Player 1 rolled a ").append(this.dice.getSum()).append("\n");

            return true;
        }
        str.append("It is not ").append(this.currentPlayerId).append("'s turn!\n"); // TODO
        edit.append(str.toString());
        return false;
    } // end rollDice action method

    /*tradePort() method
     *
     * Player trades with ports, gives resources and receives a resource;
     * number depends on the resource
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
     */
    public boolean buyDevCard(boolean move, EditText edit, int playerId) {
        DevelopmentCard dc = new DevelopmentCard();
        if (playerId == currentPlayerId){
            if (playerList.get(playerId).getResources().get("Ore") >= 1 && playerList.get(playerId).getResources().get("Sheep") >= 1 && playerList.get(playerId).getResources().get("Wheat") >= 1){

            }
            dc.build(playerList.get(playerId));
            /*playerList.get(playerId).removeResources("Ore", 1);
            playerList.get(playerId).removeResources("Wool", 1);
            playerList.get(playerId).removeResources("Grain", 1);*/

        }

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
     */
    public boolean useDevCard(boolean move, EditText edit, int playerId) {
        DevelopmentCard dc = new DevelopmentCard();
        if (playerId == currentPlayerId){
            //playerList.get(playerId).useDevCard(dc.generateDevCardDeck());

        }

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
     */
    public boolean robberDiscard(boolean move, EditText edit) {

        //go through players
        //check if they need to discard
        //make an array list of strings that will be discarded
        //selectResourceCards
        ArrayList<String> discardedCards = new ArrayList<>();

        for (int n = 0; n < 4; n++){
            int handSize = playerList.get(n).getResources().size();
            if (handSize > 7){
                int newHandSize = handSize / 2;
                discardedCards = selectResourceCards(playerList.get(n), newHandSize);
                for (int x = 0; x < discardedCards.size(); x++){
                    playerList.get(n).removeResources(discardedCards.get(x), 1);
                }
            }
        }
        edit.append("Removed resources from all players");
        return true;

    }
    /*robberMove() method
     *
     * must check if
     *
     * If the player has rolled a 7, player will move the robber to another Hexagon that
     * has settlements nearby
     *
     */
    public boolean robberMove(boolean move, EditText edit, int hexagonId, int playerId) {
        if (playerId != currentPlayerId) {
            if (board.moveRobber(hexagonId)) {
                edit.append("Player " + playerId + " moved the Robber to Hexagon " + hexagonId + "!\n");
                return true;
            }
            edit.append("Player " + playerId + "  cannot move the Robber to Hexagon " + hexagonId + "!\n");
            return false;
        } else {
            edit.append("It is not " + playerId + "'s turn. " + hexagonId + "!\n");
            return false;
        }

    }

    /*robberSteal() method
     *
     * After the player has moved the Robber, the player will choose a player to steal from and
     * receive a random card from their hand
     *
     */
    public boolean robberSteal(boolean move, EditText edit, int hexagonId, int playerId) {
        if (playerId == this.currentPlayerId){
            Random random = new Random();
            String resource = playerList.get(random.nextInt(3)).getRandomCard();

            playerList.get(playerId).addResources(resource, 1);
            edit.append("Stolen card " + resource + " added to: " + playerList.get(playerId));

            return true;
        }
        edit.append("Can't steal a card");
        return false;

    }

	/*confirmAction();
	    --Gamestate will ask player to confirm action; based on player's decision, gamestate decides
	    --On next steps
	TODO
	 */

    /**
     * action for a player ending their turn, increments currentPlayerId. As of now does no checks. AW
     *
     * @param move - ???
     * @param edit - text displayed on tablet
     * @return boolean
     */
    public boolean endTurn(boolean move, EditText edit) {

        if (move) { // if player can end turn
            edit.append("Player " + currentPlayerId + " has ended their turn.");
            currentPlayerId++;
            edit.append("It is now player id: " + currentPlayerId + " turn.");
            return true;
        }
        // if player cant end turn?
        return false;
    }

    /**
     * TODO
     *
     * @return String
     */
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
