package roguelike.display;

import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import roguelike.Global;
import roguelike.character.Player;
import roguelike.map.Map;

import java.awt.*;
import java.util.ArrayList;

public class DrawUi {

    public static void drawUI(Point displayPoint, Map map, Player player, CursesLikeAPI term, int xLen,
                              int yLen, ColorPalette palette)
    {
        ArrayList<String> messages = Global.log.getShortLogString(Global.maxLength, Global.toplines);
        //Draw the event log at the top.
        for (int i = 0; i < Global.toplines; i++) {
            if (messages.size() > i) {
                term.mvputs(i, 0, messages.get(i));
            }
        }
    }
}
