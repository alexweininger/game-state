package cs.up.catan.catangamestate;

public class Road extends GameState {

    private int startIntersectionID, endIntersectionID;
    public Road(){
        this.startIntersectionID = 0;
        this.startIntersectionID = 1;
    }

    @Override
    public String toString() {
        return "Road{" +
                "startIntersectionID=" + startIntersectionID +
                ", endIntersectionID=" + endIntersectionID +
                '}';
    }
}
