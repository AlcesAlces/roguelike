package roguelike;

import java.awt.Point;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class CursorMode {
    
    
    
    public static void drawCursor(ConsoleSystemInterface csi, Point cursorPoint,
           Point cursorPointActual, Map map, Player player, Point frameLocUp, 
           Point frameLocDown) {
        
        boolean cursorMode = true;
        
        while (cursorMode) {
        csi.cls();      
        map.drawFrame(csi);
        map.drawElements(csi, frameLocUp, frameLocDown, null);
        map.drawItems(frameLocUp, frameLocDown, csi);
        map.drawPlayerStatus(player, csi);
        csi.print(player.creaturePoint.x - (frameLocUp.x - 1),player.creaturePoint.y - (frameLocUp.y - 1), '@', CSIColor.WHITE);
            
        Point tempPoint = new Point(cursorPointActual);
        csi.print(cursorPoint.x, cursorPoint.y, 'X', CSIColor.WHITE);
        displayDetectedObjects(map, cursorPointActual, csi, player);
        csi.refresh();
        CharKey dir = csi.inkey();
        // && tempPoint.y > map.yMin
        
        
        tempPoint.translate(0,-1);
        
        if (dir.isUpArrow() && ((cursorPoint.y -1) >= 1)) {
            
            cursorPointActual.translate(0, -1);
            cursorPoint.translate(0, -1);
        }
        
        tempPoint.translate(0,2);
        
        if (dir.isDownArrow() && (cursorPoint.y + 1) <= 18) {
            
            cursorPointActual.translate(0, 1);
            cursorPoint.translate(0, 1);
        }
        
        tempPoint.translate(-1,-2);
        
        if (dir.isLeftArrow() && (cursorPoint.x - 1) >= 1) {
            
            cursorPointActual.translate(-1, 0);
            cursorPoint.translate(-1, 0);
        }
        
        tempPoint.translate(2,0);
        
        if (dir.isRightArrow() && (cursorPoint.x + 1) <= 78) {
            
            cursorPointActual.translate(1, 0);
            cursorPoint.translate(1, 0);
        }
        
        if (dir.code == CharKey.q) { 
            
            cursorMode = false;
        }
        
        }
    }
    
    public static void displayDetectedObjects(Map map, Point cursorPointActual,
            ConsoleSystemInterface csi, Player player) {
        
        if (!map.checkMonsterPositions(cursorPointActual)) {
            csi.print(1, 23, map.creatureArrayList.get(map.monsterIndexFound).name, 
                    CSIColor.WHITE);
            csi.print(1, 24, "the " + map.creatureArrayList.get(map.monsterIndexFound).identifyRace(map.creatureArrayList.get(map.monsterIndexFound).raceID), 
                    CSIColor.WHITE);
        }
        
        else if (cursorPointActual.equals(player.creaturePoint)) {
            
            csi.print(1, 23, player.name, CSIColor.WHITE);
            csi.print(1, 24, "the " + player.identifyRace(player.raceID), CSIColor.WHITE);
            
        }
        
        else {
            
            for (int i = 0; i < map.treeArray.size();i++) {
                
                if(cursorPointActual.equals(map.treeArray.get(i).position)) {
                    
                    csi.print(1, 23, "A tree");
                }
            }
        }
    }
}
