package roguelike.character;

/* Races master list:
 * Human 0
 * Elf 1
 * Orc 2
 * FlyingNun 3
 * Goblin 4
 * Wight 5
 * 
 * Stats Reference:
 * health, mana, strength, dexterity, endurance, intelligence, wisdom
 */
public class RaceInitialStats {
    
    public static int[] startingStats(int raceID) {
        
        int[] initialStat = new int[7];
        
        switch (raceID) {
            case 0:
                initialStat[0] = 50;
                initialStat[1] = 15;
                initialStat[2] = 5;
                initialStat[3] = 5;
                initialStat[4] = 5;
                initialStat[5] = 5;
                initialStat[6] = 5;
                return initialStat;
            case 1:
                initialStat[0] = 40;
                initialStat[1] = 25;
                initialStat[2] = 4;
                initialStat[3] = 6;
                initialStat[4] = 5;
                initialStat[5] = 7;
                initialStat[6] = 7;
                return initialStat;
            case 2:
                initialStat[0] = 60;
                initialStat[1] = 5;
                initialStat[2] = 7;
                initialStat[3] = 6;
                initialStat[4] = 7;
                initialStat[5] = 1;
                initialStat[6] = 2;
                return initialStat;
            case 3:
                initialStat[0] = 35;
                initialStat[1] = 15;
                initialStat[2] = 2;
                initialStat[3] = 2;
                initialStat[4] = 2;
                initialStat[5] = 2;
                initialStat[6] = 2;
                return initialStat;
            case 4:
                initialStat[0] = 20;
                initialStat[1] = 0;
                initialStat[2] = 5;
                initialStat[3] = 5;
                initialStat[4] = 3;
                initialStat[5] = 0;
                initialStat[6] = 0;
                return initialStat;
            case 5:
                initialStat[0] = 50;
                initialStat[1] = 15;
                initialStat[2] = 10;
                initialStat[3] = 5;
                initialStat[4] = 5;
                initialStat[5] = 5;
                initialStat[6] = 5;
                return initialStat;
        }
        return null;
    }
    
    public static String randomGoblinNames(int rand) {
        
        String[] nameArray = { "Glogdor", "Rackpan", "Betk", "Bugz", "Bzom", "Dotusg",
            "Gozabm", "Karunt", "Keg", "Ktegaz", "Kun", "Mus",
            
    };
        
        return nameArray[rand];
    }
    
    public static String randomGhostNames(int rand) {
        
        String[] nameArray = { "Dusa", "Duan", "Dumnonos", "Dusan", "Drogo", 
            "Hudd", "Hugh", "Hugo", "Huib", "Huppert", "Shen", "Kotori"
            
    };
        
        return nameArray[rand];
    }
    /*
     * This returns the amount of a stat that a monster would get upon levelup,
     * so if a monster was level 5, you'd multiply the values in the progression
     * array, and add that to their base stats. Special monsters could be done
     * seperately.
     */
    public static int[] monsterClassProgression(int classID) {
        //health, mana, strength, dexterity, endurance, intelligence, wisdom
        //warrior, rogue wizard
        int[] progressStats = new int[7];
        
        switch(classID) {
            //Warrior
            case 0:
                progressStats[0] = 0;
                progressStats[1] = 0;
                progressStats[2] = 3;
                progressStats[3] = 1;
                progressStats[4] = 1;
                progressStats[5] = 0;
                progressStats[6] = 0;
                return progressStats;
                
            //Rogue
            case 1:
                progressStats[0] = 0;
                progressStats[1] = 0;
                progressStats[2] = 1;
                progressStats[3] = 3;
                progressStats[4] = 1;
                progressStats[5] = 0;
                progressStats[6] = 0;
                return progressStats;
                
            //Wizard
            case 2:
                progressStats[0] = 0;
                progressStats[1] = 0;
                progressStats[2] = 0;
                progressStats[3] = 0;
                progressStats[4] = 1;
                progressStats[5] = 3;
                progressStats[6] = 1;
                return progressStats;
        }
        return progressStats;
    }
    
}
