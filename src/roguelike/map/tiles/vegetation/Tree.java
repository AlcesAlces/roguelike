package roguelike.map.tiles.vegetation;

import roguelike.map.tiles.Tile;

import java.awt.*;
import java.io.Serializable;

public class Tree extends Tile implements Serializable{

    public Tree(Point p, String s, Tile.tiletype t, Boolean pass) {
        super(p, s, t, pass, "Green");
    }
}
