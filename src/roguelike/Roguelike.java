package roguelike;

import com.googlecode.blacken.colors.ColorNames;
import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.swing.SwingTerminal;
import com.googlecode.blacken.terminal.BlackenKeys;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import roguelike.character.Player;
import roguelike.display.DrawMap;
import roguelike.display.DrawUi;
import roguelike.map.Map;
import roguelike.map.generation.Feature;
import roguelike.map.generation.GenerateMap;
import roguelike.menu.MenuMain;

import java.awt.Point;
import java.util.ArrayList;

public class Roguelike {

    static boolean quit = false;

    public static void loop() {

        //Init all the terminal stuff
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

        MenuMain main = new MenuMain(false,palette);
        main.drawMenu(term, palette);

        //Handle all the map things.
        ArrayList<Feature> feat = new ArrayList<>();
        feat.add(new Feature(Feature.feature.grass_floor, 100));
        //Active map
        Map map = GenerateMap.generateNewMap(feat);
        ArrayList<Object> dummyItemArray = new ArrayList<>();
        int[] dummyArray = {0, 0, 0, 0, 0, 0, 0};
        Player player = new Player(dummyArray, new Point(10, 10), dummyItemArray, 0, 0, 0, "Echdah");

        boolean running = true;
        Point displayPoint = new Point((player.creaturePoint.x - (term.getWidth() / 2)),
                player.creaturePoint.y - (term.getHeight() / 2));

        while (running) {
            int yLen = term.getHeight();
            int xLen = term.getWidth();
            term.clear();
            //Contain all of our drawing garbage
            DrawMap.drawAllElements(displayPoint, map, player, term, xLen, yLen, palette);
            DrawUi.drawUI(displayPoint, map, player, term, xLen, yLen, palette);

            term.refresh();
            ch = term.getch();

            switch (ch) {
                case BlackenKeys.KEY_UP:
                    if (map.checkMove(Map.Direction.up, player.creaturePoint)) {
                        map.moveCharacter(player, Map.Direction.up);
                        displayPoint.translate(0, -1);
                        Global.log.addLog("You went up");
                    }
                    break;
                case BlackenKeys.KEY_DOWN:
                    if (map.checkMove(Map.Direction.down, player.creaturePoint)) {
                        map.moveCharacter(player, Map.Direction.down);
                        displayPoint.translate(0, 1);
                        Global.log.addLog("You went down");
                    }
                    break;
                case BlackenKeys.KEY_LEFT:
                    if (map.checkMove(Map.Direction.left, player.creaturePoint)) {
                        map.moveCharacter(player, Map.Direction.left);
                        displayPoint.translate(-1, 0);
                    }
                    break;
                case BlackenKeys.KEY_RIGHT:
                    if (map.checkMove(Map.Direction.right, player.creaturePoint)) {
                        map.moveCharacter(player, Map.Direction.right);
                        displayPoint.translate(1, 0);
                    }
                    break;
                case BlackenKeys.KEY_ESCAPE:
                    running = false;
                    quit = true;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {

        Global.setUpGlobal();
        loop();
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

//        //displayPoint is the top left corner of the display screen.
//        Point displayPoint = new Point(0,0);
//
//        int sizeX = 50;
//        int sizeY = 50;
//        MapTiny[][] mt = GenerateOverworld.createOverworld(sizeX, sizeY);
//        term.setCursorLocation(10,10);
//        while (!quit) {
//            int yLen = term.getHeight() - 1;
//            int xLen = term.getWidth();
//            // getch automatically does a refresh
//            ch = term.getch();
//
//            term.clear();
//            for(int i = displayPoint.x; i < (displayPoint.x + xLen); ++i)
//            {
//                for(int j = displayPoint.y + 2; j < (displayPoint.y + yLen); ++j)
//                {
//                    if(i < sizeX && j < sizeY && i >= 0 && j >= 0) {
//                        if (!mt[i][j].isEmpty) {
//                            try {
//                                //term.mvputs(i - displayPoint.x, j - displayPoint.y, mt[i][j].symbol + "");
//                                term.set( j - displayPoint.y,i - displayPoint.x, mt[i][j].symbol, 0x11, 0x4);
//                            } catch (Exception ex) {
//                                System.out.println("DONE GOOFED!");
//                            }
//                        }
//                    }
//                }
//            }
//
//            if(ch == BlackenKeys.KEY_RIGHT)
//            {
//                displayPoint.translate(1, 0);
//            }
//            else if(ch == BlackenKeys.KEY_LEFT)
//            {
//                displayPoint.translate(-1, 0);
//            }
//            else if(ch == BlackenKeys.KEY_DOWN)
//            {
//                displayPoint.translate(0, 1);
//            }
//            else if(ch == BlackenKeys.KEY_UP)
//            {
//                displayPoint.translate(0, -1);
//            }
//
//            if (ch == BlackenKeys.KEY_F10) {
//                quit = true;
//            }
//        }
    }
 }
