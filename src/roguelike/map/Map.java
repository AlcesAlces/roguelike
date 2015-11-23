package roguelike.map;

import java.awt.Point;
import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import roguelike.Items.Item;
import roguelike.Items.Weapon;
import roguelike.character.Player;
import roguelike.creatures.Creature;
import roguelike.map.mapObjects.Grass;
import roguelike.map.mapObjects.Tree;
import roguelike.map.mapObjects.Wall;
import roguelike.map.mapObjects.Water;

public class Map {
    
    /*
     * Map type key:
     * 0 forest
     * 1 swamp
     */
    
    /* Array list map elements order:
     * 0 Grass .
     * 1 Trees &
     */
    
    public int monsterIndexFound;
    int grassIndexFound;
    int treeIndexFound;
    int wallIndexFound;
    int waterIndexFound;
    public int mapType;
    public int mapID;
    int mapTypeDifficulty;
    // zLevel determines how high/low you are.
    int zLevel;
    public int xMax;
    public int yMax;
    public int xMin;
    public int yMin;
    /*
     * Zone influence is going to be the system that determines how corrupt
     * an area is. The more corrupt it is, the more likely you're going to have
     * bad shit. So 0 is a pure zone, and 30 is the pits of hell.
     */
    public int zoneInfluence;
    //Max being the smallest amount required for this to be an evil zone.
    int infMax;
    int baseInfluence;
    int[] monsterListE;
    int[] monsterListN;
    
    
    String name;
    CSIColor color;
    char overworldChar;
    Point descentPoint;
    Point ascentPoint;
    public Point overworldPoint;
    ArrayList<MapTransition> mapTransitionArray = new ArrayList();
    ArrayList<Point> transitionPoints = new ArrayList();
    ArrayList<Map> linkedMaps = new ArrayList();
    public ArrayList<Item> itemsOnMap = new ArrayList();
    ArrayList<Object> armorsOnMap = new ArrayList();
    ArrayList<Weapon> weaponsOnMap = new ArrayList();
    public ArrayList<Creature> creatureArrayList = new ArrayList();
    public ArrayList<Creature> deadCreatureList = new ArrayList();
    ArrayList<Grass> grassArray = new ArrayList();
    public ArrayList<Tree> treeArray = new ArrayList();
    ArrayList<Wall> wallArray = new ArrayList();
    ArrayList<Water> waterArray = new ArrayList();
    String mapName;
    public boolean visited = false;
    public boolean monsterGenerated = false;
    
    /*
     * This is the constructor overloaded from the default constructor,
     * this is going to be used to rebuild the maps.
     */
    Map(int pointTempX, int pointTempY, int typeTemp, int idTemp, 
            int xMaxTemp, int yMaxTemp, int xMinTemp, int yMinTemp) {
        
        this.overworldPoint = new Point(pointTempX, pointTempY);
        this.mapType = typeTemp;
        this.mapID = idTemp;
        this.xMax = xMaxTemp;
        this.yMax = yMaxTemp;
        this.xMin = xMinTemp;
        this.yMin = yMinTemp;
    }
    
    Map(int xMax, int yMax, int xMin, int yMin, CSIColor color, Point descentPoint,
            Point ascentPoint) {
        
        this.xMax = xMax;
        this.yMax = yMax;
        this.xMin = xMin;
        this.yMin = yMin;
        this.color = color;
        this.descentPoint = descentPoint;
        this.ascentPoint = ascentPoint;
    }
    
    void addMonstersToMap(Creature importCreature) {
        this.creatureArrayList.add(importCreature);
    }
    
    public boolean checkMonsterPositions(Point pointToCheck) {
        
       boolean boolToReturn = false;
          
       if (!creatureArrayList.isEmpty()) {
          for (int i = 0; i < creatureArrayList.size(); i++) {
              
              if (!pointToCheck.equals(creatureArrayList.get(i).creaturePoint)) {
                  boolToReturn = true;
              }
              
              else {
                  monsterIndexFound = i;
                  boolToReturn = false;
                  break;
              }
          }
          
       }
          return boolToReturn;
    }
    
