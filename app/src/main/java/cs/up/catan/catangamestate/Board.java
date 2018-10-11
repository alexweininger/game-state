package cs.up.catan.catangamestate;

import java.util.ArrayList;
import java.util.HashMap;

public class Board extends GameState {

    private ArrayList<ArrayList<Integer>> intersectionRings = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> hexagonRings = new ArrayList<>();

    public Board(){

    }

    @Override
    public String toString() {
        String intsectionRingsString = "";
        String hexagonRingString = "";

        for (int i = 0; i < intersectionRings.size(); i++)
        {
            intsectionRingsString = intersectionRings.get(i) + " ";
        }

        for (int i = 0; i < hexagonRings.size(); i++)
        {
            hexagonRingString = hexagonRings.get(i) + " ";
        }


        return "Intersection Rings: " + intsectionRingsString + "\n" +
                "Hexagon Rings: " + hexagonRingString + "\n";
    }
}
