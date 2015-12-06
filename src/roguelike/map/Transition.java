package roguelike.map;

import java.awt.*;
import java.io.Serializable;

public class Transition implements Serializable{
    public Point pos;
    public Map.Direction dir;

    public Transition(Point pt, Map.Direction dir){
        this.pos = pt;
        this.dir = dir;
    }
}
