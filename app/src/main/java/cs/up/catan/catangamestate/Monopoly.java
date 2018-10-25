package cs.up.catan.catangamestate;

public class Monopoly extends DevelopmentCards {

    /**
     *
     */
    public Monopoly() {
        super("Monopoly");
    }

    /** useCard method
     *
     * @param player - player who uses the Monopoly card
     */
    @Override
    public void useCard(Player player) {
        super.useCard(player);
        //TODO: How we gonna do this... DB
        /*
        Monopoly: If you play this card, you must name 1 type of
                  resource. All the other players must give you all of the
                  Resource Cards of this type that they have in their hands.

                  1. option to select resource
                  2. take res from each player who has res
         */
    } // end useCard method

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }
}