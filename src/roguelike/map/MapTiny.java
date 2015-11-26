package roguelike.map;

import java.awt.*;

/**
 * This class represents the smallest possible object needed by the overworld map
 */
public class MapTiny {

    //Symbol of the map on the overworld.
    public char symbol;
    public String name;
    public boolean isEmpty = true;
    public boolean isGenerated = false;
    public int Id;
    public Point point;

    //Empty
    public MapTiny()
    {

    }

    public void setElements(char s, String n, int i, Point p)
    {
        isEmpty = false;
        name = n;
        symbol = s;
        Id = i;
        point = p;
    }
}
