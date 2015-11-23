package roguelike.creatures;

import roguelike.EventLog;
import roguelike.character.Player;
import roguelike.combat.Combat;
import roguelike.map.Map;

import java.awt.Point;
import java.util.ArrayList;

public class MonsterAIPrototype {
    
    static ArrayList<Point> firstPath = new ArrayList();
    static ArrayList<Point> secondPath = new ArrayList();
    
    public static void findBestRoute(Map map, Player player, EventLog eventLog) {
        
        for (int i = 0; i < map.creatureArrayList.size();i++) {
            
            if (map.creatureArrayList.get(i).actionPoints > 0 && 
                    map.creatureArrayList.get(i).activeStats[0] > 0) {
            
            firstPath.clear();
            secondPath.clear();
            
            Point tempMonster = new Point();
            tempMonster.setLocation(map.creatureArrayList.get(i).creaturePoint);
            
            if (map.creatureArrayList.get(i).actionPoints > 0) {
            firstPathTest(tempMonster, player, map, eventLog);
            tempMonster.setLocation(map.creatureArrayList.get(i).creaturePoint);
            secondPathTest(tempMonster, player, map, eventLog);
            }
            
            if (firstPath.size() > secondPath.size() && !secondPath.isEmpty())  {
                map.creatureArrayList.get(i).creaturePoint.setLocation(secondPath.get(0));
                map.creatureArrayList.get(i).actionPoints--;
            }
            
            else if (firstPath.size() < secondPath.size() && !firstPath.isEmpty()) {
                map.creatureArrayList.get(i).creaturePoint.setLocation(firstPath.get(0));
                map.creatureArrayList.get(i).actionPoints--;
            }
            
            else if (firstPath.size() == secondPath.size() && (!firstPath.isEmpty() && !secondPath.isEmpty())){
                
                map.creatureArrayList.get(i).creaturePoint.setLocation(firstPath.get(0));
                map.creatureArrayList.get(i).actionPoints--;
            }
            
            else {
                Combat.meleeCombat(map.creatureArrayList.get(i), player, eventLog);
            }
            
            }
            
        }
    }
    // The idea here is that it runs through a few scenarios where it
    // calculates the best route, like A*.
    public static void firstPathTest(Point monsterPoint, Player playerPoint, Map map, EventLog eventLog) {
        
        Point temp = new Point(0,0);
        Point temp2 = new Point(0,0);
        
        for (int i = 0; i < 50; i++) {
            
            if (playerPoint.creaturePoint.x + 1 <= monsterPoint.x && (Math.abs(monsterPoint.x - playerPoint.creaturePoint.x) >= Math.abs(monsterPoint.y - playerPoint.creaturePoint.y))) {
                
                temp.setLocation(monsterPoint.x - 1, monsterPoint.y);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    
                    temp2.setLocation(monsterPoint.x, monsterPoint.y + 1);
                    
                    if (map.checkElements(temp2)) {
                        monsterPoint.translate(0, 1);
                    }
                    else if (!map.checkElements(temp2)) {
                        
                        temp2.translate(1, -1);
                        
                        if (map.checkElements(temp2)) {
                            monsterPoint.translate(1, 0);
                        }
                        
                        else if (!map.checkElements(temp2)) {
                            temp2.translate(-1, -1);
                            
                            if (map.checkElements(temp2)) {
                                monsterPoint.translate(-1,0);
                            }
                            else if (!map.checkElements(temp2)) {
                                System.out.println("Halp, I'm stuck");
                            }
                            
                        }
                        
                    }
                    firstPath.add(new Point(monsterPoint));
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                
                    monsterPoint.translate(-1, 0);
                    firstPath.add(new Point(monsterPoint));
                
                }
                // break here because the player is found
                else {
                    break;
                } 
            }
            else if (playerPoint.creaturePoint.y + 1 <= monsterPoint.y && (Math.abs(monsterPoint.y - playerPoint.creaturePoint.y) >= Math.abs(monsterPoint.x - playerPoint.creaturePoint.x))) {
                
                temp.setLocation(monsterPoint.x, monsterPoint.y - 1);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    
                    temp2.setLocation(monsterPoint.x, monsterPoint.y + 1);
                    
                    if (map.checkElements(temp2)) {
                        monsterPoint.translate(0, 1);
                    }
                    else if (!map.checkElements(temp2)) {
                        
                        temp2.translate(-1, -1);
                        
                        if (map.checkElements(temp2)) {
                            monsterPoint.translate(-1, 0);
                        }
                        
                        else if (!map.checkElements(temp2)) {
                            temp2.translate(2,0);
                            
                            if (map.checkElements(temp2)) {
                                monsterPoint.translate(1,0);
                            }
                            else if (!map.checkElements(temp2)) {
                                System.out.println("Halp, I'm stuck");
                            }
                            
                        }
                        
                    }
                    firstPath.add(new Point(monsterPoint));
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                
                    monsterPoint.translate(0, -1);
                    firstPath.add(new Point(monsterPoint));
                }
                
                else {
                    break;
                }
            }
            
