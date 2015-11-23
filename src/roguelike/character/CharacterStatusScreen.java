package roguelike.character;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class CharacterStatusScreen {
    
    public static void drawCharacterStatusScreen(ConsoleSystemInterface csi, Player player) {
        
        boolean closeCharacterScreen = false;
        
        while (!closeCharacterScreen) {
            
            csi.cls();
            
            csi.print(1,0,    "----------------------------------Character--------------------------------", CSIColor.WHITE);
            csi.print(30,1,    player.name + " the " + player.identifyRace(player.raceID), CSIColor.WHITE);
            csi.print(1, 3,   "Attributes:", CSIColor.WHITE);
            csi.print(1, 4,   "Strength     : " + player.activeStats[2], checkForAffliction(player,2,csi));
            csi.print(1, 5,   "Dexterity    : " + player.activeStats[3], checkForAffliction(player,3,csi));
            csi.print(1, 6,   "Endurance    : " + player.activeStats[4], checkForAffliction(player,4,csi));
            csi.print(1, 7,   "Intelligence : " + player.activeStats[5], checkForAffliction(player,5,csi));
            csi.print(1, 8,   "Wisdom       : " + player.activeStats[6], checkForAffliction(player,6,csi));
            
            csi.print(1,10, "To next level: " + (player.experienceRequired - player.experience));
            
            if (player.levelUp) {
            csi.print(1,24, "------Press q to quit-----------------------Press c to level up------------------", CSIColor.WHITE);
            }
            else if (!player.levelUp) {
                csi.print(1,24, "------Press q to quit---------------------------------------------------------", CSIColor.WHITE);
            }
            
            csi.refresh();
            CharKey dir = csi.inkey();
            
            if (dir.code == CharKey.q) {
                
                closeCharacterScreen = true;
            }
            
            if ((dir.code == CharKey.c) && player.levelUp) {
                
                LevelUp.drawLevelUpScreen(csi, player);
            }
            
        }
    }
    
    static CSIColor checkForAffliction(Player player, int index,ConsoleSystemInterface csi) {
        
        CSIColor colorToReturn;
        
        if (player.activeStats[index] < player.stats[index]) {
            
            colorToReturn = CSIColor.RED;
            String statDifference = (player.activeStats[index] - player.stats[index]) + "";
            csi.print(1, (index + 2), "                   " + statDifference, CSIColor.RED);
        }
        
        else if (player.activeStats[index] > player.stats[index]) {
            String statDifference = (player.activeStats[index] - player.stats[index]) + "";
            csi.print(1, (index + 2), "                    +" + statDifference, CSIColor.GREEN);
            colorToReturn = CSIColor.GREEN;
        }
        
        else {
            colorToReturn = CSIColor.WHITE;
        }
        
        return colorToReturn;
    }
    
}
