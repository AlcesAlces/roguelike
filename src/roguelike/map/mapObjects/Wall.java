package roguelike.map.mapObjects;

import java.awt.Point;
import net.slashie.libjcsi.CSIColor;
import roguelike.map.MapObject;

public class Wall extends MapObject {

    public CSIColor color = CSIColor.BROWN;
    
    public Wall(int type, Point position, boolean isPassable) {
        super(type, position, isPassable);
    }
    
    
}
