package cs.up.catan.catangamestate;

import java.util.HashMap;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October th, 2018
 * https://github.com/alexweininger/game-state
 **/
public class Settlement extends Building {

    public Settlement(String name, HashMap<String, Integer> checkResources, int victoryPoints)
    {
        super(name, victoryPoints);
        checkResources.put("Brick", 1);
        checkResources.put("Ore", 0);
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
