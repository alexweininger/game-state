package cs.up.catan.catangamestate;

/**
 * Board class
 *
 * @author Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version October 10th, 2018
 * https://github.com/alexweininger/game-state
 * all information about the current state of the board is contained in this class:
 * - buildings, and their locations
 */

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class Board {
    /*
     * External Citation
     * Date: 8 October 2018
     * Problem: Struggling to represent board and tiles.
     * Resource:
     * https://www.academia.edu/9699475/Settlers_of_Catan_Developing_an_Implementation_
     * of_an_Emerging_Classic_Board_Game_in_Java
     * Solution: We used the concepts and ideas from this research paper to help us represent the board
     * information and the hexagons.
     */

    /* 'Rings' are used to organize the following ID 2D-ArrayLists. Rings in context mean ring of hexagons or intersections
     * on the board. So for hexagons, the first ring contains the very middle hexagon. Ring 2 are the hexagons around that one.
     * Hexagon 0 is the center, and hex 1 is directly right of hex 0, and then they are numbered by ring. So ring 0 has 1
     * hexagon. Ring 2 has 6, and ring 3 (outer ring) has 12 hexagons.
     */

    // hexagonIdRings holds the IDs of each hexagon on the board, organized into rings.
    private ArrayList<ArrayList<Integer>> hexagonIdRings = new ArrayList<>();
    // intersectionIdRings holds the IDs of each intersection on the board, organized into rings.
    private ArrayList<ArrayList<Integer>> intersectionIdRings = new ArrayList<>();

    /*  hGraph and iGraph are 2d arrays that hold adjacency information for hexagons and intersections. */
    private boolean[][] hGraph = new boolean[19][19];
    private boolean[][] iGraph = new boolean[54][54];

    /* maps relating hex to intersection and intersection to hex ids */
    private ArrayList<ArrayList<Integer>> hexToIntIdMap = new ArrayList<>(); // rows: hex id - col: int ids
    private ArrayList<ArrayList<Integer>> intToHexIdMap = new ArrayList<>(); // rows: int id - col: hex id

    /* k: intersectionId v: building, works perfectly since there can only be 1 building per intersection*/
    private HashMap<Integer, Building> buildings = new HashMap<>();

    private ArrayList<Road> roads = new ArrayList<>();

    private ArrayList<Hexagon> hexagons = new ArrayList<>(); // list of resource tiles
    private Robber robber; // robber object

    /**
     * defines hexagonIdRings, intersectionIdRings, and hexagonAdjacencyGraph.
     */
    Board() {
        // populate ids
        populateHexagonIds();
        populateIntersectionIds();

        // generate adj. graphs
        hGraphGeneration();
        iGraphGeneration();

        // print graphs
        printGraph(hGraph);
        printGraph(iGraph);
        // generateHexToIntIdMap();

        // TODO place robber on desert tile at start
        int desertTileId = 0;
        robber = new Robber(desertTileId);

    } // end Board constructor

    /**
     * @param hexagonId - hexagon id - AW
     * @return Hexagon
     */
    Hexagon getHexagonFromId(int hexagonId) {
        if (hexagonId < 0 || hexagonId >= hexagons.size()) { // error checking
            Log.d("devError", "getHexagonFromId: ERROR cannot get hexagon with id: " + hexagonId + ". Does not exists in ArrayList hexagons.");
            return null;
        }
        return hexagons.get(hexagonId);
    }

    /**
     * @param chitValue - value of dice sum and tile chit value that will produce resources
     * @return list of hexagons with chitValue AND DO NOT HAVE ROBBER - AW
     */
    ArrayList<Integer> getHexagonsFromChitValue(int chitValue) {
        ArrayList<Integer> hexagonIdList = new ArrayList<>();
        for (int i = 0; i < this.hexagons.size(); i++) {
            // check for chit value
            if (this.hexagons.get(i).getChitValue() == chitValue) {
                // check if robber is on hexagon
                if (this.robber.getHexagonId() != i) {
                    hexagonIdList.add(i);
                } else {
                    Log.d("dev", "getHexagonsFromChitValue: robber was detected on hexagon, hexagon with id: " + i + " not producing resources chit values: " + chitValue);
                }
            }
        }
        if (hexagonIdList.size() > 2) { // error checking
            Log.d("devError", "getHexagonsFromChitValue: ERROR returning a list with more than 2 hexagons with chit values of: " + chitValue);
        }
        return hexagonIdList;
    }

    /**
     * @param hexagonId - hexagonId to move the robber to
     * @return - true robber is moved, false if robber cannot be moved (trying to move to same hex) - AW
     */
    public boolean moveRobber(int hexagonId) {
        // check if moving to same hexagon
        if (hexagonId == this.robber.getHexagonId()) return false;

        // change robber position
        this.robber.setHexagonId(hexagonId);
        return true;
    }

    /**
     * adds the building to the building HashMap - AW
     *
     * @param intersectionId - intersection id of the building location
     * @param building       - building object
     */
    public void addBuilding(int intersectionId, Building building) {
        if (this.buildings.containsKey(intersectionId)) {
            Log.d("devError", "addBuilding: ERROR added a building when there was already a building here intersection id: " + intersectionId);
        }
        this.buildings.put(intersectionId, building);
    }

    /**
     * addRoad - attempts to add a new road to the board,
     *
     * @param startIntersectinId
     * @param endIntersectionId
     * @param ownerId
     * @return
     */
    public boolean addRoad(int startIntersectinId, int endIntersectionId, int ownerId) {

        Road road = new Road(startIntersectinId, endIntersectionId, ownerId);
        return false;
    }

    /**
     * populateHexagonList - AW
     * builds the ArrayList of Hexagon objects, creating the correct amount of each resource tile,
     * randomly assigning them to locations. Also randomly gives Hexagon a chit value.
     */
    public void populateHexagonList() {
        int[] resourceTypeCount = {4, 3, 3, 3, 4};
        int[] chitValuesCount = {0, 0, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1};
        String[] resources = {"Brick", "Wool", "Grain", "Ore", "Wood"};
        for (int i = 0; i < 18; i++) {
            int max = resourceTypeCount.length - 1;
            Random random = new Random();
            int randomResourceType = random.nextInt((max) + 1);
            while (resourceTypeCount[randomResourceType] < 0) {
                randomResourceType = random.nextInt((max) + 1);
            }
            max = chitValuesCount.length - 1;
            int randomChitValue = random.nextInt((max) + 1);
            while (chitValuesCount[randomChitValue] < 0) {
                randomChitValue = random.nextInt((max) + 1);
            }


            hexagons.add(new Hexagon(resources[randomResourceType], randomChitValue));
            resourceTypeCount[randomResourceType]--;
        }
    }

    /**
     * isIntersectionBuildable
     *
     * @param intersectionId
     * @return
     */
    public boolean isIntresectionBuildable(int intersectionId) {
        return this.buildings.containsKey(intersectionId);
    }


    /**
     * getAdjacentIntersections
     *
     * @param intersectionId - given intersection i (0-53)
     * @return - ArrayList of intersection ids that are adjacent to the given intersection id
     */
    public ArrayList<Integer> getAdjacentIntersections(int intersectionId) {
        ArrayList<Integer> adjacentIntersections = new ArrayList<>(6);
        for (int i = 0; i < 54; i++) {
            if (adjacentIntersections.size() > 3) {
                Log.d("devError", "getAdjacentIntersections: ERROR got more than 3 adjacent intersections");
                break;
            }
            if (iGraph[intersectionId][i] || iGraph[i][intersectionId]) {
                adjacentIntersections.add(i);
            }
        }
        return adjacentIntersections;
    }

    /**
     * getAdjacentHexagons
     *
     * @param hexagonId - hexagon id that you want to get adjacency of
     * @return ArrayList<Integer> - list of adj. hex id's
     */
    public ArrayList<Integer> getAdjacentHexagons(int hexagonId) {
        ArrayList<Integer> adjacentHexagons = new ArrayList<>(6);
        for (int i = 0; i < 19; i++) {
            if (adjacentHexagons.size() > 6) {
                Log.d("devError", "getAdjacentHexagons: ERROR got more than 6 adjacent hexagons");
                break;
            }
            if (hGraph[hexagonId][i] || hGraph[i][hexagonId]) {
                adjacentHexagons.add(i);
            }
        }
        return adjacentHexagons;
    }

    /**
     * generateHexToIntIdMap
     * generates hexagon to int id map TODO
     */
    private void generateHexToIntIdMap() {
        for (int i = 0; i < 19; i++) {
            hexToIntIdMap.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < 54; i++) {
            intToHexIdMap.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < 3; i++) { // rings
            for (int j = 0; j < hexagonIdRings.get(i).size(); j++) { // j = hex id
                int hexId = getHexagonId(i, j);
                boolean corner = false;
                corner = j % i == 0;
                if (corner) {
                    // 2 prev 4 next
                    hexToIntIdMap.get(hexId).add(getIntersectionId(i - 1, j));
                    hexToIntIdMap.get(hexId).add(getIntersectionId(i - 1, j + 1));
                    hexToIntIdMap.get(hexId).add(getIntersectionId(i, j));
                    hexToIntIdMap.get(hexId).add(getIntersectionId(i, j - 1));
                    hexToIntIdMap.get(hexId).add(getIntersectionId(i, j - 2));
                    hexToIntIdMap.get(hexId).add(getIntersectionId(i, j - 3));
                } else {
                    // 3 prev 3 next
                }

            }
        }
    }

    /**
     * generateIntToHexIdMap TODO
     */
    private void generateIntToHexIdMap() {

    }

    /**
     * intersectionAdjCheck
     *
     * @param intId1 - intersection id
     * @param intId2 - intersection id
     * @return - boolean adjacency
     */
    boolean intersectionAdjCheck(int intId1, int intId2) {
        return (iGraph[intId1][intId2] || iGraph[intId2][intId1]);
    }

    /**
     * checkHexagonAdjacency
     *
     * @param hexId1 -
     * @param hexId2 -
     * @return - boolean
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

                this.hGraph[getHexagonId(i, j)][getHexagonId(i + 1, j + sextant)] = true;
                this.hGraph[getHexagonId(i, j)][getHexagonId(i + 1, j + sextant + 1)] = true;

                if (corner) {
                    int size = hexagonIdRings.get(i + 1).size();
                    int nextIndex = ((j - 1 + sextant) % size);
                    if (nextIndex < 12 && nextIndex >= 0) {
                        hGraph[getHexagonId(i, j)][getHexagonId(i + 1, nextIndex)] = true;
                    } else {
                        // Log.d("dev", "id value wrapped: " + (Math.abs(j - 1 + sextant) % size));
                        hGraph[getHexagonId(i, j)][getHexagonId(i + 1, size - Math.abs(j - 1 + sextant) % size)] = true;
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < hexagonIdRings.get(i).size(); j++) {
                hGraph[getHexagonId(i, j)][getHexagonId(i, j)] = true;
                int newIndex = j + 1;
                int newIndexBack = j - 1;

                if (newIndex >= hexagonIdRings.get(i).size()) {
                    newIndex = newIndex % hexagonIdRings.get(i).size();
                }
                if (newIndexBack < 0) {
                    newIndexBack = hexagonIdRings.get(i).size() - Math.abs(newIndexBack);
                }
                hGraph[getHexagonId(i, j)][getHexagonId(i, newIndex)] = true;
                hGraph[getHexagonId(i, j)][getHexagonId(i, newIndexBack)] = true;
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
     * @param ring - ring of intersection
     * @param col  - column within ring of intersection
     * @return - int intersection id
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

                int size = intersectionIdRings.get(i).size();
                int col = j % size; // wrap if needs to be 0
                int ringIndexDiff = -1;

                if (i == 1) {
                    if (skipCount == 0) {
                        hasNextLink = false;
                        skipCount = 2;
                    } else {
                        hasNextLink = true;
                        skipCount--;
                    }
                    col = (j + 1) % size;
                }

                if (i == 2) hasNextLink = false;

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
     * getHexagonId
     *
     * @param ring - hexagon ring (0-2)
     * @param col  - column within hexagon ring
     * @return - int hexagon id
     */
    private int getHexagonId(int ring, int col) {
        return hexagonIdRings.get(ring).get(col);
    }

    public int getPlayerRoadLength(int playerId) {
        int count = 0;
        Collection<Building> buildingCollection = this.buildings.values();

        for (Building building : buildingCollection) {
            if (building instanceof Road) {
                if (building.getOwnerId() == playerId) {
                    count++;
                }
            }
        }
        return count;
    }

    private int getRoadLength(int intersectionId, ArrayList<Integer> checkedIntersections) {
        // base case if road is dead end
        ArrayList<Integer> adjInts = getAdjacentIntersections(intersectionId);
        return 0;

    }

    public boolean hasRoad(int intersectionId) {
        return false;
    }

    /**
     * printGraph
     *
     * @param arr - graph array 2d boolean array
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
    } // end printGraph

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

    /**
     * listToString - converts list to a string for printing
     *
     * @param list - list to convert
     * @return - String
     */
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
