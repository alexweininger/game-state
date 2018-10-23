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
    public void build()
    {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Settlement toString()\n");
        sb.append(super.toString());

        return sb.toString();
    } // end toString
} // end Class
