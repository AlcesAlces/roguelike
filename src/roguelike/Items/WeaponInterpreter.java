package roguelike.Items;



public class WeaponInterpreter {
    
    // for the future: DamageType damageInterpreter = new DamageType();
    int damage;
    int speed;
    String damageType;
    String weaponName;
    
    WeaponInterpreter(Weapon weapon) {
        damage = (weapon.totalWeaponValue() * 10);
        speed = (50 - weapon.weaponWeight());
        //damageType = damageInterpreter.damageType(weapon);
        weaponName = weapon.toString();
    }

   // public DamageType getDamageInterpreter() {
    //    return damageInterpreter;
   // }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDamageType() {
        return damageType;
    }
    
    
}
