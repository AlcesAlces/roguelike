package roguelike.map.generation;

import net.slashie.libjcsi.CSIColor;
import net.slashie.util.Position;
import roguelike.Global;
import roguelike.map.Map;
import roguelike.map.Overworld;
import roguelike.map.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class GenerateMap {

    //Move this somewhere?
    static int scaleX = 100;
    static int scaleY = 50;

    public static Map generateNewMap(ArrayList<Feature> features, Overworld overworld)
    {
        //Empty constructor, all default values.
        Map map = new Map();
        map.mapFeatures = features;

        createSize(scaleX, scaleY, map);
        createEmptyTiles(map);
        FeatureGenerator.generateFeatures(map);
        setMapEdges(map);
        return map;
    }

    //sizeScale is a way to determine how big these maps are going to be.
    private static void createSize(int sizeScaleX, int intsizeScaleY, Map map)
    {
        int defaultMin = 40;

        int x = (int)( Math.random() * sizeScaleX) + defaultMin;
        int y = (int) ( Math.random() * intsizeScaleY) + defaultMin;

        map.x = x;
        map.y = y;
    }

    private static void createEmptyTiles(Map map)
    {
        map.initTiles(map.x, map.y);
        map.initTilesDefault();
    }

    private static void setMapEdges(Map map)
    {
        for(int i = 0; i < map.x; i++)
        {
            for(int j = 0; j < map.y; j++)
            {

                Point testPoint = new Point(i,j);
                //up
                testPoint.translate(0,-1);
                if(testPoint.y < 0){
                    map.tiles[i][j].cellWalls.add(Map.Direction.up);
                }
                else if(!map.tiles[testPoint.x][testPoint.y].generated){
                    map.tiles[i][j].cellWalls.add(Map.Direction.up);
                }

                //down
                testPoint = new Point(i,j);
                testPoint.translate(0,1);
                if(testPoint.y >= map.y){
                    map.tiles[i][j].cellWalls.add(Map.Direction.down);
                }
                else if(!map.tiles[testPoint.x][testPoint.y].generated){
                    map.tiles[i][j].cellWalls.add(Map.Direction.down);
                }

                //Right
                testPoint = new Point(i,j);
                testPoint.translate(1,0);
                if(testPoint.x >= map.x){
                    map.tiles[i][j].cellWalls.add(Map.Direction.right);
                }
                else if(!map.tiles[i][j].generated){
                    map.tiles[i][j].cellWalls.add(Map.Direction.right);
                }

                //Left
                testPoint = new Point(i,j);
                testPoint.translate(-1,0);
                if(testPoint.x < 0)
                {
                    map.tiles[i][j].cellWalls.add(Map.Direction.left);
                }
                else if(!map.tiles[i][j].generated){
                    map.tiles[i][j].cellWalls.add(Map.Direction.left);
                }
            }
        }
    }

    private static void generateTransitions(Overworld ow){

    }

}
