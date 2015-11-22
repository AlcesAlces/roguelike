package roguelike;

import java.util.ArrayList;


public class TraitDescription {
    
    public static ArrayList<String> get(int traitID) {
        
        ArrayList<String> listToReturn = new ArrayList();
        
        switch (traitID) {
            
            case 0:
                listToReturn.add("Brute strength gives the player a");
                listToReturn.add("boost to physical strength.");
                break;
                
            case 1:
                listToReturn.add("Nimble fingers gives the player a");
                listToReturn.add("boost to dexterity.");
                break;
                
            case 2:
                listToReturn.add("Fortitude gives the player a");
                listToReturn.add("boost to endurance.");
                break;
                
            case 3:
                listToReturn.add("Sheer brainpower gives the player a");
                listToReturn.add("boost to intelligence.");
                break;
                
            case 4:
                listToReturn.add("Piety gives the player a");
                listToReturn.add("boost to wisdom.");
                break;
                
            case 5:
                listToReturn.add("Speedy gives the player a");
                listToReturn.add("boost to speed.");
                break;
                
            case 6:
                listToReturn.add("Double attack gives a player a ");
                listToReturn.add("slight chance to attack twice");
                listToReturn.add("during their round of combat.");
                break;
                
            case 7:
                listToReturn.add("Power attack gives a player a");
                listToReturn.add("slight chance to inflict massive");
                listToReturn.add("damage during their round of combat.");
                break;
                
            case 8:
                listToReturn.add("Nimble mind gives a player a");
                listToReturn.add("chance to cast a spell twice");
                listToReturn.add("during their spellcasting round.");
                break;
                
            case 9:
                listToReturn.add("Hearty gives a player an inate");
                listToReturn.add("boost to the regeneration of health.");
                break;
                
            case 10:
                listToReturn.add("Spiritualist gives a player an");
                listToReturn.add("inate boost to the regeneration of");
                listToReturn.add("mana.");
                break;
        }
        return listToReturn;
    }
}
