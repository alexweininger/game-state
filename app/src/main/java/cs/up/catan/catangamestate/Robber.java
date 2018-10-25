package cs.up.catan.catangamestate;

/**
 * @author Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version October 10th, 2018
 * https://github.com/alexweininger/game-state
 **/
public class Robber {

    private int currentHexagonId; // hexagon that the robber is placed on


    /**
     *
     * @param currentHexagonId - where the robber is currently
     */
    public Robber(int currentHexagonId) {
        this.currentHexagonId = currentHexagonId;
    }

    /**
     *
     * @return string representation of a Robber
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("The robber is currently at: ");
        sb.append(currentHexagonId);
        sb.append("\nThe robber has been");
        sb.append("move to: ");

        return sb.toString();
    }

    //sets the new position of the Robber to be moved
    public void setCurrentHexagonId(int newHexagonId) {
        this.currentHexagonId = newHexagonId;
    }

    //returns the current location of the Robber
    public int getCurrentHexagonId() {
        return currentHexagonId;
    }
}
