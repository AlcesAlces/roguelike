package roguelike.Items;

public class ItemScroll extends Item{

    int itemType;
    Age age;
    public Effect effect;
    Material material;
    public boolean identified = false;
    
    ItemScroll(Age age, Effect effect, Material material, int itemType, boolean identified) {
        super(itemType, identified);
        
        this.identified = identified;
        this.itemType = itemType;
        this.age = age;
        this.effect = effect;
        this.material = material;
        this.itemType = itemType;
    }
    
    enum Age {
        New(1), Worn(2), Old(3), TimeWorn(4), Ancient(5), Timeless(6);
        
        private int ageValue;
        
        private Age (int ageReturn) {
            this.ageValue = ageReturn;
        }
        
        public int returnAgeValue() {
            return ageValue;
        }
    }
    
    public enum Effect {
        EnhanceAttribute(1), DamageAttribute(2), Identify(3), Curse(4);
        
        private int effectValue;
        
        private Effect (int effectReturn) {
            this.effectValue = effectReturn;
        }
        
        public int returnEffectValue() {
            return effectValue;
        }
    }
    
    enum Material {
        Paper(1), Parchment(2), Leather(3), BloodSoakedFlesh(4);
        
        private int materialValue;
        
        private Material (int materialReturn) {
            this.materialValue = materialReturn;
        }
        
        public int returnMaterialValue() {
            return materialValue;
        }
    }

    public Age getAge() {
        return age;
    }

    public Effect getEffect() {
        return effect;
    }

    public Material getMaterial() {
        return material;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
    @Override
    public String toString() {
        
        if (identified) {
            return (age + " " + material + " scroll of " + effect);
        }
        else {
            return (age + " " + material + " scroll");
        }
    }
}
