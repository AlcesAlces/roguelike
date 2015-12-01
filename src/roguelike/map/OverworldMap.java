package roguelike.map;

import java.awt.Point;
import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class OverworldMap {
    
//    public static void showMap(ArrayList<MapCompact> mapArray, ConsoleSystemInterface csi, int currentIndex,
//                               Point visibleTop, Point visibleBottom) {
//
//        boolean displayActive = true;
//
//
//        while (displayActive) {
//            csi.cls();
//            for (int i = 0; i < mapArray.size(); i++) {
//
//                if (mapArray.get(i).overworldPoint.x >= visibleTop.x &&
//                    mapArray.get(i).overworldPoint.y >= visibleTop.y &&
//                    mapArray.get(i).overworldPoint.x <= visibleBottom.x &&
//                    mapArray.get(i).overworldPoint.y <= visibleBottom.y) {
//
//                    if(currentIndex == i) {
//                    csi.print(mapArray.get(i).overworldPoint.x-(visibleTop.x-1),
//                            mapArray.get(i).overworldPoint.y-(visibleTop.y -1),
//                        mapArray.get(i).overworldChar, CSIColor.RED);
//                    }
//
//                    else {
//
//                        csi.print(mapArray.get(i).overworldPoint.x-(visibleTop.x-1),
//                                mapArray.get(i).overworldPoint.y-(visibleTop.y-1),
//                                mapArray.get(i).overworldChar, mapArray.get(i).color);
//
//                    }
//
//            }
//
//                /*if (currentIndex == i) {
//                   csi.print(mapArray.get(i).overworldPoint.x + 5, mapArray.get(i).overworldPoint.y + 5,"#",CSIColor.RED);
//                }
//                else {
//                    csi.print(mapArray.get(i).overworldPoint.x + 5, mapArray.get(i).overworldPoint.y + 5,"#",CSIColor.GREEN);
//                }*/
//                //csi.refresh();
//            }
//
//            csi.refresh();
//            CharKey dir = csi.inkey();
//
//            if (dir.code == CharKey.q) {
//                displayActive = false;
//            }
//
//            if (dir.isUpArrow()) {
//                visibleTop.translate(0,-1);
//                visibleBottom.translate(0,-1);
//            }
//
//            if (dir.isDownArrow()) {
//                visibleTop.translate(0, 1);
//                visibleBottom.translate(0,1);
//            }
//            if (dir.isLeftArrow()) {
//                visibleTop.translate(-1,0);
//                visibleBottom.translate(-1, 0);
//            }
//            if (dir.isRightArrow()) {
//                visibleTop.translate(1,0);
//                visibleBottom.translate(1,0);
//            }
//        }
//    }
}