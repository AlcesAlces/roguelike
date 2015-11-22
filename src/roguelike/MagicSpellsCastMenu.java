package roguelike;

import java.awt.Point;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class MagicSpellsCastMenu {
    
    public static void DisplayCastableSpells(ConsoleSystemInterface csi, Player player,
            Point frameLocUp, Point frameLocDown, Map map, EventLog eventLog) {
        
        boolean closeMenu = false;
        int maximumIndex;
        int absoluteMax = player.spellBook.size();
        int minimumIndex = 0;
        int activeIndex = 0;
        
        if (player.spellBook.size() > 21) {
            maximumIndex = 21;
        }
        
        else {
            maximumIndex = player.spellBook.size();
        }
        
        while(!closeMenu) {
            
            csi.cls();
            
            csi.print(1,0,    "------------------------------------------------------------------------", CSIColor.WHITE);
            csi.print(1,1,    "                             Select a spell to cast", CSIColor.WHITE);
            csi.print(1,2,    "Name", CSIColor.WHITE);
            csi.print(20,2,    "Damage", CSIColor.WHITE);
            csi.print(30,2,    "Accuracy", CSIColor.WHITE);
            csi.print(40,2,    "Cost", CSIColor.WHITE);
            int counter = 3;
            for (int i = minimumIndex; i < maximumIndex; i++) {
                
                
                csi.print(1,counter, player.spellBook.get(i).name, checkColor(activeIndex,i));
                csi.print(20,counter, player.spellBook.get(i).damageToString(), checkColor(activeIndex,i));
                csi.print(30,counter, player.spellBook.get(i).accuracyToString(), checkColor(activeIndex,i));
                csi.print(40,counter, player.spellBook.get(i).costToString(), checkColor(activeIndex,i));
                counter++;
        }
            
            csi.refresh();
            CharKey dir = csi.inkey();
            
            if (dir.isUpArrow() && ((activeIndex - 1) != -1)) {
                activeIndex--;
                if (activeIndex == (minimumIndex - 1)) {
                    minimumIndex--;
                    maximumIndex--;
                }
            }
            
            if (dir.isDownArrow() && activeIndex + 1 != absoluteMax) {
                activeIndex++;
                if (activeIndex == maximumIndex) {
                    minimumIndex++;
                    maximumIndex++;
                }
            }
            
            if (dir.code == CharKey.ENTER) {
                
                if ((player.activeStats[1] >= player.spellBook.get(activeIndex).baseCost)) {
                
                if (player.spellBook.get(activeIndex).targetable) {
                    
                drawCursor(csi, new Point(35,10), new Point(player.creaturePoint), 
                        map, player, frameLocUp, frameLocDown, activeIndex, eventLog);
            }
                else {
                    //cast spell on self
                    player.lastCastSpell = player.spellBook.get(activeIndex);
                    MonsterAIPrototype.findBestRoute(map, player, eventLog);
             }
                }
                
                else {
                    eventLog.manageEventLog("Not enough mana to cast " + player.spellBook.get(activeIndex).name);
                }
                closeMenu = true;
            }
            
            if (dir.code == CharKey.q) {
                closeMenu = true;
            }
        }
    }
    
    public static CSIColor checkColor(int activeIndex, int currentIndex) {
        
        if (activeIndex == currentIndex) {
            return CSIColor.BLUE;
        }
        
        else
            return CSIColor.WHITE;
    }
    
        public static void drawCursor(ConsoleSystemInterface csi, Point cursorPoint,
           Point cursorPointActual, Map map, Player player, Point frameLocUp, 
           Point frameLocDown, int activeIndex, EventLog eventLog) {
        
        boolean cursorMode = true;
        
        while (cursorMode) {
        csi.cls();      
        map.drawFrame(csi);
        map.drawElements(csi, frameLocUp, frameLocDown, null);
        map.drawPlayerStatus(player, csi);
        csi.print(player.creaturePoint.x - (frameLocUp.x - 1),player.creaturePoint.y - (frameLocUp.y - 1), '@', CSIColor.WHITE);
            
        Point tempPoint = new Point(cursorPointActual);
        csi.print(cursorPoint.x, cursorPoint.y, 'X', CSIColor.WHITE);
        displayDetectedObjects(map, cursorPointActual, csi, player);
        eventLog.printEventLog(eventLog, csi);
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
        
        if (dir.isRightArrow() && (cursorPoint.x - 1) <= 78) {
            
            cursorPointActual.translate(1, 0);
            cursorPoint.translate(1, 0);
        }
        
        if (dir.code == CharKey.ESC) { 
            
            cursorMode = false;
        }
        
        if (dir.code == CharKey.ENTER) {
            
            if (!map.checkMonsterPositions(cursorPointActual)) {
                MonsterAIPrototype.findBestRoute(map, player, eventLog);
                Combat.magicCombat(player, map.creatureArrayList.get(map.monsterIndexFound),
                        eventLog, player.spellBook.get(activeIndex));
                player.lastCastSpell = player.spellBook.get(activeIndex);
                
                cursorMode = false;
            }
        }
        
        }
    }
        
            
        public static void displayDetectedObjects(Map map, Point cursorPointActual,
            ConsoleSystemInterface csi, Player player) {
        
        if (!map.checkMonsterPositions(cursorPointActual) && !map.creatureArrayList.isEmpty()) {
            csi.print(1, 23, map.creatureArrayList.get(map.monsterIndexFound).name, 
                    CSIColor.WHITE);
            csi.print(1, 24, "the " + map.creatureArrayList.get(map.monsterIndexFound).identifyRace(map.creatureArrayList.get(map.monsterIndexFound).raceID), 
                    CSIColor.WHITE);
        }
        
        else if (cursorPointActual.equals(player.creaturePoint)) {
            
            csi.print(1, 23, player.name, CSIColor.WHITE);
            csi.print(1, 24, "the " + player.identifyRace(player.raceID), CSIColor.WHITE);
            
        }
    }
    
}
