package roguelike;

import com.googlecode.blacken.colors.ColorNames;
import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.swing.SwingTerminal;
import com.googlecode.blacken.terminal.BlackenKeys;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import com.sun.istack.internal.Nullable;
import roguelike.character.Player;
import roguelike.creatures.MonsterAI;
import roguelike.creatures.race_class.Race;
import roguelike.display.DrawMap;
import roguelike.display.DrawUi;
import roguelike.display.OverworldDisplay;
import roguelike.file.FileWriter;
import roguelike.map.Map;
import roguelike.map.Overworld;
import roguelike.map.generation.Feature;
import roguelike.menu.ContinueMenu;
import roguelike.menu.Menu;
import roguelike.menu.MenuCreateCharacter;
import roguelike.menu.MenuMain;

import java.awt.Point;
import java.util.ArrayList;

public class Roguelike {

    static boolean quit = false;
    static boolean monsterAIToggle = true;

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

        boolean mainMenu = true;
        Overworld ow = null;

        while(mainMenu) {
            //Main menu section.
            MenuMain main = new MenuMain(false, palette);
            main.drawMenu(term, palette);

            //Handle map init based on user selections in main menu.
            ow = handleContent(main.responseIndex, term, palette);

            if (ow != null) {
                mainMenu = false;
            }
        }

        //Active player
        Player player = Global.player;
        boolean running = true;
        while (running) {

            //Sets up where we draw the center of the screen.
            Point displayPoint = new Point((player.point.x - (term.getWidth() / 2)),
                    player.point.y - (term.getHeight() / 2));

            int yLen = term.getHeight();
            int xLen = term.getWidth();
            term.clear();
            //Contain all of our drawing garbage
            DrawMap.drawAllElements(displayPoint, ow.getCurrentMap(), player, term, xLen, yLen, palette);
            DrawUi.drawUI(displayPoint, ow.getCurrentMap(), player, term, xLen, yLen, palette);

            term.refresh();
            ch = term.getch();
            boolean playerActed = false;

            switch (ch) {
                case BlackenKeys.KEY_UP:
                    if (ow.getCurrentMap().checkMove(Map.Direction.up, player.point)) {
                        ow.getCurrentMap().moveCharacter(player, Map.Direction.up, ow);
                        displayPoint.translate(0, -1);
                        Global.log.addLog("You went up");
                        playerActed = true;
                    }
                    break;
                case BlackenKeys.KEY_DOWN:
                    if (ow.getCurrentMap().checkMove(Map.Direction.down, player.point)) {
                        ow.getCurrentMap().moveCharacter(player, Map.Direction.down, ow);
                        displayPoint.translate(0, 1);
                        Global.log.addLog("You went down");
                        playerActed = true;
                    }
                    break;
                case BlackenKeys.KEY_LEFT:
                    if (ow.getCurrentMap().checkMove(Map.Direction.left, player.point)) {
                        ow.getCurrentMap().moveCharacter(player, Map.Direction.left, ow);
                        displayPoint.translate(-1, 0);
                        playerActed = true;
                    }
                    break;
                case BlackenKeys.KEY_RIGHT:
                    if (ow.getCurrentMap().checkMove(Map.Direction.right, player.point)) {
                        ow.getCurrentMap().moveCharacter(player, Map.Direction.right, ow);
                        displayPoint.translate(1, 0);
                        playerActed = true;
                    }
                    break;
                case BlackenKeys.KEY_ESCAPE:
                    running = false;
                    quit = true;
                    break;
                case 'x':
                    monsterAIToggle = !monsterAIToggle;
                    System.out.println("AI has been toggled");
                    break;
                case (int)'m':
                    OverworldDisplay.displayOverworld(ow,ow.sizeX, ow.sizeY, term, Global.player.overworldPoint, palette);
                default:
                    break;
            }

            if(playerActed && monsterAIToggle){
                MonsterAI.CreatureAction(player,ow.getCurrentMap());
            }
        }
    }

    //Decides how to proceed
    @Nullable
    public static Overworld handleContent(int resp, CursesLikeAPI term, ColorPalette palette){

        Overworld toReturn = null;
        switch(resp) {
            case 0:
                //continue
                ContinueMenu cm = new ContinueMenu(palette);
                cm.drawMenu(term,palette);
                toReturn = cm.loadedOverworld;
                break;
            case 1:
                //New game
                //Create Character.

                int defaultX = 50;
                int defaultY = 50;
                toReturn = new Overworld(defaultX, defaultY);
                toReturn.FillOverworld();
                MenuCreateCharacter m = new MenuCreateCharacter(palette);
                m.drawMenu(term, palette);
                break;
            case 2:
                break;
            case 3:

                toReturn = new Overworld(50, 50);
                toReturn.FillOverworld();
                //Generate user's first map
                Point rand = toReturn.findRandomCreatureStart();
                Global.player = new Player(1, new Race(Race.RACE.elf));
                Global.player.overworldPoint = rand;
                Global.player.point = new Point(10,10);
                Global.player.name = "echdah";
                Global.saveDir = "echdah-save";
                toReturn.SwapInMap(rand);
                FileWriter.savePlayer(Global.player);
                FileWriter.saveOverwolrd(toReturn);
                break;
            default:
                break;
        }

        return toReturn;
    }

    public static void main(String[] args) {

        Global.setUpGlobal();
//        Global.saveDir = "echdah-save";
//
//        ArrayList<Feature> feature = new ArrayList<>();
//        feature.add(new Feature(Feature.feature.grass_floor, 100));
//        Map t = GenerateMap.generateNewMap(feature, new Overworld(1,1));
//        t.pos = new Position(1,1);
//
//        FileWriter.saveMap(t);
//
//        Overworld o = new Overworld(5,5);
//        o.allMaps = GenerateOverworld.createOverworld(5,5);
//
//        FileWriter.saveOverwolrd(o);
//
//        Player player = new Player(new int[8], new Point(0,0), new ArrayList<Object>(), 0, 0, 0, "echdah");
//        FileWriter.savePlayer(player);

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
