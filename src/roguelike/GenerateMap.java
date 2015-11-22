package roguelike;

import java.awt.Point;
import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;

public class GenerateMap {
    
    //for reference 10 is the world size I was thinking of while designing this.
    //Map types: 0 - Forest, 1 - Swamp
    static ArrayList<Map> mapGenerator(int sizeOfWorld) {
        
        int mapCount = 0;
        int counter = 0;
        int mapsToPlace = 3;
        Point centerOfCluster = new Point(0,0);
        int prevMapType = 0;
        ArrayList<Map> mapList = new ArrayList();
        Point startingPoint = new Point(0,0);
        Point currentPoint = new Point(startingPoint);
        Point tempPoint = new Point(0,0);
        boolean addMap;
        boolean overWorldCheck = true;
        boolean generating = true;
        boolean scanModeForest = true;
        boolean searchSecondTile = false;
        Map mapToAdd = null;
        
        //Fist pass: Generate ground level areas.
        
        //Generate forest cluster
        
        while(generating) {
            
            if (counter == 0) {
                // Base Case
                centerOfCluster = new Point(currentPoint);
                for (int j = 0; j < 1; j++) {
                    generateCluster(currentPoint, sizeOfWorld, mapCount, mapList, mapToAdd, 0);
                
                    randomDirection(currentPoint, 1);
                    counter++;
                    prevMapType = 0;
                }
                
            }
            
            //Generate the specific elements of the cluster forest.
            //Testing with adding more forestClusters in the same spot here
            /*
             * The current idea with the generator is that it needs to
             * find a way to intelligently link these maps.
             */
            int worldSize = 5;
            /*Currently works in a dumb-fire mode, doesn't take into account
              the fact that these maps aren't related.
            */ 
            for (int i = 0; i < worldSize; i++) {
                //Picks one of the two current maps
                int mapType = nextMapType(prevMapType);
                
                    //The 40 in the loop seems like the optimal _density_ number.
                centerOfCluster = new Point(currentPoint);
                System.out.println("Center: " + centerOfCluster);
                
                for (int j = 0; j < 10; j++) {
                    
                    generateCluster(currentPoint, sizeOfWorld, mapCount, mapList, mapToAdd, mapType);
                    randomDirection(currentPoint, 1);
                }
                
                prevMapType = mapType;
                counter++;
                System.out.println("Current: " + currentPoint);
                currentPoint = new Point(centerOfCluster);
                /*
                 * This needs to be a function that removes the cursor from the 
                 * current cluster. This function could be the simple thing that
                 * acts as the work horse.
                 */
                
                currentPoint.translate(5,0);
                //randomDirection(currentPoint, 5);
            }
            
            
            
            
            System.out.println("Amount of maps generated: " + mapList.size());
            checkZoneInfluence(mapList);
            GenerateMonsters.populateMaps(mapList);
            
            generating = false;
        }
        determineMapLinks(mapList);
        for (int i = 0; i < mapList.size(); i++) {
            char temp = '&';
            createMapLinkGraphic(mapList.get(i),temp);
        }
        return mapList;
        
    }
    
    static void randomDirection(Point point, int amount) {
        
        int rand = (int) (Math.random() * 4);
        
        switch(rand) {
            
            case 0:
                point.translate(0, amount);
            case 1:
                point.translate(amount,0);
            case 2:
                amount = amount * -1;
                point.translate(amount,0);
            case 3:
                amount = amount * -1;
                point.translate(0,amount);
     
        }
    }
    
    static int nextMapType(int prevMapType) {
        
        int rand;
        
        switch(prevMapType) {
            //Map is forest
            case 0:
                //So this says "Hey you can return 0, or 1.
                rand = (int) (Math.random() * 2);
                return rand;
            //Map is swamp
            case 1:
                rand = (int) (Math.random() * 3);
                return rand;
            //map is bog
            case 2:
                //Can return 1-3
                rand = 1 + (int) (Math.random() * 3);
                return rand;
            //Map is wasteland
            case 3:
                rand = 2 + (int) (Math.random() * 3);
                return rand;
            //Map is desert
            case 4:
                rand = 3 + (int) (Math.random() * 2);
                return rand;
                
        }
        
        //Return the default case of 0
        return 0;
    }
    
