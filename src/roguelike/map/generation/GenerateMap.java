package roguelike.map.generation;

import roguelike.map.Map;

import java.util.ArrayList;

public class GenerateMap {

    //Move this somewhere?
    static int scale = 100;

    public static Map generateNewMap(ArrayList<Feature> features)
    {
        //Empty constructor, all default values.
        Map map = new Map();
        map.mapFeatures = features;

        createSize(scale, map);
        createEmptyTiles(map);
        FeatureGenerator.generateFeatures(map);

        return map;
    }

    //sizeScale is a way to determine how big these maps are going to be.
    private static void createSize(int sizeScale, Map map)
    {
        int defaultMin = 40;

        int x = (int)( Math.random() * sizeScale) + defaultMin;
        int y = (int) ( Math.random() * sizeScale) + defaultMin;

        map.x = x;
        map.y = y;
    }

    private static void createEmptyTiles(Map map)
    {
        map.initTiles(map.x, map.y);
        map.initTilesDefault();
    }

}
