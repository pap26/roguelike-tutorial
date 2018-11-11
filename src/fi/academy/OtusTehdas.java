package fi.academy;

import asciiPanel.AsciiPanel;

import java.util.List;

public class OtusTehdas {
    private World world;

    public Otus uusiSankari(List<String> messages){
        Otus sankari = new Otus(world, '@', AsciiPanel.brightWhite, 100, 20, 5);
        world.addAtEmptyLocation(sankari);
        new SankariAi(sankari, messages);
        return sankari;
    }

    public Otus uusiSieni(){
        Otus fungus = new Otus(world, 'f', AsciiPanel.green, 10, 0, 0);
        world.addAtEmptyLocation(fungus);
        new FungusAi(fungus, this);
        return fungus;
    }

    public OtusTehdas(World world){
        this.world = world;
    }
}

