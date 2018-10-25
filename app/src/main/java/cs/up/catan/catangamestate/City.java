package cs.up.catan.catangamestate;

import java.util.HashMap;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 24th, 2018
 * https://github.com/alexweininger/game-state
 **/

public class City extends Building {

    /**
     *
     * @param ownerId
     */
    public City(int ownerId) {
        super("City", 1, ownerId);
        HashMap<String, Integer> checkResources = new HashMap<String, Integer>();
        checkResources.put("Ore", 3);
        checkResources.put("Wheat", 2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("City toString()\n");
        sb.append(super.toString());

        return sb.toString();
    }
}
