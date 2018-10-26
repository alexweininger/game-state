package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 24th, 2018
 * https://github.com/alexweininger/game-state
 **/

public class VictoryPoints extends DevelopmentCard {

    public VictoryPoints() {
        super("Victory Points");
    }


    /**
     *
     * @param player player who is using the card
     */
    @Override
    public void useCard(Player player) {
        super.useCard(player);
        player.setLocalScore(player.getLocalScore() + 1);
    }


    /**
     *
     * @return string representation of a VictoryPoints
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        return sb.toString();
    }
}
