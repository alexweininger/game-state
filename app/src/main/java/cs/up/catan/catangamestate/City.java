package cs.up.catan.catangamestate;

public class City extends Building {

    private boolean validResources;
    private int validWheat;
    private int validOre;
    private boolean checkValidWheat;
    private boolean checkValidOre;

    public City(int wheat, int ore, boolean checkWheat, boolean checkOre){
        this.validWheat = wheat;
        this.validOre = ore;
        this.checkValidWheat = checkWheat;
        this.checkValidOre = checkOre;

    }
}
