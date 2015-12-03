package roguelike.map.generation;

import net.slashie.libjcsi.CSIColor;
import net.slashie.util.Position;
import roguelike.map.Map;
import roguelike.map.tiles.Tile;

/*
 * A note about feature creation: Feature creation is done consecutively.
 * Floor options, for example, would be called first. This all has to do with the order
 * that they're in the array list.
 */
public class FeatureGenerator {

    public static void generateFeatures(Map toGen)
    {
        for(Feature feat : toGen.mapFeatures)
        {
            generateFeature(feat, toGen);
        }
    }

    private static void generateFeature(Feature feat, Map toGen)
    {
        switch(feat.feat)
        {
            case grass_floor:
                generateGrassFloor(feat, toGen);
                break;
            case forest:
                generateForest(feat, toGen);
                break;
            case lake:
                generateLake(feat, toGen);
                break;
            default:
                break;
        }
    }

    private static void generateGrassFloor(Feature feat, Map toGen)
    {
        for(int i = 0; i < toGen.x; i++){
            for(int j = 0; j < toGen.y; j++){
                //TODO: Relocate the symbol for grass somewhere?
                toGen.tiles[i][j] = new Tile(new Position(i,j), ",", Tile.tiletype.floor, true, "Green");
            }
        }
    }

    private static void generateForest(Feature feat, Map toGen)
    {

    }

    private static void generateLake(Feature feat, Map toGen)
    {

    }

}
