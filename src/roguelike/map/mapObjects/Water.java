package roguelike.map.mapObjects;

import java.awt.Point;
import net.slashie.libjcsi.CSIColor;
import roguelike.map.MapObject;

public class Water extends MapObject {

    public CSIColor color = CSIColor.BLUE;
    
    public Water(int type, Point position, boolean isPassable) {
        super(type, position, isPassable);
    }
}
