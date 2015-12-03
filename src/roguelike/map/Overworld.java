package roguelike.map;

import roguelike.map.generation.GenerateOverworld;

import java.awt.*;
import java.io.Serializable;

/*
    Overworld is going to be what keeps track of each map.
    More specifically this controls all overworld actions.
 */
public class Overworld implements Serializable {

    public int sizeX;
    public int sizeY;
    public MapTiny[][] allMaps;
    Map currentMap;

    public Overworld(int x, int y)
    {
        sizeX = x;
        sizeY = y;
        allMaps = new MapTiny[x][y];

        for(int i = 0; i < x; i++)
        {
            for(int j = 0; j < y; j++)
            {
                //Make all empty maps
                allMaps[i][j] = new MapTiny();
            }
        }
    }

    /*
        Function which generates the maps generic information.
     */
    public void FillOverworld()
    {
        allMaps = GenerateOverworld.createOverworld(sizeX, sizeY);
    }

    public boolean SwapMaps(Point newPoint)
    {
        if(!allMaps[newPoint.x][newPoint.y].isEmpty)
        {
            //TOOD: Pull in the map to swap
            return true;
        }
        else
        {
            return false;
        }
    }

    public Map getCurrentMap(){
        return currentMap;
    }
}