        public void drawFrame(ConsoleSystemInterface csi) {
        
        for (int i = 0; i <= 19; i++) {
            csi.print(0, i, '|', CSIColor.WHITE);
         }
        
        for (int i = 0; i <= 19; i++) {
            csi.print(79, i, '|', CSIColor.WHITE);
        }
        
        for (int i = 0; i <= 79; i++) {
            csi.print(i, 0, '-', CSIColor.WHITE);
        }
        
        for (int i = 0; i <= 79; i++) {
            csi.print(i, 19, '-', CSIColor.WHITE);
        }
    }
        
        public void drawItems(Point visibleTop, Point visibleBottom, ConsoleSystemInterface csi) {
            
            for (int i = 0; i < itemsOnMap.size(); i++) {
            
            //Draw the grass
            if (itemsOnMap.get(i).pointOnMap.x >= visibleTop.x &&
                    itemsOnMap.get(i).pointOnMap.y >= visibleTop.y &&
                    itemsOnMap.get(i).pointOnMap.x <= visibleBottom.x &&
                    itemsOnMap.get(i).pointOnMap.y <= visibleBottom.y) {
                csi.print(itemsOnMap.get(i).pointOnMap.x - (visibleTop.x - 1), 
                        itemsOnMap.get(i).pointOnMap.y - (visibleTop.y - 1),
                        itemsOnMap.get(i).itemSymbol, CSIColor.WHITE);
            }
            
        }
        }
        
        public boolean checkForMonsters(Point pointToCheck) {
            
            boolean boolToReturn = false;
            
            for (int i = 0; i < creatureArrayList.size(); i++) {
                if (!pointToCheck.equals(creatureArrayList.get(i).creaturePoint)) {
                    boolToReturn = true;
                }
                
                else {
                    monsterIndexFound = i;
                    boolToReturn = false;
                    break;
                }
            }
          return boolToReturn;
        }
        
        public boolean checkElements(Point pointToCheck) {
        // This just says that something is in the way, it does not perform combat checks.
            boolean boolToReturn = false;
          
            
            for (int i = 0; i < treeArray.size(); i++) {
              
                if (!pointToCheck.equals(treeArray.get(i).position)) {
                    boolToReturn = true;
              }
              
              else {
                  boolToReturn = false;
                  treeIndexFound = i;
                  break;
              }
          }
            for (int i = 0; i < wallArray.size(); i++) {
                if (!pointToCheck.equals(wallArray.get(i).position) && boolToReturn) {
                    boolToReturn = true;
                }
                
                else {
                    boolToReturn = false;
                    wallIndexFound = i;
                    break;
                }
            }
            
            for (int i = 0; i < creatureArrayList.size(); i++) {
                if (!pointToCheck.equals(creatureArrayList.get(i).creaturePoint) && boolToReturn) {
                    boolToReturn = true;
                }
                
                else {
                    boolToReturn = false;
                    break;
                }
            }
            
            for (int i = 0; i < waterArray.size(); i++) {
                if (!pointToCheck.equals(waterArray.get(i).position) && boolToReturn) {
                    boolToReturn = true;
                }
                
                else {
                    boolToReturn = false;
                    waterIndexFound = i;
                    break;
                }
            }
          return boolToReturn;
    }
    
        public void drawPlayerStatus(Player player, ConsoleSystemInterface csi) {
            
            csi.print(1, 20, "Health: " + player.activeStats[0] , CSIColor.WHITE);
            csi.print(1, 21, "Mana  : " + player.activeStats[1] , CSIColor.WHITE);
            csi.print(1, 22, "DEBUG: ACTION POINTS: " + player.actionPoints, CSIColor.WHITE);
            csi.print(1, 23, "DEBUG: INFLUENCE: " + zoneInfluence);
            
        }
        
        public void drawElements(ConsoleSystemInterface csi, Point visibleTop,
                                 Point visibleBottom, ArrayList<ArrayList<MapObject>> mapElements) {
            
        }
        
        public int[] possibleMonsters() {
            
            //Creates the neutral set.
            if (zoneInfluence > infMax) {
                return monsterListE;
            }
            
            //Creates the "Evil" set.
            else {
                return monsterListN;
            }
        }
}
