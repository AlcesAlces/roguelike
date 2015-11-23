package roguelike.map.mapObjects;

import roguelike.map.MapObject;

import java.awt.Point;

public class Tree extends MapObject {

    public Tree(int type, Point position, boolean isPassable) {
        super(type, position, isPassable);
    }
}
