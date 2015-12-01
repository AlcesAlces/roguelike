package roguelike.map.tiles;

import net.slashie.libjcsi.CSIColor;
import net.slashie.util.Position;

import java.io.Serializable;

public class Tile implements Serializable {

    public enum tiletype {

        floor(1), tree(2), wall(3);

        private final int value;

        private tiletype(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public Position position;
    public String description;
    public String symbol;
    public tiletype type;
    public CSIColor color;
    //Can you pass over this?
    public boolean isPass;
    public boolean isOccupied = false;
    public boolean generated = false;

    public Tile()
    {

    }

    public Tile(Position p, String s, tiletype t, Boolean pass, CSIColor c)
    {
        generated = true;
        position = p;
        symbol = s;
        type = t;
        isPass = pass;
        color = c;
    }
}
