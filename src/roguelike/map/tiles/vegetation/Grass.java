package roguelike.map.tiles.vegetation;


import net.slashie.libjcsi.CSIColor;
import net.slashie.util.Position;
import roguelike.map.tiles.Tile;

import java.io.Serializable;

public class Grass extends Tile implements Serializable{

    public Grass(Position p, String s, Tile.tiletype t, Boolean pass)
    {
        super(p,s,t,pass, CSIColor.GREEN);
    }
}
