package cs.up.catan.catangamestate;

import java.util.ArrayList;
import java.util.HashMap;

public class Board extends GameState {

    private ArrayList<ArrayList<Integer>> intersectionRings = new ArrayList<ArrayList<Integer>>();
    private ArrayList<ArrayList<Integer>> hexagonRings = new ArrayList<ArrayList<Integer>>();

    public Board() {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(6);
        this.intersectionRings.add(temp);
        this.intersectionRings.add(temp);
    }

    @Override
    public String toString() {
        String intersectionRingsString = "";
        String hexagonRingString = "";

        for (int i = 0; i < intersectionRings.size(); i++) {
            intersectionRingsString = intersectionRings.get(i) + " ";
        }

        for (int i = 0; i < hexagonRings.size(); i++) {
            hexagonRingString = hexagonRings.get(i) + " ";
        }


        return "Intersection Rings: " + intersectionRingsString + "\n" +
                "Hexagon Rings: " + hexagonRingString + "\n";
    }
}
