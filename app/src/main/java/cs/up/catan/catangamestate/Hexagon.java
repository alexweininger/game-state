package cs.up.catan.catangamestate;

public class Hexagon {
    private int id, x, y, z;
    private String resource;

    /**
     *
     * @param resource
     */
    public Hexagon(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "Hexagon{" +
                "resource='" + resource + '\'' +
                '}';
    }
}
