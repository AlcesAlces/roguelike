package roguelike.map.tiles.vegetation;

import roguelike.map.tiles.Tile;

import java.awt.*;
import java.io.Serializable;

public class Grass extends Tile implements Serializable{

    public Grass(Point p, String s, Tile.tiletype t, Boolean pass)
    {
        super(p,s,t,pass, "Green");
    }
}
