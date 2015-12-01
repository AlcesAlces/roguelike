package roguelike.display;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import roguelike.map.MapTiny;

import java.awt.*;

public class OverworldDisplay {

    public static void displayOverworld(MapTiny[][] mt, int sizeX, int sizeY, ConsoleSystemInterface csi)
    {

        //How big is the screen? Use these.
        int xLen = 80;
        int yLen = 25;

        boolean mapping = true;
        Point displayPoint = new Point(0,0);

        while(mapping)
        {
            csi.cls();
            for(int i = displayPoint.x; i < (displayPoint.x + xLen); ++i)
            {
                for(int j = displayPoint.y; j < (displayPoint.y + yLen); ++j)
                {
                    if(i < sizeX && j < sizeY && i >= 0 && j >= 0) {
                        if (!mt[i][j].isEmpty) {

                            try {
                                csi.print(i - displayPoint.x, j - displayPoint.y, mt[i][j].symbol + "");
                            } catch (Exception ex) {
                                System.out.println("DONE GOOFED!");
                            }
                        }
                    }
                }
            }
            csi.refresh();
            CharKey dir = csi.inkey();

            if(dir.isRightArrow())
            {
                displayPoint.translate(1, 0);
            }
            else if(dir.isLeftArrow())
            {
                displayPoint.translate(-1, 0);
            }
            else if(dir.isDownArrow())
            {
                displayPoint.translate(0, 1);
            }
            else if(dir.isUpArrow())
            {
                displayPoint.translate(0, -1);
            }
            else if(dir.code == CharKey.q)
            {
                mapping = false;
            }
        }
    }
}
