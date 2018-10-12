package cs.up.catan.catangamestate;

/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 10th, 2018
 * https://github.com/alexweininger/game-state
 **/
public class Settlement extends Building {

    public Settlement() {
        super();
    } // end constructor

    @Override
    public String toString() {
        String str = "";
        str += "Settlement toString()\n";
        str += super.toString();
        return str;
    } // end toString
} // end Class
