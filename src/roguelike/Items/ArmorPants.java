package roguelike.Items;

public class ArmorPants extends Item {
    
    public boolean identified;
    int itemType = 4;
    
    ArmorPants(Origin origin, Modifier mod, Type type, int itemType, boolean identified) {
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

    Type getType() {
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
    
    enum Type {
        Cloth(1), Leather(2), ScaleMail(3), PlateMail(4);
        
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
        Dwarfish(1), Orcish(2), Human(3), Elvish(4), FlyingNunish(5);
        
        private int weaponOrigin;
        
        private Origin(int originReturn) {
            this.weaponOrigin = originReturn;
        }
        
        public int getOriginValue() {
            return weaponOrigin;
        }
    }
    
    public int totalArmorValue() {
        return this.origin.getOriginValue() + this.type.getWeight() +
                this.mod.getModValue();
    }
    
    public int armorACValue() {
        return (this.type.getWeight() - this.origin.getOriginValue() +
                this.mod.getModValue()) / 2;
    }
    
    private Origin origin;
    private Type type;
    private Modifier mod;

    @Override
    public String toString() {
        if ((type.weight == 1) | (type.weight == 2)) {
            
            return mod + " " + origin + " " + type + " pants";
        }
        else
            return mod + " " + origin + " " + type + " greaves";
        
    }
}