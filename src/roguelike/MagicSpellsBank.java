package roguelike;

import java.util.ArrayList;

public class MagicSpellsBank {
    
    ArrayList<MagicSpells> magicSpellStorage = new ArrayList();
    
    void generateBookOfSpells() {
        
        //Spells are made as such:
        //Damage, accuracy, Cost, ID, Name, Description, Targetable
        
        magicSpellStorage.add(new MagicSpells(2, 20, 5, 0, "Magic Bolt", "PEW PEW",
                true, false));
        magicSpellStorage.add(new MagicSpells(10, 20, 10, 1, "Fire Ball", "PEW PEW",
                true, true));
        magicSpellStorage.add(new MagicSpells(500 , 20, 0, 2, "Nuke from orbit", "debug",
                true, true));
    }
}
