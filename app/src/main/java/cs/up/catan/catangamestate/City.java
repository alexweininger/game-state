package cs.up.catan.catangamestate;

public class City extends Building {

    private int validWheat = 0;
    private int validOre = 0;
    private boolean checkValidWheat = false;
    private boolean checkValidOre = false;


    public City(int wheat, int ore, boolean checkWheat, boolean checkOre){
        this.validWheat = wheat;
        this.validOre = ore;
        this.checkValidWheat = checkWheat;
        this.checkValidOre = checkOre;

    }

    @Override
    public String toString() {
        return buildingName + " requires " + validWheat + " Wheat and  "+ validOre + " Ore.\n";
    }
}
