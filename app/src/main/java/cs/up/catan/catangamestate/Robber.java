package cs.up.catan.catangamestate;

public class Robber extends GameState {

    //number that the robber is placed on
    private int currentPosition = 0;
    private int positionMovedTo = 0 ;

    public Robber(int current_position, int position_moved_to) {
        this.currentPosition = current_position;
        this.positionMovedTo = position_moved_to;
    }

    @Override
    public String toString() {
        return "The robber is currently at: " + currentPosition + "\nThe robber has been" +
                "move to: " + positionMovedTo;
    }
}
