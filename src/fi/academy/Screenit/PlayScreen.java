package fi.academy.Screenit;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import fi.academy.DunskuBuilder;
import fi.academy.Creature;
import fi.academy.CreatureFactory;
import fi.academy.World;

public class PlayScreen implements Screen {

    private World world;
    private Creature pc;
    private int screenWidth;
    private int screenHeight;
    private List<String> messages;

    public PlayScreen() {
        screenWidth = 80;
        screenHeight = 21;
        messages = new ArrayList<String>();
        createDungeon();

        CreatureFactory cf = new CreatureFactory(world);
        createCreatures(cf);
    }

        private void createCreatures(CreatureFactory cf){
        pc = cf.newPc(messages);

        //paljonko sieniä luodaan alussa?
        for (int i = 0; i < 8; i++){
            cf.newShroom();
        }
    }

    //dunskun luonti, koko
    private void createDungeon() {
        world = new DunskuBuilder(90, 31)
                .makeCaves()
                .build();
    }

    public int getScrollX() { return Math.max(0, Math.min(pc.x - screenWidth / 2, world.width() - screenWidth)); }

    public int getScrollY() { return Math.max(0, Math.min(pc.y - screenHeight / 2, world.height() - screenHeight)); }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        int left = getScrollX();
        int top = getScrollY();
        displayTiles(terminal, left, top);
        displayMessages(terminal, messages);
        String stats = String.format(" %3d/%3d hp", pc.hp(), pc.maxHp());
        terminal.write(stats, 1, 23);
        terminal.writeCenter("-- paina [escape] tai [enter] --", 22);
    }

    private void displayMessages(AsciiPanel terminal, List<String> messages) {
        int top = screenHeight - messages.size();
        for (int i = 0; i < messages.size(); i++){
            terminal.writeCenter(messages.get(i), top + i);
        }
        messages.clear();
    }

//näytä monsterit aktiivisella alueella - tehoton tapa, tee uusiksi!

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        for (int x = 0; x < screenWidth; x++){
            for (int y = 0; y < screenHeight; y++){
                int wx = x + left;
                int wy = y + top;

                Creature creature = world.creature(wx, wy);
                if (creature != null)
                    terminal.write(creature.glyph(), creature.x - left, creature.y - top, creature.color());
                else
                    terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
            }
        }
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                return new LoseScreen();
            case KeyEvent.VK_ENTER:
                return new WinScreen();
            case KeyEvent.VK_LEFT:
                pc.moveBy(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                pc.moveBy(1, 0);
                break;
            case KeyEvent.VK_UP:
                pc.moveBy(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                pc.moveBy(0, 1);
                break;
            case KeyEvent.VK_Y:
                pc.moveBy(-1, -1);
                break;
            case KeyEvent.VK_U:
                pc.moveBy(1, -1);
                break;
            case KeyEvent.VK_B:
                pc.moveBy(-1, 1);
                break;
            case KeyEvent.VK_N:
                pc.moveBy(1, 1);
                break;
        }
        return this;
    }

    }
