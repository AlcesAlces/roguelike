package roguelike;

public class StatusEffects {
    
    // Effect Type Key:
    // 0: Attribute, 1: Bleed, 2: Curse
    int statusEffectType;
    int statusEffectStrength;
    int statEffected;
    int duration;
    boolean helpfull;
    boolean applied = false;
    String statusEffectName;
    
    StatusEffects(int statusEffectType, int statusEffectStrength, boolean helpfull, 
            String statusEffectName) {
        
        this.helpfull = helpfull;
        this.statusEffectType = statusEffectType;
        this.statusEffectStrength = statusEffectStrength;
        this.statusEffectName = statusEffectName;
    }
}
