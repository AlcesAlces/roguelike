package roguelike;

public class Combat {
    // Equipment Stats 1-8: AC, AP, Dodge, Hit, Magic Defense, Magic Hit, Potency
    // Melee bonuses: Power Attack, Double Attack
    
    static void meleeCombat(Creature attacker, Creature defender, EventLog eventLog) {
        
        int checkHit = (int) (Math.random() * attacker.equipStats[3]);
        int checkDodge = (int) (Math.random() * defender.equipStats[2]);
        int checkAP = (int) (Math.random() * attacker.equipStats[1]);
        int checkAC = (int) (Math.random() * defender.equipStats[0]);
        int damageDone;
        
        if (attacker.weaponTypeEquiped == defender.weaponTypeWeakness) {
            checkAP = checkAP + (int) ((Math.random() * attacker.equipStats[1]) / 2);
        }
        
        if (attacker.weaponTypeEquiped == defender.weaponTypeStrength) {
            
            checkAP = checkAP - (int) ((Math.random() * attacker.equipStats[1]) / 2);
        }
        
        if (checkHit >= checkDodge) {
            //Landed a hit
            damageDone = checkAP - checkAC;
            if (damageDone < 0) {
                damageDone = 0;
            }
            defender.activeStats[0] = defender.activeStats[0] - damageDone;
            
            if(attacker.creatureEquipedWeapon != null) {
            eventLog.manageEventLog(attacker.name + " inflicts " + damageDone + " " + 
                    attacker.creatureEquipedWeapon.weaponDamageTypeToString() +" damage to " + defender.name);
            }
            else{
                eventLog.manageEventLog(attacker.name + " inflicts " + damageDone + " " + 
                    "blunt" +" damage to " + defender.name);
            }
        }
        
        else {
            //Defender dodges
            eventLog.manageEventLog(defender.name + " dodges!");
        }
    
    }
    
    // Equipment Stats 1-8: AC, AP, Dodge, Hit, Magic Defense, Magic Attack, Potency
    // Stats 1- 7: health, mana, strength, dexterity, endurance, intelligence, wisdom
    static void magicCombat(Creature attacker, Creature defender, EventLog eventLog,
            MagicSpells spell) {
        
        int randHit = (int) (Math.random() * spell.baseAccuracy + attacker.activeStats[5] / 2);
        int randDamage = (int) (Math.random() * spell.baseDamage + (attacker.equipStats[5] / 3));
        int randDefense = (int) (Math.random() * defender.equipStats[4] + defender.activeStats[6]);
        int randDodge = (int) (Math.random() * (defender.equipStats[4] / 2) + defender.activeStats[3] / 2);
        int spellCost = spell.baseCost - (attacker.activeStats[6] / 5);
        int damageDealt = 0;
        
        if (attacker.activeStats[5] > defender.activeStats[6]) {
            //attack has a chance to go through magical defenses.
            if (randHit > randDodge) {
                damageDealt = randDamage - randDefense;
                if (damageDealt < 0) {
                    damageDealt = 0;
                }
                eventLog.manageEventLog(attacker.name + " hits " + defender.name + 
                        " with " + spell.name + " for " + damageDealt + " damage.");
                defender.activeStats[0] = defender.activeStats[0] - damageDealt;
            }
            
            else {
                eventLog.manageEventLog(defender.name + " dodges " + attacker.name + "'s " +
                        spell.name);
            }
            
        }
        attacker.activeStats[1] = attacker.activeStats[1] - spellCost ;  
        attacker.actionPoints--;
    }
    
