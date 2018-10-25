package cs.up.catan.catangamestate;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 25th, 2018
 * https://github.com/alexweininger/game-state
 **/

public class Knights extends DevelopmentCards {

    public Knights(String name) {
        super(name);
    }

    public void useCard(Robber robber, Player player)
    {
        super.useCard(player);
        //TODO:need to get input of title user pressed on screen to move robber to
        int hexNumber = 5; //5 is only a placeholder for now
        robber.setCurrentPosition(hexNumber);
        player.setArmySize(player.getArmySize() + 1);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("");
        return sb.toString();
    }
}
