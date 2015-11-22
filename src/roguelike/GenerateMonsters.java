package roguelike;

import java.awt.Point;
import java.util.ArrayList;

public class GenerateMonsters {
    
    static void populateMaps(ArrayList<Map> mapList) {
        
        //This loop will fill up each map with monsters, and eventually creatures.
        for(int i = 0; i < mapList.size();i++) {
            
            if(mapList.get(i).visited && !mapList.get(i).monsterGenerated) {
            
                int monsterAmount = mapList.get(i).zoneInfluence / 3;
                System.out.println("Generating monster for map: " + i);
                for (int j = 0; j < monsterAmount; j++) {
                
                    determineCreatureType(mapList.get(i));
                }
                
                mapList.get(i).monsterGenerated = true;
            }
        }
    }
    
    static void determineCreatureType(Map map) {
        
        int[] temp = map.possibleMonsters();
        int rand = (int) Math.random() * temp.length;
        int monsterToUse = temp[rand];
        
        switch(monsterToUse) {
        
            case 0:
                //goblin
                map.creatureArrayList.add(MonsterGoblin.generateGoblin(map.xMax, map.yMax, map));
                
                
            case 1:
                //Wight/Ghost
                map.creatureArrayList.add(MonsterWight.generateWight(map.xMax, map.yMax, map));
                
        }
        
    }
}
