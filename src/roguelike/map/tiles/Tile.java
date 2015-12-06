package roguelike.map.tiles;

import com.googlecode.blacken.terminal.CellWalls;
import com.googlecode.blacken.terminal.TerminalCellLike;
import roguelike.map.Map;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

public class Tile implements Serializable {

    public enum tiletype {

        floor(1), tree(2), wall(3);

        private final int value;

        private tiletype(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public Point position;
    public String description;
    public String symbol;
    public tiletype type;
    public String color;
    //Can you pass over this?
    public boolean isPass;
    public boolean isOccupied = false;
    public boolean generated = false;
    public boolean isTransition = false;
    public ArrayList<Map.Direction> cellWalls = new ArrayList<>();

    public Tile()
    {

    }

    public Tile(Point p, String s, tiletype t, Boolean pass, String c)
    {
        generated = true;
        position = p;
        symbol = s;
        type = t;
        isPass = pass;
        color = c;
    }

    public void setBoarder(TerminalCellLike cell){

        List<CellWalls> walls = new ArrayList<>();
        for(Map.Direction dir : cellWalls){
            switch(dir)
            {
                case up:
                    walls.add(CellWalls.TOP);
                    break;
                case down:
                    walls.add(CellWalls.BOTTOM);
                    break;
                case left:
                    walls.add(CellWalls.LEFT);
                    break;
                case right:
                    walls.add(CellWalls.RIGHT);
                    break;
                default:
                    break;
            }
            cell.setCellWalls(new HashSet<>(walls));
        }
    }

    public boolean validForTransition(){
        return (isPass && !isOccupied && generated);
    }
}
