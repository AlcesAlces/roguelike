package roguelike.Items;

import roguelike.Items.ItemPotion;

import java.util.ArrayList;

public class ItemPotionGenerator {
    
    ArrayList<ItemPotion> potionStorage = new ArrayList();
    
    public ItemPotion returnPotion(int index) {
        return potionStorage.get(index);
    }
    
    public ItemPotionGenerator() {
        
        potionStorage = new ArrayList<>();
        
        for (int i = 0; i < ItemPotion.Color.values().length; i++) {
            for (int j = 0; j < ItemPotion.Visual.values().length; j++) {
                for (int k = 0; k < ItemPotion.Effect.values().length; k++) {
                
                    ItemPotion potion = new ItemPotion(ItemPotion.Color.values()[i],
                            ItemPotion.Effect.values()[k], ItemPotion.Visual.values()[j], 6, false);
                    
                    potionStorage.add(potion);
                    potion.toString();
            
                }
        
            }
        
        }
    }
}
