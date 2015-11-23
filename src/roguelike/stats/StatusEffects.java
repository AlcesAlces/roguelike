package roguelike.stats;

public class StatusEffects {
    
    // Effect Type Key:
    // 0: Attribute, 1: Bleed, 2: Curse
    public int statusEffectType;
    public int statusEffectStrength;
    public int statEffected;
    public int duration;
    public boolean helpfull;
    public boolean applied = false;
    String statusEffectName;
    
    StatusEffects(int statusEffectType, int statusEffectStrength, boolean helpfull, 
            String statusEffectName) {
        
        this.helpfull = helpfull;
        this.statusEffectType = statusEffectType;
        this.statusEffectStrength = statusEffectStrength;
        this.statusEffectName = statusEffectName;
    }
}
