package roguelike;

import roguelike.character.Player;

import java.util.HashMap;
import java.util.Map;

public class Global {
    public static Player player = null;
    public static Map<String, Integer> config = new HashMap<>();
    public static roguelike.display.EventLog log = new roguelike.display.EventLog();
    public static int toplines = 2;
    public static int bottomlines = 1;
    public static int maxLength = 80;
    public static boolean debug = true;

    public static void setUpGlobal()
    {
        populateConfig();
    }

    private static void populateConfig()
    {
        //From one of the blacken examples:
        config.put("diggable", "\u2592".codePointAt(0)); // 50% shade
        config.put("floor", "\u25AA".codePointAt(0)); // small black square
        config.put("hall:floor", "\u25AB".codePointAt(0)); // sm. white square
        config.put("hall:wall", "\u2591".codePointAt(0)); // 25% shade
        config.put("room:door", "+".codePointAt(0));
        config.put("room:wall:top", "\u2500".codePointAt(0)); // light horiz
        config.put("room:wall:left", "\u2502".codePointAt(0)); // light vert
        config.put("room:wall:bottom", "\u2550".codePointAt(0)); // heavy horiz
        config.put("room:wall:right", "\u2551".codePointAt(0)); // heavy horiz
        config.put("room:wall:top-left", "\u250C".codePointAt(0)); // Lh/Lv
        config.put("room:wall:top-right", "\u2556".codePointAt(0)); // Lh/Hv
        config.put("room:wall:bottom-left", "\u2558".codePointAt(0)); // Hh/Lv
        config.put("room:wall:bottom-right", "\u255D".codePointAt(0)); // Hv/Hh

        // game specific
        config.put("void", " ".codePointAt(0));
        config.put("player", "@".codePointAt(0));
        config.put("water", "~".codePointAt(0));
        config.put("mountains", "^".codePointAt(0));
    }
}
