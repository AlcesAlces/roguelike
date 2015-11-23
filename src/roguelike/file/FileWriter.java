package roguelike.file;

import roguelike.character.Player;
import roguelike.creatures.Creature;
import roguelike.map.Map;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWriter {
    
    public static void WriteMap(ArrayList<Map> mapArray, String name) {
        
        String fileName = name + "-map-save.txt";
        
        try {
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        for(int i = 0; i < mapArray.size(); i++) {
            
            Map tempMap = mapArray.get(i);
            String testString = "(" + tempMap.overworldPoint.x + "," + tempMap.overworldPoint.y
            + ",";
            testString = testString + tempMap.mapType +",";
            testString = testString + tempMap.mapID + ",";
            testString = testString + tempMap.xMax + "," + tempMap.yMax + "," +
                    tempMap.xMin + "," + tempMap.yMin;
            
            
            
            //Ends particular map statement
            testString = testString + ")";
            
            //Ends the statement completely
            writer.print(testString);
        }
            writer.close();
        }catch(IOException ex) {
        System.out.println("Something goofed");
    }
    }
    
    public static void WriteMonsters(ArrayList<Map> mapArray, String name) {
        
        try {
           String fileName = name + "-monster-save.txt";
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        
            for(int i = 0; i < mapArray.size(); i++) {
            
            for(int j = 0; j < mapArray.get(i).creatureArrayList.size();j++) {
                Map tempMap = mapArray.get(i);
                Creature tempCreature = mapArray.get(i).creatureArrayList.get(j);
                //mapid, stats 1-7, type weak, type strength, class, level, exp
                //,expReq, currentHp, baseHp, baseMana, raceid, regenindex, regened,
                //manaregenindex, manaregened, symbol, point (x,y), name
                String testString = "(" + tempCreature.mapID;
                for(int k = 0; k < 7; k++) {
                    testString = testString + "," + tempCreature.stats[k];
                }
                testString = testString + "," + tempCreature.weaponTypeWeakness + "," +
                        tempCreature.weaponTypeStrength + "," + tempCreature.raceID + "," +
                        tempCreature.level + "," + tempCreature.experience + "," +
                        tempCreature.experienceRequired + "," + tempCreature.currentHealth
                        + "," + tempCreature.baseHealth + "," + tempCreature.baseMana +
                        "," + tempCreature.raceID + "," + tempCreature.healthRegenIndex +
                        "," + tempCreature.healthRegened + "," + tempCreature.manaRegenIndex +
                        "," + tempCreature.manaRegened + "," + tempCreature.symbol +
                        "," + tempCreature.creaturePoint.x + "," + tempCreature.creaturePoint.y +
                        "," + tempCreature.name;
                
                
                //Ends particular map statement
                testString = testString + ")";
            
                //Ends the statement completely
                writer.print(testString);
            }
            
        }
        writer.close();
    }catch(IOException ex) {
        System.out.println("Something goofed");
    }
    }
    
    public static void WritePlayer(Player player) {
        
        try {
            String fileName = player.name + "-player-save.txt";
            
            PrintWriter writer = new PrintWriter(fileName,"UTF-8");
            String testString = "(" + player.mapID;
                for(int k = 0; k < 7; k++) {
                    testString = testString + "," + player.stats[k];
                }
                testString = testString + "," + player.weaponTypeWeakness + "," +
                        player.weaponTypeStrength + "," + player.raceID + "," +
                        player.level + "," + player.experience + "," +
                        player.experienceRequired + "," + player.currentHealth
                        + "," + player.baseHealth + "," + player.baseMana +
                        "," + player.raceID + "," + player.healthRegenIndex +
                        "," + player.healthRegened + "," + player.manaRegenIndex +
                        "," + player.manaRegened + "," + player.symbol +
                        "," + player.creaturePoint.x + "," + player.creaturePoint.y +
                        "," + player.name;
                
                
                //Ends particular map statement
                testString = testString + ")";
            
                //Ends the statement completely
                writer.print(testString);
                writer.close();
                
        } catch(IOException ex) {
            System.out.println("Something done goofed");
        }
    }
    
    static void WriteConductor(String name) {
        
        //This guy is goin to check to make sure that all of the saves are on the level
        
        
    }
}
