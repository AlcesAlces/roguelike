package roguelike;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.util.Position;
import roguelike.map.MapCompact;
import roguelike.map.MapTiny;
import roguelike.pathfinding.OverworldPathfinder;

import java.awt.Point;
import java.util.ArrayList;

public class Roguelike {
    
    public static void main(String[] args) {
        
        //MovementCalculator test = new MovementCalculator();

        //test.initializeMovement();

        int sizeX = 50;
        int sizeY = 50;
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
                mt[point.x][point.y].setElements('#', "WHAT!?", id, point);
                id++;
            }
        }

        //TODO: Fill the mt object according to how we want to populate the world.

        //Screen writing testing:

        ConsoleSystemInterface csi = null;

        try {

            csi = new WSwingConsoleInterface("Planes of Endatl", true);
        }
        catch (ExceptionInInitializerError eiie) {
            System.out.println("*** Error: Swing Console Box cannot be initialized. Exiting...");
            eiie.printStackTrace();
            System.exit(-1);
        }

        Point displayPoint = new Point(0,0);
        //How big is the screen? Use these.
        int xLen = 80;
        int yLen = 25;

        boolean mapping = true;

        while(mapping)
        {
            csi.cls();
            for(int i = displayPoint.x; i < (displayPoint.x + xLen); ++i)
            {
                for(int j = displayPoint.y; j < (displayPoint.y + yLen); ++j)
                {
                    if(i < sizeX && j < sizeY && i >= 0 && j >= 0) {
                        if (!mt[i][j].isEmpty) {

                            try {
                                csi.print(i - displayPoint.x, j - displayPoint.y, mt[i][j].symbol + "");
                            } catch (Exception ex) {
                                System.out.println("DONE GOOFED!");
                            }
                        }
                    }
                }
            }
            csi.refresh();
            CharKey dir = csi.inkey();

            if(dir.isRightArrow())
            {
                displayPoint.translate(1, 0);
            }
            else if(dir.isLeftArrow())
            {
                displayPoint.translate(-1, 0);
            }
            else if(dir.isDownArrow())
            {
                displayPoint.translate(0, 1);
            }
            else if(dir.isUpArrow())
            {
                displayPoint.translate(0, -1);
            }
        }
	}
 }
