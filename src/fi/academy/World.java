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

    private List<Creature> monsters;


    public World(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.monsters = new ArrayList<Creature>();
    }

    //aseta monsteri tiettyyn paikkaan

    public Creature creature(int x, int y) {
        for (Creature o : monsters) {
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
        if (tile(x, y).canDig())
            tiles[x][y] = Tile.FLOOR;
    }

    //otusten tiputtelu kartalle, tsekkaus onko tile varattu, lisaa uusi monsteri listaam

    public void addAtEmptyLocation(Creature creature) {
        int x;
        int y;

        do {
            x = (int) (Math.random() * width);
            y = (int) (Math.random() * height);
        }
        while (!tile(x, y).isGround() || creature(x, y) != null);

        creature.x = x;
        creature.y = y;
        monsters.add(creature);
    }

    //attack-metodin lopputulos:

    public void remove(Creature other) {
        monsters.remove(other);
    }

    public void update() {
        List<Creature> toUpdate = new ArrayList<Creature>(monsters);
        for (Creature creature : toUpdate) {
            creature.update();
        }

    }
}