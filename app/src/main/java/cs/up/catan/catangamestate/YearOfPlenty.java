package cs.up.catan.catangamestate;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 25th, 2018
 * https://github.com/alexweininger/game-state
 **/
public class YearOfPlenty extends DevelopmentCards {
    public YearOfPlenty() {
        super("Year of plenty");
    }

    /*
    Year of Plenty: If you play this card you may immediately
                    take any 2 Resource Cards from the supply stacks. You
                    may use these cards to build in the same turn.
     */
    @Override
    public void useCard(Player player) {
        super.useCard(player);
        for (int i = 0; i < 2; i++) {
            int random = (int) Math.random() * 5;
            switch (random) {
                case 0:
                    player.addResources("Brick", 1);
                    break;
                case 1:
                    player.addResources("Ore", 1);
                    break;
                case 2:
                    player.addResources("Sheep", 1);
                    break;
                case 3:
                    player.addResources("Wheat", 1);
                    break;
                case 4:
                    player.addResources("Wood", 1);
                    break;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        return sb.toString();
    }
}
