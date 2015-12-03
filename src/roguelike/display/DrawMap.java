package roguelike.display;

import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import roguelike.character.Player;
import roguelike.map.Map;

import java.awt.*;

public class DrawMap {
    public static void drawAllElements(Point displayPoint, Map map, Player player, CursesLikeAPI term, int xLen,
                                        int yLen, ColorPalette palette)
    {
        for (int i = displayPoint.x; i < (displayPoint.x + xLen); ++i) {
            for (int j = displayPoint.y + 2; j < (displayPoint.y + yLen); ++j) {
                if (i < map.x && j < map.y && i >= 0 && j >= 0) {
                    if (map.tiles[i][j].generated) {
                        try {
                            //term.mvputs(i - displayPoint.x, j - displayPoint.y, mt[i][j].symbol + "");
                            map.tiles[i][j].setBoarder(term.get(j - displayPoint.y, i - displayPoint.x));
                            if (player.creaturePoint.equals(new Point(i, j))) {
                                term.set(j - displayPoint.y, i - displayPoint.x, "@", palette.get("White"), palette.get("Black"));
                            } else {
                                term.set(j - displayPoint.y, i - displayPoint.x, map.tiles[i][j].symbol, palette.get(map.tiles[i][j].color), palette.get("Black"));
                            }
                        } catch (Exception ex) {
                            System.out.println("DONE GOOFED!");
                        }
                    }
                }
            }
        }
    }
}
