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
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class GameState {

    // GameState instance variables
    private Dice dice; // dice object
    private int currentDiceSum = -1;
    private int currentPlayerId = -1; // id of player who is the current playing player
    private boolean isActionPhase = false; // has the current player rolled the dice


    private ArrayList<Player> playerList = new ArrayList<>(); // list of players in game
    private Board board = new Board();

    private int currentLargestArmyPlayerId = -1; // player who currently has the largest army
    private int currentLongestRoadPlayerId = -1;

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

        Road.roadResourcePriceMake();
        Settlement.cityResourcePriceMake();
        City.cityResourcePriceMake();

        this.board.toString();

        // set all vic points to 0 to start
        for (int i = 0; i < playerVictoryPoints.length; i++) {
            playerVictoryPoints[i] = 0;
            playerPrivateVictoryPoints[i] = 0;
        }
    } // end GameState constructor

    /**
     * GameState deep copy constructor
     *
     * @param gameState - GameState object to make a copy of
     */
    public GameState(GameState gameState) {
        this.dice = gameState.dice;
        this.currentPlayerId = gameState.currentPlayerId;
        this.currentDiceSum = gameState.currentDiceSum;
        this.isActionPhase = gameState.isActionPhase;
        this.board = gameState.board;
        this.currentLongestRoadPlayerId = gameState.currentLongestRoadPlayerId;
        this.currentLargestArmyPlayerId = gameState.currentLargestArmyPlayerId;

        for (int i = 0; i < gameState.playerList.size(); i++) {
            this.playerList.add(new Player(gameState.playerList.get(i)));
        }

        for (int i = 0; i < gameState.playerVictoryPoints.length; i++) {
            this.playerVictoryPoints[i] = gameState.playerVictoryPoints[i];
            this.playerPrivateVictoryPoints[i] = gameState.playerPrivateVictoryPoints[i];
        }


    } // end deep copy constructor

    /**
     * checkArmySize - after each turn checks who has the largest army (amount of played knight cards) with a minimum of 3 knight cards played.
     */
    public void checkArmySize() {
        int max = -1;
        if (this.currentLargestArmyPlayerId != -1) {
            max = this.playerList.get(this.currentLargestArmyPlayerId).getArmySize();
        }
        int playerIdWithLargestArmy = -1;
        for (int i = 0; i < 4; i++) {
            if (this.playerList.get(i).getArmySize() > max) {
                max = this.playerList.get(i).getArmySize();
                playerIdWithLargestArmy = i;
            }
        }
        if (max > 2) {
            this.currentLargestArmyPlayerId = playerIdWithLargestArmy;
        }
    }

    /**
     * checkRoadLength - after each turn check if any player has longest road, with a min of 5 road segments
     * probably just calls a method in board?
     * recursion???
     */
    public void checkRoadLength() {
        int max = -1;
        int playerIdWithLongestRoad = -1;
        if (currentLongestRoadPlayerId != -1) {
            max = playerVictoryPoints[currentLargestArmyPlayerId];
        }
        for (int i = 0; i < 4; i++) {
            if (board.getPlayerRoadLength(i) > max) {
                max = board.getPlayerRoadLength(i);
                playerIdWithLongestRoad = i;
            }
        }
        if (max > 4) {
            this.currentLongestRoadPlayerId = playerIdWithLongestRoad;
        }
    }

    // TODO move vic points to GameState
    public void updateVictoryPoints() {
        if (this.currentLongestRoadPlayerId != -1) {
            this.playerVictoryPoints[this.currentLongestRoadPlayerId] -= 2;
        }
        checkRoadLength();
        if (this.currentLongestRoadPlayerId != -1) {
            this.playerVictoryPoints[this.currentLongestRoadPlayerId] += 2;
        }

        if (this.currentLargestArmyPlayerId != -1) {
            this.playerVictoryPoints[this.currentLargestArmyPlayerId] -= 2;
        }
        checkArmySize();
        if (this.currentLargestArmyPlayerId != -1) {
            this.playerVictoryPoints[this.currentLargestArmyPlayerId] += 2;
        }
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
        if (playerId != currentPlayerId) {
            return false;
        }
        int rollNum = dice.roll();
        produceResources(rollNum, edit);
        isActionPhase = true;

        StringBuilder str = new StringBuilder();
        str.append("Player " + playerId + " rolled a " + rollNum + "\n");
        edit.append(str);
        return true;
    } // end rollDice action method

    /*tradePort() method
     *
     * Player trades with ports, gives resources and receives a resource;
     * number depends on the resource
     *
     * error checking:
     * - checks if it is given players turn
     * - checks if it is the action phase of the turn
     * - checks if the player has enough resources to trade
     * */
    public boolean tradePort(int playerId, int givenResourceId, int receivedResourceId, EditText edit) {
        // check if current player's turn and then if player has rolled dice
        if (playerId != this.currentPlayerId) {
            edit.append("It is not Player " + playerId + "'s turn!\n");
            return false;
        }
        // check if the turn is in the action phase
        if (!this.isActionPhase) {
            edit.append("Player " + playerId + " must roll dice first!\n");
            return false;
        }

        // creating a random trade ratio
        Random random = new Random();
        int ratio = random.nextInt(1) + 2;

        // check if player has enough resources to complete trade
        if (this.playerList.get(playerId).getResourceCards()[givenResourceId] < ratio) {
            edit.append("Player" + playerId + " does not have enough resources!\n");
            return false;
        }

        this.playerList.get(playerId).removeResourceCard(givenResourceId, ratio);
        this.playerList.get(playerId).addResourceCard(receivedResourceId, 1);

        edit.append("Player " + playerId + " traded " + ratio + " " + givenResourceId + " for a " + receivedResourceId + " with a Port!\n");
        return true;
    }

    /*tradeBank() method
     *
     * Player trades with bank, gives resources and receives a resource;
     * number depends on the resource
     *
     * */
    public boolean tradeBank(int playerId, int givenResourceId, int receivedResourceId, EditText edit) {
        //Check if current player's turn and then if player has rolled dice
        if (playerId != this.currentPlayerId) {
            edit.append("It is not Player " + playerId + "'s turn!\n");
            return false;
        }
        if (!this.isActionPhase) {
            edit.append("Player " + playerId + " must roll dice first!\n");
            return false;
        }

        //Setting ration then checking resources; if enough, we commence with trade
        Random random = new Random();
        int ratio = random.nextInt(1) + 2;

        if (this.playerList.get(playerId).getResources().get(givenResourceId) < ratio) {
            edit.append("Player " + playerId + " does not have enough resources!\n");
            return false;
        }

        this.playerList.get(playerId).removeResourceCard(givenResourceId, ratio);
        this.playerList.get(playerId).addResourceCard(receivedResourceId, 1);

        edit.append("Player " + playerId + " traded " + ratio + " " + givenResourceId + " for a " + givenResourceId + " with the Bank!\n");
        return true;
    }

    /*buildRoad() method
     *
     * Player requests to build road ands Gamestate processes requests and returns true
     * if build was successful
     *
     * TODO Implement method
     * */
    public boolean buildRoad(int playerId, int startIntersectionID, int endIntersectionID, EditText edit) {
        if (playerId != this.currentPlayerId) {
            edit.append("It is not Player " + playerId + "'s turn!\n");
            return false;
        }
        if (!this.isActionPhase) {
            edit.append("Player " + playerId + " must roll dice first!\n");
            return false;
        }

        if (this.playerList.get(playerId).getResources().get("Brick") < 1 && this.playerList.get(playerId).getResources().get("Wood") < 1) {
            edit.append("Player " + playerId + " does not have enough resources!\n");
        }

        Road road = new Road(startIntersectionID, endIntersectionID, playerId);


        //board.addRoad

        edit.append("Player " + playerId + " built a Road!\n");
        return true;
    }

    /*buildSettlement() method
     *
     * Player requests to build settlement and Gamestate processes requests and returns true
     * if build was successful
     *
     * TODO Implement method
     * */
    public boolean buildSettlement(int playerId, int intersectionID, EditText edit) {
        if (playerId != this.currentPlayerId) {
            edit.append("It is not Player " + playerId + "'s turn!\n");
            return false;
        }
        if (!this.isActionPhase) {
            edit.append("Player " + playerId + " must roll dice first!\n");
            return false;
        }

        if (this.playerList.get(playerId).getResources().get("Brick") == 1 && this.playerList.get(playerId).getResources().get("Grain") == 1
                && this.playerList.get(playerId).getResources().get("Wood") == 1 && this.playerList.get(playerId).getResources().get("Wool") == 1) {
            edit.append("Player " + playerId + " does not have enough resources!\n");
        }

        Settlement settlement = new Settlement(intersectionID, playerId);
        //board.addSettlement

        edit.append("Player " + playerId + " built a Settlement!\n");
        return true;
    }

    /*buildCity() method
     *
     * Player requests to build city and Gamestate processes requests and returns true
     * if build was successful
     *
     * TODO Implement method
     * */
    public boolean buildCity(int playerId, int intersectionID, EditText edit) {
        if (playerId != this.currentPlayerId) {
            edit.append("It is not Player " + playerId + "'s turn!\n");
            return false;
        }
        if (!this.isActionPhase) {
            edit.append("Player " + playerId + " must roll dice first!\n");
            return false;
        }

        if (this.playerList.get(playerId).getResources().get("Ore") == 3 && this.playerList.get(playerId).getResources().get("Grain") == 2) {
            edit.append("Player " + playerId + " does not have enough resources!\n");
        }

        City city = new City(intersectionID, playerId);
        //board.addCity

        edit.append("Player " + playerId + " built a City!\n");
        return true;
    }

    /*buyDevCard() method
     *
     * Player will choose "Development Card" from the build menu, confirm, and then add a
     * random development card to their development card inventory
     *
     * TODO Implement method
     */
    public boolean buyDevCard(int playerId, EditText edit) {
        DevelopmentCard dc = new DevelopmentCard();
        if (playerId == this.currentPlayerId) {
            if (this.playerList.get(playerId).getResources().get("Ore") >= 1 && this.playerList.get(playerId).getResources().get("Sheep") >= 1 && this.playerList.get(playerId).getResources().get("Wheat") >= 1) {
                dc.build(this.playerList.get(playerId));
                return true;
            }
            /*playerList.get(playerId).removeResources("Ore", 1);
            playerList.get(playerId).removeResources("Wool", 1);
            playerList.get(playerId).removeResources("Grain", 1);*/

        }
        return false;
    }

    /*useDevCard() method
     *
     * Player will select a development card they own and use it; gamestate will determine
     * legality and then carry out development cards function
     *
     */
    public boolean useDevCard(int playerId, EditText edit) {
        DevelopmentCard dc = new DevelopmentCard();
        if (playerId == this.currentPlayerId) {
            //playerList.get(playerId).useDevCard(dc.generateDevCardDeck());

        }

        if (true) {
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
    public boolean robberDiscard(EditText edit) {

        //go through players
        //check if they need to discard
        //make an array list of strings that will be discarded
        //selectResourceCards
        ArrayList<String> discardedCards = new ArrayList<>();

        for (int n = 0; n < 4; n++) {
            int handSize = this.playerList.get(n).getResources().size();
            if (handSize > 7) {
                int newHandSize = handSize / 2;
                discardedCards = selectResourceCards(this.playerList.get(n), newHandSize);
                for (int x = 0; x < discardedCards.size(); x++) {
                    this.playerList.get(n).removeResources(discardedCards.get(x), 1);
                }
            }
        }
        edit.append("Removed half of all resources from players with more than 7 cards\n");
        return true;

    }

    /**
     * @param playerId - id to check
     * @return - if it is that players turn or not
     */
    private boolean checkTurn(int playerId) {
        return playerId == this.currentPlayerId;
    }

    /**
     * If the player has rolled a 7, player will move the robber to another Hexagon that has settlements nearby
     *
     * @param move
     * @param edit
     * @param hexagonId
     * @param playerId
     * @return
     */
    public boolean robberMove(int playerId, int hexagonId, EditText edit) {
        if (checkTurn(playerId)) {
            if (this.board.moveRobber(hexagonId)) {
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

    /**
     * After the player has moved the Robber, the player will choose a player to steal from and receive a random card from their hand
     *
     * @param move
     * @param edit
     * @param hexagonId
     * @param playerId
     * @return
     */
    public boolean robberSteal(int playerId, int hexagonId, EditText edit) {
        if (playerId == this.currentPlayerId) {
            Random random = new Random();
            String resource = this.playerList.get(random.nextInt(3)).getRandomCard();

            this.playerList.get(playerId).addResources(resource, 1);
            edit.append("Stolen card " + resource + " added to: " + this.playerList.get(playerId));

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
    public boolean endTurn(int playerId, EditText edit) {

        if (playerId == currentPlayerId) { // if player can end turn
            edit.append("Player " + this.currentPlayerId + " has ended their turn.");
            this.currentPlayerId++;
            edit.append("It is now player id: " + this.currentPlayerId + " turn.");
            updateVictoryPoints();
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
        result.append(this.currentPlayerId);
        result.append("\n");
        result.append("Current Dice Sum: ");
        result.append(this.currentDiceSum);
        result.append("\n");
        result.append("isActionPhase: ");
        result.append(this.isActionPhase);
        result.append("\n");

        for (int i = 0; i < this.playerList.size(); i++) {
            result.append(this.playerList.get(i).toString() + " "); // TODO
            result.append("\n\n");
        }
        result.append(this.board.toString());

        result.append("currentLargestArmyPlayerId: " + this.currentLargestArmyPlayerId + "\n");
        result.append("currentLongestRoadPlayerId: " + this.currentLongestRoadPlayerId + "\n\n");

        for (int i = 0; i < this.playerList.size(); i++) {

        }
        str = result.toString();
        return str;
    } // end GameState toString()
}
