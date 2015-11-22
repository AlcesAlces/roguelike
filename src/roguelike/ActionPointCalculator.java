package roguelike;

public class ActionPointCalculator {
    //Dex is the 3rd stat
    static void calculateTurn(Map map, Player player) {
        
        int greatestDex = player.stats[3];
        
        for (int i = 0; i < map.creatureArrayList.size(); i++) {
            
            if (map.creatureArrayList.get(i).stats[3] >= greatestDex) {
                greatestDex = map.creatureArrayList.get(i).stats[3];
            }
        }
        
        int tempRand = (int) (Math.random() * 6) + 1;
        
        if (player.stats[3] >= greatestDex) {
            player.actionPoints = tempRand;
        }
        
        else if (player.stats[3] < greatestDex) {
            player.actionPoints = (tempRand - 1);
        }
        
        for (int i = 0; i < map.creatureArrayList.size(); i++) {
        
            if (map.creatureArrayList.get(i).stats[3] >= greatestDex) {
                
                map.creatureArrayList.get(i).actionPoints = tempRand;
            }
            
            else if (map.creatureArrayList.get(i).stats[3] < greatestDex) {
                map.creatureArrayList.get(i).actionPoints = tempRand - 1;
            }
    }
        
    }
    
}
