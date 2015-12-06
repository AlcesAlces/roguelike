package roguelike.map;

import roguelike.Global;
import roguelike.character.Player;
import roguelike.file.FileReader;
import roguelike.file.FileWriter;
import roguelike.map.generation.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

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

    public Map getCurrentMap(){
        return currentMap;
    }

    public void setCurrentMap(Map map){
        this.currentMap = map;
    }

    public void HandleTransition(Map from, Transition tran){

        Point temp = new Point(Global.player.overworldPoint.x, Global.player.overworldPoint.y);

        switch(tran.dir){
            case up:
                temp.translate(0,-1);
                break;
            case down:
                temp.translate(0,1);
                break;
            case right:
                temp.translate(1, 0);
                break;
            case left:
                temp.translate(-1,0);
                break;
            default:
                break;
        }

        //Write current map.
        FileWriter.saveMap(from);
        //Get the next map.
        SwapInMap(temp);

        //TODO: Change the user's position.
        //TODO: Set user's old tile to walkable.
        Point newPos = new Point(0,0);
        switch(tran.dir){
            case up:
                newPos = currentMap.getTransitionByDir(Map.Direction.down).pos;
                break;
            case down:
                newPos = currentMap.getTransitionByDir(Map.Direction.up).pos;
                break;
            case right:
                newPos = currentMap.getTransitionByDir(Map.Direction.left).pos;
                break;
            case left:
                newPos = currentMap.getTransitionByDir(Map.Direction.right).pos;
                break;
            default:
                break;
        }

        Global.player.point = newPos;
        Global.player.overworldPoint = currentMap.overworldPoint;
    }

    public void SwapInMap(Point pos){

        Map map = FileReader.getMap(pos);
        //Check to see if the map was generated.
        if(map == null){
            //Map needs to be generated.
            ArrayList<Feature> feat = allMaps[pos.x][pos.y].getFeaturesFromType();
            currentMap = roguelike.map.generation.GenerateMap.generateNewMap(this, pos);
        }
        else{
            currentMap = map;
        }
    }
}
