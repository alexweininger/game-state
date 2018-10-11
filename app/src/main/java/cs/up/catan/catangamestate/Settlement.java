package cs.up.catan.catangamestate;

public class Settlement extends Building {

    public Settlement() {
        super();
    }

    @Override
    public String toString() {
        String str = "";
        str += "Settlement toString()\n";
        str += super.toString();
        return str;
    }
}
