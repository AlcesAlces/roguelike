package roguelike;

import java.awt.Point;
import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class DefaultMapType extends Map{
    
    public DefaultMapType(int xMax, int yMax, int xMin, int yMin, CSIColor color
            , Point descentPoint, Point ascentPoint) {
        super(xMax, yMax, xMin, yMin, color, descentPoint, ascentPoint);
        randomElements.generateRandomElements();
    }
    
    RandomMapElements randomElements = new RandomMapElements(xMax, yMax);
    DrawMonsters drawMonsters = new DrawMonsters(creatureArrayList);
    
    void drawMapElements(ConsoleSystemInterface csi) {
        
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMin; j <= yMax; j++) {
                csi.print(i, j, '.', color);
            }
        }
        
        if (descentPoint != null) {
        csi.print(descentPoint.x, descentPoint.y, ">", color);
        }
        if (ascentPoint != null) {
        csi.print(ascentPoint.x, ascentPoint.y, "<", color);
        }
        
        randomElements.drawElements(csi, color);
        
        for (int i = xMin; i <= xMax; i++) {
            csi.print(i, yMin, '#', color);
        }
        
        for (int i = xMin; i <= xMax; i++) {
            csi.print(i, yMax, '#', color);
        }
        
        for (int i = yMin; i <= yMax; i++) {
            csi.print(xMin, i, '#', color);
        }
        
        for (int i = yMin; i <= yMax; i++) {
            csi.print(xMax, i, '#', color);
        }
        drawMonsters.drawMonstersOnMap(csi);
    }
    
    void addMonstersToMap(Creature importCreature) {
        this.creatureArrayList.add(importCreature);
    }
}
