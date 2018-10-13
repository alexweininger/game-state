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
    public String toString() {
        String str = "";
        str.concat("City toString()\n");
        str.concat(super.toString());
        return str;
    }
}
