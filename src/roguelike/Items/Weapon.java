package roguelike.Items;

import roguelike.Items.Item;

public class Weapon extends Item {
    
    public boolean identified;
    int itemType = 5;
    
    Weapon(Origin origin, Modifier mod, Type type, int itemType, boolean identified){
    super (itemType, identified);
    this.identified = identified;
    this.itemType = itemType;
        this.origin = origin;
        this.type = type;
        this.mod = mod;
    }
    
    void setOrigin(Origin origin) {
        this.origin = origin;
    }

    Origin getOrigin() {
        return origin;
    }

    public Type getType() {
        return type;
    }

    void setType(Type type) {
        this.type = type;
    }

    void setMod(Modifier mod) {
        this.mod = mod;
    }

    Modifier getMod() {
        return mod;
    }
    // Weapon Skills: Unarmed, Blunt1h, Bladed1h, Point1h, Blunt2h, Blade2h, Point2h
    
    public enum Type {
        ShortSword(2), Axe(2), Flail(1), Mace(1), Longsword(2), Hammer(1), Polearm(6),
        Poleaxe(5), Halberd(5), Polehammer(4), Dagger(3), Dirk(3);
        
        private int weight;
        
        private Type (int weightReturn) {
            this.weight = weightReturn;
        }
        
        public int getWeight() {
            return weight;
        }
    }
    
    enum Modifier {
        Terrible(1), Poor(2), Bad(3), Normal(4), Decent(5), Excellent(6), 
        Great(7), Fantastic(8), Stupendous(9), Superb(10), Awesome(11), 
        Earthshattering(12), Godly(13);
        
        private int weaponModValue;
        
        private Modifier(int modReturn) {
            this.weaponModValue = modReturn;
        }
        
        public int getModValue() {
            return weaponModValue;
        }
    }
    
    enum Origin {
        Orcish(4), Human(3), Elvish(2), FlyingNunish(1);
        
        private int weaponOrigin;
        
        private Origin(int originReturn) {
            this.weaponOrigin = originReturn;
        }
        
        public int getOriginValue() {
            return weaponOrigin;
        }
    }
    
    /*
     * Total weapon value acts like rarity for non-rare items.
     */
    public int totalWeaponValue() {
        return this.origin.getOriginValue() + this.mod.getModValue();
    }
    
    int weaponWeight() {
        return this.type.weight;
    }
    
    private Origin origin;
    private Type type;
    private Modifier mod;

    @Override
    public String toString() {
        return mod + " " + origin + " " + type;
    }
    // Weapon Skills: Unarmed, Blunt1h, Bladed1h, Point1h, Blunt2h, Blade2h, Point2h
    public String weaponDamageTypeToString() {
        
        String stringToReturn = "";
        
        switch(damageType(this.type.getWeight())) {
            
            case 0:
                stringToReturn = "Blunt";
                return stringToReturn;
            case 1:
                stringToReturn = "Slashing";
                return stringToReturn;
            case 2:
                stringToReturn = "Piercing";
                return stringToReturn;
        }
        return stringToReturn;
    }
    // Blunt, slashing, piercing
    public int damageType(int weaponWeight) {
        
        int damageType = 0;
        
        switch (weaponWeight) {
            case 0:
                damageType = 0;
                return damageType;
            case 1:
                damageType = 0;
                return damageType; 
            case 2:
                damageType = 1;
                return damageType;
            case 3:
                damageType = 2;
                return damageType;
            case 4:
                damageType = 0;
                return damageType;
            case 5:
                damageType = 1;
                return damageType;
            case 6:
                damageType = 2;
                return damageType;
        }
        return damageType;
    }
    
    
}
