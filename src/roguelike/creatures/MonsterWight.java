package roguelike.creatures;

import java.awt.Point;
import net.slashie.libjcsi.CSIColor;
import roguelike.character.RaceInitialStats;
import roguelike.map.Map;

public class MonsterWight extends Creature{
    
    //Overloaded constructor for rebuilding monster types
    public MonsterWight(int mapIdTemp, int[] stats, int weakTemp, int strongTemp,
            int classTemp, int levelTemp, int expTemp, int expReqTemp, int currentHpTemp,
            int baseHpTemp, int baseManaTemp, int raceidTemp, int regenIndexTemp,
            int regenedTemp, int manaRegenIndexTemp, int manaRegenedTemp, char tempSymbol,
            int pointXTemp, int pointYTemp, String tempName){
        super (stats, new Point(pointXTemp,pointYTemp),raceidTemp,0,tempName);
        
        this.color = CSIColor.WHITE;
        this.stats = stats;
        this.weaponTypeWeakness = weakTemp;
        this.weaponTypeStrength = strongTemp;
        this.classID = classTemp;
        this.level = levelTemp;
        this.experience = expTemp;
        this.experienceRequired = expReqTemp;
        this.currentHealth = currentHpTemp;
        this.baseHealth = baseHpTemp;
        this.baseMana = baseManaTemp;
        this.raceID = raceidTemp;
        this.healthRegenIndex = regenIndexTemp;
        this.healthRegened = regenedTemp;
        this.manaRegenIndex = manaRegenIndexTemp;
        this.manaRegened = manaRegenedTemp;
        this.symbol = tempSymbol;
        this.creaturePoint = new Point(pointXTemp, pointYTemp);
        this.name = tempName;
        this.mapID = mapIdTemp;
        
    }
    
    public MonsterWight(int[] stats, Point monsterPoint, int raceID, int actionPoints,
            String name) {
        super(stats, monsterPoint, raceID, actionPoints, name);
        this.color = CSIColor.WHITE;
        this.symbol = 'g';
        this.raceID = 5;
        
    }
    
    public static MonsterWight generateWight(int sizeX, int sizeY, Map map) {
        
        int randClass = (int) (Math.random() * 3);
            int randPointX = (int) (Math.random() * sizeX);
            int randPointY = (int) (Math.random() * sizeY);
            int[] stats = new int[7];
            int[] statToAdd;
            Point monsterPoint = new Point(randPointX, randPointY);
            
            for (int j = 0; j < 7; j++) {
                
                statToAdd = (RaceInitialStats.monsterClassProgression(randClass));
                stats[j] = (statToAdd[j]);
            }
            
            for (int j =0; j < 7; j++) {
                stats[j] = (stats[j] + RaceInitialStats.startingStats(5)[j]);
            }
            
            MonsterWight monster = new MonsterWight(stats, monsterPoint, 4, 0, 
                    RaceInitialStats.randomGhostNames((int) (Math.random() * 11)));
            monster.classID = randClass;
            monster.baseHealth = monster.stats[0];
            monster.level = map.zoneInfluence / 5;
            monster.giveWeapon();
            monster.adjustLevelStats();
            monster.statInitializer();
            monster.updateStats();
            monster.statInitializer();
            monster.mapID = map.mapID;
            
            return monster;
    }
    
}
