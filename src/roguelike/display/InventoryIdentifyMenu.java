package roguelike.display;

import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import roguelike.Items.*;

public class InventoryIdentifyMenu {
    
        static void drawInventoryIdentifyMenu(ConsoleSystemInterface csi, ArrayList<Item> itemArray,
                int indexOfScroll, Item item) {
        
        int lastLineTracker = 0;
        int minimumIndex = 0;
        int maximumIndex;
        int absoluteMax;
        int activeMenuOption = 0;
        Object itemToCheck;
        boolean closeInvMenu = false;
        boolean itemDetailMode = false;
        boolean alreadyIdentified = false;
        
        if (itemArray.size() > 22) {
            maximumIndex = 22;
        }
        
        else {
            maximumIndex = itemArray.size();
        }
        
        CSIColor activeColor = CSIColor.GREEN;
            
            
            while (!closeInvMenu) {
                
                lastLineTracker = 0;
                absoluteMax = itemArray.size();
            
                csi.cls();
                csi.print(1,0,    "----------------------------------------------------------------------------", CSIColor.WHITE);
                csi.print(35,0,    "Identify Item", CSIColor.GREEN);
                
            for (int i = minimumIndex; i < maximumIndex; i++) {
                if (i == activeMenuOption) {
            csi.print(1,lastLineTracker +1,  (itemArray.get(i).toString()), activeColor);
            lastLineTracker++;
                }
                else {
                    csi.print(1,lastLineTracker +1,  (itemArray.get(i).toString()), CSIColor.WHITE);
                    lastLineTracker++;
                }
            }
            if (alreadyIdentified) {
                csi.print(1, 23, "Item is already identified", CSIColor.WHITE);
            }
            
            csi.print(1,24,    "-----------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(10,24,  "press q to return, press enter to identify item.");
        
            csi.refresh();
            
            CharKey dir = csi.inkey();
            
            if(dir.isUpArrow() && ((activeMenuOption - 1) != -1)) {
                activeMenuOption--;
                if(activeMenuOption == minimumIndex - 1) {
                    minimumIndex--;
                    maximumIndex--;
                }
            }
            if(dir.isDownArrow() && activeMenuOption + 1 != absoluteMax) {
                activeMenuOption++;
                if (activeMenuOption == maximumIndex) {
                    minimumIndex++;
                    maximumIndex++;
                }
            }
            
            if (dir.code == CharKey.q) {
                closeInvMenu = true;
            }
            
            if (dir.code == CharKey.ENTER) {
                
                boolean identifyProcess = false;
                if (itemArray.get(activeMenuOption).identified) {
                    alreadyIdentified = true;
                }
                else {
                    while (!identifyProcess) {
                    csi.cls();
                    csi.print(1,0,    "------------------------------------Items-------------------------------", CSIColor.WHITE);
                    csi.print(1, 2, ("Identify " + itemArray.get(activeMenuOption) + "?"));
                    csi.print(1,24,    "------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
                    csi.print(10,24,  "Press enter to confirm, q to decline");
                    csi.refresh();
                    dir = csi.inkey();
                    
                    if (dir.code == CharKey.ENTER) {
                        
                        if (itemArray.get(activeMenuOption).itemType == 0) {
                            Armor itemTemp = (Armor) itemArray.get(activeMenuOption);
                        itemTemp.identified = true;
                        itemArray.remove(indexOfScroll);
                        identifyProcess = true;
                        closeInvMenu = true;
                        }
                        
                        if (itemArray.get(activeMenuOption).itemType == 1) {
                            ArmorBoots itemTemp = (ArmorBoots) itemArray.get(activeMenuOption);
                        itemTemp.identified = true;
                        itemArray.remove(indexOfScroll);
                        identifyProcess = true;
                        closeInvMenu = true;
                        }
                        
                        if (itemArray.get(activeMenuOption).itemType == 2) {
                            ArmorGloves itemTemp = (ArmorGloves) itemArray.get(activeMenuOption);
                        itemTemp.identified = true;
                        itemArray.remove(indexOfScroll);
                        identifyProcess = true;
                        closeInvMenu = true;
                        }
                        
                        if (itemArray.get(activeMenuOption).itemType == 3) {
                            ArmorHelmet itemTemp = (ArmorHelmet) itemArray.get(activeMenuOption);
                        itemTemp.identified = true;
                        itemArray.remove(indexOfScroll);
                        identifyProcess = true;
                        closeInvMenu = true;
                        }
                        
                        if (itemArray.get(activeMenuOption).itemType == 4) {
                            ArmorPants itemTemp = (ArmorPants) itemArray.get(activeMenuOption);
                        itemTemp.identified = true;
                        itemArray.remove(indexOfScroll);
                        identifyProcess = true;
                        closeInvMenu = true;
                        }
                        
                        if (itemArray.get(activeMenuOption).itemType == 5) {
                            Weapon itemTemp = (Weapon) itemArray.get(activeMenuOption);
                        itemTemp.identified = true;
                        itemArray.remove(indexOfScroll);
                        identifyProcess = true;
                        closeInvMenu = true;
                        }
                        
                        if (itemArray.get(activeMenuOption).itemType == 6) {
                        ItemPotion itemTemp = (ItemPotion) itemArray.get(activeMenuOption);
                        itemTemp.identified = true;
                        itemArray.remove(indexOfScroll);
                        identifyProcess = true;
                        closeInvMenu = true;
                        }
                        
                        if (itemArray.get(activeMenuOption).itemType == 7) {
                        ItemScroll itemTemp = (ItemScroll) itemArray.get(activeMenuOption);
                        itemTemp.identified = true;
                        itemArray.remove(indexOfScroll);
                        identifyProcess = true;
                        closeInvMenu = true;
                        }
                    }
                    else if (dir.code == CharKey.q) {
                        identifyProcess = true;
                    }
                    
                }
            }
            }
        }
}}
