package roguelike;

import com.googlecode.blacken.colors.ColorNames;
import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.swing.SwingTerminal;
import com.googlecode.blacken.terminal.BlackenKeys;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.util.Position;
import roguelike.character.Player;
import roguelike.display.MainMenu;
import roguelike.file.FileWriter;
import roguelike.map.Map;
import roguelike.map.MapTiny;
import roguelike.map.MapType;
import roguelike.map.Overworld;
import roguelike.map.generation.Feature;
import roguelike.map.generation.FeatureGenerator;
import roguelike.map.generation.GenerateMap;
import roguelike.map.generation.GenerateOverworld;
import roguelike.pathfinding.OverworldPathfinder;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Roguelike {

    static boolean quit = false;

    public static void main(String[] args) {

        //MovementCalculator test = new MovementCalculator();

        //test.initializeMovement();

        //Screen writing testing:



//        ConsoleSystemInterface csi = null;
//
//        try {
//
//            csi = new WSwingConsoleInterface("Planes of Endatl", true);
//        }
//        catch (ExceptionInInitializerError eiie) {
//            System.out.println("*** Error: Swing Console Box cannot be initialized. Exiting...");
//            eiie.printStackTrace();
//            System.exit(-1);
//        }
//
//        boolean mainMenuStep = true;
//
//        //All of the main menu/creation stuff goes in here.
//        while(mainMenuStep) {
//
//            ArrayList<Object> dummyItemArray = new ArrayList<>();
//            int[] dummyArray = {0, 0, 0, 0, 0, 0, 0
//            };
//            Player player = new Player(dummyArray, new Point(35, 10), dummyItemArray, 0, 0, 0, "Echdah");
//            MainMenu mm = new MainMenu();
//            mm.drawMainMenu(csi, player);
//
//            if(Global.player != null)
//            {
//                mainMenuStep = false;
//            }
//        }
//
//        ArrayList<Feature> features = new ArrayList<>();
//        features.add(new Feature(Feature.feature.grass_floor, 100));
//        Map map = GenerateMap.generateNewMap(features);
//        FeatureGenerator.generateFeatures(map);
//
//
//        int xMid = 40;
//        int yMid = 12;
//
//        int xLen = 80;
//        int yLen = 25;
//
//        Point displayPoint = Global.player.creaturePoint;
//
//        for(int i = displayPoint.x; i < (displayPoint.x + xLen); ++i)
//        {
//            for(int j = displayPoint.y; j < (displayPoint.y + yLen); ++j)
//            {
//                if(i < map.x && j < map.y && i >= 0 && j >= 0) {
//                    if (map.tiles[i][j].generated) {
//
//                        try {
//                            csi.print(i - displayPoint.x, j - displayPoint.y, map.tiles[i][j].symbol + "", map.tiles[i][j].color);
//                        } catch (Exception ex) {
//                            System.out.println("DONE GOOFED!");
//                        }
//                    }
//                }
//            }
//        }
//        csi.cls();
//        csi.refresh();

        CursesLikeAPI term = new CursesLikeAPI(new SwingTerminal());
        term.init("Testing", 25, 80);

        ColorPalette palette = new ColorPalette();
        palette.addAll(ColorNames.XTERM_256_COLORS, false);
        palette.putMapping(ColorNames.SVG_COLORS);
        term.setPalette(palette);

        int ch = BlackenKeys.NO_KEY;
        if (palette.containsKey("Black")) {
            term.setCurBackground("Black");
        }
        if (palette.containsKey("White")) {
            term.setCurForeground("White");
        }

        int xLen = term.getHeight();
        int yLen = term.getWidth();

        Point displayPoint = new Point(0,0);

        int sizeX = 50;
        int sizeY = 50;
        MapTiny[][] mt = GenerateOverworld.createOverworld(sizeX, sizeY);

        while (!quit) {
            // getch automatically does a refresh
            ch = term.getch();

            term.clear();
            for(int i = displayPoint.x; i < (displayPoint.x + xLen); ++i)
            {
                for(int j = displayPoint.y; j < (displayPoint.y + yLen); ++j)
                {
                    if(i < sizeX && j < sizeY && i >= 0 && j >= 0) {
                        if (!mt[i][j].isEmpty) {

                            try {
                                term.mvputs(i - displayPoint.x, j - displayPoint.y, mt[i][j].symbol + "");
                            } catch (Exception ex) {
                                System.out.println("DONE GOOFED!");
                            }
                        }
                    }
                }
            }

            if(ch == BlackenKeys.KEY_RIGHT)
            {
                displayPoint.translate(0, 1);
            }
            else if(ch == BlackenKeys.KEY_LEFT)
            {
                displayPoint.translate(0, -1);
            }
            else if(ch == BlackenKeys.KEY_DOWN)
            {
                displayPoint.translate(1, 0);
            }
            else if(ch == BlackenKeys.KEY_UP)
            {
                displayPoint.translate(-1, 0);
            }

            if (ch == BlackenKeys.KEY_F10) {
                quit = true;
            }
        }
        term.refresh();
    }
 }
