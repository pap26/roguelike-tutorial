package fi.academy.Screenit;
import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

public class WinScreen implements Screen {

    public void displayOutput(AsciiPanel terminal) {
        terminal.write("Voitit. Jippii!", 1,1);
        terminal.writeCenter("-- aloita uudelleen painamalla [enter] --", 22);
    }

    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen(): this;
    }

}
