package roguelike;

import java.awt.Point;
import java.util.ArrayList;

public class MapTransition {
    
    /*This array holds all of the transition points for a given transition.
     *  The entries themselves are used as reference points. So if you look at
     * the transition points in the array list at index 0, those transition points
     * will show you where they translate to with the overWorldLInkingSite.
     */
    ArrayList<Point> arrayListOfPoints = new ArrayList();
    Point overWorldLinkingSite;
    boolean north = false;
    boolean south = false;
    boolean east = false;
    boolean west = false;
    
    
    MapTransition(ArrayList<Point> arrayListOfPoints, Point overWorldLinkingSite) {
        
        this.arrayListOfPoints = arrayListOfPoints;
        this.overWorldLinkingSite = overWorldLinkingSite;
    }
}
