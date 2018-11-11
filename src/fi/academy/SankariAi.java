package fi.academy;

import java.util.List;

public class SankariAi extends OtusAi {

    private List<String> messages;

    public SankariAi(Otus otus, List<String> messages) {
        super(otus);
        this.messages = messages;
    }

    public void onEnter(int x, int y, Tile tile) {
        if (tile.isGround()) {
            otus.x = x;
            otus.y = y;
        } else if (tile.voiKaivaa()) {
            otus.dig(x, y);
        }
    }

    public void onNotify(String message) {
        messages.add(message);
    }
}