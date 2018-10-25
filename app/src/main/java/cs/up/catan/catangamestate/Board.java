package cs.up.catan.catangamestate;
/*
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 24th, 2018
 * github: https://github.com/alexweininger/game-state
 */

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

    /*  hGraph and iGraph are 2d arrays that hold adjacency information for hexagons and intersections. */
    private boolean[][] hGraph = new boolean[19][19];
    private boolean[][] iGraph = new boolean[54][54];

    /**
     * Board constructor
     * defines hexagonIdRings, intersectionIdRings, and hexagonAdjacencyGraph.
     */
    public Board() {

        // populate ids
        populateHexagonIds();
        populateIntersectionIds();

        // generate adj. graphs
        hGraphGeneration();
        iGraphGeneration();

        // print adj. graphs
        printGraph(this.hGraph);
        printGraph(this.iGraph);

    } // end constructor

    /**
     * intersectionAdjCheck
     *
     * @param intId1
     * @param intId2
     * @return
     */
    boolean intersectionAdjCheck(int intId1, int intId2) {
        return (iGraph[intId1][intId2] || iGraph[intId2][intId1]);
    }

    /**
     * checkHexagonAdjacency
     *
     * @param hexId1
     * @param hexId2
     * @return
     */
    public boolean checkHexagonAdjacency(int hexId1, int hexId2) {
        return (hGraph[hexId1][hexId2] || hGraph[hexId2][hexId1]);
    }

    /**
     * populateHexagonIds
     * populating hexagonIdRings with hex IDs (0-18, 19 hexagons)
     */
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

    /**
     * populateIntersectionIds
     * populating intersectionIdRings with intersection IDs (0-53, 54 intersections)
     */
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

    /**
     * hGraphGeneration
     */
    private void hGraphGeneration() {
        // set all values in the 2d array to false
        for (int i = 0; i < 2; i++) { // rings
            for (int j = 0; j < this.hexagonIdRings.get(i).size(); j++) { // cols
                this.hGraph[i][hexagonIdRings.get(i).get(j)] = false;
            }
        }
        for (int col = 0; col < 6; col++) {
            hGraph[0][col] = true;
        }
        for (int i = 0; i < 2; i++) { // rings (rows)
            for (int j = 0; j < this.hexagonIdRings.get(i).size(); j++) { // cols
                // i and j are only 0 once and never are 0 again 0, 0 = center
                /*
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

                if (newIndex >= hexagonIdRings.get(i).size()) {
                    newIndex = newIndex % hexagonIdRings.get(i).size();
                }
                if (newIndexBack < 0) {
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
    } // end hGraphGeneration

    /**
     * getIntersectionId
     *
     * @param ring
     * @param col
     * @return intersection id
     */
    private int getIntersectionId(int ring, int col) {
        return intersectionIdRings.get(ring).get(col);
    }

    /**
     * iGraphGeneration
     * generates the intersection adjacency graph
     */
    private void iGraphGeneration() {
        // set all values in the 2d array to false
        for (int i = 0; i < 2; i++) { // rings
            for (int j = 0; j < this.intersectionIdRings.get(i).size(); j++) { // ids
                this.iGraph[i][intersectionIdRings.get(i).get(j)] = false;
            }
        }
        for (int i = 0; i < 3; i++) { //rings 0-2
            boolean hasNextLink = true; // is it looking to the next ring or prev ring
            int skipCount = 2; // # of intersections to skip to switch hasNext
            for (int j = 0; j < intersectionIdRings.get(i).size(); j++) { //columns starts at 1 and ends at 0 (wrapped by 1)
                if (i == 1) {
                    if (skipCount == 0) {
                        hasNextLink = false;
                        skipCount = 2;
                    } else {
                        hasNextLink = true;
                        skipCount--;
                    }
                }

                int size = intersectionIdRings.get(i).size();
                int col = j % size; // wrap if needs to be 0
                int ringIndexDiff = -1;
                if (i == 2) {
                    hasNextLink = false;
                }
                if (i == 0) {
                    hasNextLink = true;
                }
                if (i == 1) {
                    col = (j + 1) % size;
                }

                int nextIntersection = (col + 1) % size;
                iGraph[getIntersectionId(i, col)][getIntersectionId(i, nextIntersection)] = true;

                Log.d("dev", "skip: " + skipCount);
                if (hasNextLink) {
                    Log.d("dev", "nextLink: i: " + i + " col: " + col + " skip: " + skipCount);
                    if (col + ringIndexDiff == -1) {
                        iGraph[getIntersectionId(i, col)][getIntersectionId(i + 1, 15)] = true;
                    } else {
                        iGraph[getIntersectionId(i, col)][getIntersectionId(i + 1, col + ringIndexDiff)] = true;
                    }
                }
            }
        }
    } // end iGraphGeneration

    /**
     * getId
     *
     * @param ring
     * @param col
     * @return
     */
    private int getId(int ring, int col) {
        return hexagonIdRings.get(ring).get(col);
    }

    /**
     * @param arr
     */
    private void printGraph(boolean arr[][]) {
        StringBuilder str = new StringBuilder();
        str.append("\n\n----------------\n");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                str.append(i).append("-").append(j).append("=");
                if (arr[i][j]) str.append("t\t");
                else str.append("f\t");
            }
            str.append("\n");
        }
        Log.d("dev", "" + str.toString());
    }

    /**
     * toString method
     *
     * @return String
     */
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

    private String listToString(ArrayList<ArrayList<Integer>> list) {
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