    static void applyBonusAbilityMelee(Creature attacker, Creature defender, EventLog eventLog) {
        
        int powerAttackRNG = (int) (Math.random()* (100) + 1);
        int attackerVsPowerRNG = attacker.equipStats[1] + attacker.activeStats[2];
        int doubleAttackRNG = (int) (Math.random()* (100 + 1));
        int attackerVsDoubleRNG = attacker.activeStats[3];
        int randHit = (int) (Math.random()* (attacker.equipStats[3] * 2 + 1));
        int randDodge = (int) (Math.random()* (defender.equipStats[2] * 2 + 1));
        int randAC = (int) (Math.random()* (defender.equipStats[0]/2 + 1));
        int randAP = (int) (Math.random()* (attacker.equipStats[1]/2 + 1));
        int damageDealt = 0;
        
        
        //Apply power attack bonus
        if (attacker.bonusMelee[0] == 1 && (powerAttackRNG) <= attackerVsPowerRNG) {
            
            int randBonusPower = (int) (Math.random()* (attacker.equipStats[1]) / 2 + 1);
            
                        
        if (attacker.equipStats[3] >= defender.equipStats[2]) {
            
            //Attacker has advantage when their hit is greater than def dodge.
            if (attacker.equipStats[3] >= randHit) {
                //Attacker lands a hit. Hit to be mitigated by AC.
                damageDealt =  randAP + randBonusPower - randAC;
                if (damageDealt < 0) {
                    System.out.println("Damage Done: " + damageDealt);
                    damageDealt = 0;
                }
                defender.activeStats[0] = defender.activeStats[0] - damageDealt;
                eventLog.manageEventLog(attacker.name + " power attacks " + defender.name + 
                        " for " + damageDealt + " damage");
            }
            // Attacker Misses the defender
            else {
                
                eventLog.manageEventLog(attacker.name + " misses " + defender.name);
            }
        }
        
        else { 
            //Defender gets to use dodge if attacker's hit isn't high enough.
            if (defender.equipStats[2] >= randDodge) {
                System.out.println(defender.name + " dodges!");
                eventLog.manageEventLog(defender.name + " dodges!");
                //Defender dodges.
            }
            
            else {
                // Defender's dodge failed
                if (attacker.equipStats[3] >= randHit) {
                    // Attacker won over defender, hit to be mitigated by AC.
                    damageDealt = (randAP + randBonusPower - randAC);
                    if (damageDealt < 0) {
                        System.out.println("Damage Done: " + damageDealt);
                        damageDealt = 0;
                    }
                    defender.activeStats[0] = defender.activeStats[0] - damageDealt;
                    eventLog.manageEventLog(attacker.name + " attacks " + defender.name + 
                            " for " + damageDealt + " damage");
                }
                
                else {
                    
                    eventLog.manageEventLog(attacker.name + " misses " + defender.name);
                }
            }
        }
        }
        
        else if (attacker.bonusMelee[1] == 1 && (doubleAttackRNG <= attackerVsDoubleRNG)) {
            
        }
        
        else {
            if (attacker.equipStats[3] >= defender.equipStats[2]) {
            
            //Attacker has advantage when their hit is greater than def dodge.
            if (attacker.equipStats[3] >= randHit) {
                //Attacker lands a hit. Hit to be mitigated by AC.
                damageDealt =  randAP - randAC;
                if (damageDealt < 0) {
                    System.out.println("Damage Done: " + damageDealt);
                    damageDealt = 0;
                }
                defender.activeStats[0] = defender.activeStats[0] - damageDealt;
                eventLog.manageEventLog(attacker.name + " attacks " + defender.name +
                        " for " + damageDealt + " damage");
            }
            // Attacker Misses the defender
            else {
                
                eventLog.manageEventLog(attacker.name + " misses " + defender.name);
            }
        }
        
        else { 
            //Defender gets to use dodge if attacker's hit isn't high enough.
            if (defender.equipStats[2] >= randDodge) {
                
                eventLog.manageEventLog(defender.name + " dodges!");
                //Defender dodges.
            }
            
            else {
                // Defender's dodge failed
                if (attacker.equipStats[3] >= randHit) {
                    // Attacker won over defender, hit to be mitigated by AC.
                    damageDealt = (randAP - randAC);
                    if (damageDealt < 0) {
                        System.out.println("Damage Done: " + damageDealt);
                        damageDealt = 0;
                    }
                    defender.activeStats[0] = defender.activeStats[0] - damageDealt;
                    eventLog.manageEventLog(attacker.name + " attacks " + defender.name +
                            " for " + damageDealt + " damage");
                }
                
                else {
                    
                    eventLog.manageEventLog(attacker.name + " misses " + defender.name);
                }
            }
        }
        }
    }
}
