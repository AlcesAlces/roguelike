package roguelike.magic;

import java.awt.Point;

public class MagicSpells {
    
    public int baseDamage;
    public int baseAccuracy;
    public int baseCost;
    int idNumber;
    public String name;
    String description;
    public boolean targetable;
    
    /* Keeping this as a stardard object constructor for now, later we might be
     * able to do special things like add damage checks, or other cool stuff like
     * that by using static methods lower in the code.
     */
    public MagicSpells(int baseDamage, int baseAccuracy, int baseCost, int idNumber, String name,
            String description, boolean targetable, boolean AOE) {
        this.baseDamage = baseDamage;
        this.baseAccuracy = baseAccuracy;
        this.baseCost = baseCost;
        this.idNumber = idNumber;
        this.name = name;
        this.description = description;
        this. targetable = targetable;
    }
    
    public String damageToString() {
        
        String damageString = "" + baseDamage;
        return damageString;
    }
    
    public String accuracyToString() {
        
        String accuracyString = "" + baseAccuracy;
        return accuracyString;
    }
    
    public String costToString() {
        
        String costToString = "" + baseCost;
        return costToString;
    }
}


