package cs.up.catan.catangamestate;

import java.util.HashMap;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 24th, 2018
 * https://github.com/alexweininger/game-state
 **/
public class Road extends Building{

    private int startIntersectionID, endIntersectionID;

    public Road(int startIntersectionID, int endIntersectionID)
    {
        super("Road", 0);
        HashMap<String, Integer> checkResources = new HashMap<String, Integer>();
        this.startIntersectionID = startIntersectionID;
        this.endIntersectionID = endIntersectionID;
        checkResources.put("Brick", 1);
        checkResources.put("Ore", 0);
        checkResources.put("Sheep", 0);
        checkResources.put("Wheat", 0);
        checkResources.put("Wood", 1);
    }

    //sets rhe start and end intersection ids
    public void placeRoad(int startIntersectionID, int endIntersectionID)
    {
        this.startIntersectionID = startIntersectionID;
        this.endIntersectionID = endIntersectionID;
    }

    //returns where the road starts
    public int getStartIntersectionID()
    {
        return startIntersectionID;
    }

    //return where the road ends
    public int getEndIntersectionID()
    {
        return endIntersectionID;
    }

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
