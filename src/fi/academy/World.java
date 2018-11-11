package fi.academy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class World {
    private Tile[][] tiles;
    private int width;

    public int width() {
        return width;
    }

    private int height;

    public int height() {
        return height;
    }

    private List<Otus> monsterit;


    public World(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.monsterit = new ArrayList<Otus>();
    }

    //aseta monsteri tiettyyn paikkaan

    public Otus otus(int x, int y) {
        for (Otus o : monsterit) {
            if (o.x == x && o.y == y)
                return o;
        }
        return null;
    }

    //onko tile sallittu?

    public Tile tile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return Tile.BOUNDS;
        else
            return tiles[x][y];
    }

    public char glyph(int x, int y) {
        return tile(x, y).glyph();
    }

    public Color color(int x, int y) {
        return tile(x, y).color();
    }

    public void dig(int x, int y) {
        if (tile(x, y).voiKaivaa())
            tiles[x][y] = Tile.FLOOR;
    }

    //otusten tiputtelu kartalle, tsekkaus onko tile varattu, lisaa uusi monsteri listaam

    public void addAtEmptyLocation(Otus otus) {
        int x;
        int y;

        do {
            x = (int) (Math.random() * width);
            y = (int) (Math.random() * height);
        }
        while (!tile(x, y).isGround() || otus(x, y) != null);

        otus.x = x;
        otus.y = y;
        monsterit.add(otus);
    }

    //attack-metodin lopputulos:

    public void remove(Otus other) {
        monsterit.remove(other);
    }

    public void update() {
        List<Otus> toUpdate = new ArrayList<Otus>(monsterit);
        for (Otus otus : toUpdate) {
            otus.update();
        }

    }
}