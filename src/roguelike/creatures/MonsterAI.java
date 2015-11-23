package roguelike.creatures;

import roguelike.EventLog;
import roguelike.character.Player;
import roguelike.map.Map;

import java.awt.Point;

public class MonsterAI {
    
    MonsterAI(){
    
}
    public static void monsterPersuit(Player playerPoint, Creature monsterPoint, Map map,
                                      EventLog eventLog) {
        
        
        Point temp = new Point(0,0);
        if (playerPoint.creaturePoint.distance(monsterPoint.creaturePoint) < 50.0) {
            
            if (playerPoint.creaturePoint.x + 1 <= monsterPoint.creaturePoint.x) {
                
                temp.setLocation(monsterPoint.creaturePoint.x - 1, monsterPoint.creaturePoint.y);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    
                    monsterPoint.creaturePoint.translate(0,1);
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                monsterPoint.creaturePoint.translate(-1,0);
                }
                
                else {
                    eventLog.manageEventLog("Master fite you!");
                    System.out.println("COMBAT!");
                } 
                    
                
            }
            else if (playerPoint.creaturePoint.y + 1 <= monsterPoint.creaturePoint.y) {
                
                temp.setLocation(monsterPoint.creaturePoint.x, monsterPoint.creaturePoint.y - 1);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    monsterPoint.creaturePoint.translate(1,0);
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                monsterPoint.creaturePoint.translate(0,-1);
                }
                
                else {
                    eventLog.manageEventLog("Master fite you!");
                    System.out.println("COMBAT!");
                } 
            }
            
            else if (playerPoint.creaturePoint.y - 1 >= monsterPoint.creaturePoint.y)  {
                
                temp.setLocation(monsterPoint.creaturePoint.x, monsterPoint.creaturePoint.y + 1);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    
                    monsterPoint.creaturePoint.translate(-1,0);
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                monsterPoint.creaturePoint.translate(0, 1);
                }
                
                else {
                    eventLog.manageEventLog("Master fite you!");
                    System.out.println("COMBAT!");
                } 
            }
            
            else if (playerPoint.creaturePoint.x - 1 >= monsterPoint.creaturePoint.x) {
                
                temp.setLocation(monsterPoint.creaturePoint.x + 1, monsterPoint.creaturePoint.y);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint) ) {
                    
                    monsterPoint.creaturePoint.translate(0,-1);
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)) {
                    monsterPoint.creaturePoint.translate(1 , 0);
                }
                
                else {
                    eventLog.manageEventLog("Master fite you!");
                    System.out.println("COMBAT!");
                } 
            }
        }
    }
}

/*
 * if (playerPoint.creaturePoint.distance(monsterPoint.creaturePoint) < 50.0) {
            
            if (playerPoint.creaturePoint.x + 1 <= monsterPoint.creaturePoint.x && (Math.abs(monsterPoint.creaturePoint.x - playerPoint.creaturePoint.x) > Math.abs(monsterPoint.creaturePoint.y - playerPoint.creaturePoint.y))) {
                
                temp.setLocation(monsterPoint.creaturePoint.x - 1, monsterPoint.creaturePoint.y);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    
                    monsterPoint.creaturePoint.translate(0,1);
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                monsterPoint.creaturePoint.translate(-1,0);
                }
                
                else {
                    eventLog.manageEventLog("Master fite you!");
                    System.out.println("COMBAT!");
                } 
                    
                
            }
            else if (playerPoint.creaturePoint.y + 1 <= monsterPoint.creaturePoint.y && (Math.abs(monsterPoint.creaturePoint.y - playerPoint.creaturePoint.y) > Math.abs(monsterPoint.creaturePoint.x - playerPoint.creaturePoint.x))) {
                
                temp.setLocation(monsterPoint.creaturePoint.x, monsterPoint.creaturePoint.y - 1);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    monsterPoint.creaturePoint.translate(1,0);
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                monsterPoint.creaturePoint.translate(0,-1);
                }
                
                else {
                    eventLog.manageEventLog("Master fite you!");
                    System.out.println("COMBAT!");
                } 
            }
            
            else if (playerPoint.creaturePoint.y - 1 >= monsterPoint.creaturePoint.y && (Math.abs(monsterPoint.creaturePoint.y - playerPoint.creaturePoint.y) > Math.abs(monsterPoint.creaturePoint.x - playerPoint.creaturePoint.x)))  {
                
                temp.setLocation(monsterPoint.creaturePoint.x, monsterPoint.creaturePoint.y + 1);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    
                    monsterPoint.creaturePoint.translate(-1,0);
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                monsterPoint.creaturePoint.translate(0, 1);
                }
                
                else {
                    eventLog.manageEventLog("Master fite you!");
                    System.out.println("COMBAT!");
                } 
            }
            
            else if (playerPoint.creaturePoint.x - 1 >= monsterPoint.creaturePoint.x && (Math.abs(monsterPoint.creaturePoint.x - playerPoint.creaturePoint.x) > Math.abs(monsterPoint.creaturePoint.y - playerPoint.creaturePoint.y))) {
                
                temp.setLocation(monsterPoint.creaturePoint.x + 1, monsterPoint.creaturePoint.y);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint) ) {
                    
                    monsterPoint.creaturePoint.translate(0,-1);
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)) {
                    monsterPoint.creaturePoint.translate(1 , 0);
                }
                
                else {
                    eventLog.manageEventLog("Master fite you!");
                    System.out.println("COMBAT!");
                } 
            }
        }
 */
