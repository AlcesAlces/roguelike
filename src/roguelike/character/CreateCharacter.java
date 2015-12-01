package roguelike.character;


import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import roguelike.Global;
import roguelike.stats.TraitDescription;

public class CreateCharacter {
    
    public void drawCharacterCreator(ConsoleSystemInterface csi, Player playerCharacter) {
        
        ArrayList<ArrayList> traitsToDisplay = new ArrayList();
        ArrayList<Object> test = new ArrayList();
        
        String classPicked = "";
        String racePicked = "";
        String characterName = "";
        
        int tagged1 = -1;
        int tagged2 = -1;
        int remainingPoints = 5;
        int classID = 0;
        int raceID = 0;
        int activeLength = 0;
        int creationStep = 1;
        int activeMenuOption = 0;
        boolean closeMainMenu = false;
        
        while(!closeMainMenu) {
            
            csi.cls();
            
            csi.print(1,0,    "------------------------------------------------------------------------", CSIColor.WHITE);
            csi.print(1,1,    "                             Create a new character", CSIColor.WHITE);
            if (creationStep == 1) {
                activeLength = 3;
                csi.print(1,2, "                               Select Your Race", CSIColor.WHITE);
                        csi.print(1,5, "                                 Human", isActiveMenu(0, activeMenuOption));
                        csi.print(1,6, "                                 Elf", isActiveMenu(1, activeMenuOption));
                        csi.print(1,7, "                                 Orc", isActiveMenu(2, activeMenuOption));
                        csi.print(1,8, "                                 Flying Nun", isActiveMenu(3, activeMenuOption));
                        
                        if (activeMenuOption == 0) {
                            csi.print(1,5, "Humans start with average", CSIColor.RED);
                            csi.print(1,6, "stats and are adept at taking", CSIColor.RED);
                            csi.print(1,7, "any role.", CSIColor.RED);
                        }
                        
                        if (activeMenuOption == 1) {
                            csi.print(1,5, "Elves are nimble and wise", CSIColor.RED);
                            csi.print(1,6, "making them perfect for rogue", CSIColor.RED);
                            csi.print(1,7, "or spell-casting roles.", CSIColor.RED);
                        }
                        
                        if (activeMenuOption == 2) {
                            csi.print(1,5, "Orcs are lumber oafs", CSIColor.RED);
                            csi.print(1,6, "who take pleasure in combat", CSIColor.RED);
                            csi.print(1,7, "and are adept at assuming", CSIColor.RED);
                            csi.print(1,8, "combative roles", CSIColor.RED);
                        }
                        
                        if (activeMenuOption == 3) {
                            csi.print(1,5, "Flying nuns are a mystery.", CSIColor.RED);
                            csi.print(1,6, "they are hopeless at everything", CSIColor.RED);
                            csi.print(1,7, "and just aren't good.", CSIColor.RED);
                        }
                        
                        
            }
            if (creationStep == 2) {
                csi.print(1,2,  "                                Character name", CSIColor.WHITE);
                csi.print(1,5,  "                          Name: " + characterName, CSIColor.WHITE);
                csi.print(1,6,  "                               ----------------------------------", CSIColor.WHITE);
            }
            
            if (creationStep == 3) {
                csi.print(1,2,  "                                Pick a Class", CSIColor.WHITE);
                csi.print(1,5,  "                                  Warrior", isActiveMenu(0, activeMenuOption));
                csi.print(1,6,  "                                  Rogue", isActiveMenu(1, activeMenuOption));
                csi.print(1,7,  "                                  Wizard", isActiveMenu(2, activeMenuOption));
            }
            
            if (creationStep == 4) {
                csi.print(1,2, "                                Stat Selection", CSIColor.WHITE);
                csi.print(1,3, "                                " + characterName + " the " + racePicked, CSIColor.WHITE);
                csi.print(1,5, "                             Health  : " + playerCharacter.stats[0] , CSIColor.WHITE);
                csi.print(1,6, "                             Mana    : " + playerCharacter.stats[1] , CSIColor.WHITE);
                csi.print(1,7,  "                            Strength : " + playerCharacter.stats[2] , isActiveMenu(0, activeMenuOption));
                csi.print(1,8,  "                          Dexterirty : " + playerCharacter.stats[3] , isActiveMenu(1, activeMenuOption));
                csi.print(1,9,  "                           Endurance : " + playerCharacter.stats[4] , isActiveMenu(2, activeMenuOption));
                csi.print(1,10,  "                        Intelligence : " + playerCharacter.stats[5] , isActiveMenu(3, activeMenuOption));
                csi.print(1,11,  "                            Wisdom   : " + playerCharacter.stats[6] , isActiveMenu(4, activeMenuOption));
                csi.print(1,13,  "                    Remaining Points: " +remainingPoints, CSIColor.RED);
            }
            
            if (creationStep == 5) {

                //TODO: Fix this by removing this hack:
                creationStep++;
                continue;

//                int lineToPrint = traitsToDisplay.get(0).size();
//                csi.print(32,2,  "Trait Selection", CSIColor.WHITE);
//
//                for (int i = 0; i < traitsToDisplay.get(0).size(); i++) {
//
//                    csi.print(40, i + 4, ""+traitsToDisplay.get(0).get(i), isActiveMenu(i, activeMenuOption));
//                    if (i == tagged1 || i == tagged2) {
//                        csi.print(39, i + 4, "*", CSIColor.PURPLE);
//                    }
//                }
//
//                for (int i = 0; i < traitsToDisplay.get(1).size(); i++) {
//
//                    if ((i + lineToPrint) == tagged1 || (i + lineToPrint) == tagged2) {
//                        csi.print(39, i + 4 + lineToPrint, "*", CSIColor.PURPLE);
//                    }
//
//                    csi.print(40, i + 4 + lineToPrint, ""
//                            + traitsToDisplay.get(1).get(i), isActiveMenu(i + traitsToDisplay.get(0).size(), activeMenuOption));
//                }
//
//                lineToPrint = lineToPrint + traitsToDisplay.get(1).size();
//
//                for (int i = 0; i < traitsToDisplay.get(2).size(); i++) {
//
//                    if ((i + lineToPrint) == tagged1 || (i + lineToPrint) == tagged2) {
//                        csi.print(39, i + 4 + lineToPrint, "*", CSIColor.PURPLE);
//                    }
//
//                    csi.print(40, i + 4 + lineToPrint, ""
//                            + traitsToDisplay.get(2).get(i), isActiveMenu(i + traitsToDisplay.get(0).size() + traitsToDisplay.get(1).size(), activeMenuOption));
//                }
//
//                lineToPrint = lineToPrint + traitsToDisplay.get(2).size();
//
//                for (int i = 0; i < traitsToDisplay.get(3).size(); i++) {
//
//                    if ((i + lineToPrint) == tagged1 || (i + lineToPrint) == tagged2) {
//                        csi.print(39, i + 4 + lineToPrint, "*", CSIColor.PURPLE);
//                    }
//
//                    csi.print(40, i + 4 + lineToPrint, ""
//                            + traitsToDisplay.get(3).get(i), isActiveMenu(i + traitsToDisplay.get(0).size() + traitsToDisplay.get(1).size() + traitsToDisplay.get(2).size(), activeMenuOption));
//                }
//
//                csi.print(1, 4, "" + TraitDescription.get(activeMenuOption).get(0), CSIColor.RED);
//                csi.print(1, 5, "" + TraitDescription.get(activeMenuOption).get(1), CSIColor.RED);
//                if (TraitDescription.get(activeMenuOption).size() == 3) {
//                    csi.print(1, 6, "" + TraitDescription.get(activeMenuOption).get(2), CSIColor.RED);
//                }
//                csi.print(35,22, "Pick 2 traits. " + remainingPoints + " Remaining." , CSIColor.GRAY);
                
            }
            
            csi.print(1,23,    "------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(1,24,  "Create a new character to delve the deep dungeons of doom.");
            csi.refresh();
            
            
            CharKey dir = csi.inkey();

            if(dir.code == CharKey.q){
                closeMainMenu = true;
                System.out.println("Stop get");
            }

            if (creationStep == 1) {
            
                if(dir.isUpArrow() && (activeMenuOption > 0)) {
                    activeMenuOption--;
                }
                if(dir.isDownArrow() && activeMenuOption < activeLength) {
                    activeMenuOption++;
                }
            if(dir.code == CharKey.ENTER) {
                if (activeMenuOption == 0) {
                    racePicked = "Human";
                    raceID = 0;
                }
                if (activeMenuOption == 1) {
                    racePicked = "Elf";
                    raceID = 1;
                }
                if (activeMenuOption == 2) {
                    racePicked = "Orc";
                    raceID = 2;
                }
                if (activeMenuOption == 3) {
                    racePicked = "FlyingNun";
                    raceID = 3;
            }
                activeMenuOption = 0;
                int[] raceBonusStats = RaceInitialStats.startingStats(raceID);
                for (int i = 0; i < 7; i++) {
                playerCharacter.stats[i] = playerCharacter.stats[i] +  raceBonusStats[i];
                }
                
                creationStep++;
            
            }
            
            }
            
            else if (creationStep == 2) {

                if (dir.code == CharKey.BACKSPACE) {
                    characterName = characterName.substring(0, characterName.length() - 1);
                } else if (dir.code == CharKey.ENTER) {
                    playerCharacter.name = characterName;
                    creationStep++;
                } else if (dir.code != CharKey.NONE) {
                    if (characterName.length() > 10) {
                        //TODO: Print name too long message.
                    }
                    else {
                        characterName = characterName + dir.toString();
                    }
                }

            }
            
            else if (creationStep == 3) {
                if(dir.isUpArrow() && (activeMenuOption > 0)) {
                    activeMenuOption--;
                }
                if(dir.isDownArrow() && activeMenuOption < 2) {
                    activeMenuOption++;
                }
            if(dir.code == CharKey.ENTER) {
                if (activeMenuOption == 0) {
                    classPicked = "Warrior";
                    classID = 0;
                }
                if (activeMenuOption == 1) {
                    classPicked = "Rogue";
                    classID = 1;
                }
                if (activeMenuOption == 2) {
                    classPicked = "Wizard";
                    classID = 2;
                }
                
                //RaceInitialStats traits = new RaceInitialStats(raceID);
                int[] classBonusStats = CharacterClass.classSkillBonuses(classID);
                //playerCharacter = new Player(traits.startingStats(), 
                //            new Point(10,10), test, raceID, classID, 0, characterName);
                for (int i = 0; i < 7; i++) {
                playerCharacter.stats[i] = playerCharacter.stats[i] +  classBonusStats[i];
                }
                activeMenuOption = 0;
                creationStep++;
            
            
            }
            }
            
            
            else if (creationStep == 4) {
                
                if(dir.isUpArrow() && (activeMenuOption > 0)) {
                    activeMenuOption--;
                }
                if(dir.isDownArrow() && activeMenuOption < 4) {
                    activeMenuOption++;
                }
                if(dir.code == CharKey.ENTER && (remainingPoints > 0)) {
                    playerCharacter.stats[activeMenuOption + 2]++;
                    remainingPoints--;
                }
                if (remainingPoints == 0) {
                    if(dir.code == CharKey.ENTER) {
                        creationStep++;
                        remainingPoints = 2;
                        activeMenuOption = 0;
                    }
                }
            }
            
            else if (creationStep == 5) {
                
                if(dir.isUpArrow() && (activeMenuOption > 0)) {
                    activeMenuOption--;
                }
                
                if(dir.isDownArrow() && activeMenuOption < 10) {
                    activeMenuOption++;
                }
                if(dir.code == CharKey.ENTER) {
                    remainingPoints--;
                    if (tagged1 >= 0) {
                        tagged2 = activeMenuOption;
                    }
                    else {
                    tagged1 = activeMenuOption;
                    }
                }
            }

            else if(creationStep == 6)
            {
                //Done creating character
                Global.player = playerCharacter;
                closeMainMenu = true;
            }
        }
        
    }
    
    CSIColor isActiveMenu(int menuOption, int selectionOption) {
        
        if (menuOption == selectionOption) {
            return CSIColor.GREEN;
        }
        else
            return CSIColor.WHITE;
        }
    
    }
