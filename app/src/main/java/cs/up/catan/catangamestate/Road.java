package cs.up.catan.catangamestate;

import java.util.HashMap;

/**
 * @author Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version October 24th, 2018
 * https://github.com/alexweininger/game-state
 **/
public class Road extends Building {

    private int startIntersectionID, endIntersectionID;

    /**
     *
     * @param startIntersectionID -
     * @param endIntersectionID -
     */
    public Road(int startIntersectionID, int endIntersectionID, int ownerId) {
        super("Road", 0, ownerId);

        this.startIntersectionID = startIntersectionID;
        this.endIntersectionID = endIntersectionID;

        HashMap<String, Integer> checkResources = new HashMap<String, Integer>();
        checkResources.put("Brick", 1);
        checkResources.put("Wood", 1);
    }

    public int getStartIntersectionID() { return startIntersectionID; }
    public int getEndIntersectionID() { return endIntersectionID; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Road{");
        sb.append("startIntersectionID=");
        sb.append(startIntersectionID);
        sb.append(", endIntersectionID=");
        sb.append(endIntersectionID);
        sb.append('}');

        return sb.toString();
    }
}
