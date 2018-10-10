package cs.up.catan.catangamestate;

public class Settlement extends Building {

    private boolean validBuild;

    public Settlement(){

    }

    @Override
    public String toString() {
        return "Is it vaild to build: " + validBuild;
    }
}
