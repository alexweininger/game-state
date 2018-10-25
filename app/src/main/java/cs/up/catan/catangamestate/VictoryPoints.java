package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 24th, 2018
 * https://github.com/alexweininger/game-state
 **/

import android.drm.DrmStore;

public class VictoryPoints extends DevelopmentCards {

    public VictoryPoints() {
        super("Victory Points");
    }

    @Override
    public void useCard(Player player) {
        super.useCard(player);
        player.setLocalScore(player.getLocalScore() + 1);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        return sb.toString();
    }
}
