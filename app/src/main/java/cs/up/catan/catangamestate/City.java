package cs.up.catan.catangamestate;

import java.util.HashMap;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 24th, 2018
 * https://github.com/alexweininger/game-state
 **/

public class City extends Building {


    public City()
    {
        super("City", 1);
        HashMap<String, Integer> checkResources = new HashMap<String, Integer>();
        checkResources.put("Brick", 0);
        checkResources.put("Ore", 3);
        checkResources.put("Sheep", 0);
        checkResources.put("Wheat", 2);
        checkResources.put("Wood", 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("City toString()\n");
        sb.append(super.toString());

        return sb.toString();
    }
}
