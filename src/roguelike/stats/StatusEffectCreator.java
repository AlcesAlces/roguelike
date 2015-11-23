package roguelike.stats;

import java.util.ArrayList;

public class StatusEffectCreator {
    
    
    
    static StatusEffects returnStatusEffect(int index, int strength) {
        
        ArrayList<StatusEffects> effectArray = new ArrayList();
        // Effect Type Key:
        // 0: Attribute, 1: Health, 2: Bleed, 3: Curse
        
        //This generates 0-9 in the array.
        generateAttributeEffects(strength, effectArray);
        
        StatusEffects effect11 = new StatusEffects(1,strength, false, "Bleed + " + strength);
        effectArray.add(effect11);
        effect11.duration = (strength * 10);
        StatusEffects effect12 = new StatusEffects(2, strength, false, "Curse equipment " + strength);
        effectArray.add(effect12);
        effect12.duration = (strength * 10);
        
        return effectArray.get(index);
    }
    
    static void generateAttributeEffects(int strength, ArrayList<StatusEffects> effectArray) {
        
        StatusEffects effect01 = new StatusEffects(0,strength, false, "Damage Strength +" + strength);
        effect01.statEffected = 2;
        effect01.duration = (strength * 10);
        effectArray.add(effect01);
        StatusEffects effect02 = new StatusEffects(0,strength, true, "Bolster Strength +" + strength);
        effect02.statEffected = 2;
        effect02.duration = (strength * 10);
        effectArray.add(effect02);
        StatusEffects effect03 = new StatusEffects(0,strength, false, "Damage Dexterity +" + strength);
        effect03.statEffected = 3;
        effect03.duration = (strength * 10);
        effectArray.add(effect03);
        StatusEffects effect04 = new StatusEffects(0,strength, true, "Boster Dexterity +" + strength);
        effect04.statEffected = 3;
        effect04.duration = (strength * 10);
        effectArray.add(effect04);
        StatusEffects effect05 = new StatusEffects(0,strength, false, "Damage Endurance +" + strength);
        effect05.statEffected = 4;
        effect05.duration = (strength * 10);
        effectArray.add(effect05);
        StatusEffects effect06 = new StatusEffects(0,strength, true, "Boster Endurance +" + strength);
        effect06.statEffected = 4;
        effect06.duration = (strength * 10);
        effectArray.add(effect06);
        StatusEffects effect07 = new StatusEffects(0,strength, false, "Damage Intelligence +" + strength);
        effect07.statEffected = 5;
        effect07.duration = (strength * 10);
        effectArray.add(effect07);
        StatusEffects effect08 = new StatusEffects(0,strength, true, "Boster Intelligence +" + strength);
        effect08.statEffected = 5;
        effect08.duration = (strength * 10);
        effectArray.add(effect08);
        StatusEffects effect09 = new StatusEffects(0,strength, false, "Damage Wisdom +" + strength);
        effect09.statEffected = 6;
        effect09.duration = (strength * 10);
        effectArray.add(effect09);
        StatusEffects effect10 = new StatusEffects(0,strength, true, "Bolster Wisdom +" + strength);
        effect10.statEffected = 6;
        effect10.duration = (strength * 10);
        effectArray.add(effect10);
    }
}

