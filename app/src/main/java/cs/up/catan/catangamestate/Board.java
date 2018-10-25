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
    private boolean[][] hGraph = new boolean[19][19];

    private ArrayList<ArrayList<Boolean>> intersectionAdjecencyGraph = new ArrayList<ArrayList<Boolean>>(36); // TODO

    /**
     * Board constructor
     * defines hexagonIdRings, intersectionIdRings, and hexagonAdjacencyGraph.
     */
    public Board() {

        populateHexagonIds();
        populateIntersectionIds();
        for (int i = 0; i < 2; i++) { // rings
            for (int j = 0; j < this.hexagonIdRings.get(i).size(); j++) { // ids
                this.hGraph[i][hexagonIdRings.get(i).get(j)] = false;
            }
        }

        for (int col = 0; col < 6; col++) {
            hGraph[0][col] = true;
        }

        for (int i = 0; i < 2; i++) { // rings (rows)
            for (int j = 0; j < this.hexagonIdRings.get(i).size(); j++) { // cols

                // i and j are only 0 once and never are 0 again 0, 0 = center
                /* TODO implement
                 * for each hexagon in hexagonIdRings:
                 *   1. check the next hexagon in the same ring
                 *     a. make sure that this 'wraps' around at the end using %
                 *   2 look at the two adjacent hexagons in the next ring
                 *     a. corner vs. non-corner hexagons = if j % i == 0
                 *     b. sextants (0-5), calculated with sextant = j / i;
                 */
                int sextant = -1;
                boolean corner = true;
                if (i == 0) {
                    sextant = j;
                    corner = false;
                } else {

                    sextant = j / i;
                    corner = j % i == 0;
                }

                Log.d("dev", " i= " + i + " j= " + j + " id= " + hexagonIdRings.get(i).get(j) + " sextant= " + sextant + " corner= " + corner);

                this.hGraph[getId(i, j)][getId(i + 1, j + sextant)] = true;
                this.hGraph[getId(i, j)][getId(i + 1, j + sextant + 1)] = true;

                if (corner) {
                    int size = hexagonIdRings.get(i + 1).size();
                    int nextIndex = ((j - 1 + sextant) % size);
                    if (nextIndex < 12 && nextIndex >= 0) {
                        hGraph[getId(i, j)][getId(i + 1, nextIndex)] = true;
                    } else {
                        Log.d("dev", "id value wrapped: " + (Math.abs(j - 1 + sextant) % size));
                        hGraph[getId(i, j)][getId(i + 1, size - Math.abs(j - 1 + sextant) % size)] = true;
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < hexagonIdRings.get(i).size(); j++) {

                hGraph[getId(i, j)][getId(i, j)] = true;

                int newIndex = j + 1;
                int newIndexBack = j - 1;

                Log.d("dev", "newIndex: " + newIndex + " newIndexBack: " + newIndexBack);

                if(newIndex >= hexagonIdRings.get(i).size()) {
                    newIndex = newIndex % hexagonIdRings.get(i).size();
                }
                if(newIndexBack < 0) {
                    newIndexBack = hexagonIdRings.get(i).size() - Math.abs(newIndexBack);
                }
                hGraph[getId(i, j)][getId(i, newIndex)] = true;
                hGraph[getId(i, j)][getId(i, newIndexBack)] = true;
            }
        }

        for (int i = 0; i < hGraph.length; i++) {
            for (int j = 0; j < hGraph[i].length; j++) {
                hGraph[j][i] = hGraph[i][j];
            }
        }
        
        StringBuilder str = new StringBuilder();
        str.append("\n\n----------------\n");
        for (int i = 0; i < hGraph.length; i++) {
            for (int j = 0; j < hGraph[i].length; j++) {
                str.append(i).append("-").append(j).append("=");
                if (hGraph[i][j]) str.append("t ");
                else str.append("f ");
            }
            str.append("\n");
        }
        Log.d("dev", "" + str.toString());
    } // end constructor

    public int getId(int ring, int col) {
        return hexagonIdRings.get(ring).get(col);
    }

    // populating hexagonIdRings with hex IDs (0-18, 19 hexagons)
    private void populateHexagonIds() {
        int id = 0;
        for (int i = 0; i < 3; i++) {
            this.hexagonIdRings.add(new ArrayList<Integer>());
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

    private void populateIntersectionIds() {
        int id = 0;
        for (int i = 0; i < 3; i++) {
            this.intersectionIdRings.add(new ArrayList<Integer>());
            for (int j = 0; j < ((2 * i) + 1) * 6; j++) {
                this.intersectionIdRings.get(i).add(id);
                id++;
            }
        }
    }

    private void buildIGraph() {
        for (int i = 0; i < 3; i++) {
            
        }
    }

    public boolean checkHexagonAdjacency(int id1, int id2) {
        return (hGraph[id1][id2] || hGraph[id2][id1]);
    }

    /**
     * toString method
     *
     * @return String
     */
    // TODO not working
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("\nHexagon IDs:\n");
        str.append(listToString(this.hexagonIdRings));
        str.append("Intersection IDs:\n");
        str.append(listToString(this.intersectionIdRings));
        Log.d("dev", "" + str.toString());
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
