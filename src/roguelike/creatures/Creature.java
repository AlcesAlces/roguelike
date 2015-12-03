package roguelike.creatures;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import roguelike.Items.*;
import roguelike.magic.MagicSpells;
import roguelike.stats.StatusEffects;
import roguelike.character.RaceInitialStats;

// Equipment Stats 1-8: AC, AP, Dodge, Hit, Magic Defense, Magic Attack, Potency
// Stats 1- 7: health, mana, strength, dexterity, endurance, intelligence, wisdom
// Weapon Skills: Unarmed, Blunt1h, Bladed1h, Point1h, Blunt2h, Blade2h, Point2h
// Bonus Melee abilities: Power Attack, Double Attack
// Bonus Magic Abilities: 

public abstract class Creature implements Serializable{
    
    public int[] stats;
    public int[] activeStats = new int[7];
    public int[] equipStats = new int[7];
    public int[] weaponSkills = new int[7];
    public int[] bonusMelee = new int[2];
    int[] bonusMagic = new int[5];
    
    public int mapID;
    public int weaponTypeWeakness;
    public int weaponTypeStrength;
    public int weaponTypeEquiped;
    int classID;
    public int level = 1;
    public int experience = 0;
    public int experienceRequired;
    public int currentHealth;
    public int baseHealth;
    public int baseMana;
    public int raceID;
    public int actionPoints;
    int armorClass;
    int weaponClass;
    int equipedWeaponType;
    public int healthRegenIndex = 10;
    int healthRegenCounter = 0;
    public int healthRegened = 1;
    public int manaRegenIndex;
    int manaRegenCounter = 0;
    public int manaRegened = 1;
    public CSIColor color;
    public char symbol;
    
    public boolean levelUp = false;
    public Point creaturePoint;
    public Armor equipedBodyArmor = null;
    public ArmorBoots equipedBoots = null;
    public ArmorGloves equipedGloves = null;
    public ArmorHelmet equipedHelmet = null;
    public ArmorPants equipedPants = null;
    public Weapon creatureEquipedWeapon;
    public String name;
    public ArrayList<MagicSpells> spellBook = new ArrayList();
    public ArrayList<Item> inventory = new ArrayList();
    ArrayList<StatusEffects> statusEffects = new ArrayList();
    
    protected Creature(int[] stats, Point creaturePoint, int raceID, int actionPoints,
                       String name) {
        this.stats = stats;
        this.raceID = raceID;
        this.actionPoints = actionPoints;
        this.creaturePoint = creaturePoint;
        equipStats[0] = equipStats[1] + equipStats[2];
        this.name = name;
        
                
    }
    
    public void statInitializer() {
        
        activeStats[0] = stats[0];
        activeStats[1] = stats[1];
        activeStats[2] = stats[2];
        activeStats[3] = stats[3];
        activeStats[4] = stats[4];
        activeStats[5] = stats[5];
        activeStats[6] = stats[6];
        
         
    }
    
    public void updateStats() {
        
        updateArmor();
        
        
        //equipedWeaponType = creatureEquipedWeapon.getType().getWeight();
        //weaponClass = creatureEquipedWeapon.totalWeaponValue();
        
        //AC formula
        equipStats[0] = (activeStats[4] + armorClass / 2) + (activeStats[3] / 2);
        //AP formula
        equipStats[1] = (activeStats[2] * 2 + (weaponClass));
        //Dodge formula
        equipStats[2] = (activeStats[3] * 2 - (armorClass / 2));
        //Hit formula
        equipStats[3] = (activeStats[3] * 2 + (weaponSkills[equipedWeaponType]/2));
        // Magic Defense Formula
        equipStats[4] = (activeStats[6] + (armorClass)/4);
        // Magic Attack Formula
        equipStats[5] = (activeStats[5] + (activeStats[6] / 2));
        
        // Max Health
        stats[0] = (activeStats[4] + baseHealth);
        //Max Mana
        stats[1] = (activeStats[6] + baseMana);
        
        manaRegenCounter++;
        if((manaRegenCounter >= manaRegenIndex) && activeStats[1] < stats[1]) {
            activeStats[1] = activeStats[1] + manaRegened;
            manaRegenCounter = 0;
        }
        
        healthRegenCounter++;
        if((healthRegenCounter >= healthRegenIndex) && activeStats[0] < stats[0]) {
            activeStats[0] = activeStats[0] + healthRegened;
            healthRegenCounter = 0;
        }
        checkStatusEffect();
        experienceRequired = (level * 100) + (level * 51 / 3);
        
        if(creatureEquipedWeapon != null) {
            
            weaponTypeEquiped = creatureEquipedWeapon.getType().getWeight();
        }
        
        else {
            
            weaponTypeEquiped = 0;
        }

    }
    /*Race IDs:
     * 0 - Human
     * 1 - Elf
     * 2 - Orc
     * 3 - Flying Nun
     * 4 - Goblin
     * 5 - Wight
     */
    public String identifyRace(int raceID) {
        
        String stringToReturn = "";
        
        switch (raceID) {
            case 0:
                stringToReturn = "Human";
                return stringToReturn;
            case 1:
                stringToReturn = "Elf";
                return stringToReturn;
            case 2:
                stringToReturn = "Orc";
                return stringToReturn;
            case 3:
                stringToReturn = "Flying nun";
                return stringToReturn;
            case 4:
                stringToReturn = "Goblin";
                return stringToReturn;
            //Changed wight to ghost
            case 5:
                stringToReturn = "Ghost";
                return stringToReturn;
        }
        return stringToReturn;
    }
    