    static void generateCluster(Point currentPoint, int sizeOfWorld,
            int mapCount, ArrayList<Map> mapList, Map mapToAdd, int mapType) {
        
        int randomX = ((int) (Math.random() * 200));
        int randomY = ((int) (Math.random() * 200));
        int xSize = ((int) (Math.random() * sizeOfWorld / 2) + 2);
        int ySize = ((int) (Math.random() * sizeOfWorld / 2) + 2);
        int mapsToPlace = (10 + (xSize * ySize) - ((xSize) * 2 / ((int) Math.random() * ySize + 1)));
        
        
        int mapAttempts = 50;
        //int mapAttempts = ((xSize * ySize) ^ 5);
        int monsterLevel = 1;
        int monsterAmount = ((int) (Math.random() * 5) + 1);
        int counter = 0;
        int failedAttempts = 0;
        boolean addMap;
        boolean placingMaps = true;
        boolean overworldPointClear;
        boolean overworldCheck = true;
        
        Point startingPoint = new Point(currentPoint);
        
        while(placingMaps) {
            
            overworldPointClear = false;
            
            if (counter == 0) {
                
                overworldPointClear = true;
                counter++;
                
            }
            
            else {
                
                int tempRandomDirection = (int) (Math.random() * 4) + 1;
                switch(tempRandomDirection) {
                    
                    case 1:
                        //Check the north point.
                        currentPoint.translate(0, -1);
                        if(checkOverworldPoint(mapList, currentPoint) ||
                                checkBounds(currentPoint, startingPoint, xSize)) {
                            mapAttempts--;
                            failedAttempts++;
                            currentPoint.translate(0,1);
                        }
                        else {
                            overworldPointClear = true;
                        }
                    case 2:
                        //Check the south point.
                        currentPoint.translate(0,1);
                        if(checkOverworldPoint(mapList, currentPoint) ||
                                checkBounds(currentPoint, startingPoint, xSize)){
                            mapAttempts--;
                            failedAttempts++;
                            currentPoint.translate(0,-1);
                        }
                        else {
                            overworldPointClear = true;
                        }
                        
                    case 3:
                        //check west
                        currentPoint.translate(1,0);
                        if(checkOverworldPoint(mapList, currentPoint) ||
                                checkBounds(currentPoint, startingPoint, xSize)) {
                            mapAttempts--;
                            failedAttempts++;
                            currentPoint.translate(-1,0);
                        }
                        else {
                            overworldPointClear = true;
                        }
                        
                    case 4:
                        //check east.
                        currentPoint.translate(-1,0);
                        if(checkOverworldPoint(mapList, currentPoint) ||
                                checkBounds(currentPoint, startingPoint, xSize)) {
                            mapAttempts--;
                            failedAttempts++;
                            currentPoint.translate(1,0);
                        }
                        else {
                            overworldPointClear = true;
                        }
                }
                
                if(failedAttempts > 5) {
                    
                    //The fail system tells the point to go back to the start
                    failedAttempts = 0;
                    currentPoint = new Point(startingPoint);
                }
                
                if(overworldPointClear) {
                    
                    //currentPoint.translate(-1,0);
                    mapsToPlace--;
                    mapAttempts--;
                    mapToAdd = mapType(mapType, mapToAdd, randomX, randomY, monsterAmount, monsterLevel);
                    mapToAdd.mapID = mapList.size();
                    mapToAdd.zLevel = 0;
                    mapToAdd.overworldPoint = new Point(currentPoint);
                    mapList.add(mapToAdd);
                    mapCount++;
                }
                
                for (int i = 0; i < mapList.size() - 1;i++) {
                    
                    if (mapList.get(mapList.size()-1).overworldPoint.equals(
                            mapList.get(i).overworldPoint)) {
                        
                        mapList.remove(mapList.size() - 1);
                        mapsToPlace++;
                    }
                }
                if ((mapsToPlace <= 0) || (mapAttempts <= 0)) {
                    
                    placingMaps = false;
                }
                counter++;
            }
        }
        
    }
    
    static Map mapType(int mapType, Map mapToAdd, int randomX, int randomY, 
            int monsterAmount, int monsterLevel) {
        
        switch(mapType) {
        
            case 0:
                mapToAdd = MapForest.newForest(randomX, randomY, monsterAmount, monsterLevel);
                return mapToAdd;
            case 1:
                mapToAdd = MapSwamp.newSwamp(randomX, randomY, monsterAmount, monsterLevel);
                return mapToAdd;
            case 2:
                mapToAdd = MapBog.newBog(randomX, randomY, monsterAmount, monsterLevel);
                return mapToAdd;
            case 3:
                mapToAdd = MapWasteland.newWasteland(randomX, randomY, monsterAmount, monsterLevel);
                return mapToAdd;
            case 4:
                mapToAdd = MapDesert.newDesert(randomX, randomY, monsterAmount, monsterLevel);
                return mapToAdd;
    
        }
        
        return mapToAdd;
    }
    
