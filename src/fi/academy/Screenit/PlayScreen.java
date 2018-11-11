package fi.academy.Screenit;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import fi.academy.DunskuBuilder;
import fi.academy.Otus;
import fi.academy.OtusTehdas;
import fi.academy.World;

public class PlayScreen implements Screen {

    private World world;
    private Otus sankari;
    private int screenWidth;
    private int screenHeight;
    private List<String> messages;

    public PlayScreen() {
        screenWidth = 80;
        screenHeight = 21;
        messages = new ArrayList<String>();
        createDungeon();

        OtusTehdas otustehdas = new OtusTehdas(world);
        createCreatures(otustehdas);
    }

        private void createCreatures(OtusTehdas otustehdas){
        sankari = otustehdas.uusiSankari(messages);

        //paljonko sieniä luodaan alussa?
        for (int i = 0; i < 8; i++){
            otustehdas.uusiSieni();
        }
    }

    //dunskun luonti, koko
    private void createDungeon() {
        world = new DunskuBuilder(90, 31)
                .makeCaves()
                .build();
    }

    public int getScrollX() { return Math.max(0, Math.min(sankari.x - screenWidth / 2, world.width() - screenWidth)); }

    public int getScrollY() { return Math.max(0, Math.min(sankari.y - screenHeight / 2, world.height() - screenHeight)); }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        int left = getScrollX();
        int top = getScrollY();
        displayTiles(terminal, left, top);
        displayMessages(terminal, messages);
        String stats = String.format(" %3d/%3d hp", sankari.hp(), sankari.maxHp());
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

                Otus otus = world.otus(wx, wy);
                if (otus != null)
                    terminal.write(otus.glyph(), otus.x - left, otus.y - top, otus.color());
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
                sankari.moveBy(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                sankari.moveBy(1, 0);
                break;
            case KeyEvent.VK_UP:
                sankari.moveBy(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                sankari.moveBy(0, 1);
                break;
            case KeyEvent.VK_Y:
                sankari.moveBy(-1, -1);
                break;
            case KeyEvent.VK_U:
                sankari.moveBy(1, -1);
                break;
            case KeyEvent.VK_B:
                sankari.moveBy(-1, 1);
                break;
            case KeyEvent.VK_N:
                sankari.moveBy(1, 1);
                break;
        }
        return this;
    }

    }
