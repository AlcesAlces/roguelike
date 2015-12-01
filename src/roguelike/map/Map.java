package roguelike.map;

import net.slashie.util.Position;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import roguelike.creatures.Creature;
import roguelike.map.generation.Feature;
import roguelike.map.tiles.Tile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable{

    public enum Direction { up, down, left, right}

    public Tile[][] tiles;
    public int x;
    public int y;
    protected ArrayList<Creature> monsters = new ArrayList<>();
    public ArrayList<Transition> transitions = new ArrayList<>();
    public ArrayList<Feature> mapFeatures = new ArrayList<>();
    public Position pos;
    private String path = "";

    public boolean checkMove(Direction dir, Position pos)
    {
        Position temp = new Position(0,0);

        switch(dir)
        {
            case up:
                temp = new Position(pos.x, pos.y + 1);
                break;
            case down:
                temp = new Position(pos.x, pos.y - 1);
                break;
            case left:
                temp = new Position(pos.x - 1, pos.y);
                break;
            case right:
                temp = new Position(pos.x + 1, pos.y);
                break;
        }

        return (tiles[temp.x][temp.y].isPass && !tiles[temp.x][temp.y].isOccupied);
    }

    public void moveCharacter(Creature toMove, Direction dir)
    {
        Position temp = new Position(0,0);
        Position pos = new Position(toMove.creaturePoint.x, toMove.creaturePoint.y);
        //Check the direction the creature wants to move.
        switch(dir)
        {
            case up:
                temp = new Position(pos.x, pos.y + 1);
                break;
            case down:
                temp = new Position(pos.x, pos.y - 1);
                break;
            case left:
                temp = new Position(pos.x - 1, pos.y);
                break;
            case right:
                temp = new Position(pos.x + 1, pos.y);
                break;
        }
        //Set the tiles to occupied/not occupied and then translate the character's position.
        toMove.creaturePoint = new Point(temp.x, temp.y);
        tiles[temp.x][temp.y].isOccupied = true;
        tiles[pos.x][pos.y].isOccupied = false;
    }

    public boolean checkIfGenerated()
    {
        return true;
    }

    //Load a single map into this object from memory.
    public void loadFromFile(Position pos)
    {

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
}