    static boolean checkBounds(Point currentPoint, Point startingPoint, int dist) {
        
        //purpose of this function is to check to see if the maps are in bounds.
        if((int) currentPoint.distance(startingPoint) > dist) {
            
            return true;
        }
        
        else {
            return false;
        }
    }
    
    static boolean checkOverworldPoint(ArrayList<Map> mapList, Point currentPoint) {
        
        
        for (int i = 0; i < mapList.size(); i++) {
            
            if(currentPoint.equals(mapList.get(i).overworldPoint)) {
                
                //occupied point
                return true;
            }
        }
        
        return false;
    }
    
    static void createMapLinkGraphic(Map map, char wallMaterial) {
        
        int rand = (int) ((Math.random()* 5) + 1);
        
        for (int i = 0; i < map.mapTransitionArray.size(); i++) {
            
            if (map.mapTransitionArray.get(i).north) {
                
                Point point1;
                Point point2;
                
                for (int j = 0; j < map.mapTransitionArray.get(i).arrayListOfPoints.size(); j++) {
                    
                    for (int k = 0; k < map.treeArray.size(); k++) {
                        Point tempPoint = new Point(map.treeArray.get(k).position.x, map.treeArray.get(k).position.y + 1);
                    
                    if (tempPoint.equals(map.mapTransitionArray.get(i).arrayListOfPoints.get(j))) {
                        map.treeArray.remove(k);
                    
                    }
                
                    }
                }
                
                //This statement establishes that point1 is more left than point2
                if (map.mapTransitionArray.get(i).arrayListOfPoints.get(0).x >
                        map.mapTransitionArray.get(i).arrayListOfPoints.get(1).x) {
                    
                    point1 = new Point(map.mapTransitionArray.get(i).arrayListOfPoints.get(0).x - 1, 1);
                    point2 = new Point(map.mapTransitionArray.get(i).arrayListOfPoints.get(1).x + 1, 1);
                }
                
                else {
                    point1 = new Point(map.mapTransitionArray.get(i).arrayListOfPoints.get(1).x + 1, 1);
                    point2 = new Point(map.mapTransitionArray.get(i).arrayListOfPoints.get(0).x - 1, 1);
                }
                
                for (int j = 0; j < rand; j++) {
                    
                    Wall wall1 = new Wall(0, new Point(point1), false);
                    Wall wall2 = new Wall(0, new Point(point2), false);
                    
                    map.wallArray.add(wall1);
                    map.wallArray.add(wall2);
                    
                    point1.translate(0,1);
                    point2.translate(0,1);
                }
            }
            
            else if (map.mapTransitionArray.get(i).south) {
                
                Point point1;
                Point point2;
                //This statement SHOULD be good.
                for (int j = 0; j < map.mapTransitionArray.get(i).arrayListOfPoints.size(); j++) {
                
                    
                    for (int k = 0; k < map.treeArray.size(); k++) {
                        Point tempPoint = new Point(map.treeArray.get(k).position.x, map.treeArray.get(k).position.y - 1);
                    
                    if (tempPoint.equals(map.mapTransitionArray.get(i).arrayListOfPoints.get(j))) {
                        map.treeArray.remove(k);
                    
                    }
                
                    }
                }
                
                //This statement establishes that point1 is more left than point2
                if (map.mapTransitionArray.get(i).arrayListOfPoints.get(0).x >
                        map.mapTransitionArray.get(i).arrayListOfPoints.get(1).x) {
                    
                    point1 = new Point(map.mapTransitionArray.get(i).arrayListOfPoints.get(0).x + 1, map.yMax - 1);
                    point2 = new Point(map.mapTransitionArray.get(i).arrayListOfPoints.get(1).x - 1, map.yMax - 1);
                }
                
                else {
                    point1 = new Point(map.mapTransitionArray.get(i).arrayListOfPoints.get(0).x - 1, map.yMax - 1);
                    point2 = new Point(map.mapTransitionArray.get(i).arrayListOfPoints.get(1).x + 1, map.yMax - 1);
                }
                
                for (int j = 0; j < rand; j++) {
                    
                    Wall wall1 = new Wall(0, new Point(point1), false);
                    Wall wall2 = new Wall(0, new Point(point2), false);
                    
                    map.wallArray.add(wall1);
                    map.wallArray.add(wall2);
                    
                    point1.translate(0,-1);
                    point2.translate(0,-1);
                }
            }
            
            else if (map.mapTransitionArray.get(i).east) {
                
                Point point1;
                Point point2;
                
                for (int j = 0; j < map.mapTransitionArray.get(i).arrayListOfPoints.size(); j++) {
                    
                    for (int k = 0; k < map.treeArray.size(); k++) {
                        Point tempPoint = new Point(map.treeArray.get(k).position.x - 1, map.treeArray.get(k).position.y);
                    
                    if (tempPoint.equals(map.mapTransitionArray.get(i).arrayListOfPoints.get(j))) {
                        map.treeArray.remove(k);
                    
                    }
                
                    }
                }
                
                //This statement establishes that point1 is higher than point2
                if (map.mapTransitionArray.get(i).arrayListOfPoints.get(0).y >
                        map.mapTransitionArray.get(i).arrayListOfPoints.get(1).y) {
                    
                    point1 = new Point(map.xMax - 1, map.mapTransitionArray.get(i).arrayListOfPoints.get(0).y + 1);
                    point2 = new Point(map.xMax - 1, map.mapTransitionArray.get(i).arrayListOfPoints.get(1).y - 1);
                }
                
                else {
                    point1 = new Point(map.xMax - 1, map.mapTransitionArray.get(i).arrayListOfPoints.get(1).y + 1);
                    point2 = new Point(map.xMax - 1, map.mapTransitionArray.get(i).arrayListOfPoints.get(0).y - 1);
                }
                
                for (int j = 0; j < rand; j++) {
                    
                    Wall wall1 = new Wall(0, new Point(point1), false);
                    Wall wall2 = new Wall(0, new Point(point2), false);
                    
                    map.wallArray.add(wall1);
                    map.wallArray.add(wall2);
                    
                    point1.translate(-1,0);
                    point2.translate(-1,0);
                }
            }
            
            else if (map.mapTransitionArray.get(i).west) {
                
                Point point1;
                Point point2;
                
                for (int j = 0; j < map.mapTransitionArray.get(i).arrayListOfPoints.size(); j++) {
                    
                    for (int k = 0; k < map.treeArray.size(); k++) {
                        Point tempPoint = new Point(map.treeArray.get(k).position.x + 1, map.treeArray.get(k).position.y);
                    
                    if (tempPoint.equals(map.mapTransitionArray.get(i).arrayListOfPoints.get(j))) {
                        map.treeArray.remove(k);
                    
                    }
                
                    }
                }
                
                //This statement establishes that point1 is higher than point2
                if (map.mapTransitionArray.get(i).arrayListOfPoints.get(0).y >
                        map.mapTransitionArray.get(i).arrayListOfPoints.get(1).y) {
                    
                    point1 = new Point(1, map.mapTransitionArray.get(i).arrayListOfPoints.get(0).y + 1);
                    point2 = new Point(1, map.mapTransitionArray.get(i).arrayListOfPoints.get(1).y - 1);
                }
                
                else {
                    point1 = new Point(1, map.mapTransitionArray.get(i).arrayListOfPoints.get(1).y + 1);
                    point2 = new Point(1, map.mapTransitionArray.get(i).arrayListOfPoints.get(0).y - 1);
                }
                
                for (int j = 0; j < rand; j++) {
                    
                    Wall wall1 = new Wall(0, new Point(point1), false);
                    Wall wall2 = new Wall(0, new Point(point2), false);
                    
                    map.wallArray.add(wall1);
                    map.wallArray.add(wall2);
                    
                    point1.translate(1,0);
                    point2.translate(1,0);
                }
            }
        }
    }
    
