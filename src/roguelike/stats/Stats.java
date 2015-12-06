package roguelike.stats;
//health, mana, strength, dexterity, endurance, intelligence, wisdom
public class Stats {

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

    private double health;
    private double mana;
    private double strength;
    private double dexterity;
    private double endurance;
    private double intelligence;
    private double wisdom;

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
}
