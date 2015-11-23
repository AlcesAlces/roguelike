package roguelike.display;

import java.awt.Point;
import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import roguelike.EventLog;
import roguelike.Items.*;
import roguelike.character.Player;
import roguelike.map.Map;

public class InventoryMenu {
    
    public static void drawInventoryMenu(ConsoleSystemInterface csi, ArrayList<Item> itemArray,
                                         Map map, Player player, EventLog eventLog) {
        
        int lastLineTracker = 0;
        int activeMenuOption = 0;
        int maximumIndex;
        int minimumIndex = 0;
        int absoluteMax;
        int arrayRangeCheck = itemArray.size();
        Object itemToCheck;
        boolean closeInvMenu = false;
        boolean itemDetailMode = false;
        CSIColor activeColor = CSIColor.GREEN;
        
        if (itemArray.size() > 22) {
            maximumIndex = 22;
        }
        
        else {
            maximumIndex = itemArray.size();
        }
        
            while (!closeInvMenu) {
                
                //arrRangeCheck prevents an arraylist index out of bounds error
                //from removing an item.
                if (arrayRangeCheck != itemArray.size()) {
                    
                   if(activeMenuOption == itemArray.size()) {
                       activeMenuOption--;
                   }
                    
                    if (itemArray.size() > 22 && minimumIndex != 0) {
                        maximumIndex--;
                        minimumIndex--;
                    }
                    
                    else if (itemArray.size() > 22 && minimumIndex == 0) {
                        maximumIndex = 22;
                    }
                    else {
                        maximumIndex = itemArray.size();
                    }
                    
                    arrayRangeCheck = itemArray.size();
                }
            csi.cls();
            csi.print(1,0,    "------------------------------------Items-------------------------------", CSIColor.WHITE);
                        
            lastLineTracker = 0;
            absoluteMax = itemArray.size();
            
            for (int i = minimumIndex; i < maximumIndex; i++) {
                
                if (i == activeMenuOption) {
            
                    csi.print(1,lastLineTracker + 1,  (itemArray.get(i).toString()), activeColor);
                    lastLineTracker++;
                }
                else {
                    csi.print(1,lastLineTracker + 1,  (itemArray.get(i).toString()), CSIColor.WHITE);
                    lastLineTracker++;
                }
            }
            
            csi.print(1,24,    "------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(10,24,  "q - quit, enter- details, d - drop, e - equip");
        
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
            
            if (dir.code == CharKey.ENTER) {
                System.out.println(itemArray.get(activeMenuOption).itemType);
                //Cast the generic item into an ArmorHelmet type, USE THIS AS
                //THE FORMAT FOR ALL OF THE OTHER TYPES!
                
                if (itemArray.get(activeMenuOption).itemType == 0) {
                    Armor armor = (Armor) itemArray.get(activeMenuOption);
                    displayArmorInfo(csi, armor);
                    
                }
                
                else if (itemArray.get(activeMenuOption).itemType == 1) {
                    ArmorBoots boots = (ArmorBoots) itemArray.get(activeMenuOption);
                    displayBootInfo(csi, boots);
                    
                }
                
                else if (itemArray.get(activeMenuOption).itemType == 2) {
                    ArmorGloves gloves = (ArmorGloves) itemArray.get(activeMenuOption);
                    displayGloveInfo(csi,gloves);
                }
                 
                else if (itemArray.get(activeMenuOption).itemType == 3) {
                    ArmorHelmet helm = (ArmorHelmet) itemArray.get(activeMenuOption);
                    displayHelmetInfo(csi, helm);
                }
                
                else if (itemArray.get(activeMenuOption).itemType == 4) {
                    ArmorPants pants = (ArmorPants) itemArray.get(activeMenuOption);
                    displayPantInfo(csi,pants);
                }
                
                else if (itemArray.get(activeMenuOption).itemType == 5) {
                    Weapon weapon = (Weapon) itemArray.get(activeMenuOption);
                    displayWeaponInfo(csi,weapon);
                }
                
                else if (itemArray.get(activeMenuOption).itemType == 6) {
                    ItemPotion potion = (ItemPotion) itemArray.get(activeMenuOption);
                    displayPotionInfo(csi,potion);
                }
                
                else if (itemArray.get(activeMenuOption).itemType == 7) {
                    ItemScroll scroll = (ItemScroll) itemArray.get(activeMenuOption);
                    displayScrollInfo(csi,scroll, itemArray, activeMenuOption);
                }
                
                else {
                    
                }
                
            }
            
            if(dir.code == CharKey.d) {
                
                eventLog.manageEventLog("You drop the " + itemArray.get(activeMenuOption));
                itemArray.get(activeMenuOption).pointOnMap = new Point(player.creaturePoint);
                map.itemsOnMap.add(itemArray.get(activeMenuOption));
                itemArray.remove(activeMenuOption);
                
            }
            
            if(dir.code == CharKey.q){
		closeInvMenu = true;
			}
            
            }
            
        
    }
    
    static void displayArmorInfo(ConsoleSystemInterface csi, Armor item) {
        
        boolean InfoClose = false;
        
        while(!InfoClose) {
        csi.cls();
        
        csi.print(1,0,    "------------------------------------Items-------------------------------", CSIColor.WHITE);
        csi.print(1,1, item.toString(), CSIColor.WHITE);
        if (!item.identified) {
        
            csi.print(1,3, ("Unidentified"), CSIColor.RED);
        }
        csi.print(1,4, ("Armor value: " + item.totalArmorValue()), CSIColor.WHITE);
        csi.print(1,24,    "------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(10,24,  "press q to return to item menu");
        csi.refresh();
        
        CharKey dir = csi.inkey();
        
        if (dir.code == CharKey.q) {
            InfoClose = true;
        }
        
        }
    }
    
    static void displayGloveInfo(ConsoleSystemInterface csi, ArmorGloves item) {
        
        boolean InfoClose = false;
        
        while(!InfoClose) {
        csi.cls();
        
        csi.print(1,0,    "------------------------------------Items-------------------------------", CSIColor.WHITE);
        csi.print(1,1, item.toString(), CSIColor.WHITE);
        if (!item.identified) {
        
            csi.print(1,3, ("Unidentified"), CSIColor.RED);
        }
        csi.print(1,4, ("Armor value: " + item.totalArmorValue()), CSIColor.WHITE);
        csi.print(1,24,    "------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(10,24,  "press q to return to item menu");
        csi.refresh();
        
        CharKey dir = csi.inkey();
        
        if (dir.code == CharKey.q) {
            InfoClose = true;
        }
        
        }
    }
    
    static void displayBootInfo(ConsoleSystemInterface csi, ArmorBoots item) {
        
        boolean InfoClose = false;
        
        while(!InfoClose) {
        csi.cls();
        
        csi.print(1,0,    "------------------------------------Items-------------------------------", CSIColor.WHITE);
        csi.print(1,1, item.toString(), CSIColor.WHITE);
        if (!item.identified) {
        
            csi.print(1,3, ("Unidentified"), CSIColor.RED);
        }
        csi.print(1,4, ("Armor value: " + item.totalArmorValue()), CSIColor.WHITE);
        csi.print(1,24,    "------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(10,24,  "press q to return to item menu");
        csi.refresh();
        
        CharKey dir = csi.inkey();
        
        if (dir.code == CharKey.q) {
            InfoClose = true;
        }
        
        }
    }
    
    static void displayHelmetInfo(ConsoleSystemInterface csi, ArmorHelmet item) {
        
        boolean InfoClose = false;
        
        while(!InfoClose) {
        csi.cls();
        
        csi.print(1,0,    "------------------------------------Items-------------------------------", CSIColor.WHITE);
        csi.print(1,1, item.toString(), CSIColor.WHITE);
        if (!item.identified) {
        
            csi.print(1,3, ("Unidentified"), CSIColor.RED);
        }
        csi.print(1,4, ("Armor value: " + item.totalArmorValue()), CSIColor.WHITE);
        csi.print(1,24,    "------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(10,24,  "press q to return to item menu");
        csi.refresh();
        
        CharKey dir = csi.inkey();
        
        if (dir.code == CharKey.q) {
            InfoClose = true;
        
        }
        
        }
    }
    
        static void displayPantInfo(ConsoleSystemInterface csi, ArmorPants item) {
        
        boolean InfoClose = false;
        
        while(!InfoClose) {
        csi.cls();
        
        csi.print(1,0,    "------------------------------------Items-------------------------------", CSIColor.WHITE);
        csi.print(1,1, item.toString(), CSIColor.WHITE);
        if (!item.identified) {
        
            csi.print(1,3, ("Unidentified"), CSIColor.RED);
        }
        csi.print(1,4, ("Armor value: " + item.totalArmorValue()), CSIColor.WHITE);
        csi.print(1,24,    "------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(10,24,  "press q to return to item menu");
        csi.refresh();
        
        CharKey dir = csi.inkey();
        
        if (dir.code == CharKey.q) {
            InfoClose = true;
        }
        
        }
    }
        
        static void displayWeaponInfo(ConsoleSystemInterface csi, Weapon item) {
        
        boolean InfoClose = false;
        
        while(!InfoClose) {
        csi.cls();
        
        csi.print(1,0,    "------------------------------------Items-------------------------------", CSIColor.WHITE);
        csi.print(1,1, item.toString(), CSIColor.WHITE);
        if (!item.identified) {
        
            csi.print(1,3, ("Unidentified"), CSIColor.RED);
        }
        csi.print(1,4, ("Damage value: " + item.totalWeaponValue()), CSIColor.WHITE);
        csi.print(1,5, ("Damage type : " + item.weaponDamageTypeToString()), CSIColor.WHITE);
        csi.print(1,24,    "-------------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(10,24,  "press q to return to item menu");
        csi.refresh();
        
        CharKey dir = csi.inkey();
        
        if (dir.code == CharKey.q) {
            InfoClose = true;
        }
        
        }
    }
        
        static void displayPotionInfo(ConsoleSystemInterface csi, ItemPotion item) {
        
        boolean InfoClose = false;
        
        while(!InfoClose) {
        csi.cls();
        
        csi.print(1,0,    "------------------------------------Items-------------------------------", CSIColor.WHITE);
        csi.print(1,1, item.toString(), CSIColor.WHITE);
        
        if (!item.identified) {
        
            csi.print(1,3, ("Unidentified"), CSIColor.RED);
        csi.print(1,4, ("The mysteries of this potion elude you."), CSIColor.WHITE);
        }
        
        if (item.identified) {
            
            if(item.effect.getEffect() == 1) {
        csi.print(1,3, ("Identified"), CSIColor.GREEN);
        csi.print(1,4, ("The potion radiates healing energy."), CSIColor.WHITE);
            }
            
            if(item.effect.getEffect() == 2) {
        csi.print(1,3, ("Identified"), CSIColor.GREEN);
        csi.print(1,4, ("The potion radiates magical power."), CSIColor.WHITE);
            }
            
            if(item.effect.getEffect() == 3) {
        csi.print(1,3, ("Identified"), CSIColor.GREEN);
        csi.print(1,4, ("The potion seethes with malice and hate."), CSIColor.WHITE);
            }
            
            if (item.cursed) {
                csi.print(1,5, ("This item is cursed, the corruption of this item"), CSIColor.RED);
                csi.print(1,6, ("makes it unpredictable."), CSIColor.RED);
            }
        }
        
        csi.print(1,24,    "-------------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(10,24,  "press q to return to item menu");
        csi.refresh();
        
        CharKey dir = csi.inkey();
        
        if (dir.code == CharKey.q) {
            InfoClose = true;
        }
        
        }
    }
        
        static void displayScrollInfo(ConsoleSystemInterface csi, ItemScroll item, 
                ArrayList<Item> itemArray, int activeMenuOption) {
            boolean InfoClose = false;
            
        while(!InfoClose) {
        csi.cls();
        
        csi.print(1,0,    "------------------------------------Items-------------------------------", CSIColor.WHITE);
        csi.print(1,1, item.toString(), CSIColor.WHITE);
        if (!item.identified) {
        
            csi.print(1,3, ("Unidentified"), CSIColor.RED);
        }
        csi.print(1,4, ("You have not deciphered the text of this scroll."), CSIColor.WHITE);
        csi.print(1,24,    "-------------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(10,24,  "press q to return to item menu, press enter to use this item");
        csi.refresh();
        
        CharKey dir = csi.inkey();
        
        if (dir.code == CharKey.q) {
            InfoClose = true;
        }
        
        if (dir.code == CharKey.ENTER) {
            if (item.effect.returnEffectValue() == 3) {
            InventoryIdentifyMenu.drawInventoryIdentifyMenu(csi, itemArray, activeMenuOption,
                    item);
            InfoClose = true;
            System.out.println("active menu option: " + activeMenuOption);
            }
            
            else{
                
            }
        }
        
        }
        }
}
