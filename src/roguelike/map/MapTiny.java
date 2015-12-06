package roguelike.map;

import roguelike.map.generation.Feature;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

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

    public ArrayList<Feature> getFeaturesFromType(){
        //TODO: Actually fill this.
        ArrayList<Feature> toReturn = new ArrayList<>();
        toReturn.add(new Feature(Feature.feature.grass_floor, 100));
        return toReturn;
    }
}
