package fi.academy;

import java.awt.Color;

public class Otus {

    private World world;

    public int x;
    public int y;

    //otusten statsit:

    private int maxHp;
    public int maxHp() { return maxHp; }

    private int hp;
    public int hp() { return hp; }

    private int attackValue;
    public int attackValue() { return attackValue; }

    private int defenseValue;
    public int defenseValue() { return defenseValue; }

    //otusten kirjaimet ja väri

    private char glyph;
    public char glyph() { return glyph; }

    private Color color;
    public Color color() { return color; }

    private OtusAi ai;
    public void setOtusAi(OtusAi ai) { this.ai = ai; }

    public Otus(World world, char glyph, Color color, int maxHp, int attack, int defense){
        this.world = world;
        this.glyph = glyph;
        this.color = color;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attackValue = attack;
        this.defenseValue = defense;
    }

    //lisätään hyökkäys, poistetaan tapettu monsteri

    public void moveBy(int mx, int my){
        Otus other = world.otus(x+mx, y+my);

        if (other == null)
            ai.onEnter(x+mx, y+my, world.tile(x+mx, y+my));
        else
            attack(other);
    }

    public void dig(int wx, int wy) {
        world.dig(wx, wy);
    }

    public void update(){
        ai.onUpdate();
    }

    public boolean canEnter(int wx, int wy) {
        return world.tile(wx, wy).isGround() && world.otus(wx, wy) == null;
    }

    public void attack(Otus other){
        world.remove(other);
    }


    public void modifyHp(int amount) {
        hp += amount;

        if (hp < 1)
            world.remove(this);
    }

    public void notify(String message, Object ... params){
        ai.onNotify(String.format(message, params));
    }

}