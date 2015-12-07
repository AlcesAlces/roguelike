package roguelike.character;

import roguelike.creatures.Creature;
import roguelike.creatures.race_class.Race;
import roguelike.magic.MagicSpells;

import java.awt.Point;
import java.util.ArrayList;

public class Player extends Creature{

    public Point overworldPoint = new Point(0,0);
    public Player(int level, Race race){
        super(level, race);
    }
}