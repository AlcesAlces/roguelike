package roguelike.Items;

import java.util.ArrayList;

public class ArmorGeneration {
    
    private static ArrayList<Armor> armorStorage;
    private static ArrayList<ArmorBoots> bootStorage;
    private static ArrayList<ArmorGloves> gloveStorage;
    private static ArrayList<ArmorHelmet> helmetStorage;
    private static ArrayList<ArmorPants> pantStorage;
    
    ArrayList<ArmorGloves> gloveStorage() {
        return gloveStorage;
    }
    
    ArrayList<Armor> armorStorage() {
        return armorStorage;
    }
    
    public int sizeOfArmor() {
        
        return armorStorage.size();
    }
    
    public Armor returnArmor() {
        
        return armorStorage.get(200);
    }
    
    public ArmorHelmet returnHelm() {
        return helmetStorage.get(20);
    }
    
    public ArmorPants returnPants() {
        return pantStorage.get(30);
    }
    
    public ArmorBoots returnBoots() {
        return bootStorage.get(40);
    }
    
    public ArmorGloves returnGloves() {
        return gloveStorage.get(50);
    }
    
    public static ArrayList<Armor> getArmorStorage() {
        return armorStorage;
    }

    public static void setArmorStorage(ArrayList<Armor> armorStorage) {
        ArmorGeneration.armorStorage = armorStorage;
    }
    int sizeOfStorage() {
        return armorStorage.size() + bootStorage.size() + gloveStorage.size() +
                helmetStorage.size() + pantStorage.size();
    }
    
        ArrayList<Armor> reorderArmor() {
        
        Armor temp;
        int storageSize = armorStorage.size();
        
        for ( int i = (storageSize - 1); i > 0; i--) {
            
            int rand = (int) (Math.random() * storageSize);
            temp = armorStorage.get(i);
            armorStorage.set(i, armorStorage.get(rand));
            armorStorage.set(rand, temp);
        }
        return armorStorage;
}
    
    public ArmorGeneration() {
        
        armorStorage = new ArrayList<>();
        
        for (int i = 0; i < Armor.Origin.values().length; i++) {
            for (int j = 0; j < Armor.Modifier.values().length; j++) {
                for (int k = 0; k < Armor.Type.values().length; k++) {
                
                Armor armor = new Armor(Armor.Origin.values()[i],
                        Armor.Modifier.values()[j], Armor.Type.values()[k], 0, false);
                
                armorStorage.add(armor);
                armor.toString();
            
                }
        
            }
        }
        
        bootStorage = new ArrayList<>();
        
            for (int i = 0; i < ArmorBoots.Origin.values().length; i++) {
                for (int j = 0; j < ArmorBoots.Modifier.values().length; j++) {
                    for (int k = 0; k < ArmorBoots.Type.values().length; k++) {
                
                ArmorBoots boot = new ArmorBoots(ArmorBoots.Origin.values()[i],
                        ArmorBoots.Modifier.values()[j], ArmorBoots.Type.values()[k], 1, false);
                
                bootStorage.add(boot);
                boot.toString();
            
                }
        
            }
        }
            gloveStorage = new ArrayList<>();
            
            for (int i = 0; i < ArmorGloves.Origin.values().length; i++) {
                for (int j = 0; j < ArmorGloves.Modifier.values().length; j++) {
                    for (int k = 0; k < ArmorGloves.Type.values().length; k++) {
                
                ArmorGloves glove = new ArmorGloves(ArmorGloves.Origin.values()[i],
                        ArmorGloves.Modifier.values()[j], ArmorGloves.Type.values()[k], 2, false);
                
                gloveStorage.add(glove);
                glove.toString();
            
                }
        
            }
        }
            
                        helmetStorage = new ArrayList<>();
            
            for (int i = 0; i < ArmorHelmet.Origin.values().length; i++) {
                for (int j = 0; j < ArmorHelmet.Modifier.values().length; j++) {
                    for (int k = 0; k < ArmorHelmet.Type.values().length; k++) {
                
                ArmorHelmet helmet = new ArmorHelmet(ArmorHelmet.Origin.values()[i],
                        ArmorHelmet.Modifier.values()[j], ArmorHelmet.Type.values()[k], 3, false);
                
                helmetStorage.add(helmet);
                helmet.toString();
            
                }
        
            }
        }
            
                    pantStorage = new ArrayList<>();
        
        for (int i = 0; i < ArmorPants.Origin.values().length; i++) {
            for (int j = 0; j < ArmorPants.Modifier.values().length; j++) {
                for (int k = 0; k < ArmorPants.Type.values().length; k++) {
                
                ArmorPants pants = new ArmorPants(ArmorPants.Origin.values()[i],
                        ArmorPants.Modifier.values()[j], ArmorPants.Type.values()[k], 4, false);
                
                pantStorage.add(pants);
                pants.toString();
            
                }
        
            }
        }
        
    }
    
    Armor returnBodyArmor(int index) {
        
        return armorStorage.get(index);
    }
        
}
