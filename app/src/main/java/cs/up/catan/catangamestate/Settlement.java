package cs.up.catan.catangamestate;

import java.util.HashMap;

/** Settlement class
 * @author Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version October th, 2018
 * https://github.com/alexweininger/game-state
 **/
public class Settlement extends Building {

    private int intersectionID;
    private static HashMap<String, Integer> checkResources = new HashMap<>();

    /**
     *
     * @param ownerId - player id of who owns the settlement
     */
    public Settlement(int ownerId) {
        super("Settlement", 2, ownerId);
        HashMap<String, Integer> checkResources = new HashMap<String, Integer>();
    } // end constructor

    /**
     *
     * @return string representation of a settlement
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Settlement toString()\n");
        sb.append(super.toString());
        return sb.toString();
    } // end toString

    public static void cityResourcePriceMake(){
        checkResources.put("brick", 3);
        checkResources.put("grain", 2);
        checkResources.put("wood", 3);
        checkResources.put("wool", 2);
    }
} // end Class
