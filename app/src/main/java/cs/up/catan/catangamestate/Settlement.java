package cs.up.catan.catangamestate;

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
