package roguelike;

import java.awt.Point;
import net.slashie.libjcsi.CSIColor;

public class Wall extends MapObject{

    CSIColor color = CSIColor.BROWN;
    
    public Wall(int type, Point position, boolean isPassable) {
        super(type, position, isPassable);
    }
    
    
}
