package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 10th, 2018
 * github: https://github.com/alexweininger/game-state
 **/

import android.util.Log;

import java.util.ArrayList;

/**
 * External Citation
 * Date: 8 October 2018
 * Problem: Struggling to represent board and tiles.
 * Resource:
 * https://www.academia.edu/9699475/Settlers_of_Catan_Developing_an_Implementation_
 * of_an_Emerging_Classic_Board_Game_in_Java
 * Solution: We used the concepts and ideas from this research paper to help us represent the board
 * information and the hexagons.
 */

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

    private ArrayList<ArrayList<Boolean>> intersectionAdjecencyGraph = new ArrayList<ArrayList<Boolean>>(36); // TODO

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

        populateHexagonIds();

        int id = 0;
        for (int i = 0; i < this.intersectionIdRings.size(); i++) {
            for (int j = 0; j < this.intersectionIdRings.get(i).size(); j++) {
                this.intersectionIdRings.get(i).add(id);
                Log.d("dev", "adding " + id + " to intersection id list");
                id++;
            }
        }

        for (int i = 0; i < this.hexagonIdRings.size(); i++) {
            for (int j = 0; j < this.hexagonIdRings.get(i).size(); j++) {
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


    // populating hexagonIdRings with hex IDs (0-18, 19 hexagons)
    public void populateHexagonIds() {
        int id = 0;
        for (int i = 0; i < 3; i++) {
            if (0 == i) {
                this.hexagonIdRings.get(i).add(0);
                id++;
            } else {
                for (int j = 0; j < i * 6; j++) {
                    this.hexagonIdRings.get(i).add(id);
                    id++;
                }
            }
        }
    }

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
        for (int i = 0; i < list.size(); i++) {
            result += "Ring " + i + ": ";
            for (int j = 0; j < list.get(i).size(); j++) {
                result += list.get(i).get(j) + " ";
            }
            result += "\n";
        }
        return result;
    } // end listToString method
} // end Class