    static void determineMapLinks(ArrayList<Map> mapArray) {
        
        Point tempPoint = new Point(0,0);
        MapTransition tempTransition;
        //Start by picking the first map entry, then find it's overworld position.
        
        for (int i = 0; i < mapArray.size(); i++) {
            
            tempPoint = new Point(mapArray.get(i).overworldPoint);
            //Translate to the RIGHT to check for another map.
            tempPoint.translate(1, 0);
            
            for (int j = 0; j < mapArray.size(); j++) {
                
                if (tempPoint.equals(mapArray.get(j).overworldPoint)) {
                    
                    Point randPoint1 = new Point(mapArray.get(i).xMax - 1, ((int) (Math.random()* mapArray.get(i).yMax) + 1));
                    Point randPoint2 = new Point(mapArray.get(i).xMax - 1, randPoint1.y + 1);
                    ArrayList<Point> tempArray = new ArrayList();
                    tempArray.add(randPoint1);
                    tempArray.add(randPoint2);
                    
                    tempTransition = new MapTransition(tempArray, mapArray.get(j).overworldPoint);
                    tempTransition.east= true;
                    mapArray.get(i).mapTransitionArray.add(tempTransition);
                    
                    //This is a link to the RIGHT of the target map.
                }
            }
            
            tempPoint.translate(-2, 0);
            
            for (int j = 0; j < mapArray.size(); j++) {
                if (tempPoint.equals(mapArray.get(j).overworldPoint)) {
                    
                    Point randPoint1 = new Point(1, ((int) (Math.random()* mapArray.get(i).yMax) + 1));
                    Point randPoint2 = new Point(1, randPoint1.y + 1);
                    ArrayList<Point> tempArray = new ArrayList();
                    tempArray.add(randPoint1);
                    tempArray.add(randPoint2);
                        
                    tempTransition = new MapTransition(tempArray, mapArray.get(j).overworldPoint);
                    tempTransition.west= true;
                    mapArray.get(i).mapTransitionArray.add(tempTransition);
                    
                    //This is a link to the LEFT of the target map
                }
            }
            
            tempPoint.translate(1, -1);
            
            for (int j = 0; j < mapArray.size(); j++) {
                if (tempPoint.equals(mapArray.get(j).overworldPoint)) {
                    
                    Point randPoint1 = new Point(((int) (Math.random()* mapArray.get(i).xMax) + 1), 1);
                    Point randPoint2 = new Point(randPoint1.x + 1, 1);
                    ArrayList<Point> tempArray = new ArrayList();
                    tempArray.add(randPoint1);
                    tempArray.add(randPoint2);
                        
                    tempTransition = new MapTransition(tempArray, mapArray.get(j).overworldPoint);
                    tempTransition.north= true;
                    mapArray.get(i).mapTransitionArray.add(tempTransition);
                    
                    //This is a link ABOVE the target map.
                }
            }
            
            tempPoint.translate(0,2);
            
            for (int j = 0; j < mapArray.size(); j++) {
                if (tempPoint.equals(mapArray.get(j).overworldPoint)) {
                    
                    Point randPoint1 = new Point(((int) (Math.random()* mapArray.get(i).xMax) + 1),  mapArray.get(i).yMax - 1);
                    Point randPoint2 = new Point(randPoint1.x + 1, mapArray.get(i).yMax - 1);
                    ArrayList<Point> tempArray = new ArrayList();
                    tempArray.add(randPoint1);
                    tempArray.add(randPoint2);
                        
                    tempTransition = new MapTransition(tempArray, mapArray.get(j).overworldPoint);
                    tempTransition.south= true;
                    mapArray.get(i).mapTransitionArray.add(tempTransition);
                    
                    //This is a link BELOW the target map.
                }
            }
        }
    }
    
