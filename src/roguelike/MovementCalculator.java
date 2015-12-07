package roguelike;

public class MovementCalculator {
    
//    void initializeMovement() {
//
//        boolean castingMode = false;
//        boolean cursorMode = false;
//        boolean inventoryMode = false;
//        boolean actionPointBool = false;
//        boolean longLogMode = false;
//        boolean characterMode = false;
//        boolean mapMode = false;
//        boolean menuMode = false;
//        int move = 0;
//        Point tempPoint = new Point(0,0);
//        EventLog eventLog = new EventLog();
//        Properties text = new Properties();
//        boolean stop = false;
//        MainMenu mainMenu = new MainMenu();
//        int[] dummyArray = { 0, 0, 0, 0, 0, 0, 0
//        };
//        ArrayList<Object> dummyItemArray = new ArrayList();
//        Player player = new Player(dummyArray,new Point(35,10),dummyItemArray,0,0,0,"Echdah" );
//
//        /* TEMP MAP ASSIGNMENTS
//        int currentMapIndex = 0;
//        ArrayList<Map> mapArray = new ArrayList();
//        mapArray.add(GenerateMap.newForest(100, 100, 10, 1));
//        Map activeMap = mapArray.get(currentMapIndex);
//        */
//        MagicSpellsBank magicBook = new MagicSpellsBank();
//        magicBook.generateBookOfSpells();
//        player.spellBook.add(magicBook.magicSpellStorage.get(2));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(1));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(1));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(1));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(1));
//        player.spellBook.add(magicBook.magicSpellStorage.get(0));
//        player.spellBook.add(magicBook.magicSpellStorage.get(1));
//
//        // TEMP MAP ASSIGNMENTS
//
//        text.setProperty("fontSize","12");
//        text.setProperty("font", "Courier");
//        ConsoleSystemInterface csi = null;
//
//        try {
//
//            csi = new WSwingConsoleInterface("Planes of Endatl", true);
//        }
//        catch (ExceptionInInitializerError eiie) {
//            System.out.println("*** Error: Swing Console Box cannot be initialized. Exiting...");
//			eiie.printStackTrace();
//			System.exit(-1);
//        }
//
//        mainMenu.drawMainMenu(csi, player);
//
//        int currentMapIndex = 0;
//        //ArrayList<Map> mapArray = new ArrayList();
//        mapArray = GenerateMap.mapGenerator(20);
//        ArrayList<MapCompact> compactMapArray = new ArrayList();
//        MapTiny[][] maps;
//        compactMapArray = MapCompact.MapToCompact(mapArray);
//        Map activeMap = mapArray.get(currentMapIndex);
//        activeMap.visited = true;
//        GenerateMonsters.populateMaps(mapArray);
//        player.frameLocDown = new Point(78,18);
//        player.frameLocUp = new Point (1,1);
//        player.overworldLocDown = new Point(78,18);
//        player.overworldLocUp = new Point(1,1);
//
//        while(!stop) {
//
//            LevelUp.checkForLevelUp(player, eventLog);
//            player.updateStats();
//            for(int i = 0; i < activeMap.creatureArrayList.size();i++) {
//
//                //activeMap.creatureArrayList.get(i).updateStats();
//            }
//
//            int tempInt = currentMapIndex;
//            currentMapIndex = checkForTransition(compactMapArray, currentMapIndex, player);
//
//             GenerateMonsters.populateMaps(mapArray);
//             activeMap = mapArray.get(currentMapIndex);
//
//            if(tempInt != currentMapIndex) {
//                //This is the flag for a map transfer.
//                activeMap.visited = true;
//
//            }
//
//            if (mapMode) {
//
//                OverworldMap.showMap(compactMapArray, csi, currentMapIndex,player.overworldLocUp, player.overworldLocDown);
//                mapMode = false;
//            }
//
//            if (characterMode) {
//
//                CharacterStatusScreen.drawCharacterStatusScreen(csi, player);
//                characterMode = false;
//            }
//
//            if (longLogMode) {
//
//                EventLog.printFullEventLog(eventLog, csi);
//                longLogMode = false;
//            }
//
//            if (cursorMode) {
//
//                CursorMode.drawCursor(csi, new Point(35,10), new Point(player.creaturePoint),
//                        activeMap, player, player.frameLocUp, player.frameLocDown);
//                cursorMode = false;
//            }
//
//            if (castingMode) {
//                MagicSpellsCastMenu.DisplayCastableSpells(csi, player, player.frameLocUp,
//                        player.frameLocDown, activeMap, eventLog);
//                castingMode = false;
//            }
//
//            if (inventoryMode) {
//                InventoryMenu.drawInventoryMenu(csi, player.inventory, activeMap, player,
//                        eventLog);
//                inventoryMode = false;
//            }
//
//            if(menuMode) {
//                InGameMenu.DrawInGameMenu(csi, player, mapArray);
//                menuMode = false;
//            }
//
//            //Check for dead things
//            CheckForDeath.checkForMonsterDeath(activeMap, player, eventLog);
//
//            csi.cls();
//            activeMap.drawFrame(csi);
//            activeMap.drawElements(csi, player.frameLocUp, player.frameLocDown, null);
//            activeMap.drawItems(player.frameLocUp, player.frameLocDown, csi);
//            activeMap.drawPlayerStatus(player, csi);
//
//            eventLog.printEventLog(eventLog, csi);
//
//
//
//            csi.print(player.creaturePoint.x - (player.frameLocUp.x - 1),player.creaturePoint.y - (player.frameLocUp.y - 1), '@', CSIColor.WHITE);
//
//            csi.refresh();
//            CharKey dir = csi.inkey();
//
//
//            for (int i = 0; i < activeMap.creatureArrayList.size(); i++) {
//
//                if (activeMap.creatureArrayList.get(i).actionPoints <= 0) {
//                    actionPointBool = true;
//                }
//                else {
//                    actionPointBool = false;
//                    break;
//                }
//
//            }
//
//            if ((player.actionPoints <= 0) && actionPointBool) {
//            ActionPointCalculator.calculateTurn(activeMap, player);
//            }
//
//            if (player.actionPoints <= 0) {
//
//                MonsterAIPrototype.findBestRoute(activeMap, player, eventLog);
//                ActionPointCalculator.calculateTurn(activeMap, player);
//            }
//
//            tempPoint.setLocation(player.creaturePoint);
//
//            if (dir.isUpArrow()) {
//
//                tempPoint.translate(0,-1);
//
//                if (activeMap.checkMove(Map.Direction.up, new Position(player.creaturePoint.x,player.creaturePoint.y)))
//                {
//                    activeMap.moveCharacter(player, Map.Direction.up);
//                }
//                else if()
//                {
//
//                }
//
//                move++;
//            }
//
//
//             else if (dir.isDownArrow()) {
//
//                 tempPoint.translate(0, 1);
//                 if (CheckMapRestrictions.checkRestrictionYDown(player.creaturePoint, activeMap)){
//
//
//                            player.creaturePoint.translate(0, 1);
//                            player.frameLocUp.translate(0, 1);
//                            player.frameLocDown.translate(0, 1);
//                            player.actionPoints--;
//
//                            MonsterAIPrototype.findBestRoute(activeMap, player, eventLog);
//
//                            }
//
//                            else if (!activeMap.checkForMonsters(tempPoint)) {
//
//                                if (!activeMap.creatureArrayList.isEmpty()) {
//                                    Combat.meleeCombat(player, activeMap.creatureArrayList.get(activeMap.monsterIndexFound),
//                                            eventLog);
//                                    player.actionPoints--;
//                                    MonsterAIPrototype.findBestRoute(activeMap, player, eventLog);
//                                }
//                            }
//                                move ++;
//			}
//
//
//             if(dir.isLeftArrow()) {
//
//                            tempPoint.translate(-1,0);
//
//                            if (CheckMapRestrictions.checkRestrictionXUp(player.creaturePoint, activeMap)) {
//
//                                player.creaturePoint.translate(-1, 0);
//                                player.frameLocUp.translate(-1, 0);
//                                player.frameLocDown.translate(-1,0);
//                                player.actionPoints--;
//                                MonsterAIPrototype.findBestRoute(activeMap, player, eventLog);
//                        }
//
//                            else if (!activeMap.checkForMonsters(tempPoint)) {
//                                if (!activeMap.creatureArrayList.isEmpty()) {
//                                Combat.meleeCombat(player, activeMap.creatureArrayList.get(activeMap.monsterIndexFound),
//                                        eventLog);
//                                player.actionPoints--;
//                                MonsterAIPrototype.findBestRoute(activeMap, player, eventLog);
//                            }
//                            }
//                            move ++;
//			}
//
//                          if(dir.isRightArrow()) {
//
//                            tempPoint.translate(1,0);
//
//                            if (CheckMapRestrictions.checkRestrictionXDown(player.creaturePoint, activeMap)) {
//
//                                player.creaturePoint.translate(1, 0);
//                                player.frameLocUp.translate(1, 0);
//                                player.frameLocDown.translate(1,0);
//                                player.actionPoints--;
//                                MonsterAIPrototype.findBestRoute(activeMap, player, eventLog);
//                        }
//
//                            else if (!activeMap.checkForMonsters(tempPoint)) {
//                                if (!activeMap.creatureArrayList.isEmpty()) {
//                                    Combat.meleeCombat(player, activeMap.creatureArrayList.get(activeMap.monsterIndexFound),
//                                            eventLog);
//                                    player.actionPoints--;
//                                    MonsterAIPrototype.findBestRoute(activeMap, player, eventLog);
//                                }
//                            }
//                            move ++;
//			}
//
//                          if (dir.code == CharKey.x) {
//                              cursorMode = true;
//                          }
//
//                          if (dir.code == CharKey.f) {
//                              castingMode = true;
//                          }
//
//                          if (dir.code == CharKey.i) {
//                              inventoryMode = true;
//                          }
//                          if (dir.code == CharKey.MORETHAN) {
//                              System.out.println("GREATER THAN");
//                          }
//                          if (dir.code == CharKey.COMMA) {
//
//                          }
//                          if (dir.code == CharKey.c) {
//                              characterMode = true;
//                          }
//
//                          if (dir.code == CharKey.l) {
//
//                              longLogMode = true;
//                          }
//
//                          if (dir.code == CharKey.m) {
//
//                              mapMode = true;
//                          }
//
//                          if(dir.code == CharKey.ESC) {
//                              menuMode = true;
//                          }
//
//                          if (dir.code == CharKey.COMMA) {
//
//                              for (int i = 0; i < activeMap.itemsOnMap.size(); i++) {
//
//                                  if(player.creaturePoint.equals(activeMap.itemsOnMap.get(i).pointOnMap)) {
//                                      eventLog.manageEventLog("You pick up the " + activeMap.itemsOnMap.get(i).toString());
//                                      player.inventory.add(activeMap.itemsOnMap.get(i));
//                                      activeMap.itemsOnMap.remove(i);
//                                      break;
//                                  }
//                              }
//                          }
//        }
//    }
//
//    static int checkForTransition(ArrayList<MapCompact> mapArray, int currentMap, Player player) {
//
//        boolean transitionMade = false;
//
//        for (int i = 0; i < mapArray.get(currentMap).mapTransitionArray.size();i++) {
//
//            for (int j = 0; j < mapArray.get(currentMap).mapTransitionArray.get(i).arrayListOfPoints.size(); j++) {
//
//
//                if (player.creaturePoint.equals(mapArray.get(currentMap).mapTransitionArray.get(i).arrayListOfPoints.get(j))) {
//
//                    for (int k = 0; k < mapArray.size();k++) {
//
//                        if (mapArray.get(currentMap).mapTransitionArray.get(i).overWorldLinkingSite.equals(mapArray.get(k).overworldPoint)) {
//
//                            if (mapArray.get(currentMap).mapTransitionArray.get(i).north) {
//
//                                for (int l = 0; l < mapArray.get(k).mapTransitionArray.size(); l++) {
//
//                                    if (mapArray.get(k).mapTransitionArray.get(l).south) {
//
//                                        Point tempPoint = new Point(mapArray.get(k).mapTransitionArray.get(l).arrayListOfPoints.get(0).x,
//                                                mapArray.get(k).mapTransitionArray.get(l).arrayListOfPoints.get(0).y -1);
//                                        Point tempLocUp = new Point(tempPoint.x - 34, tempPoint.y - 9);
//                                        Point tempLocDown = new Point(tempPoint.x +43, tempPoint.y +8);
//
//                                        player.frameLocUp = new Point(tempLocUp);
//                                        player.frameLocDown = new Point(tempLocDown);
//                                        player.creaturePoint = new Point(tempPoint);
//                                        transitionMade = true;
//                                        System.out.println("Transition made!");
//                                    }
//                                }
//                            }
//
//                            else if (mapArray.get(currentMap).mapTransitionArray.get(i).south) {
//
//                                for (int l = 0; l < mapArray.get(k).mapTransitionArray.size(); l++) {
//
//                                    if (mapArray.get(k).mapTransitionArray.get(l).north) {
//
//                                        Point tempPoint = new Point(mapArray.get(k).mapTransitionArray.get(l).arrayListOfPoints.get(0).x,
//                                                mapArray.get(k).mapTransitionArray.get(l).arrayListOfPoints.get(0).y + 1);
//                                        Point tempLocUp = new Point(tempPoint.x - 34, tempPoint.y - 9);
//                                        Point tempLocDown = new Point(tempPoint.x +43, tempPoint.y +8);
//
//                                        player.frameLocUp = new Point(tempLocUp);
//                                        player.frameLocDown = new Point(tempLocDown);
//                                        player.creaturePoint = new Point(tempPoint);
//                                        transitionMade = true;
//                                        System.out.println("Transition made!");
//                                    }
//                                }
//                            }
//
//                            else if (mapArray.get(currentMap).mapTransitionArray.get(i).west) {
//
//                                for (int l = 0; l < mapArray.get(k).mapTransitionArray.size(); l++) {
//
//                                    if (mapArray.get(k).mapTransitionArray.get(l).east) {
//
//                                        Point tempPoint = new Point(mapArray.get(k).mapTransitionArray.get(l).arrayListOfPoints.get(0).x - 1,
//                                                mapArray.get(k).mapTransitionArray.get(l).arrayListOfPoints.get(0).y);
//                                        Point tempLocUp = new Point(tempPoint.x - 34, tempPoint.y - 9);
//                                        Point tempLocDown = new Point(tempPoint.x +43, tempPoint.y +8);
//
//                                        player.frameLocUp = new Point(tempLocUp);
//                                        player.frameLocDown = new Point(tempLocDown);
//                                        player.creaturePoint = new Point(tempPoint);
//                                        transitionMade = true;
//                                        System.out.println("Transition made!");
//                                    }
//                                }
//                            }
//
//                            else if (mapArray.get(currentMap).mapTransitionArray.get(i).east) {
//
//                                for (int l = 0; l < mapArray.get(k).mapTransitionArray.size(); l++) {
//
//                                    if (mapArray.get(k).mapTransitionArray.get(l).west) {
//
//                                        Point tempPoint = new Point(mapArray.get(k).mapTransitionArray.get(l).arrayListOfPoints.get(0).x + 1,
//                                                mapArray.get(k).mapTransitionArray.get(l).arrayListOfPoints.get(0).y);
//                                        Point tempLocUp = new Point(tempPoint.x - 34, tempPoint.y - 9);
//                                        Point tempLocDown = new Point(tempPoint.x +43, tempPoint.y +8);
//
//                                        player.frameLocUp = new Point(tempLocUp);
//                                        player.frameLocDown = new Point(tempLocDown);
//                                        player.creaturePoint = new Point(tempPoint);
//                                        transitionMade = true;
//                                        System.out.println("Transition made!");
//                                    }
//                                }
//                            }
//
//                            currentMap = k;
//                            break;
//                        }
//
//                        if (transitionMade) {
//                            break;
//                        }
//                    }
//                    if (transitionMade) {
//                            break;
//                        }
//
//                }
//                if (transitionMade) {
//                            break;
//                        }
//            }
//            if (transitionMade) {
//                            break;
//                        }
//        }
//
//        return currentMap;
//    }
}
