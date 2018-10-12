package cs.up.catan.catangamestate;

import android.util.Log;
import java.util.ArrayList;

// TODO https://www.academia.edu/9699475/Settlers_of_Catan_Developing_an_Implementation_of_an_Emerging_Classic_Board_Game_in_Java

public class Board {

    /*  RINGS - What are they?
     * 'Rings' are used to organize the following ID 2D-ArrayLists. Rings in context mean
     *  A ring of hexagons or intersections on the board. So for hexagons, the first ring contains
     *  the very middle hexagon. Ring 2 are the hexagons around that one. Hexagon 0 is the center,
     *  and hex 1 is directly right of hex 0, and then they are numbered by ring. So ring 0 has 1
     *  hexagon. Ring 2 has 6, and ring 3 (outer ring) has 12 hexagons.
     */

    // hexagonIdRings holds the IDs of each hexagon on the board, organized into rings.
    private ArrayList<ArrayList<Integer>> hexagonIdRings = new ArrayList<ArrayList<Integer>>();
    // intersectionIdRings holds the IDs of each intersection on the board, organized into rings.
    private ArrayList<ArrayList<Integer>> intersectionIdRings = new ArrayList<ArrayList<Integer>>();

    /*  hexagonAdjacencyGraph is a 2d ArrayList that holds adjacency information for each hexagon.    *
     *  To check if hexagon 2 and 7 are adjacent, we can get the value located at list.get(2).get(7); */
    private ArrayList<ArrayList<Boolean>> hexagonAdjacencyGraph = new ArrayList<ArrayList<Boolean>>(18);


    /**
     * Board constructor
     * defines hexagonIdRings, intersectionIdRings, and hexagonAdjacencyGraph.
     */
    public Board() {

        // for loop to add the rings to the lists
        int intersectionRingSize;
        for (int i = 0; i < 3; i++) {
            intersectionRingSize = ((i * 2) + 1) * 6; // size of intersection ring i
            // add the calculated IDs to the corresponding lists
            this.hexagonIdRings.add(new ArrayList<Integer>(new ArrayList<Integer>(intersectionRingSize)));
        }

        hexagonIdRings.add(new ArrayList<Integer>(1));
        hexagonIdRings.add(new ArrayList<Integer>(6));
        hexagonIdRings.add(new ArrayList<Integer>(12));

        int id = 0;
        for (int i = 0; i < hexagonIdRings.size(); i++) {
            for (int j = 0; j < hexagonIdRings.get(i).size(); j++) {
                hexagonIdRings.get(i).add(id);
                Log.d("dev", "adding " + id + " to hexagon id list");
                id++;
            }
        }
        id = 0;
        for (int i = 0; i < intersectionIdRings.size(); i++) {
            for (int j = 0; j < intersectionIdRings.get(i).size(); j++) {
                intersectionIdRings.get(i).add(id);
                Log.d("dev", "adding " + id + " to intersection id list");
                id++;
            }
        }

        for (int i = 0; i < hexagonIdRings.size(); i++) {
            for (int j = 0; j < hexagonIdRings.get(i).size(); j++) {
                /* TODO implement
                 * for each hexagon in hexagonIdRings:
                 *   1. check the next hexagon in the same ring
                 *     a. make sure that this 'wraps' around at the end using %
                 *   2 look at the two adjacent hexagons in the next ring
                 *     a. corner vs. non-corner hexagons = if j % i == 0
                 *     b. sextants (0-5), calculated with sextant = j / i;
                 */
            }
        }
    } // end constructor

    /**
     * toString method
     * @return String
     */
    // TODO not working
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Hexagon IDs:\n");
        str.append(listToString(this.hexagonIdRings));
        str.append("Intersection IDs:\n");
        str.append(listToString(this.intersectionIdRings));

        return str.toString();
    } // end toString()

    public String listToString(ArrayList<ArrayList<Integer>> list) {
        String result = "";
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).size(); j++){
                result += list.get(i).get(j);
            }
            // System.out.println();
            result += "\n";
        }
        return result;
    } // end listToString method
} // end Class
