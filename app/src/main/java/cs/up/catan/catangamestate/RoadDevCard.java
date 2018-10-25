package cs.up.catan.catangamestate;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 25th, 2018
 * https://github.com/alexweininger/game-state
 **/
public class RoadDevCard extends DevelopmentCards {

    public RoadDevCard() {
        super("Road");
    }

    /**
     *
     * @param player player whose turn it is
     */
    @Override
    public void useCard(Player player) {
        super.useCard(player);
        for (int i = 0; i < 2; i++) {
            int x = 5; //TODO: Get the start and end intersection id from users tap. This is only a placeholder for now
            Road road = new Road(x, x, player.getPlayerId());
            // TODO player.addBuilding(road);
        }
    }

    /**
     *
     * @return string representation of a RoadDevCard
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
