package cs.up.catan.catangamestate;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 10th, 2018
 * https://github.com/alexweininger/game-state
 **/
public class Road extends Building{

    private int startIntersectionID, endIntersectionID;

    public Road() {
        this.startIntersectionID = 0;
        this.startIntersectionID = 1;
    }

    //sets rhe start and end intersection ids
    public void placeRoad(int startIntersectionID, int endIntersectionID)
    {
        this.startIntersectionID = startIntersectionID;
        this.endIntersectionID = endIntersectionID;
    }

    //returns wehere the road starts
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
