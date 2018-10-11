package cs.up.catan.catangamestate;

import java.util.ArrayList;

public class Board extends GameState {

    private ArrayList<ArrayList<Integer>> intersectionIdRings = new ArrayList<ArrayList<Integer>>();
    private ArrayList<ArrayList<Integer>> hexagonIdRings = new ArrayList<ArrayList<Integer>>();

    public Board() {
        int hexagonRingSize, intersectionRingSize;
        for (int i = 0; i < 3; i++) {
            hexagonRingSize = i * 6;
            intersectionRingSize = ((i * 2) + 1) * 6;
            this.hexagonIdRings.add(new ArrayList<Integer>(hexagonRingSize));
            this.hexagonIdRings.add(new ArrayList<Integer>(intersectionRingSize));
        }
    }

    @Override
    public String toString() {
        String intersectionRingsString = "";
        String hexagonRingString = "";

        for (int i = 0; i < intersectionIdRings.size(); i++) {
            intersectionRingsString = intersectionIdRings.get(i) + " ";
        }

        for (int i = 0; i < hexagonIdRings.size(); i++) {
            hexagonRingString = hexagonIdRings.get(i) + " ";
        }


        return "Intersection Rings: " + intersectionRingsString + "\n" +
                "Hexagon Rings: " + hexagonRingString + "\n";
    }
}
