package cs.up.catan.catangamestate;

public class Settlement extends Building {

    public Settlement(){
        super();
    }

    @Override
    public String toString() {
        String str = "";
        str.concat("Settlement toString()\n");
        str.concat(super.toString());
        return str;
    }
}
