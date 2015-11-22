package roguelike;

public class CheckForDeath {
    
    public static void checkForMonsterDeath(Map map, Player player, EventLog eventLog) {
        
        if (!map.creatureArrayList.isEmpty()) {
        
        for (int i = 0; i < map.creatureArrayList.size();i++) {
            
                if (map.creatureArrayList.get(i).activeStats[0] <= 0) {
                    
                    int experienceToAward = map.creatureArrayList.get(i).experienceValue();
                    
                    eventLog.manageEventLog(map.creatureArrayList.get(i).name + 
                            " has died.");
                    eventLog.manageEventLog("You gain " + experienceToAward + " experience");
                    player.experience = player.experience + experienceToAward;
                    map.deadCreatureList.add(map.creatureArrayList.get(i));
                    map.creatureArrayList.remove(i);
                }
            }
    }
    }
}
