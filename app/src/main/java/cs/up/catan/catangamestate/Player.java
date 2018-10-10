package cs.up.catan.catangamestate;

public class Player extends GameState {

    //instance variables
    private int settlementCount;
    private int cityCount;
    private int roadCount;
    private boolean isTurn;
    private int woodCount;
    private int oreCount;
    private int brickCount;
    private int wheatCount;
    private int woolCount;

    public Player(int settlementCount, int cityCount, int roadCount, boolean isTurn, int woodCount, int oreCount, int brickCount, int wheatCount, int woolCount) {
        this.settlementCount = settlementCount;
        this.cityCount = cityCount;
        this.roadCount = roadCount;
        this.isTurn = isTurn;
        this.woodCount = woodCount;
        this.oreCount = oreCount;
        this.brickCount = brickCount;
        this.wheatCount = wheatCount;
        this.woolCount = woolCount;
    }

    @Override
    public String toString() {
        return "The player has "+ settlementCount + " Settlements, " + cityCount + " Cities, " +
                roadCount + " Roads." + "Is it their turn: " + isTurn + ". In their inventory" +
                "they have:" + brickCount + " Brick, " + oreCount + " Ore, " + woolCount +
                " Sheep, " + wheatCount + " Wheat, and " + woodCount + " wood.";
    }
}
