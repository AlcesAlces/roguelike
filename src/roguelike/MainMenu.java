package roguelike;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class MainMenu {
    
    void drawMainMenu(ConsoleSystemInterface csi, Player player) {
        
        int activeMenuOption = 0;
        boolean closeMainMenu = false;
        CSIColor activeColor = CSIColor.GREEN;
        
        while(!closeMainMenu) {
            
            csi.cls();
            
            csi.print(1,0,    "------------------------------------------------------------------------", CSIColor.WHITE);
            csi.print(1,1,    "                 Welcome to: Planes of Endatl     ", CSIColor.WHITE);
            if (activeMenuOption == 0) {
            csi.print(1,10,  ("                    Continue"), activeColor);
            csi.print(1,11,  ("                    New Game"), CSIColor.WHITE);
            csi.print(1,12,  ("                    Exit"), CSIColor.WHITE);
            csi.print(1,13,  ("                    PREMADE: DEBUG"), CSIColor.WHITE);
                }
            else if (activeMenuOption == 1) {
                
                csi.print(1,10,  ("                    Continue"), CSIColor.WHITE);
                csi.print(1,11,  ("                    New Game"), activeColor);
                csi.print(1,12,  ("                    Exit"), CSIColor.WHITE);
                csi.print(1,13,  ("                    PREMADE: DEBUG"), CSIColor.WHITE);
                
            }
            else if (activeMenuOption == 2) {
                csi.print(1,10,  ("                    Continue"), CSIColor.WHITE);
                csi.print(1,11,  ("                    New Game"), CSIColor.WHITE);
                csi.print(1,12,  ("                    Exit"), activeColor);
                csi.print(1,13,  ("                    PREMADE: DEBUG"), CSIColor.WHITE);
            }
            else if (activeMenuOption == 3) {
                csi.print(1,10,  ("                    Continue"), CSIColor.WHITE);
                csi.print(1,11,  ("                    New Game"), CSIColor.WHITE);
                csi.print(1,12,  ("                    Exit"), CSIColor.WHITE);
                csi.print(1,13,  ("                    PREMADE: DEBUG"), activeColor);
            }
            csi.print(1,23,    "------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(1,24,  "Select an option");
            csi.refresh();
            CharKey dir = csi.inkey();
            
            if(dir.isUpArrow() && (activeMenuOption > 0)) {
                activeMenuOption--;
            }
            if(dir.isDownArrow() && activeMenuOption < 3) {
                activeMenuOption++;
            }
            if(dir.code == CharKey.ENTER) {
                if (activeMenuOption == 0) {
                    System.out.println("Save not implimented");
                }
                if (activeMenuOption == 1) {
                    CreateCharacter newCharacter = new CreateCharacter();
                    newCharacter.drawCharacterCreator(csi, player);
                }
                if (activeMenuOption == 2) {
                    System.exit(0);
                }
                if (activeMenuOption == 3) {
                    
                    ArmorGeneration armor = new ArmorGeneration();
                    WeaponGeneration weapon = new WeaponGeneration();
                    ItemPotionGenerator potion = new ItemPotionGenerator();
                    ItemScrollGenerator scroll = new ItemScrollGenerator();
                    int[] premadeStats = { 10, 10, 10, 10, 10, 1, 2
                    };
                    
                    player.stats = premadeStats;
                    player.baseHealth = 65;
                    player.baseMana = 40;
                    player.equipedBodyArmor = armor.returnArmor();
                    player.equipedHelmet = armor.returnHelm();
                    player.equipedGloves = armor.returnGloves();
                    player.equipedPants = armor.returnPants();
                    player.equipedBoots = armor.returnBoots();
                    player.creatureEquipedWeapon = weapon.returnRandomWeapon(6);
                    player.inventory.add(player.equipedBodyArmor);
                    player.inventory.add(player.creatureEquipedWeapon);
                    player.inventory.add(player.equipedBoots);
                    player.inventory.add(player.equipedGloves);
                    player.inventory.add(player.equipedHelmet);
                    player.inventory.add(player.equipedPants);
                    ItemPotion potions = potion.returnPotion(50);
                    potions.cursed = true;
                    player.inventory.add(potions);
                    potions = potion.returnPotion(10);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(15);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(5);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(10);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(15);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(5);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(10);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(15);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(5);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(10);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(15);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(5);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(10);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(15);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(5);
                    player.inventory.add(potions);
                    potions = potion.returnPotion(1);
                    player.inventory.add(potions);
                    player.inventory.add(scroll.returnScroll(50));
                    player.inventory.add(scroll.returnScroll(50));
                    player.inventory.add(scroll.returnScroll(55));
                    player.weaponSkills[1] = 15;
                    player.statInitializer();
                    player.updateStats();
                    player.statInitializer();
                    player.bonusMelee[0] = 1;
                    player.manaRegenIndex = 3;
                    
                    closeMainMenu = true;
                }
            }
            if(dir.code == CharKey.Q){
		closeMainMenu = true;
			}
}
    }
}