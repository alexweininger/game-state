package cs.up.catan.catangamestate;

public class Dice extends GameState {
    private int roll_number;

    public Dice(int roll){
        this.roll_number = roll;
        int[] rollArr = new int[2];

        int x = (int) (Math.random() * 6);
        int y = (int) (Math.random() * 6);

        rollArr[0] = x;
        rollArr[1] = y;

        int endVal = rollArr[0] + rollArr[1];
    }

    @Override
    public String toString() {
        return "The dice roll was: " + roll_number;
    }
}
