package roguelike.creatures;

import java.awt.Point;
import net.slashie.libjcsi.CSIColor;
import roguelike.character.RaceInitialStats;
import roguelike.map.Map;

public class MonsterGoblin extends Creature{
    
    public MonsterGoblin(int mapIdTemp, int[] stats, int weakTemp, int strongTemp,
            int classTemp, int levelTemp, int expTemp, int expReqTemp, int currentHpTemp,
            int baseHpTemp, int baseManaTemp, int raceidTemp, int regenIndexTemp,
            int regenedTemp, int manaRegenIndexTemp, int manaRegenedTemp, char tempSymbol,
            int pointXTemp, int pointYTemp, String tempName){
        super (stats, new Point(pointXTemp,pointYTemp),raceidTemp,0,tempName);
        
        this.color = CSIColor.BURGUNDY;
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
    
    public MonsterGoblin(int[] stats, Point monsterPoint, int raceID, int actionPoints,
            String name) {
        super(stats, monsterPoint, raceID, actionPoints, name);
        this.color = CSIColor.BURGUNDY;
        this.symbol = 'G';
        this.raceID = 4;
        // -1 in this case means that this mob likes every map.
    }
    
    public static MonsterGoblin generateGoblin(int sizeX, int sizeY, Map map) {
        
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
            stats[j] = (stats[j] + RaceInitialStats.startingStats(4)[j]);
        }
            
        MonsterGoblin goblin = new MonsterGoblin(stats, monsterPoint, 4, 0, 
                RaceInitialStats.randomGoblinNames((int) (Math.random() * 11)));
        goblin.classID = randClass;
        goblin.baseHealth = goblin.stats[0];
        goblin.level = map.zoneInfluence / 5;
        goblin.adjustLevelStats();
        goblin.statInitializer();
        goblin.updateStats();
        goblin.statInitializer();
        goblin.giveWeapon();
        goblin.mapID = map.mapID;
        
        return goblin;
    }
}
