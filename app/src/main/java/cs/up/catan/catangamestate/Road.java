package cs.up.catan.catangamestate;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 10th, 2018
 * https://github.com/alexweininger/game-state
 **/
public class Road {

    private int startIntersectionID, endIntersectionID;

    public Road() {
        this.startIntersectionID = 0;
        this.startIntersectionID = 1;
    }

    @Override
    public String toString() {
        return "Road{" +
                "startIntersectionID=" + startIntersectionID +
                ", endIntersectionID=" + endIntersectionID + '}';
    }
}
