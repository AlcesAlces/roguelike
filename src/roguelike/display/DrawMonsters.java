package roguelike.display;

import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import roguelike.creatures.Creature;

public class DrawMonsters {
    ArrayList<Creature> monsterList;
    public DrawMonsters(ArrayList<Creature> monsterList) {
        this.monsterList = monsterList;
    }
    
    public void drawMonstersOnMap(ConsoleSystemInterface csi) {
        for (int i = 0; i < monsterList.size(); i++) {
            csi.print(monsterList.get(i).creaturePoint.x, 
                    monsterList.get(i).creaturePoint.y, "G", CSIColor.PINK);
        }
    }
}
    