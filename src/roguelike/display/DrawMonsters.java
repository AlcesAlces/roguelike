package roguelike.display;

import java.awt.*;
import java.util.ArrayList;

import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import roguelike.creatures.Creature;

public class DrawMonsters {
    ArrayList<Creature> monsterList;
    public DrawMonsters(ArrayList<Creature> monsterList) {
        this.monsterList = monsterList;
    }
    
    public void drawMonstersOnMap(CursesLikeAPI term, ColorPalette palette, Rectangle rect, Point displayPoint) {
        for (int i = 0; i < monsterList.size(); i++) {
            if(rect.contains(monsterList.get(i).point)) {
                int y = monsterList.get(i).point.y - displayPoint.y;
                if(y > 1) {
                    term.set(y,
                            monsterList.get(i).point.x - displayPoint.x,
                            "G", palette.get("Blue"), palette.get("Black"));
                }
            }
        }
    }
}
    