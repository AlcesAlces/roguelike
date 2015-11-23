package roguelike.Items;

import java.util.ArrayList;

public class WeaponGeneration {
    
    private static ArrayList<Weapon> weaponStorage;
    
    Weapon returnWeapon(int index) {
        return weaponStorage.get(index);
    }
    public Weapon returnRandomWeapon(int weaponType) {
        
        Weapon weaponToReturn = weaponStorage.get(1);
        int rand;
        
        for (int i = 0; i < weaponStorage.size();i++) {
            
        rand = (int) (Math.random() * weaponStorage.size());
        if (weaponStorage.get(rand).getType().getWeight() == weaponType) {
            weaponToReturn = weaponStorage.get(rand);
            weaponStorage.remove(rand);
        }
    }
        return weaponToReturn;
        
    }

    public static ArrayList<Weapon> getWeaponStorage() {
        return weaponStorage;
    }

    public static void setWeaponStorage(ArrayList<Weapon> weaponStorage) {
        WeaponGeneration.weaponStorage = weaponStorage;
    }
    
    int sizeOfStorage() {
        return weaponStorage.size();
    }
    
        ArrayList<Weapon> reorderWeapons() {
        
        Weapon temp;
        int storageSize = weaponStorage.size();
        
        for ( int i = (storageSize - 1); i > 0; i--) {
            
            int rand = (int) (Math.random() * storageSize);
            temp = weaponStorage.get(i);
            weaponStorage.set(i, weaponStorage.get(rand));
            weaponStorage.set(rand, temp);
        }
        return weaponStorage;
    }
    
    public WeaponGeneration() {
        
        weaponStorage = new ArrayList<>();
        
        for (int i = 0; i < Weapon.Origin.values().length; i++) {
            for (int j = 0; j < Weapon.Modifier.values().length; j++) {
                for (int k = 0; k < Weapon.Type.values().length; k++) {
                
                Weapon weapon = new Weapon(Weapon.Origin.values()[i],
                        Weapon.Modifier.values()[j], Weapon.Type.values()[k], 5, false);
                
                weaponStorage.add(weapon);
                weapon.toString();
            
                }
        
            }
        }
    }
    
    public Weapon returnMonsterWeapon(int level) {
        
        level += 1;
        int index = 0;
        boolean done = false;
        Weapon toReturn = null;
        
        while (!done) {
            
            if(weaponStorage.get(index).totalWeaponValue() <= level) {
                
                done = true;
                toReturn = weaponStorage.get(index);
            }
            
            index++;
        }
        
        return toReturn;
    }
}
