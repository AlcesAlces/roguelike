package roguelike.display;

import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.terminal.BlackenKeys;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import roguelike.map.Overworld;

import java.awt.*;

public class OverworldDisplay {

    public static void displayOverworld(Overworld ow, int sizeX, int sizeY, CursesLikeAPI term, Point playerPos,
                                        ColorPalette palette)
    {
        Point displayPoint = new Point(0,0);

        boolean quit = false;
        while (!quit) {
            int yLen = term.getHeight() - 1;
            int xLen = term.getWidth();
            // getch automatically does a refresh


            term.clear();
            for(int i = displayPoint.x; i < (displayPoint.x + xLen); ++i)
            {
                for(int j = displayPoint.y + 2; j < (displayPoint.y + yLen); ++j)
                {
                    if(i < sizeX && j < sizeY && i >= 0 && j >= 0) {
                        if (!ow.allMaps[i][j].isEmpty) {
                            try {
                                if(playerPos.equals(new Point(i,j)))
                                {
                                    term.set(j - displayPoint.y, i - displayPoint.x, ow.allMaps[i][j].symbol, palette.get("Red"), 0x4);
                                }
                                else {
                                    term.set(j - displayPoint.y, i - displayPoint.x, ow.allMaps[i][j].symbol, 0x11, 0x4);
                                }
                            } catch (Exception ex) {
                                System.out.println("DONE GOOFED!");
                            }
                        }
                    }
                }
            }

            term.refresh();
            int ch = term.getch();

            if(ch == BlackenKeys.KEY_RIGHT)
            {
                displayPoint.translate(1, 0);
            }
            else if(ch == BlackenKeys.KEY_LEFT)
            {
                displayPoint.translate(-1, 0);
            }
            else if(ch == BlackenKeys.KEY_DOWN)
            {
                displayPoint.translate(0, 1);
            }
            else if(ch == BlackenKeys.KEY_UP)
            {
                displayPoint.translate(0, -1);
            }

            if (ch == BlackenKeys.KEY_ESCAPE) {
                quit = true;
            }
        }


//        //How big is the screen? Use these.
//        int xLen = 80;
//        int yLen = 25;
//
//        boolean mapping = true;
//        Point displayPoint = new Point(0,0);
//
//        while(mapping)
//        {
//            term.clear();
//            for(int i = displayPoint.x; i < (displayPoint.x + xLen); ++i)
//            {
//                for(int j = displayPoint.y; j < (displayPoint.y + yLen); ++j)
//                {
//                    if(i < sizeX && j < sizeY && i >= 0 && j >= 0) {
//                        if (!ow.allMaps[i][j].isEmpty) {
//
//                            try {
//                                term.mvputs(i - displayPoint.x, j - displayPoint.y, ow.allMaps[i][j].symbol);
//                            } catch (Exception ex) {
//                                System.out.println("DONE GOOFED!");
//                            }
//                        }
//                    }
//                }
//            }
//            term.refresh();
//            int ch = term.getch();
//
//            switch (ch) {
//                case BlackenKeys.KEY_UP:
//                    displayPoint.translate(0, -1);
//                    break;
//                case BlackenKeys.KEY_DOWN:
//                    displayPoint.translate(0, 1);
//                    break;
//                case BlackenKeys.KEY_LEFT:
//                    displayPoint.translate(-1, 0);
//                    break;
//                case BlackenKeys.KEY_RIGHT:
//                    displayPoint.translate(1, 0);
//                    break;
//                case BlackenKeys.KEY_ESCAPE:
//                    mapping = false;
//                default:
//                    break;
//            }
//        }
    }
}
