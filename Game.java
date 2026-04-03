import java.util.Scanner;
import java.util.ArrayList;
//Disguise mimic as a chest
//Add chests to contain items(?)
//Implement monster drops
//Implement combat
public class Game {   
   public static void main(String[] args) {
      int allottedItems = 1;
      int combatDuration = 0;
      boolean commandPrompt = true;
      String currentStage = "Upkeep";
      String playerStatus = null;
      boolean exitGame = false;
      boolean inCombat = false;
      Monster enemy = null;
      Game game = new Game();
      Map dungeonMap = new Map(12, 11);
      Scanner scanner = new Scanner(System.in);
      Player MC = new Player(100, "Hero", ((int)Math.random()*12 + (int)(1+Math.random()*6)), ((int)Math.random()*12 + (int)(1+Math.random()*6)), null, null, null, null, null);
      MC.addItemToInventory(Item.forceNewItem(3));
      MC.addItemToInventory(Item.forceNewItem(2));
      MC.addItemToInventory(Item.forceNewItem(1));
      MC.addItemToInventory(Item.forceNewItem(10));
      MC.addItemToInventory(Item.forceNewItem(10));
      MC.addItemToInventory(Item.forceNewItem(13));
      MC.addItemToInventory(Item.forceNewItem(13));
      
      while (exitGame != true) {
         if(commandPrompt) {
            System.out.print("Enter command: ");
         }
         else {commandPrompt = true;}
            String input = scanner.nextLine().toUpperCase();
            
            if (input.equals("EXIT")) {
                System.out.println("Exiting game. Goodbye!");
                exitGame = true;
            }
            //Tutorial/Commands
            else if (input.equals("HELP") || input.equals("\"HELP\"") || input.equals("CMDS") || input.equals("COMMANDS")) {
               System.out.println("-------------List of Recognized Commands-------------"
               +"\nHelp/Cmds/Commands - Return a list of applicable commands."
               +"\nLook/Search - Observe your surroundings (Requires a lit torch)."
               +"\nOpen Inventory/View Inventory/Inventory/etc. - View the contents of your inventory."
               +"\nUse [ITEM] - Use an item."
               +"\nEquip [WEAPON/ARMOR/SHIELD] - Equip a selected item."
               +"\nTake [ITEM] - Take an item that is within the room you are in."
               +"\nDrop [ITEM] - Drop an item from within your inventory onto the floor of the room you are inside"
               +"\nMove [NORTH/EAST/SOUTH/WEST] - Move in a specified direction (Other movement expressions are recognized (e.g. Up/Down/Left/Right))"
               +"\nInspect Weapon/Armor/Shield/[ITEM] - Return the stats and additional details of a selected item."
               +"\nFight [MONSTER] - Initiate combat with a monster in the same room as you. (Use \"Help - Combat\" for a more in-depth explanation of combat mechanics.)"
               +"\nHealth/Injuries/Wounds - Returns an estimation of your current health & any sustained injuries\n");
            }
            else if (input.equals("HELP - COMBAT") || input.equals("HELP-COMBAT") || input.equals("HELP COMBAT") || input.equals("COMBAT HELP")) {
            int initialHealth = MC.getHP();
               System.out.println("Upon initiating combat with a monster, you\nwill be presented with the option of either:");
               System.out.println("\n\n\n\n\n                                         ----------------------------"
                              +"\n                                         | Attack | Defend |  Item  |"
                              +"\n[ Press [ENTER] to proceed. ]            ----------------------------");
               input = scanner.nextLine();
               if (input.contains("")) {
                  System.out.println("Choosing to attack will deal damage equal to your weapon's current attack power.");
                  System.out.println("                         [ Inspect your weapon and then attack the enemy. ]\n");
                  System.out.print("Enter Command: Inspect");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("Silver Longsword\n----------\n17 Damage\n\" A heavy blade forged from a silver alloy. Inflicts bonus damage to werewolves. \"\n");
                  System.out.print("Enter Command: Attack");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("[ You hit [ENEMY] for 17 damage! ]");
                  System.out.println("[ENEMY] has 33 health remaining.\n");
                  System.out.println("Having finished your attack stage, it is now the enemy's turn. Their first attack will\nalways do the same amount of damage relative to their typing; however subsequent attacks\nwill scale in damage indefinitely.\n[ Press [ENTER] to proceed.]");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("[ Enemy hit you for 15 damage. ]\n\n[ Press [ENTER] to proceed.]");
                  MC.setHP(20);
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("\nThat marks the end of the first combat cycle.\nIn order to check your remaining health, you must use the \"Check Health\" command; which\nwill give you a rough estimate of your current health.");
                  System.out.println("\n\n\n\n\n                                         ----------------------------"
                              +"\n                                         | Attack | Defend |  Item  |"
                              +"\n[ Check your health. ]                   ----------------------------");
                  System.out.print("Enter Command: Check Health");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.print("[ \""+MC.getHealthPercentage()+"\" ]\n\n");
                  System.out.println("Looks like you're hurt pretty bad. Luckily, you're able to use up to one item at the\nupkeep of your turn.Check your inventory for any items worth using.\n[ Use a healing potion. ]\n");
                  MC.addItemToInventory(Item.forceNewItem(13));
                  System.out.print("Enter Command: View Inventory");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("Checking through your bag, you find:\n"+MC.viewInventory());
                  System.out.print("Enter Command: Use Healing Potion");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  MC.useItem("HEALTH POTION");
                  System.out.println("\nGreat! Because you're still badly injured, make sure to defend this turn.\nDefending makes you take reduced damage(fully negated if you have a powerful enough\nshield), and allows you to use another item at the upkeep of your turn. Blocking an\nattack without any shield equipped will always reduce the damage recieved\nby a minimum of 50%.");
                  System.out.println("\n\n\n\n\n                                         ----------------------------"                              
                                  +"\n                                         | Attack | Defend |  xxxx  |"
                                  +"\n[ Defend the enemy's incoming attack. ]  ----------------------------");
                  System.out.print("Enter Command: Defend");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("[...]");
                  System.out.println("[ Enemy hit you for 8 damage. ]\n\n[ Press [ENTER] to proceed.]");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("That covers all the mechanics of combat! Feel free to reopen this guide at any time if\nyou ever feel confused about any combat mechanics.");
              }
              MC.setHP(initialHealth);
            }
            //End Tutorial
            
            
            
            else if(MC.getHP() <= 0) {
                  System.out.print("Your vision darkens for a moment, and you stumble on your feet-- Looking down, you see\nyour ripped and bloodied clothes. Below that, you notice the steadily growing pool of\nwhat appears to be your own blood underneath your feet.\n> Your vision darkens once more.\n> ...You realize you don't have long to live.\n...You stumble on the sharp rocks beneath you and fall to the ground; now staring up at\nthe dark tiles above.\n\nAs your vision goes dark for the final time, you try not to think about what kind of\ncreature will feed off your remains.\n\n[ GAME OVER : YOU DIED... ]");
                  exitGame = true;
            }
            else if (input.equals("LOOK") || input.equals("SEARCH")) {
               if(!inCombat) {
                  if(MC.hasItem("Lit Torch [3]") || MC.hasItem("Lit Torch [2]") || MC.hasItem("Lit Torch [1]")) {
                     System.out.print(MC.look(dungeonMap));
                  } else {
                     System.out.println("You attempt to look around and gain your bearings, but it's too dark to see anything...");
                     if((Math.random()*2 > 1) && (dungeonMap.getRoom(MC.getX(), MC.getY()).getItemsInRoom().size() > 1)) {
                        System.out.println("> All you can make out is a single "+dungeonMap.getRoom(MC.getX(), MC.getY()).getItemInRoom(0).toString().toLowerCase()+" on the floor beside your feet...");
                     }
                     System.out.println("Maybe you have something to light the way?..\n");
                  }
               } else{System.out.println("[ \" I can't do this amidst combat. \" ]\n");}
            }
            else if (input.contains("INVENTORY") || input.equals("ITEM")) {
               System.out.println("Checking through your bag, you find:\n"+MC.viewInventory());
            }
            else if (input.startsWith("USE ") || input.startsWith("ITEM") || input.startsWith("EQUIP ")) {
               String chosenItem;
               Boolean successful = false;
               if(!inCombat || allottedItems > 0) {
                  if(input.startsWith("USE ")) {
                     chosenItem = input.substring(4);
                     successful = MC.useItem(chosenItem);
                  } else if(input.startsWith("ITEM ")) {
                     chosenItem = input.substring(5);
                     successful = MC.useItem(chosenItem);
                  } else if(input.startsWith("EQUIP ")) {
                     chosenItem = input.substring(6);
                     if(MC.getItemFromInventory(chosenItem) != null) {
                        if(MC.getItemFromInventory(chosenItem).getType().contains("Weapon")) {
                           if(MC.getEquippedWeapon() != null) {MC.getEquippedWeapon().equip(false);}
                           MC.setEquippedWeapon(MC.getItemFromInventory(chosenItem));
                           MC.getEquippedWeapon().equip(true);
                           if(MC.getEquippedWeapon().getType().contains("Heavy Weapon")) {
                              if(MC.getEquippedShield() != null) {
                                 MC.getEquippedShield().equip(false);
                                 MC.setEquippedShield(null);
                                 System.out.println("> Setting aside your shield, you grip the "+MC.getEquippedWeapon().toString().toLowerCase()+" with both hands, assuming a stance permitting of greater control.");
                              } else {System.out.println("> You grip the "+MC.getEquippedWeapon().toString().toLowerCase()+" with both hands, assuming a stance permitting of greater control.");}
                           } else {System.out.println("> You firmly grasp your "+MC.getEquippedWeapon().toString().toLowerCase()+"; preparing yourself for whatever foe may befall you.");}
                        } else if(MC.getItemFromInventory(chosenItem).getType().contains("Armor")) {
                           if(MC.getEquippedArmor() != null) {MC.getEquippedArmor().equip(false);}
                           MC.setEquippedArmor(MC.getItemFromInventory(chosenItem));
                           MC.getEquippedArmor().equip(true);
                           System.out.println("> You exchange your previous armor for the "+MC.getEquippedArmor().toString().toLowerCase()+", securely attaching it to your person.");
                        } else if(MC.getItemFromInventory(chosenItem).getType().contains("Shield")) {
                           if(MC.getEquippedShield() != null) {MC.getEquippedShield().equip(false);}
                           MC.setEquippedShield(MC.getItemFromInventory(chosenItem));
                           MC.getEquippedShield().equip(true);
                           if(MC.getEquippedWeapon() != null && MC.getEquippedWeapon().getType().contains("Heavy Weapon")) {
                              System.out.println("> You discard your "+MC.getEquippedWeapon().toString().toLowerCase()+", opting to use a "+MC.getEquippedShield().toString().toLowerCase()+" instead. A safer bet, but you should probably find a lightweight weapon to match it.");
                              MC.getEquippedWeapon().equip(false);
                              MC.setEquippedWeapon(null);
                           } else {System.out.println("> You attach the "+MC.getEquippedShield().toString().toLowerCase()+" to your offhand.");}
                        } else if(MC.getItemFromInventory(chosenItem).getType().contains("Necklace")) {
                           if(MC.getEquippedAccessory(1) != null) {MC.getEquippedAccessory(1).equip(false);}
                           MC.setEquippedAccessory(MC.getItemFromInventory(chosenItem), 1);
                           MC.getEquippedAccessory(1).equip(true);
                           System.out.println("> You clasp the "+MC.getEquippedAccessory(1).toString().toLowerCase()+" around your neck.");
                        } else if(MC.getItemFromInventory(chosenItem).getType().contains("Ring")) {
                           if(MC.getEquippedAccessory(2) != null) {MC.getEquippedAccessory(2).equip(false);}
                           MC.setEquippedAccessory(MC.getItemFromInventory(chosenItem), 2);
                           MC.getEquippedAccessory(2).equip(true);
                           System.out.println("> You slide the "+MC.getEquippedAccessory(2).toString().toLowerCase()+" over your finger.");
                        } else {System.out.println("You are unable to equip this item. Maybe it has a different use?..\n[ Attempted item: "+chosenItem+" ]");}
                     } else {System.out.println("You are unable to equip this item. Maybe it has a different use?..\n[ Attempted item: "+chosenItem+" ]");}
                  } else {System.out.println("[ Action not recognized. Use \"Help\" for a list of applicable commands. ]");}
                  System.out.println("");
                  if(inCombat && successful) {allottedItems-=1;getCombatMenu(allottedItems);}
               } else {System.out.println("[ \" I can't do this right now. \" ]");}
            }
            else if(input.startsWith("TAKE ")) {
               boolean success = false;
               String chosenItem = input.substring(5);
               ArrayList<Item> itemsInRoom = dungeonMap.getRoom(MC.getX(), MC.getY()).getItemsInRoom();
               if(!inCombat) {
                  for(int i = 0; i < itemsInRoom.size(); i++) {
                     if(itemsInRoom.get(i).toString().toUpperCase().contains(chosenItem)) {
                        success = true;
                        if(MC.hasItem("Lit Torch [3]") || MC.hasItem("Lit Torch [2]") || MC.hasItem("Lit Torch [1]")) {
                           if(!itemsInRoom.get(i).isNewItem()) {
                              System.out.println("> You hastily reclaim your "+itemsInRoom.get(i).toString().toLowerCase()+", hoping nothing minds you taking it.");
                           } else {
                           System.out.println("You hastily grab the "+itemsInRoom.get(i).toString().toLowerCase()+", hoping nothing minds you taking it.");
                           }
                           if(dungeonMap.getRoom(MC.getX(), MC.getY()).getMonstersInRoom().size() != 0 && Math.random()*4 > 3) {
                              System.out.println("> Suddenly, you hear a low growl from behind your back! Whirling around, you see a\n"+dungeonMap.getRoom(MC.getX(), MC.getY()).getMonsterInRoom(0)+" poised to strike--!");
                              currentStage = "Upkeep";
                              allottedItems = 1;
                              inCombat = true;
                              enemy = dungeonMap.getRoom(MC.getX(), MC.getY()).getMonsterInRoom(0);
                              getCombatMenu(allottedItems);
                           } else {System.out.println("> Luckily, it seems your actions have gone unnoticed, as you stow the\n"+itemsInRoom.get(i).toString().toLowerCase()+" away into your bag.");}
                        } else {
                           if(!itemsInRoom.get(i).isNewItem()) {
                              System.out.println("> You hastily reclaim your "+itemsInRoom.get(i).toString().toLowerCase()+" from the rough stone floor.");
                           } else {
                              System.out.println("You kneel onto the rough cobblestone floor to steal the "+itemsInRoom.get(i).toString().toLowerCase()+" off whatever\ncreature or corpse previously owned it.");
                              System.out.print("> Amidst the suffocating darkness, you feel as though a pair of vigilant eyes are\npiercing a hole through the back of your head");
                           }  
                           if(dungeonMap.getRoom(MC.getX(), MC.getY()).getMonstersInRoom().size() != 0 && Math.random()*3 > 2) {
                              System.out.println("....Suddenly, you hear a scrape from\nbehind you! Whirling around, you see a pair of wide eyes accented by gleaming\nteeth uncomfortably near your head--!");
                              currentStage = "Upkeep";
                              allottedItems = 1;
                              inCombat = true;
                              enemy = dungeonMap.getRoom(MC.getX(), MC.getY()).getMonsterInRoom(0);
                              getCombatMenu(allottedItems);
                           } else {System.out.println("; although nothing makes a move to stop\nyou... Yet.");}
                        }
                        MC.addItemToInventory(itemsInRoom.get(i));
                        dungeonMap.getRoom(MC.getX(), MC.getY()).removeItemFromRoom(itemsInRoom.get(i));
                        System.out.println("");
                     }
                  } if(!success){System.out.println("You cannot find an item by that name within this room. Maybe you are looking for\nsomething else?..\n[ Attempted item: "+chosenItem+" ]\n");}
                  //dungeonMap.getRoom(MC.getX(), MC.getY()).getMonstersInRoom(); - Monster arraylist
                  //dungeonMap.getRoom(MC.getX(), MC.getY()).getItemsInRoom(); - Item arraylist
               } else{System.out.println("[ \" I can't do this amidst combat. \" ]\n");}
            }
            else if (input.startsWith("DROP ")) {
               String chosenItem = input.substring(5);
               if(!inCombat) {
                  if(MC.hasItem(chosenItem)) {
                     String removedItem = MC.getItemFromInventory(chosenItem).toString();
                     if(removedItem.equals("Lit Torch [3]") || removedItem.equals("Lit Torch [2]") || removedItem.equals("Lit Torch [1]")) {
                        System.out.println("As you toss your torch, you watch the flame extinguish itself against the cold\ncobblestones beneath you.\n> You doubt it will be of use anymore.\n"); 
                        MC.removeItemFromInventory(MC.getItemFromInventory(chosenItem));
                     } else {
                        dungeonMap.getRoom(MC.getX(), MC.getY()).addItemToRoom(MC.getItemFromInventory(chosenItem)).setNewItem(false);
                        MC.removeItemFromInventory(MC.getItemFromInventory(chosenItem));
                        if(MC.getEquippedWeapon() != null && MC.getEquippedWeapon().toString().toUpperCase().equals(chosenItem)) {MC.getEquippedWeapon().equip(false);MC.setEquippedWeapon(null);}
                        if(MC.getEquippedArmor() != null && MC.getEquippedArmor().toString().toUpperCase().equals(chosenItem)) {MC.getEquippedArmor().equip(false);MC.setEquippedArmor(null);}
                        if(MC.getEquippedShield() != null && MC.getEquippedShield().toString().toUpperCase().equals(chosenItem)) {MC.getEquippedShield().equip(false);MC.setEquippedShield(null);}
                        if(MC.getEquippedAccessory(1) != null && MC.getEquippedAccessory(1).toString().toUpperCase().equals(chosenItem)) {MC.getEquippedAccessory(1).equip(false);MC.setEquippedAccessory(null, 1);}
                        if(MC.getEquippedAccessory(2) != null && MC.getEquippedAccessory(2).toString().toUpperCase().equals(chosenItem)) {MC.getEquippedAccessory(2).equip(false);MC.setEquippedAccessory(null, 2);}
                        System.out.println("> You throw your "+removedItem.toLowerCase()+" to the ground.\n");
                     }
                  } else {
                     System.out.println("You thoroughly search your bag; however cannot find an item by that name. Perhaps you\nare looking for the wrong thing?..");
                     System.out.println("[ Attempted item: "+chosenItem+" ]\n");
                  }
               } else{System.out.println("[ \" I shouldn't do this right now. \" ]\n");}
            }
            else if (input.contains("HEALTH") || input.contains("INJURIES") || input.contains("WOUNDS") || input.contains("HP") || input.equals("J")) {
               System.out.print("[ \""+MC.getHealthPercentage()+"\" ]\n\n");
            }
            else if (input.contains("INSPECT ")) {
               String targetItem = input.substring(8);
               boolean complete = false;
               if(targetItem.equals("WEAPON")) {
                  for(int i = 0; i < MC.getInventoryList().size(); i++) {
                     if(MC.getInventoryList().get(i).getType().contains("Weapon") && MC.getInventoryList().get(i).isEquipped()) {
                        System.out.println(MC.getInventoryList().get(i).inspectItem());
                        complete = true;
                     }
                  }
                  if(!complete) {System.out.println("You do not currently have an equipped weapon.\n[ Attempted weapon: "+targetItem+" ]\n");}
               } else if(MC.getItemFromInventory(targetItem) != null) {
               System.out.println(MC.getItemFromInventory(targetItem).inspectItem());
               } else {
                  System.out.println("You thoroughly search your bag; however cannot find an item by that name. Perhaps you\nare looking for the wrong thing?..");
                  System.out.println("[ Attempted item: "+targetItem+" ]\n");
               }
            }
            
            
            // Combat
            else if (inCombat != true && input.startsWith("FIGHT ")) {
               String target = input.substring(6);
               if(dungeonMap.getRoom(MC.getX(), MC.getY()).getMonstersInRoom().size() != 0 && dungeonMap.getRoom(MC.getX(), MC.getY()).getMonstersInRoom().toString().toUpperCase().contains(target)) {
                  currentStage = "Upkeep";
                  allottedItems = 1;
                  inCombat = true;
                  enemy = dungeonMap.getRoom(MC.getX(), MC.getY()).getMonsterInRoomFromString(target);
                  getCombatMenu(allottedItems);
               } else {
                  System.out.println("...You cannot identify your target within the close confines of this room. Perhaps you\nshould place your priorities elsewhere?..");
                  System.out.println("[ Attempted combatant: "+target+" ]\n");
               }
            }
            else if (inCombat == true && currentStage.equals("Upkeep") && input.startsWith("ATTACK")) {
               playerStatus = "Attacking";
               int playerDamage;
               if(MC.getEquippedWeapon() != null) {playerDamage = MC.getEquippedWeapon().getDamage();}
               else {playerDamage = 5;}
               enemy.setHealth(enemy.getHealth()-playerDamage);
               System.out.println("[ You hit "+enemy.getMonsterType()+" for "+playerDamage+" damage! ]");
               if(enemy.getHealth() < 0) {enemy.setHealth(0);}
               System.out.println("[ "+enemy.getMonsterType()+" has "+enemy.getHealth()+" health remaining. ]\n\n[ Press [ENTER] to proceed.]");
               commandPrompt = false;
               allottedItems = 0;
               currentStage = "Enemy";
               if(enemy.getHealth() == 0) {
                  System.out.println("[ "+enemy.getMonsterType()+" has been slain! ]\n");
                  dungeonMap.getRoom(MC.getX(), MC.getY()).removeMonsterFromRoom(enemy);
                  allottedItems = 1;
                  combatDuration = 0;
                  currentStage = "Upkeep";
                  playerStatus = null;
                  inCombat = false;
                  enemy = null;
                  commandPrompt = true;
               }
            }
            else if (inCombat == true && currentStage.equals("Upkeep") && input.startsWith("DEFEND")) {
               playerStatus = "Defending";
               System.out.println("You assume a defensive stance, preparing for the worst.\n\n[ Press [ENTER] to proceed.]");
               commandPrompt = false;
               allottedItems = 2;
               currentStage = "Enemy";
            }
            else if (inCombat == true && currentStage.equals("Upkeep") && (input.startsWith("FLEE") || input.startsWith("RUN") || input.startsWith("ESCAPE"))) {
               System.out.print("..You narrowly escape the encounter");
               if(Math.random()*8 > 5) {
                  System.out.println(", however you sustain a few scratches from the\nmonster.\n");
                  int enemyDamage = enemy.getDamage()*((int)(Math.random() * 2)+1);
                  if(MC.getEquippedArmor() != null) {
                     MC.setHP(MC.getHP() - (int)((double)enemyDamage*(((double)MC.getEquippedArmor().getArmor())/100)));
                  } else {MC.setHP(MC.getHP() - enemyDamage);}
               } else {System.out.println(" without any further injuries.\n");}
               allottedItems = 1;
               combatDuration = 0;
               currentStage = "Upkeep";
               playerStatus = null;
               inCombat = false;
               enemy = null;
            }
            else if(currentStage.equals("Enemy")) {
               int enemyDamage = enemy.getDamage() + combatDuration;
               if(playerStatus.equals("Defending")) {
                  if(MC.getEquippedShield() != null) {
                     enemyDamage = (int)((double)(enemy.getDamage()+combatDuration)*(1-MC.getEquippedShield().getBlock()));
                  } else {
                     enemyDamage /= 2;
                  }
               }
               if(MC.getEquippedArmor() != null) {
                  enemyDamage = (int)((double)enemyDamage*(((double)MC.getEquippedArmor().getArmor())/100));
               }
                  System.out.print("[ "+enemy.getMonsterType()+" hit you for "+enemyDamage+" damage. ]");
                  MC.setHP(MC.getHP()-enemyDamage);
               currentStage = "Upkeep";
               combatDuration++;
               if((allottedItems <= 1) && !(playerStatus.equals("Defending"))) {allottedItems = 1;}
               else {allottedItems = 2;}
               getCombatMenu(allottedItems);
            }
            // End Combat
            
            
            else if (input.contains("PRINT DEBUG")) {
               String combatStatus = "False";
               if(inCombat) {combatStatus = "True";}
               System.out.println(dungeonMap.getRoom(MC.getX(), MC.getY()).getItemsInRoom()+" - Item arraylist\n"
               +dungeonMap.getRoom(MC.getX(), MC.getY()).getMonstersInRoom()+" - Monster arraylist\n"
               +"["+MC.debugGetTempItemList()+"] - Player temp inventory list\n"
               +MC.getInventoryList()+" - Player actual inventory list\n"
               +"("+dungeonMap.getNumRows()+", "
               +dungeonMap.getNumCols()+") - Map dimensions\n"
               +"("+MC.getX()+", "+MC.getY()+") - Player coordinates\n"
               +"["+MC.getHP()+"/"+MC.maxHealth+"] - Player health points\n"
               +MC.getEquippedWeapon()+" - Equipped weapon\n"+MC.getEquippedArmor()+" - Equipped armor\n"+MC.getEquippedShield()+" - Equipped shield\n"+MC.getEquippedAccessory(1)+", "+MC.getEquippedAccessory(2)+" - Equipped accessories");
               if(inCombat) {
                  System.out.println("~~Combat~~\n"
                  +"["+enemy.getMonsterType()+"] - Current enemy\n"
                  +currentStage+" - Current stage\n"
                  +playerStatus+" - Player status\n"
                  +combatDuration+" Turns - Combat duration"
                  );
               }
               System.out.println(combatStatus+" - Combat status\n");
            }
            else if (!input.startsWith("MOVE ") && !input.startsWith("WALK ") && !input.startsWith("GO ") && !input.startsWith("UP") && !input.startsWith("DOWN") && !input.startsWith("LEFT") && !input.startsWith("RIGHT")) {
               System.out.println("[ Action not recognized. Use \"Help\" for a list of applicable commands. ]\n");
            } 



            // Movement
            mainloop:
            if (input.startsWith("MOVE ") || input.startsWith("WALK ") || input.startsWith("GO ") || input.startsWith("UP") || input.startsWith("DOWN") || input.startsWith("LEFT") || input.startsWith("RIGHT")) {
               String direction;
               if(input.startsWith("GO ")) {
                  direction = input.substring(3);
               } else if (input.startsWith("MOVE ") || input.startsWith("WALK ")) {
                  direction = input.substring(5);
               } else {
                  direction = input.substring(0);
               }
               String NESW;
               if ((direction.equals("UP") || direction.equals("FORWARD") || direction.equals("FORWARDS") || direction.equals("NORTH") || direction.equals("N")) && (MC.getY()+1 < dungeonMap.getNumRows()-1)) {
               MC.moveTo(MC.getX(), MC.getY()+1);
               NESW = "North";
               } else if ((direction.equals("DOWN") || direction.equals("BACKWARD") || direction.equals("BACKWARDS") || direction.equals("SOUTH") || direction.equals("S")) && (MC.getY() > 0)) {
               MC.moveTo(MC.getX(), MC.getY()-1);
               NESW = "South";
               } else if ((direction.equals("LEFT") || direction.equals("WEST") || direction.equals("W")) && (MC.getX() > 0)) {
               MC.moveTo(MC.getX()-1, MC.getY());
               NESW = "West";
               } else if ((direction.equals("RIGHT") || direction.equals("EAST") || direction.equals("E")) && (MC.getX()+1 < dungeonMap.getNumCols()-1)) {
               MC.moveTo(MC.getX()+1, MC.getY());
               NESW = "East";
               } else {
                  System.out.println("You find yourself before a flat wall, with no discernable way forward... Perhaps you\nshould try a different direction?\n");
                  break mainloop;
               }
               if(MC.hasItem("Compass")) {
                  System.out.println("You take out your compass and study it as you begin to move "+NESW+"ward.");
               } else {
                  System.out.println("Utterly lost, you begin to wander in whichever direction feels right to you.");
               }
               if(MC.hasItem("Lit Torch [3]")) {
                  MC.removeStringItemFromInventory("Lit Torch [3]");
                  MC.addItemToInventory(Item.forceNewItem(5));
                  System.out.println("> As you move, you can feel your torch dimming by the second. You feel this illumination\nwon't last long.");
               } else if(MC.hasItem("Lit Torch [2]")) {
                  MC.removeStringItemFromInventory("Lit Torch [2]");
                  MC.addItemToInventory(Item.forceNewItem(6));
                  System.out.println("> Your torch barely illuminates a thing anymore... It could go out at any second.");
               } else if(MC.hasItem("Lit Torch [1]")) {
                  MC.removeStringItemFromInventory("Lit Torch [1]");
                  System.out.println("> Your torch flickers.. And then goes out completely. As darkness rapidly engulfs you,\nyou get the sense that you are being watched.");
               }
               if(MC.hasItem("Tattered Map")) {
                  System.out.print(". . .\nAfter some time, you think it wise to open your map and try to discern your current\nlocation.\n");
                  MC.useItem("MAP");
               } else {
                  System.out.print("[ \"Shit... I really wish I had a map on me.\" ]\n");
               }
               
               System.out.println("");
            }
            // End Movement
         
         
       }
   }
   public static void getCombatMenu(int allottedItems) {
      if(allottedItems == 2) {System.out.println("\n\n\n\n\n                                         -----------------------------\n                                         | Attack | Defend | Item(2) |\n                                         -----------------------------");}
      else if(allottedItems == 1) {System.out.println("\n\n\n\n\n                                         ----------------------------\n                                         | Attack | Defend |  Item  |\n                                         ----------------------------");}
      else {System.out.println("\n\n\n\n\n                                         ----------------------------\n                                         | Attack | Defend |  xxxx  |\n                                         ----------------------------");}
   }
}