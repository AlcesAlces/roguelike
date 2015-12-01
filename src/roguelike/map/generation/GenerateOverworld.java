package roguelike.map.generation;

import roguelike.map.MapTiny;
import roguelike.map.MapType;
import roguelike.pathfinding.OverworldPathfinder;

import java.awt.*;
import java.util.ArrayList;

public class GenerateOverworld {

    public static MapTiny[][] createOverworld(int sizeX, int sizeY)
    {
        int iterations = (sizeX + sizeY) + ((sizeX + sizeY) / 4);

        MapTiny[][] mt = new MapTiny[sizeX][sizeY];

        for(int i = 0; i < sizeX; i++)
        {
            for(int j = 0; j < sizeY; j++)
            {
                mt[i][j] = new MapTiny();
            }
        }

        ArrayList<ArrayList<Point>> list = new ArrayList<>();
        OverworldPathfinder test = new OverworldPathfinder(sizeX, sizeY, mt);
        //Use the overworld pathfinder to create random-ish patterns in the world.
        for(int i = 0; i < iterations; i++)
        {
            //Pick random points to draw our lines
            int fx  = (int) (Math.random() * (sizeX - 1));
            int fy = (int) (Math.random() * (sizeY - 1));
            Point tempStart = new Point(fx, fy);
            int sx  = (int) (Math.random() * (sizeX - 1));
            int sy  = (int) (Math.random() * (sizeY - 1));
            Point tempEnd = new Point(sx, sy);

            //TODO: Does this 5000 hardcoded need to change? Is 5000  too much too little?
            ArrayList<Point> path = test.findPath(tempStart, tempEnd, 5000);
            if(path != null) {
                list.add(path);
            }
        }

        int id = 0;
        //Put all of those patterns we made into the MapTiny Map.
        for(ArrayList<Point> lists : list)
        {
            for(Point point : lists)
            {
                //Each line will be a different
                mt[point.x][point.y].setElements("WHAT!?", id, point, MapType.randomMapType());
                id++;
            }
        }

        return mt;
    }

}