    void updateArmor() {
        
        armorClass = 0;
        
        if(equipedBodyArmor != null) {
            
            armorClass = armorClass + equipedBodyArmor.armorACValue();
        }
        
        if(equipedGloves != null) {
            
            armorClass = armorClass + equipedGloves.armorACValue();
        }
        
        if(equipedHelmet != null) {
            
            armorClass = armorClass + equipedHelmet.armorACValue();
        }
        
        if(equipedPants != null) {
            
            armorClass = armorClass + equipedPants.armorACValue();
        }
        
        if(equipedBoots != null) {
            
            armorClass = armorClass + equipedBoots.armorACValue();
        }
        
    }
    
    public void adjustLevelStats() {
        
        int[] amountToAdjust = RaceInitialStats.monsterClassProgression(classID);
        
        for (int i = 0; i < level; i++) {
            
            for (int j = 0; j < 7; j++) {
                
                stats[j] += amountToAdjust[j];
            }
        }
    }
    
    public int experienceValue() {
        
        int experienceToReturn;
        
        experienceToReturn = (level * 10) + ((level * 3) / 2);
        return experienceToReturn;
    }
    
    public void checkStatusEffect() {
        
        for (int i = statusEffects.size() - 1; i >= 0;i--) {
            if (statusEffects.get(i).duration <= 0) {
                if (!statusEffects.get(i).helpfull) {
                activeStats[statusEffects.get(i).statEffected] = activeStats[statusEffects.get(i).statEffected] +
                            statusEffects.get(i).statusEffectStrength;
                }
                if (statusEffects.get(i).helpfull) {
                    activeStats[statusEffects.get(i).statEffected] = activeStats[statusEffects.get(i).statEffected] -
                            statusEffects.get(i).statusEffectStrength;
                }
                statusEffects.remove(i);
            }
            else if (!statusEffects.isEmpty()) {
            //If the status is not applied, apply the unique type
            if (!statusEffects.get(i).applied) {
                //Apply effect on atributes
                if (statusEffects.get(i).statusEffectType == 0) {
                    
                    if (!statusEffects.get(i).helpfull) {
                    activeStats[statusEffects.get(i).statEffected] = activeStats[statusEffects.get(i).statEffected] -
                            statusEffects.get(i).statusEffectStrength;
                    statusEffects.get(i).applied = true;
                    }
                    else if (statusEffects.get(i).helpfull) {
                    activeStats[statusEffects.get(i).statEffected] = activeStats[statusEffects.get(i).statEffected] +
                            statusEffects.get(i).statusEffectStrength;
                    statusEffects.get(i).applied = true;
                    }
                }
            }
            
            
            else if(statusEffects.get(i).applied) {
                statusEffects.get(i).duration--;
                
            }
            }
        }
    }
    
    void giveWeapon() {
        
        WeaponGeneration tempGen = new WeaponGeneration();
        creatureEquipedWeapon = tempGen.returnMonsterWeapon(level);
        
    }
}