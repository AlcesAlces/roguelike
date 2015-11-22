package roguelike;

public class CharacterClass {
    
    /* Races master list:
    * Human 00
     * Elf 01
    * Orc 02
    * FlyingNun 03
    * Goblin 04
    * 
    * Stats Reference:
    * health, mana, strength, dexterity, endurance, intelligence, wisdom
    */
    
    public static int[] classSkillBonuses(int classID) {
        
        int[] statsToReturn = new int[7];
        
        switch (classID) {
            case 00:
                statsToReturn[0] = 5;
                statsToReturn[1] = 0;
                statsToReturn[2] = 2;
                statsToReturn[3] = 0;
                statsToReturn[4] = 2;
                statsToReturn[5] = 0;
                statsToReturn[6] = 0;
                return statsToReturn;
                
            case 01:
                statsToReturn[0] = 2;
                statsToReturn[1] = 1;
                statsToReturn[2] = 1;
                statsToReturn[3] = 2;
                statsToReturn[4] = 0;
                statsToReturn[5] = 0;
                statsToReturn[6] = 0;
                return statsToReturn;
                
            case 02:
                statsToReturn[0] = 0;
                statsToReturn[1] = 5;
                statsToReturn[2] = 0;
                statsToReturn[3] = 0;
                statsToReturn[4] = 0;
                statsToReturn[5] = 3;
                statsToReturn[6] = 3;
                return statsToReturn;
                
        }
        
        return null;
    }
    
    public static String classIDToString(int classID) {
        
        switch (classID) {
            case 00:
                return "Warrior";
            case 01:
                return "Rogue";
            case 02:
                return "Wizard";
        }
        return null;
    }
}
