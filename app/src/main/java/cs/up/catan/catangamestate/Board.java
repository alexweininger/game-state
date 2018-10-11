package cs.up.catan.catangamestate;

import java.util.ArrayList;
import java.util.HashMap;

// https://www.academia.edu/9699475/Settlers_of_Catan_Developing_an_Implementation_of_an_Emerging_Classic_Board_Game_in_Java

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
        StringBuilder str = new StringBuilder();

        str.append("Intersection rings:\n");
        for (ArrayList ring : intersectionRings) {
            str.append("Ring: ");
            str.append(this.intersectionRings.indexOf(ring));
            for (Object i: ring) {
                str.append(i);
                str.append(" ");
            }
            str.append("\n");
        }
        str.append("Hexagon rings:\n");
        for (ArrayList ring : hexagonRings) {
            str.append("Ring: ");
            str.append(this.hexagonRings.indexOf(ring));
            for (Object i: ring) {
                str.append(i);
                str.append(" ");
            }
            str.append("\n");
        }

        return str.toString();
    }
}
