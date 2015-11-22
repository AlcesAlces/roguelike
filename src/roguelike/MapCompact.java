package roguelike;

import java.awt.Point;
import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;

public class MapCompact {
    
    ArrayList<MapTransition> mapTransitionArray;
    int mapID;
    Point overworldPoint;
    char overworldChar;
    CSIColor color;
    
    MapCompact(ArrayList<MapTransition> mapTransitionArray, int mapID, Point overworldPoint,
            char overworldChar, CSIColor color) {
        
        this.mapTransitionArray = mapTransitionArray;
        this.mapID = mapID;
        this.overworldPoint = overworldPoint;
        this.overworldChar = overworldChar;
        this.color = color;
        
    }
    
    static ArrayList<MapCompact> MapToCompact(ArrayList<Map> mapArray) {
        
        ArrayList<MapCompact> toReturn = new ArrayList();
        
        for(int i = 0; i < mapArray.size(); i++) {
            
            Map tempMap = mapArray.get(i);
            toReturn.add(new MapCompact(tempMap.mapTransitionArray,tempMap.mapID,
                    tempMap.overworldPoint,tempMap.overworldChar,tempMap.color));
        }
        
        return toReturn;
    }
}
