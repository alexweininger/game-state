package cs.up.catan.catangamestate;

import java.util.Random;

public class Dice extends GameState {
    private int[] diceValues; // array of dice values

    public Dice() {
        this.diceValues = new int[2];
    }

    /**
     * roll() - sets both dice values to random int from 1 to 6 (inclusive)
     */
    public void roll() {
        Random random = new Random();
        this.diceValues[0] = random.nextInt(5) + 1;
        this.diceValues[1] = random.nextInt(5) + 1;
    }

    /**
     * getSum
     * @return the sum of the dice values
     */
    public int getSum() {
        return diceValues[0] + diceValues[1];
    }

    @Override
    public String toString() {
        // TODO
        return "";
    }
}
