package fi.academy;

public class FungusAi extends OtusAi {
    private OtusTehdas tehdas;
    private int spreadcount;

    public FungusAi(Otus otus, OtusTehdas tehdas) {
        super(otus);
        this.tehdas = tehdas;
    }

    public void onUpdate(){
        if (spreadcount < 5 && Math.random() < 0.02)
            spread();
    }

    private void spread(){
        int x = otus.x + (int)(Math.random() * 15) - 5;
        int y = otus.y + (int)(Math.random() * 15) - 5;

        if (!otus.canEnter(x, y))
            return;

        Otus child = tehdas.uusiSieni();
        child.x = x;
        child.y = y;
        spreadcount++;
    }
}