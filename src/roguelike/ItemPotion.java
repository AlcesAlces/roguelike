package roguelike;

public class ItemPotion extends Item{
    
    boolean drinkable = true;
    boolean identified;
    Color color;
    Visual visual;
    Effect effect;
    int itemType = 6;
    
    ItemPotion(Color color, Effect effect, Visual visual, int itemType, boolean identified) {
        super (itemType, identified);
        this.itemType = itemType;
        this.identified = identified;
        this.color = color;
        this.visual = visual;
        this.effect = effect;
    }

    public Color getColor() {
        return color;
    }

    public Visual getVisual() {
        return visual;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setVisual(Visual visual) {
        this.visual = visual;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
    
    enum Color {
        
        Pink(1), Purple(2), Orange(3), Red(4), Blue(5), Black(6);
        
        private int colorIdentifier;
        
        private Color(int colorToReturn) {
            this.colorIdentifier = colorToReturn;
        }
        
        public int getColor() {
            return colorIdentifier;
        }
        
    }
    
    enum Effect {
        
        Healing(1), Mana(2), Curse(3);
        
        private int effectIdentifier;
        
        private Effect(int effectToReturn) {
            this.effectIdentifier = effectToReturn;
        }
        
        public int getEffect() {
            return effectIdentifier;
        }
    }
    
    enum Visual {
        
        Swirling(1), Bubbling(2), Steaming(3);
        
        private int visualIdentifier;
        
        private Visual(int visualToReturn) {
            this.visualIdentifier = visualToReturn;
        }
        
        public int getVisual() {
            return visualIdentifier;
        }
    }
    
    @Override
    public String toString() {
        
        if (identified) {
            return color + " " + visual + " " + effect + " potion";
        }
        
        else {
            return color + " " + visual + " potion";
        }
    }
}
