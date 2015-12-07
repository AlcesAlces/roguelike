package roguelike.stats;

import roguelike.creatures.race_class.Race;

import java.io.Serializable;

//health, mana, strength, dexterity, endurance, intelligence, wisdom
public class Stats implements Serializable {

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getDexterity() {
        return dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public double getEndurance() {
        return endurance;
    }

    public void setEndurance(double endurance) {
        this.endurance = endurance;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public double getWisdom() {
        return wisdom;
    }

    public void setWisdom(double wisdom) {
        this.wisdom = wisdom;
    }

    private double health= 0.0 ;
    private double mana= 0.0;
    private double strength= 0.0;
    private double dexterity= 0.0;
    private double endurance= 0.0;
    private double intelligence= 0.0;
    private double wisdom= 0.0;

    public Stats(){

    }

    public Stats(double[] stats){
        health = stats[0];
        mana = stats[1];
        strength = stats[2];
        dexterity = stats[3];
        endurance = stats[4];
        intelligence = stats[5];
        wisdom = stats[6];
    }

    public Stats(int level, Race race){

        health = race.starting.health;
        mana = race.starting.mana;
        strength = race.starting.strength;
        dexterity = race.starting.dexterity;
        endurance = race.starting.endurance;
        intelligence = race.starting.intelligence;
        wisdom = race.starting.wisdom;

        for(int i = 1; i <= level; i++){

            health += race.progression.health;
            mana += race.progression.mana;
            strength += race.progression.strength;
            dexterity += race.progression.dexterity;
            endurance += race.progression.endurance;
            intelligence += race.progression.intelligence;
            wisdom += race.progression.wisdom;
        }
    }
}
