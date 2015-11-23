package roguelike.Items;

import java.awt.Point;

public class Item {
    
    public int itemType;
    public boolean identified = false;
    public boolean cursed;
    boolean dropped;
    public Point pointOnMap;
    public char itemSymbol = '*';
    
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
