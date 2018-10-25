package cs.up.catan.catangamestate;

import java.util.HashMap;

/** Settlement class
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October th, 2018
 * https://github.com/alexweininger/game-state
 **/
public class Settlement extends Building {

    public Settlement() {
        super("Settlement", 2);
        HashMap<String, Integer> checkResources = new HashMap<String, Integer>();
        checkResources.put("Brick", 1);
        checkResources.put("Sheep", 1);
        checkResources.put("Wheat", 1);
        checkResources.put("Wood", 1);
    } // end constructor

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Settlement toString()\n");
        sb.append(super.toString());

        return sb.toString();
    } // end toString
} // end Class
