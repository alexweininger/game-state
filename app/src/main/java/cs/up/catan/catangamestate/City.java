package cs.up.catan.catangamestate;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 10th, 2018
 * https://github.com/alexweininger/game-state
 **/

public class City extends Building {

    public City() {
        super();
    }

    @Override
    public void build()
    {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("City toString()\n");
        sb.append(super.toString());

        return sb.toString();
    }
}
