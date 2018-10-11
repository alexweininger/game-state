package cs.up.catan.catangamestate;

import java.util.ArrayList;

public class Board extends GameState {

    private ArrayList<ArrayList<Integer>> intersectionRings;
    private ArrayList<ArrayList<Integer>> hexagonRings;

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
