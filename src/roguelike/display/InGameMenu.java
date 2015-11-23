package roguelike.display;

import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import roguelike.file.FileWriter;
import roguelike.character.Player;
import roguelike.map.Map;

public class InGameMenu {
    
    public static void DrawInGameMenu(ConsoleSystemInterface csi, Player player,
                                      ArrayList<Map> mapArray) {
        int activeMenuOption = 0;
        boolean closeMainMenu = false;
        CSIColor activeColor = CSIColor.GREEN;
        
        while(!closeMainMenu) {
            
            csi.cls();
            
            csi.print(1,0,    "------------------------------------------------------------------------", CSIColor.WHITE);
            csi.print(1,1,    "                 Game Menu     ", CSIColor.WHITE);
            if (activeMenuOption == 0) {
            csi.print(1,10,  ("                    Save Game"), activeColor);
            csi.print(1,11,  ("                    Options"), CSIColor.WHITE);
            csi.print(1,12,  ("                    Exit Game"), CSIColor.WHITE);
                }
            else if (activeMenuOption == 1) {
                
                csi.print(1,10,  ("                    Save Game"), CSIColor.WHITE);
                csi.print(1,11,  ("                    Options"), activeColor);
                csi.print(1,12,  ("                    Exit Game"), CSIColor.WHITE);
                
            }
            else if (activeMenuOption == 2) {
                csi.print(1,10,  ("                    Save Game"), CSIColor.WHITE);
                csi.print(1,11,  ("                    Options"), CSIColor.WHITE);
                csi.print(1,12,  ("                    Exit Game"), activeColor);
            }
            
            csi.print(1,23,    "------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(1,24,  "Select an option, q to return to game");
            csi.refresh();
            CharKey dir = csi.inkey();
            
            if(dir.isUpArrow() && (activeMenuOption > 0)) {
                activeMenuOption--;
            }
            if(dir.isDownArrow() && activeMenuOption < 2) {
                activeMenuOption++;
            }
            
            if(dir.code == CharKey.q){
		closeMainMenu = true;
             }
            
            if(dir.code == CharKey.ENTER) {
                if (activeMenuOption == 0) {
                    //The save option
                    boolean decided = false;
                    int activeSubmenu = 0;
                    while(!decided) {
                        csi.cls();
                        csi.print(1,0,    "------------------------------------------------------------------------", CSIColor.WHITE);
                        csi.print(1,1,    "                 Save Menu     ", CSIColor.WHITE);
                        csi.print(1,8,  ("           Saving will over-ride the save for " + player.name), CSIColor.RED);
                        csi.print(1,9,  ("           Do you want to save?"), CSIColor.RED);
                        
                        if (activeSubmenu == 0) {
                        csi.print(1,10,  ("                    Yes"), activeColor);
                        csi.print(1,11,  ("                    No"), CSIColor.WHITE);
                        }
                        else if(activeSubmenu == 1) {
                            csi.print(1,10,  ("                    Yes"), CSIColor.WHITE);
                            csi.print(1,11,  ("                    No"), activeColor);
                        }
                        csi.refresh();
                        dir = csi.inkey();
                        if(dir.code == CharKey.ENTER) {
                            if(activeSubmenu == 0) {
                                //save
                                FileWriter.WriteMap(mapArray, player.name);
                                FileWriter.WriteMonsters(mapArray, player.name);
                                FileWriter.WritePlayer(player);
                                decided = true;
                            }
                            else {
                                //Don't save
                                decided = true;
                            }
                        }
                        
                        if(dir.isUpArrow() && (activeSubmenu > 0)) {
                        activeSubmenu--;
                        }
                        if(dir.isDownArrow() && activeSubmenu < 1) {
                        activeSubmenu++;
                        }
                }
            }
            
                if (activeMenuOption == 1) {
                    //The options option
                    System.out.println("I'M WORKIN ON IT");
                }
                if (activeMenuOption == 2) {
                    //The exit option
                    System.exit(0);
                }
            }
        }
    }
}
