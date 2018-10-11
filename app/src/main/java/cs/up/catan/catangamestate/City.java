package cs.up.catan.catangamestate;

import java.util.ArrayList;

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
