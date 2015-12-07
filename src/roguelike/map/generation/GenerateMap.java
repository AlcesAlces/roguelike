package roguelike.map.generation;

import net.slashie.libjcsi.CSIColor;
import net.slashie.util.Position;
import roguelike.Global;
import roguelike.creatures.Creature;
import roguelike.creatures.race_class.Race;
import roguelike.map.Map;
import roguelike.map.Overworld;
import roguelike.map.Transition;
import roguelike.map.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class GenerateMap {

    //Move this somewhere?
    static int scaleX = 100;
    static int scaleY = 50;

    public static Map generateNewMap(Overworld overworld, Point owPoint)
    {
        //Empty constructor, all default values.
        Map map = new Map();
        //This represents the type of map this is.
        map.mapFeatures = overworld.allMaps[owPoint.x][owPoint.y].getFeaturesFromType();
        map.overworldPoint = owPoint;

        createSize(scaleX, scaleY, map);
        createEmptyTiles(map);
        FeatureGenerator.generateFeatures(map);
        setMapEdges(map);
        generateTransitions(overworld, map);
        Populate(overworld, map);
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

    private static void generateTransitions(Overworld ow, Map map){

        Point owp = map.overworldPoint;

        //Check up
        Point temp = new Point(owp.x,owp.y-1);
        if(temp.y >= 0){
            //Valid to check
            if(!ow.allMaps[temp.x][temp.y].isEmpty){
                //Valid
                SetTransition(map, Map.Direction.up);
            }
        }
        //Check down
        temp = new Point(owp.x,owp.y+1);
        if(temp.y < map.y){
            if(!ow.allMaps[temp.x][temp.y].isEmpty){
                SetTransition(map,Map.Direction.down);
            }
        }
        //Check left
        temp = new Point(owp.x-1,owp.y);
        if(temp.x > 0){
            if(!ow.allMaps[temp.x][temp.y].isEmpty){
                SetTransition(map, Map.Direction.left);
            }
        }
        //Check right
        temp = new Point(owp.x+1,owp.y);
        if(temp.x < map.x){
            if(!ow.allMaps[temp.x][temp.y].isEmpty){
                SetTransition(map, Map.Direction.right);
            }
        }
    }

    private static void SetTransition(Map map, Map.Direction dir){

        Tile toset;
        ArrayList<Tile> tiles = new ArrayList<>();
        boolean found = false;
        Map.Direction dts = Map.Direction.down;

        switch(dir){
            case up:
                //Check top to bottom for valid tiles.
                for(int i = 0; i < map.y; i++){
                    for(int j = 0; j < map.x; j++){
                        if(map.tiles[j][i].validForTransition()){
                            tiles.add(map.tiles[j][i]);
                            found = true;
                        }
                    }
                    if(found){
                        break;
                    }
                }
                break;
            case down:
                //Check top to bottom for valid tiles.
                for(int i = map.y-1; i >= 0; i--){
                    for(int j = 0; j < map.x; j++){
                        if(map.tiles[j][i].validForTransition()){
                            tiles.add(map.tiles[j][i]);
                            found = true;
                            dts = Map.Direction.up;
                        }
                    }
                    if(found){
                        break;
                    }
                }
                break;
            case left:
                for(int i = 0; i < map.x; i++){
                    for(int j = 0; j < map.y; j++){
                        if(map.tiles[i][j].validForTransition()){
                            tiles.add(map.tiles[i][j]);
                            found = true;
                            dts = Map.Direction.right;
                        }
                    }
                    if(found){
                        break;
                    }
                }
                break;
            case right:
                for(int i = map.x-1; i >= 0; i--){
                    for(int j = 0; j < map.y; j++){
                        if(map.tiles[i][j].validForTransition()){
                            tiles.add(map.tiles[i][j]);
                            found = true;
                            dts = Map.Direction.left;
                        }
                    }
                    if(found){
                        break;
                    }
                }
                break;
            default:
                break;
        }
        int ran = (int) (Math.random() * tiles.size());
        toset = tiles.get(ran);
        toset.isTransition = true;
        toset.symbol = "!";
        map.transitions.put(new Point(toset.position.x, toset.position.y),
                            new Transition(new Point(toset.position.x, toset.position.y), dts));
    }

    public static void Populate(Overworld ow, Map map){

        int amtToCreate = 10;
        int playerLevel = Global.player.level.level;

        for(int i = 0; i < amtToCreate; i++){
            //TODO: Determine race/level/class with the god system.
            Race race = new Race(Race.RACE.human);
            Creature temp = new Creature(playerLevel, race);

            Point point = ow.findRandomCreatureStart();
            temp.point = point;

            map.monsters.add(temp);
        }
    }

}
