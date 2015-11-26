package roguelike.map;

import java.awt.Point;

public abstract class MapObject {
    
    int type;
    public Point position;
    boolean isPassable;
    char symbol;
    
    public MapObject(int type, Point position, boolean isPassable) {
        this.type = type;
        this.position = position;
        this.isPassable = isPassable;
                
    }
    
}