    static void checkZoneInfluence(ArrayList<Map> mapArray) {
        
        int surroundingInfluence = 0;
        Point tempPoint;
        
        for (int i = 0; i < mapArray.size();i++) {
            
            tempPoint = new Point(mapArray.get(i).overworldPoint);
            
            tempPoint.translate(1,0);
            for(int j = 0; j < mapArray.size();j++) {
                
                if(tempPoint.equals(mapArray.get(j).overworldPoint)) {
                    surroundingInfluence += mapArray.get(j).zoneInfluence;
                }
            }
            
            tempPoint.translate(-2, 0);
            for(int j = 0; j < mapArray.size();j++) {
                if(tempPoint.equals(mapArray.get(j).overworldPoint)) {
                    surroundingInfluence += mapArray.get(j).zoneInfluence;
                }
            }
            
            tempPoint.translate(1,1);
            for(int j = 0; j < mapArray.size();j++) {
                
                if(tempPoint.equals(mapArray.get(j).overworldPoint)) {
                    surroundingInfluence += mapArray.get(j).zoneInfluence;
                }
            }
            tempPoint.translate(0,-2);
            for(int j = 0; j < mapArray.size();j++) {
                
                if(tempPoint.equals(mapArray.get(j).overworldPoint)) {
                    surroundingInfluence += mapArray.get(j).zoneInfluence;
                }
            }
            
            mapArray.get(i).zoneInfluence =
                    mapArray.get(i).baseInfluence + 
                    (int) (Math.random()* surroundingInfluence);
            surroundingInfluence = 0;
        }
    }
}
