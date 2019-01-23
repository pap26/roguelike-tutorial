package fi.academy;

import java.util.List;

public class PcAi extends CreatureAi {

    private List<String> messages;

    public PcAi(Creature creature, List<String> messages) {
        super(creature);
        this.messages = messages;
    }

    public void onEnter(int x, int y, Tile tile) {
        if (tile.isGround()) {
            creature.x = x;
            creature.y = y;
        } else if (tile.canDig()) {
            creature.dig(x, y);
        }
    }

    public void onNotify(String message) {
        messages.add(message);
    }
}