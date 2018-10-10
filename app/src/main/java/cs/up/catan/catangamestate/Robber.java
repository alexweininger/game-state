package cs.up.catan.catangamestate;

public class Robber extends GameState {

    //number that the robber is placed on
    private int current_position;
    private int position_moved_to;

    public Robber(int current_position, int position_moved_to) {
        this.current_position = current_position;
        this.position_moved_to = position_moved_to;
    }

    @Override
    public String toString() {
        return "The robber is currently at: " + current_position;
    }
}
