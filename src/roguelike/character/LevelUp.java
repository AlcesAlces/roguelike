package roguelike.character;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import roguelike.EventLog;

public class LevelUp {
    
    public static void checkForLevelUp(Player player, EventLog eventLog) {
        
        if (player.experience >= player.experienceRequired) {
            
            player.experience = 0;
            player.level++;
            eventLog.manageEventLog("Level up!");
            player.levelUp = true;
        }
    }
    
    public static void drawLevelUpScreen(ConsoleSystemInterface csi, Player player) {
        
        boolean closeLevelMenu = false;
        int activeMenuOption = 2;
        int remainingPoints = 5;
        
        while(!closeLevelMenu) {
            
            csi.cls();
            
            csi.print(1,0,    "------------------------------------Level up----------------------------", CSIColor.WHITE);
            csi.print(1,2,    "Pick 5 points to improve", CSIColor.RED);
            csi.print(1,3, "                    " + player.name + " the " + player.identifyRace(player.raceID), CSIColor.WHITE);
                csi.print(1,5, "                             Health  : " + player.stats[0] , CSIColor.WHITE);
                csi.print(1,6, "                             Mana    : " + player.stats[1] , CSIColor.WHITE);
                csi.print(1,7,  "                            Strength : " + player.stats[2] , isActiveMenu(0, activeMenuOption));
                csi.print(1,8,  "                          Dexterirty : " + player.stats[3] , isActiveMenu(1, activeMenuOption));
                csi.print(1,9,  "                           Endurance : " + player.stats[4] , isActiveMenu(2, activeMenuOption));
                csi.print(1,10,  "                        Intelligence : " + player.stats[5] , isActiveMenu(3, activeMenuOption));
                csi.print(1,11,  "                            Wisdom   : " + player.stats[6] , isActiveMenu(4, activeMenuOption));
                csi.print(1,13,  "                    Remaining Points: " + remainingPoints, CSIColor.RED);
            
                
                csi.refresh();
                CharKey dir = csi.inkey();
                
                if(dir.isUpArrow() && (activeMenuOption > 0)) {
                    activeMenuOption--;
                }
                if(dir.isDownArrow() && activeMenuOption < 4) {
                    activeMenuOption++;
                }
                if(dir.code == CharKey.ENTER && (remainingPoints > 0)) {
                    player.stats[activeMenuOption + 2]++;
                    player.activeStats[activeMenuOption + 2]++;
                    remainingPoints--;
                }
                if (remainingPoints == 0) {
                    if(dir.code == CharKey.ENTER) {
                        closeLevelMenu = true;
                    }
                }
                
        }
        
    }
    
    static CSIColor isActiveMenu(int menuOption, int selectionOption) {
        
        if (menuOption == selectionOption) {
            return CSIColor.GREEN;
        }
        else
            return CSIColor.WHITE;
        }
    
}
