package roguelike;

import java.awt.Point;

public class Item {
    
    int itemType;
    boolean identified = false;
    boolean cursed;
    boolean dropped;
    Point pointOnMap;
    char itemSymbol = '*';
    
    Item(int itemType, boolean identified) {
    this.itemType = itemType;
    this.identified = identified;
}
    void setIdentifyTrue() {
        identified = true;
    }
    
    public String displayInfo() {
        return "";
    }
}
