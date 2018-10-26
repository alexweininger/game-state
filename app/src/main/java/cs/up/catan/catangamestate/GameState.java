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
    private boolean actionPhase = false;

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
        if(playerId != currentPlayerId){
            return false;
        }
        int rollNum = dice.roll();
        produceResources(rollNum, edit);
        actionPhase = true;

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
     * TODO Implement method
     *
     * */
    public boolean tradePort(int playerId, String resGiven, String resReceive, EditText edit) {

        //Check if current player's turn and then if player has rolled dice
        if(playerId != currentPlayerId){
            edit.append("It is not Player " + playerId + "'s turn!\n");
            return false;
        }
        if(!actionPhase){
            edit.append("Player " + playerId + " must roll dice first!\n");
            return false;
        }

        //Setting ration then checking resources; if enough, we commence with trade
        Random random = new Random();
        int ratio = random.nextInt(1) + 2;

        if(playerList.get(playerId).getResources().get(resGiven) < ratio){
            edit.append("Player" + playerId + " does not have enough resources!\n");
            return false;
        }

        playerList.get(playerId).removeResources(resGiven, ratio);
        playerList.get(playerId).addResources(resReceive, 1);

        edit.append("Player " + playerId + " traded " + ratio + " " + resGiven + " for a " + resReceive + " with a Port!\n");
        return true;
    }

    /*tradeBank() method
     *
     * Player trades with bank, gives resources and receives a resource;
     * number depends on the resource
     *
     * */
    public boolean tradeBank(int playerId, String resGiven, String resReceive, EditText edit) {
        //Check if current player's turn and then if player has rolled dice
        if(playerId != currentPlayerId){
            edit.append("It is not Player " + playerId + "'s turn!\n");
            return false;
        }
        if(!actionPhase){
            edit.append("Player " + playerId + " must roll dice first!\n");
            return false;
        }

        //Setting ration then checking resources; if enough, we commence with trade
        Random random = new Random();
        int ratio = random.nextInt(1) + 2;

        if(playerList.get(playerId).getResources().get(resGiven) < ratio){
            edit.append("Player " + playerId + " does not have enough resources!\n");
            return false;
        }

        playerList.get(playerId).removeResources(resGiven, ratio);
        playerList.get(playerId).addResources(resReceive, 1);

        edit.append("Player " + playerId + " traded " + ratio + " " + resGiven + " for a " + resReceive + " with the Bank!\n");
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
        if(playerId != currentPlayerId){
            edit.append("It is not Player " + playerId + "'s turn!\n");
            return false;
        }
        if(!actionPhase){
            edit.append("Player " + playerId + " must roll dice first!\n");
            return false;
        }

        if(playerList.get(playerId).hasResources("brick", 3) && playerList.get(playerId).hasResources("wood", 2)){
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
        if(playerId != currentPlayerId){
            edit.append("It is not Player " + playerId + "'s turn!\n");
            return false;
        }
        if(!actionPhase){
            edit.append("Player " + playerId + " must roll dice first!\n");
            return false;
        }

        if(playerList.get(playerId).hasResources("brick", 1) && playerList.get(playerId).hasResources("grain", 1)
                && playerList.get(playerId).hasResources("wood", 1) && playerList.get(playerId).hasResources("wool", 1)){
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
        if(playerId != currentPlayerId){
            edit.append("It is not Player " + playerId + "'s turn!\n");
            return false;
        }
        if(!actionPhase){
            edit.append("Player " + playerId + " must roll dice first!\n");
            return false;
        }

        if(playerList.get(playerId).hasResources("ore", 3) && playerList.get(playerId).hasResources("grain", 2)){
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
        if (playerId == currentPlayerId){
            if (playerList.get(playerId).getResources().get("Ore") >= 1 && playerList.get(playerId).getResources().get("Sheep") >= 1 && playerList.get(playerId).getResources().get("Wheat") >= 1){
                dc.build(playerList.get(playerId));
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

    /**
     * @param playerId
     * @return
     */
    private boolean checkTurn(int playerId) {
        return playerId == this.currentPlayerId;
    }

    /*robberMove() method AW
     *
     * If the player has rolled a 7, player will move the robber to another Hexagon that
     * has settlements nearby
     *
     */
    public boolean robberMove(boolean move, EditText edit, int hexagonId, int playerId) {
        if (checkTurn(playerId)) {
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
