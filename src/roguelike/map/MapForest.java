package roguelike.map;

import java.awt.Point;
import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import roguelike.map.mapObjects.Grass;
import roguelike.map.mapObjects.Tree;
import roguelike.map.mapObjects.Wall;

public class MapForest extends Map{
    
    int density = 100;
    CSIColor color = CSIColor.GREEN;
    
    //This constructor is to be used with the file offloading and onloading
    public MapForest(int pointTempX, int pointTempY, int typeTemp, int idTemp, 
            int xMaxTemp, int yMaxTemp, int xMinTemp, int yMinTemp) {
        super(pointTempX, pointTempY, typeTemp, idTemp, xMaxTemp, yMaxTemp,
                xMinTemp, yMinTemp);
        /*
        this.overworldPoint = new Point(pointTempX, pointTempY);
        this.mapType = typeTemp;
        this.mapID = idTemp;
        this.xMax = xMaxTemp;
        this.yMax = yMaxTemp;
        this.xMin = xMinTemp;
        this.yMin = yMinTemp;
        */
        this.color = CSIColor.GREEN;
        this.baseInfluence = 5;
        generateGrassElements();
        overworldChar = '&';
        
    }
    
    public MapForest(int xMax, int yMax, int xMin, int yMin, CSIColor color, Point descentPoint, Point ascentPoint) {
        super(xMax, yMax, xMin, yMin, color, descentPoint, ascentPoint);
        generateGrassElements();
        generateRandomElements(density);
        
        //These are the permitted monster types
        int[] temp1 = {1};
        int[] temp2 = {0};
        this.monsterListN = temp2;
        this.monsterListE = temp1;
        
        this.baseInfluence = 5;
        this.mapType = 0;
        this.infMax = 25;
        overworldChar = '&';
    }
    
    void generateGrassElements() {
        
        for (int i = xMin; i <= xMax; i++) {
            
            for (int j = yMin; j <= yMax; j++) {
                
                grassArray.add(new Grass(1, new Point(i,j),true));
            }
        }
        
    }
        void generateRandomElements(int density) {
        
        for (int i = 0; i < density; i++) {
            
            Point temp = new Point((int) (Math.random() * xMax), (int) (Math.random() * yMax));
            treeArray.add(new Tree(1,temp,false));
        }
        
        for (int i = 0; i <= xMax; i++) {
            
            treeArray.add(new Tree(1,new Point(i,0),false));
        }
        
        for (int i = 0; i <= xMax; i++) {
            
            treeArray.add(new Tree(1,new Point(i,yMax),false));
        }
        
        for (int i = 0; i <= yMax; i++) {
            
            treeArray.add(new Tree(1,new Point(0,i),false));
        }
        for (int i = 0; i <= yMax; i++) {
            
            treeArray.add(new Tree(1,new Point(xMax,i),false));
        }
    }
        
        static Map newForest(int sizeX, int sizeY, int monsterAmount, int level) {
        
        int mapSizeX = (int) (Math.random() * sizeX) + 100;
        int mapSizeY = (int) (Math.random() * sizeY) + 100;
        int randomBuildingAmount = (int) (Math.random() * 5);
        ArrayList<Wall> wallArray = new ArrayList();
        
        for (int i = 0; i <= randomBuildingAmount; i++) {
            
        int randomBuildingLocationX = (int) (Math.random() * mapSizeX);
        int randomBuildingLocationY = (int) (Math.random() * mapSizeY);
        int randomBuildingSizeX = (int) (Math.random() * 10) + 3;
        int randomBuildingSizeY = (int) (Math.random() * 10) + 3;
        
            for (int j = 0; j <= randomBuildingSizeX; j++) {
                wallArray.add(new Wall(1,new Point(j + randomBuildingLocationX,
                        randomBuildingLocationY),false));
            }
            
            for (int j = 0; j <= randomBuildingSizeX; j++) {
                wallArray.add(new Wall(1,new Point(j + randomBuildingLocationX,
                        randomBuildingSizeY + randomBuildingLocationY),false));
            }
            
            wallArray.remove(wallArray.size() - (randomBuildingSizeX / 2));
            
            for (int j = 0; j <= randomBuildingSizeY; j++) {
                wallArray.add(new Wall(1,new Point(randomBuildingLocationX,
                        j + randomBuildingLocationY),false));
            }
            
            for (int j = 0; j <= randomBuildingSizeY; j++) {
                wallArray.add(new Wall(1,new Point(randomBuildingSizeX + randomBuildingLocationX,
                        j + randomBuildingLocationY),false));
            }
        }
        MapForest map01 = new MapForest(mapSizeX, mapSizeY, 0, 0, CSIColor.GREEN, null, null);
        map01.wallArray = wallArray;
        
        /*ArrayList<Creature> monsters = new ArrayList();
        monsters = GenerateMonsters.generateGoblins(monsterAmount, level, mapSizeX, mapSizeY);
        for (int i = 0; i < monsters.size(); i++) {
            
            map01.addMonstersToMap(monsters.get(i));
        }
        */
        return map01;
    }
        @Override
        public void drawElements(ConsoleSystemInterface csi, Point visibleTop,
                                 Point visibleBottom, ArrayList<ArrayList<MapObject>> mapElements) {
        
        
        for (int i = 0; i < grassArray.size(); i++) {
            
            //Draw the grass
            if (grassArray.get(i).position.x >= visibleTop.x &&
                    grassArray.get(i).position.y >= visibleTop.y &&
                    grassArray.get(i).position.x <= visibleBottom.x &&
                    grassArray.get(i).position.y <= visibleBottom.y) {
                csi.print(grassArray.get(i).position.x - (visibleTop.x - 1), 
                        grassArray.get(i).position.y - (visibleTop.y - 1),
                        '.', CSIColor.GREEN);
            }
            
        }
        
        
        for (int i = 0; i < treeArray.size(); i++) {
            
            if (treeArray.get(i).position.x >= visibleTop.x &&
                    treeArray.get(i).position.y >= visibleTop.y &&
                    treeArray.get(i).position.x <= visibleBottom.x &&
                    treeArray.get(i).position.y <= visibleBottom.y) {
                csi.print(treeArray.get(i).position.x - (visibleTop.x - 1), 
                        treeArray.get(i).position.y - (visibleTop.y - 1),
                        '&', CSIColor.GREEN);
            }
            
        }
        
                for (int i = 0; i < creatureArrayList.size(); i++) {
            
            if (creatureArrayList.get(i).creaturePoint.x >= visibleTop.x &&
                    creatureArrayList.get(i).creaturePoint.y >= visibleTop.y &&
                    creatureArrayList.get(i).creaturePoint.x <= visibleBottom.x &&
                    creatureArrayList.get(i).creaturePoint.y <= visibleBottom.y) {
                csi.print(creatureArrayList.get(i).creaturePoint.x - (visibleTop.x - 1), 
                        creatureArrayList.get(i).creaturePoint.y - (visibleTop.y - 1),
                        creatureArrayList.get(i).symbol, creatureArrayList.get(i).color);
            }
            
        }
                
                for (int i = 0; i < wallArray.size(); i++) {
            
            if (wallArray.get(i).position.x >= visibleTop.x &&
                    wallArray.get(i).position.y >= visibleTop.y &&
                    wallArray.get(i).position.x <= visibleBottom.x &&
                    wallArray.get(i).position.y <= visibleBottom.y) {
                csi.print(wallArray.get(i).position.x - (visibleTop.x - 1), 
                        wallArray.get(i).position.y - (visibleTop.y - 1),
                        '#', wallArray.get(i).color);
            }
            
        }
        
    }
}
