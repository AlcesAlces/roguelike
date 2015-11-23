package roguelike.map.mapObjects;

import roguelike.map.MapObject;

import java.awt.Point;

public class Grass extends MapObject {

    public Grass(int type, Point position, boolean isPassable) {
        super(type, position, isPassable);
    }
    
}
