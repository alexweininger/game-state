package cs.up.catan.catangamestate;

/**
 * Board class
 *
 * @author Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version October 25th, 2018
 * https://github.com/alexweininger/game-state
 * all information about the current state of the board is contained in this class:
 * - buildings, and their locations
 */

import android.util.Log;

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

    private ArrayList<Integer> portIntersectionLocations = new ArrayList<>(12);

    Board() {
        // populate ids
        populateHexagonIds();
        populateIntersectionIds();
        populatePortIntersectionIds();

        // generate adj. graphs
        generateHexagonGraph();
        generateIntersectionGraph();

        // print graphs
        printGraph(hGraph);
        printGraph(iGraph);

        // generate maps
        generateIntToHexMap();
        generateHexToIntMap();

        Log.d("devInfo", "INFO: int to hex map: " + this.intToHexIdMap.toString());
        Log.d("devInfo", "INFO: hex to int map" + this.hexToIntIdMap.toString());

        int desertTileId = 0;
        robber = new Robber(desertTileId);

    } // end Board constructor

    /**
     * @param intersectionId
     * @return
     */
    public Building getBuildingAtIntersection(int intersectionId) {
        return this.buildings.get(intersectionId);
    }

    /**
     * @param b - board to copy
     */
    Board(Board b) {
        this.hexagonIdRings = b.getHexagonIdRings();
        this.intersectionIdRings = b.getIntersectionIdRings();
        this.hGraph = b.gethGraph();
        this.iGraph = b.getiGraph();
        this.hexToIntIdMap = b.getHexToIntIdMap();
        this.intToHexIdMap = b.getIntToHexIdMap();
        this.buildings = b.getBuildings();
        this.roads = b.getRoads();
        this.hexagons = b.getHexagons();
        this.robber = new Robber(b.getRobber());
        this.portIntersectionLocations = b.getPortIntersectionLocations();
    } // end Board deep copy constructor

    /**
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
     * generateHexagonGraph
     */
    private void generateHexagonGraph() {
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
    } // end generateHexagonGraph

    /**
     * generates the intersection adjacency graph
     */
    private void generateIntersectionGraph() {
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
    } // end generateIntersectionGraph method

    /**
     * generates the hexagon to integer map from the integer to hexagon map
     */
    private void generateHexToIntMap() {
        for (int i = 0; i < 19; i++) {
            this.hexToIntIdMap.add(new ArrayList<Integer>(6));
        }
        for (int i = 0; i < intToHexIdMap.size(); i++) {
            for (int j = 0; j < intToHexIdMap.get(i).size(); j++) {
                this.hexToIntIdMap.get(this.intToHexIdMap.get(i).get(j)).add(i);
            }
        }
    }

    /**
     * manually generates the integer to hexagon map
     */
    private void generateIntToHexMap() {
        for (int i = 0; i < 54; i++) {
            intToHexIdMap.add(new ArrayList<Integer>(3));
        }
        intToHexIdMap.get(0).add(0); // inner ring of intersections (ring 0)
        intToHexIdMap.get(0).add(1);
        intToHexIdMap.get(0).add(2);

        intToHexIdMap.get(1).add(0);
        intToHexIdMap.get(1).add(2);
        intToHexIdMap.get(1).add(3);

        intToHexIdMap.get(2).add(0);
        intToHexIdMap.get(2).add(3);
        intToHexIdMap.get(2).add(4);

        intToHexIdMap.get(3).add(4);
        intToHexIdMap.get(3).add(5);
        intToHexIdMap.get(3).add(0);

        intToHexIdMap.get(4).add(0);
        intToHexIdMap.get(4).add(5);
        intToHexIdMap.get(4).add(6);

        intToHexIdMap.get(5).add(0);
        intToHexIdMap.get(5).add(1);
        intToHexIdMap.get(5).add(6);

        intToHexIdMap.get(6).add(1); // middle ring of intersections (ring 1)
        intToHexIdMap.get(6).add(2);
        intToHexIdMap.get(6).add(8);

        intToHexIdMap.get(7).add(2);
        intToHexIdMap.get(7).add(8);
        intToHexIdMap.get(7).add(9);

        intToHexIdMap.get(8).add(2);
        intToHexIdMap.get(8).add(10);
        intToHexIdMap.get(8).add(9);

        intToHexIdMap.get(9).add(2);
        intToHexIdMap.get(9).add(3);
        intToHexIdMap.get(9).add(10);

        intToHexIdMap.get(10).add(3);
        intToHexIdMap.get(10).add(11);
        intToHexIdMap.get(10).add(10);

        intToHexIdMap.get(11).add(3);
        intToHexIdMap.get(11).add(12);
        intToHexIdMap.get(11).add(11);

        intToHexIdMap.get(12).add(3);
        intToHexIdMap.get(12).add(4);
        intToHexIdMap.get(12).add(12);

        intToHexIdMap.get(13).add(4);
        intToHexIdMap.get(13).add(12);
        intToHexIdMap.get(13).add(13);

        intToHexIdMap.get(14).add(4);
        intToHexIdMap.get(14).add(13);
        intToHexIdMap.get(14).add(14);

        intToHexIdMap.get(15).add(4);
        intToHexIdMap.get(15).add(5);
        intToHexIdMap.get(15).add(14);

        intToHexIdMap.get(16).add(5);
        intToHexIdMap.get(16).add(14);
        intToHexIdMap.get(16).add(15);

        intToHexIdMap.get(17).add(5);
        intToHexIdMap.get(17).add(15);
        intToHexIdMap.get(17).add(16);

        intToHexIdMap.get(18).add(5);
        intToHexIdMap.get(18).add(6);
        intToHexIdMap.get(18).add(16);

        intToHexIdMap.get(19).add(6);
        intToHexIdMap.get(19).add(16);
        intToHexIdMap.get(19).add(17);

        intToHexIdMap.get(20).add(6);
        intToHexIdMap.get(20).add(17);
        intToHexIdMap.get(20).add(18);

        intToHexIdMap.get(21).add(6);
        intToHexIdMap.get(21).add(1);
        intToHexIdMap.get(21).add(18);

        intToHexIdMap.get(22).add(1);
        intToHexIdMap.get(22).add(18);
        intToHexIdMap.get(22).add(7);

        intToHexIdMap.get(23).add(1);
        intToHexIdMap.get(23).add(7);
        intToHexIdMap.get(23).add(8);

        intToHexIdMap.get(24).add(7); // outer ring of intersections (ring 2)
        intToHexIdMap.get(24).add(8); // side 1

        intToHexIdMap.get(25).add(8);

        intToHexIdMap.get(26).add(8);
        intToHexIdMap.get(26).add(9);

        intToHexIdMap.get(27).add(9);

        intToHexIdMap.get(28).add(9);

        intToHexIdMap.get(29).add(9); // side 2
        intToHexIdMap.get(29).add(10);

        intToHexIdMap.get(30).add(10);

        intToHexIdMap.get(31).add(10);
        intToHexIdMap.get(31).add(11);

        intToHexIdMap.get(32).add(11);

        intToHexIdMap.get(33).add(11);

        intToHexIdMap.get(34).add(11); // side 3
        intToHexIdMap.get(34).add(12);

        intToHexIdMap.get(35).add(12);

        intToHexIdMap.get(36).add(12);
        intToHexIdMap.get(36).add(13);

        intToHexIdMap.get(37).add(13);

        intToHexIdMap.get(38).add(13);

        intToHexIdMap.get(39).add(13); // side 4
        intToHexIdMap.get(39).add(14);

        intToHexIdMap.get(40).add(14);

        intToHexIdMap.get(41).add(14);
        intToHexIdMap.get(41).add(15);

        intToHexIdMap.get(42).add(15);

        intToHexIdMap.get(43).add(15);

        intToHexIdMap.get(44).add(15); // side 5
        intToHexIdMap.get(44).add(16);

        intToHexIdMap.get(45).add(16);

        intToHexIdMap.get(46).add(16);
        intToHexIdMap.get(46).add(17);

        intToHexIdMap.get(47).add(17);

        intToHexIdMap.get(48).add(17);

        intToHexIdMap.get(49).add(17); // side 6
        intToHexIdMap.get(49).add(18);

        intToHexIdMap.get(50).add(18);

        intToHexIdMap.get(51).add(18);
        intToHexIdMap.get(51).add(7);

        intToHexIdMap.get(52).add(7);

        intToHexIdMap.get(53).add(7);
    }

    /**
     * builds the ArrayList of Hexagon objects, creating the correct amount of each resource tile,
     * randomly assigning them to locations. Also randomly gives Hexagon a chit value.
     */
    private void populateHexagonList() {
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
     * TODO remove and fix
     * adds ports to the intersection and port hash map
     */
    private void populatePortIntersectionIds() {
        for (int i = 0; i < 6; i++) {
            portIntersectionLocations.add(17 + i * 6);
            portIntersectionLocations.add(17 + i * 6 + 1);
        }
    }

    /**
     * @param hexagonId - hexagon id - AW
     * @return Hexagon
     */
    Hexagon getHexagonFromId(int hexagonId) {
        if (hexagonId < 0 || hexagonId >= this.hexagons.size()) { // error checking
            Log.d("devError", "getHexagonFromId: ERROR cannot get hexagon with id: " + hexagonId + ". Does not exists in ArrayList hexagons.");
            return null;
        }
        return this.hexagons.get(hexagonId);
    }


    /**
     * @param intersectionId - intersection to check for port adjacency
     * @return - if the given intersection is adjacent to a port AW
     */
    public boolean checkPortAdjacency(int intersectionId) {
        return portIntersectionLocations.contains(intersectionId);
    }


    /**
     * @return ArrayList of buildings on board AW
     */
    public ArrayList<Building> getBuildingList() {
        ArrayList<Building> result = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            if (buildings.containsKey(i)) {
                result.add(buildings.get(i));
            }
        }
        return result;
    }

    /**
     * @param road - road to add to the board, this is called after checks are made in the GameState class
     */
    public boolean addRoad(Road road) {
        /* 1. check if intersections have buildings owned by other players
         * 2. check if intersection has building or road owned by player
         */


        roads.add(road);
        return false;
    }


    /**
     * @param intersectionId
     * @return
     */
    public boolean checkIntersectionForBuilding(int intersectionId) {
        // TODO
        return false;
    }

    /**
     * @param intersectionId -
     * @return -
     */
    public ArrayList<Integer> getIntersectionOwners(int intersectionId) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (buildings.containsKey(intersectionId)) {
            result.add(buildings.get(intersectionId).getOwnerId());
            return result;
        } else if (hasRoad(intersectionId)) {
            for (int i = 0; i < roads.size(); i++) {
                if (roads.get(i).getEndIntersectionID() == intersectionId || roads.get(i).getStartIntersectionID() == intersectionId) {
                    result.add(roads.get(i).getOwnerId());
                }
            }
        }
        return result;
    }

    /**
     * returns whether a given player is an owner of the intersection
     *
     * @param intersectionId - intersection to check if playerId owns
     * @param playerId       - playerId to check against
     * @return
     */
    public boolean isIntersectionOwner(int intersectionId, int playerId) {
        if (buildings.containsKey(intersectionId) || hasRoad(intersectionId)) {
            if (buildings.get(intersectionId).getOwnerId() == playerId) {
                return true;
            }
            for (int i = 0; i < roads.size(); i++) {
                if (roads.get(i).getEndIntersectionID() == intersectionId || roads.get(i).getStartIntersectionID() == intersectionId) {
                    if (roads.get(i).getOwnerId() == playerId) {
                        return true;
                    }
                }
            }
        }
        return false;
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
     * isIntersectionBuildable
     *
     * @param intersectionId
     * @return
     */
    public boolean isIntresectionBuildable(int intersectionId) {
        return this.buildings.containsKey(intersectionId);
    }


    /**
     * TODO TEST
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
     * TODO TEST
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
     * TODO used for resource production
     *
     * @param hexagonId - hexagon id
     * @return - array list of intersection ids that are adjacent to the given hexagon
     */
    public ArrayList<Integer> getIntersectoinsAdjToHexagon(int hexagonId) {
        return this.hexToIntIdMap.get(hexagonId);
    }

    /**
     * @param intId1 - intersection id
     * @param intId2 - intersection id
     * @return - boolean adjacency
     */
    boolean intersectionAdjCheck(int intId1, int intId2) {
        return (iGraph[intId1][intId2] || iGraph[intId2][intId1]);
    }

    /**
     * @param hexId1 -
     * @param hexId2 -
     * @return - boolean
     */
    public boolean checkHexagonAdjacency(int hexId1, int hexId2) {
        return (hGraph[hexId1][hexId2] || hGraph[hexId2][hexId1]);
    }


    /**
     * @param ring - ring of intersection
     * @param col  - column within ring of intersection
     * @return - int intersection id
     */
    private int getIntersectionId(int ring, int col) {
        return intersectionIdRings.get(ring).get(col);
    }


    /**
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

    /**
     * @param intersectionId
     * @param checkedIntersections
     * @return
     */
    private int getRoadLength(int intersectionId, ArrayList<Integer> checkedIntersections) {
        checkedIntersections.add(intersectionId);
        // base case if road is dead end
        ArrayList<Integer> adjInts = getAdjacentIntersections(intersectionId);
        for (int i = 0; i < adjInts.size(); i++) {
            if (hasRoad(adjInts.get(i)) && !checkedIntersections.contains(adjInts.get(i))) {
                return getRoadLength(adjInts.get(i), checkedIntersections) + 1;
            }
        }
        return 0;
    }

    /**
     * @param intersectionId -
     * @return returns if road is connected to given intersection
     */
    public boolean hasRoad(int intersectionId) {
        for (int i = 0; i < this.roads.size(); i++) {
            if (this.roads.get(i).getEndIntersectionID() == intersectionId || this.roads.get(i).getEndIntersectionID() == intersectionId) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<ArrayList<Integer>> getHexagonIdRings() {
        return hexagonIdRings;
    }

    public void setHexagonIdRings(ArrayList<ArrayList<Integer>> hexagonIdRings) {
        this.hexagonIdRings = hexagonIdRings;
    }

    public ArrayList<ArrayList<Integer>> getIntersectionIdRings() {
        return intersectionIdRings;
    }

    public void setIntersectionIdRings(ArrayList<ArrayList<Integer>> intersectionIdRings) {
        this.intersectionIdRings = intersectionIdRings;
    }

    public boolean[][] gethGraph() {
        return hGraph;
    }

    public boolean[][] getiGraph() {
        return iGraph;
    }

    public ArrayList<ArrayList<Integer>> getHexToIntIdMap() {
        return hexToIntIdMap;
    }

    public ArrayList<ArrayList<Integer>> getIntToHexIdMap() {
        return intToHexIdMap;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }


    public ArrayList<Hexagon> getHexagons() {
        return this.hexagons;
    }

    private Robber getRobber() {
        return this.robber;
    }

    public HashMap<Integer, Building> getBuildings() {
        return this.buildings;
    }

    protected ArrayList<Integer> getPortIntersectionLocations() {
        return this.portIntersectionLocations;
    }

    /**
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
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Hexagon IDs:\n");
        str.append(listToString(this.hexagonIdRings));
        str.append("Intersection IDs:\n");
        str.append(listToString(this.intersectionIdRings));
        Log.d("dev", "" + str.toString());
        return str.toString();
    } // end toString()

    /**
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