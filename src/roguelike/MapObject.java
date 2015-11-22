package roguelike;

import java.awt.Point;

public abstract class MapObject {
    
    int type;
    Point position;
    boolean isPassable;
    
    MapObject(int type, Point position, boolean isPassable) {
        this.type = type;
        this.position = position;
        this.isPassable = isPassable;
                
    }
    
}