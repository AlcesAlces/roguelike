package roguelike.map.tiles.vegetation;

import net.slashie.libjcsi.CSIColor;
import net.slashie.util.Position;
import roguelike.map.tiles.Tile;

import java.io.Serializable;

public class Tree extends Tile implements Serializable{

    public Tree(Position p, String s, Tile.tiletype t, Boolean pass) {
        super(p, s, t, pass, "Green");
    }
}
