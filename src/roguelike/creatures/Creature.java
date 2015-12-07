package roguelike.creatures;

import roguelike.creatures.race_class.Race;
import roguelike.stats.Level;
import roguelike.stats.Stats;

import java.awt.Point;
import java.io.Serializable;

// Equipment Stats 1-8: AC, AP, Dodge, Hit, Magic Defense, Magic Attack, Potency
// Stats 1- 7: health, mana, strength, dexterity, endurance, intelligence, wisdom
// Weapon Skills: Unarmed, Blunt1h, Bladed1h, Point1h, Blunt2h, Blade2h, Point2h
// Bonus Melee abilities: Power Attack, Double Attack
// Bonus Magic Abilities: 

public class Creature implements Serializable{

    public Point point;
    public String name;
    private Stats base;
    private Stats equiped;
    private Race race;
    public Level level;

    //Persuit stuff
    public boolean aggro = false;
    public int aggroRange = 10;

    char symbol = '?';
    String color = "Blue";

    public Creature(int level, Race race){
        this.level = new Level(level);
        this.race = race;
        base = new Stats(this.level.level, this.race);
    }

}