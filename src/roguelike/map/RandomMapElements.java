package roguelike.map;

import java.awt.Point;
import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class RandomMapElements {
    
    int xRandom;
    int yRandom;
    ArrayList<Point> randomPointsArray = new ArrayList();
    
    int xMax;
    int yMax;
    
    RandomMapElements(int xMax, int yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
                
      }
    
    void generateRandomElements() {
        
        for (int i = 0; i < 10; i++) {
            
            Point temp = new Point((int) (Math.random() * xMax), (int) (Math.random() * yMax));
            randomPointsArray.add(temp);
        }
    }
    
    void drawElements (ConsoleSystemInterface csi, CSIColor color) {
        for (int i = 0; i < randomPointsArray.size(); i++) {
            csi.print(randomPointsArray.get(i).x, randomPointsArray.get(i).y, 
                    '#', color);
    }
    }
    boolean checkElements(Point pointToCheck) {
        
       boolean boolToReturn = false;
          
          for (int i = 0; i < randomPointsArray.size(); i++) {
              
              if (!pointToCheck.equals(randomPointsArray.get(i))) {
                  boolToReturn = true;
              }
              
              else {
                  boolToReturn = false;
                  break;
              }
          }
          return boolToReturn;
    }
}
