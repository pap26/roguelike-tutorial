package fi.academy;

import asciiPanel.AsciiPanel;

import java.util.List;

public class CreatureFactory {
    private World world;

    public Creature newPc(List<String> messages){
        Creature pc = new Creature(world, '@', AsciiPanel.brightWhite, 100, 20, 5);
        world.addAtEmptyLocation(pc);
        new PcAi(pc, messages);
        return pc;
    }

    public Creature newShroom(){
        Creature fungus = new Creature(world, 'f', AsciiPanel.green, 10, 0, 0);
        world.addAtEmptyLocation(fungus);
        new FungusAi(fungus, this);
        return fungus;
    }

    public CreatureFactory(World world){
        this.world = world;
    }
}