            else if (playerPoint.creaturePoint.y - 1 >= monsterPoint.y && (Math.abs(monsterPoint.y - playerPoint.creaturePoint.y) >= Math.abs(monsterPoint.x - playerPoint.creaturePoint.x)))  {
                
                temp.setLocation(monsterPoint.x, monsterPoint.y + 1);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    
                    temp2.setLocation(monsterPoint.x, monsterPoint.y - 1);
                    
                    if (map.checkElements(temp2)) {
                        monsterPoint.translate(0, -1);
                    }
                    else if (!map.checkElements(temp2)) {
                        
                        temp2.translate(1, 1);
                        
                        if (map.checkElements(temp2)) {
                            monsterPoint.translate(1, 0);
                        }
                        
                        else if (!map.checkElements(temp2)) {
                            temp2.translate(-1, 0);
                            
                            if (map.checkElements(temp2)) {
                                monsterPoint.translate(-1,0);
                            }
                            else if (!map.checkElements(temp2)) {
                                System.out.println("Halp, I'm stuck");
                            }
                            
                        }
                        
                    }
                    firstPath.add(new Point(monsterPoint));
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                    
                    monsterPoint.translate(0,1);
                    firstPath.add(new Point(monsterPoint));
                }
                
                else {
                    break;
                }
            }
            
            else if (playerPoint.creaturePoint.x - 1 >= monsterPoint.x && (Math.abs(monsterPoint.x - playerPoint.creaturePoint.x) >= Math.abs(monsterPoint.y - playerPoint.creaturePoint.y))) {
                
                temp.setLocation(monsterPoint.x + 1, monsterPoint.y);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint) ) {
                    
                    temp2.setLocation(monsterPoint.x - 1, monsterPoint.y);
                    
                    if (map.checkElements(temp2)) {
                        monsterPoint.translate(-1, 0);
                    }
                    else if (!map.checkElements(temp2)) {
                        
                        temp2.translate(1, -1);
                        
                        if (map.checkElements(temp2)) {
                            monsterPoint.translate(0, -1);
                        }
                        
                        else if (!map.checkElements(temp2)) {
                            temp2.translate(0, 2);
                            
                            if (map.checkElements(temp2)) {
                                monsterPoint.translate(0,1);
                            }
                            else if (!map.checkElements(temp2)) {
                                System.out.println("Halp, I'm stuck");
                            }
                            
                        }
                        
                    }
                    firstPath.add(new Point(monsterPoint));
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)) {
                    
                    monsterPoint.translate(1,0);
                    firstPath.add(new Point(monsterPoint));
                }
                
                else {
                    break;
                }
            }
        }
    }
    
            public static void secondPathTest(Point monsterPoint, Player playerPoint, Map map, EventLog eventLog) {
        
        Point temp = new Point(0,0);
        Point temp2 = new Point(0,0);
        
        for (int i = 0; i < 50; i++) {
            
            if (playerPoint.creaturePoint.x + 1 <= monsterPoint.x && (Math.abs(monsterPoint.x - playerPoint.creaturePoint.x) >= Math.abs(monsterPoint.y - playerPoint.creaturePoint.y))) {
                
                temp.setLocation(monsterPoint.x - 1, monsterPoint.y);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    
                    temp2.setLocation(monsterPoint.x, monsterPoint.y + 1);
                    
                    if (map.checkElements(temp2)) {
                        monsterPoint.translate(0, 1);
                    }
                    else if (!map.checkElements(temp2)) {
                        
                        temp2.translate(1, -1);
                        
                        if (map.checkElements(temp2)) {
                            monsterPoint.translate(1, 0);
                        }
                        
                        else if (!map.checkElements(temp2)) {
                            temp2.translate(-1, -1);
                            
                            if (map.checkElements(temp2)) {
                                monsterPoint.translate(-1,0);
                            }
                            else if (!map.checkElements(temp2)) {
                                System.out.println("Halp, I'm stuck");
                            }
                            
                        }
                        
                    }
                    secondPath.add(new Point(monsterPoint));
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                
                    monsterPoint.translate(-1, 0);
                    secondPath.add(new Point(monsterPoint));
                
                }
                // break here because the player is found
                else {
                    break;
                } 
            }
            else if (playerPoint.creaturePoint.y + 1 <= monsterPoint.y && (Math.abs(monsterPoint.y - playerPoint.creaturePoint.y) >= Math.abs(monsterPoint.x - playerPoint.creaturePoint.x))) {
                
                temp.setLocation(monsterPoint.x, monsterPoint.y - 1);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    
                    temp2.setLocation(monsterPoint.x, monsterPoint.y + 1);
                    
                    if (map.checkElements(temp2)) {
                        monsterPoint.translate(0, 1);
                    }
                    else if (!map.checkElements(temp2)) {
                        
                        temp2.translate(-1, -1);
                        
                        if (map.checkElements(temp2)) {
                            monsterPoint.translate(-1, 0);
                        }
                        
                        else if (!map.checkElements(temp2)) {
                            temp2.translate(2,0);
                            
                            if (map.checkElements(temp2)) {
                                monsterPoint.translate(1,0);
                            }
                            else if (!map.checkElements(temp2)) {
                                System.out.println("Halp, I'm stuck");
                            }
                            
                        }
                        
                    }
                    secondPath.add(new Point(monsterPoint));
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                
                    monsterPoint.translate(0, -1);
                    secondPath.add(new Point(monsterPoint));
                }
                
                else {
                    break;
                }
            }
            
            else if (playerPoint.creaturePoint.y - 1 >= monsterPoint.y && (Math.abs(monsterPoint.y - playerPoint.creaturePoint.y) >= Math.abs(monsterPoint.x - playerPoint.creaturePoint.x)))  {
                
                temp.setLocation(monsterPoint.x, monsterPoint.y + 1);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint)) {
                    
                    temp2.setLocation(monsterPoint.x, monsterPoint.y - 1);
                    
                    if (map.checkElements(temp2)) {
                        monsterPoint.translate(0, -1);
                    }
                    else if (!map.checkElements(temp2)) {
                        
                        temp2.translate(1, 1);
                        
                        if (map.checkElements(temp2)) {
                            monsterPoint.translate(1, 0);
                        }
                        
                        else if (!map.checkElements(temp2)) {
                            temp2.translate(-1, 0);
                            
                            if (map.checkElements(temp2)) {
                                monsterPoint.translate(-1,0);
                            }
                            else if (!map.checkElements(temp2)) {
                                System.out.println("Halp, I'm stuck");
                            }
                            
                        }
                        
                    }
                    secondPath.add(new Point(monsterPoint));
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)){
                    
                    monsterPoint.translate(0,1);
                    secondPath.add(new Point(monsterPoint));
                }
                
                else {
                    break;
                }
            }
            
            else if (playerPoint.creaturePoint.x - 1 >= monsterPoint.x && (Math.abs(monsterPoint.x - playerPoint.creaturePoint.x) >= Math.abs(monsterPoint.y - playerPoint.creaturePoint.y))) {
                
                temp.setLocation(monsterPoint.x + 1, monsterPoint.y);
                
                if (!map.checkElements(temp) && !temp.equals(playerPoint.creaturePoint) ) {
                    
                    temp2.setLocation(monsterPoint.x - 1, monsterPoint.y);
                    
                    if (map.checkElements(temp2)) {
                        monsterPoint.translate(-1, 0);
                    }
                    else if (!map.checkElements(temp2)) {
                        
                        temp2.translate(1, -1);
                        
                        if (map.checkElements(temp2)) {
                            monsterPoint.translate(0, -1);
                        }
                        
                        else if (!map.checkElements(temp2)) {
                            temp2.translate(0, 2);
                            
                            if (map.checkElements(temp2)) {
                                monsterPoint.translate(0,1);
                            }
                            else if (!map.checkElements(temp2)) {
                                System.out.println("Halp, I'm stuck");
                            }
                            
                        }
                        
                    }
                    secondPath.add(new Point(monsterPoint));
                }
                
                else if (!temp.equals(playerPoint.creaturePoint)) {
                    
                    monsterPoint.translate(1,0);
                    secondPath.add(new Point(monsterPoint));
                }
                
                else {
                    break;
                }
            }
        }
    }
}
