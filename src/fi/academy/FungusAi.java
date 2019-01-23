package fi.academy;

public class FungusAi extends CreatureAi {
    private CreatureFactory cf;
    private int spreadcount;

    public FungusAi(Creature creature, CreatureFactory cf) {
        super(creature);
        this.cf = cf;
    }

    public void onUpdate(){
        if (spreadcount < 5 && Math.random() < 0.02)
            spread();
    }

    private void spread(){
        int x = creature.x + (int)(Math.random() * 15) - 5;
        int y = creature.y + (int)(Math.random() * 15) - 5;

        if (!creature.canEnter(x, y))
            return;

        Creature child = cf.newShroom();
        child.x = x;
        child.y = y;
        spreadcount++;
    }
}