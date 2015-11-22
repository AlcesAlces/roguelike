package roguelike;

import java.awt.Point;
import net.slashie.libjcsi.CSIColor;

public class Water extends MapObject{

    CSIColor color = CSIColor.BLUE;
    
    public Water(int type, Point position, boolean isPassable) {
        super(type, position, isPassable);
    }
}
