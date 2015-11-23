package roguelike;

import roguelike.map.Map;

import java.awt.Point;

public class CheckMapRestrictions {
    
    public static boolean checkRestrictionYUp(Point playerPoint, Map currentMap) {
        
        Point temp = new Point(0,0);
        temp.setLocation(playerPoint.x, playerPoint.y - 1);
        
        if ((playerPoint.y - 1 != currentMap.xMin) && currentMap.checkElements(temp)) {
            return true;
        }
        else 
            return false;
        }
    
    public static boolean checkRestrictionYDown(Point playerPoint, Map currentMap) {
        
        Point temp = new Point(0,0);
        temp.setLocation(playerPoint.x, playerPoint.y + 1);
        
        if ((playerPoint.y + 1 != currentMap.yMax) && currentMap.checkElements(
                temp)) {
            return true;
        }
        else 
            return false;
        } 
    
    public static boolean checkRestrictionXUp(Point playerPoint, Map currentMap) {
        
        Point temp = new Point(0,0);
        temp.setLocation(playerPoint.x - 1, playerPoint.y);
        
        if ((playerPoint.x - 1 != currentMap.yMin) && currentMap.checkElements(
                temp)) {
            return true;
        }
        else 
            return false;
        }
    
    public static boolean checkRestrictionXDown(Point playerPoint, Map currentMap) {
        
        Point temp = new Point(0,0);
        temp.setLocation(playerPoint.x + 1, playerPoint.y);
        
        if ((playerPoint.x + 1 != currentMap.xMax) && currentMap.checkElements(
                temp)) {
            return true;
        }
        else 
            return false;
        }
    
    public static boolean checkMonsterYUp(Point playerPoint, Map test) {
        
        Point temp = new Point(0,0);
        temp.setLocation(playerPoint.x, playerPoint.y - 1);
        
        if (test.checkMonsterPositions(temp)) {
            
            return true;
        }
        else 
            return false;
        }
    
    public static boolean checkMonsterYDown(Point playerPoint, Map test) {
        
        Point temp = new Point(0,0);
        temp.setLocation(playerPoint.x, playerPoint.y + 1);
        
        if (test.checkMonsterPositions(temp)) {
            
            return true;
        }
        else 
            return false;
        }
    
    public static boolean checkMonsterXLeft(Point playerPoint, Map test) {
        
        Point temp = new Point(0,0);
        temp.setLocation(playerPoint.x - 1, playerPoint.y);
        
        if (test.checkMonsterPositions(temp)) {
            
            return true;
        }
        else 
            return false;
        }
    
    public static boolean checkMonsterXRight(Point playerPoint, Map test) {
        
        Point temp = new Point(0,0);
        temp.setLocation(playerPoint.x + 1, playerPoint.y);
        
        if (test.checkMonsterPositions(temp)) {
            
            return true;
        }
        else 
            return false;
        }
}