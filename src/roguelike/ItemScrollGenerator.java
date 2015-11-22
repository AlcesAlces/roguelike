package roguelike;

import java.util.ArrayList;

public class ItemScrollGenerator {
    
    ArrayList<ItemScroll> scrollStorage = new ArrayList();
    
    public ItemScroll returnScroll(int index) {
        return scrollStorage.get(index);
    }
    
    ItemScrollGenerator() {
        
        scrollStorage = new ArrayList<>();
        
        for (int i = 0; i < ItemScroll.Age.values().length; i++) {
            for (int j = 0; j < ItemScroll.Material.values().length; j++) {
                for (int k = 0; k < ItemScroll.Effect.values().length; k++) {
                
                    ItemScroll scroll= new ItemScroll(ItemScroll.Age.values()[i],
                            ItemScroll.Effect.values()[k], ItemScroll.Material.values()[j], 7, false);
                    
                    scrollStorage.add(scroll);
                    scroll.toString();
            
                }
        
            }
        
        }
    }
}
