package roguelike.character;

import roguelike.creatures.Creature;
import roguelike.magic.MagicSpells;

import java.awt.Point;
import java.util.ArrayList;

public class Player extends Creature {
    
    // Stats 1- 7: health, mana, strength, dexterity, endurance, intelligence, wisdom
    // Equipment Stats 1-4: AC, AP, Dodge, Hit
    
    public Point frameLocUp;
    public Point frameLocDown;
    public Point overworldLocUp;
    public Point overworldLocDown;
    public MagicSpells lastCastSpell;
    
    public Player(int[] stats, Point playerPoint, ArrayList<Object> playerInventory, int raceID
            , int classID, int actionPoints, String name) {
        super(stats, playerPoint, raceID, actionPoints, name);
        
        this.symbol = '@';
    }
    
}
