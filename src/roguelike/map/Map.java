package roguelike.map;

import com.sun.istack.internal.Nullable;
import roguelike.creatures.Creature;
import roguelike.map.generation.Feature;
import roguelike.map.tiles.Tile;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Map implements Serializable{

    public enum Direction { up, down, left, right}

    public Tile[][] tiles;
    public int x;
    public int y;
    public ArrayList<Creature> monsters = new ArrayList<>();
    public HashMap<Point, Transition> transitions = new HashMap<>();
    public ArrayList<Feature> mapFeatures = new ArrayList<>();
    private String path = "";
    public Point overworldPoint;

    public boolean checkMove(Direction dir, Point pos)
    {
        Point temp = new Point(0,0);

        switch(dir)
        {
            case up:
                temp = new Point(pos.x, pos.y - 1);
                break;
            case down:
                temp = new Point(pos.x, pos.y + 1);
                break;
            case left:
                temp = new Point(pos.x - 1, pos.y);
                break;
            case right:
                temp = new Point(pos.x + 1, pos.y);
                break;
        }
        if(temp.x < 0 || temp.y < 0 || temp.x >= x || temp.y >= y){
            return false;
        }
        else {
            return (tiles[temp.x][temp.y].isPass && !tiles[temp.x][temp.y].isOccupied);
        }
    }

    public void moveCharacter(Creature toMove, Direction dir, Overworld ow)
    {
        Point temp = new Point(0,0);
        Point pos = new Point(toMove.point.x, toMove.point.y);
        //Check the direction the creature wants to move.
        switch(dir)
        {
            case up:
                temp = new Point(pos.x, pos.y - 1);
                break;
            case down:
                temp = new Point(pos.x, pos.y + 1);
                break;
            case left:
                temp = new Point(pos.x - 1, pos.y);
                break;
            case right:
                temp = new Point(pos.x + 1, pos.y);
                break;
        }
        //Set the tiles to occupied/not occupied and then translate the character's position.
        if(tiles[temp.x][temp.y].isTransition)
        {
            //Handle transition
            tiles[pos.x][pos.y].isOccupied = false;
            ow.HandleTransition(this, transitions.get(new Point(temp.x, temp.y)));
        }
        else {
            toMove.point = new Point(temp.x, temp.y);
            tiles[temp.x][temp.y].isOccupied = true;
            tiles[pos.x][pos.y].isOccupied = false;
        }
    }

    public void moveCharacter(Creature toMove, Point newPoint){
        Point pos = new Point(toMove.point.x, toMove.point.y);
        tiles[pos.x][pos.y].isOccupied = false;
        tiles[newPoint.x][newPoint.y].isOccupied = true;
        toMove.point = newPoint;
    }

    public boolean checkIfGenerated()
    {
        return true;
    }


    public void initTiles(int sx, int sy)
    {
        tiles = new Tile[sx][sy];
    }

    public void initTilesDefault()
    {
        if(tiles != null) {
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    //Make all the tiles default empty tile.
                    tiles[i][j] = new Tile();
                }
            }
        }
        else{
            initTiles(x,y);
        }
    }

    @Nullable
    public Transition getTransitionByDir(Direction dir){

        Transition toReturn = null;

        for(Transition t : transitions.values()){
            if(t.dir == dir){
                toReturn = t;
            }
        }

        return toReturn;
    }
}
