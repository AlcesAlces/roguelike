package roguelike;

import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class DrawMonsters {
    ArrayList<Creature> monsterList;
    DrawMonsters(ArrayList<Creature> monsterList) {
        this.monsterList = monsterList;
    }
    
    void drawMonstersOnMap(ConsoleSystemInterface csi) {
        for (int i = 0; i < monsterList.size(); i++) {
            csi.print(monsterList.get(i).creaturePoint.x, 
                    monsterList.get(i).creaturePoint.y, "G", CSIColor.PINK);
        }
    }
}
    