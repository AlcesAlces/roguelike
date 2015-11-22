package roguelike;

import java.awt.Point;
import java.util.ArrayList;

public class Player extends Creature {
    
    // Stats 1- 7: health, mana, strength, dexterity, endurance, intelligence, wisdom
    // Equipment Stats 1-4: AC, AP, Dodge, Hit
    
    Point frameLocUp;
    Point frameLocDown;
    Point overworldLocUp;
    Point overworldLocDown;
    MagicSpells lastCastSpell;
    
    public Player(int[] stats, Point playerPoint, ArrayList<Object> playerInventory, int raceID
            , int classID, int actionPoints, String name) {
        super(stats, playerPoint, raceID, actionPoints, name);
        
        this.symbol = '@';
    }
    
}
