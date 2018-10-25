package cs.up.catan.catangamestate;

public class Monopoly extends DevelopmentCards {

    public Monopoly(String name) {
        super(name);
    }

    @Override
    public void useCard(Player player)
    {
        super.useCard(player);
        //TODO: How we gonna do this....
        /*
        Monopoly: If you play this card, you must name 1 type of
                  resource. All the other players must give you all of the
                  Resource Cards of this type that they have in their hands.
         */
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("");

        return sb.toString();
    }
}
