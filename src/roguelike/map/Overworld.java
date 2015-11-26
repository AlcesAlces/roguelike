package roguelike.map;

import java.awt.*;

/*
    Overworld is going to be what keeps track of each map.
    More specifically this controls all overworld actions.
 */
public class Overworld {

    int sizeX;
    int sizeY;
    MapTiny[][] allMaps;
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
}
