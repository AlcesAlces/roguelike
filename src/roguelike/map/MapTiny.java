package roguelike.map;

import java.awt.*;
import java.io.Serializable;

/**
 * This class represents the smallest possible object needed by the overworld map
 */
public class MapTiny implements Serializable{

    //Symbol of the map on the overworld.
    public String symbol;
    public String name;
    public boolean isEmpty = true;
    public boolean isGenerated = false;
    public int Id;
    public Point point;
    public MapType.Type type;

    //Empty
    public MapTiny()
    {

    }

    public void setElements(String n, int i, Point p, MapType.Type t)
    {
        isEmpty = false;
        name = n;
        symbol = MapType.resolveDisplayCharacter(t);
        Id = i;
        point = p;
        type = t;
    }
}
