import java.util.Scanner;
import java.util.ArrayList;
//Disguise mimic as a chest
//Add chests to contain items(?)
//Implement monster drops
//Implement combat
public class Game {   
   public static void main(String[] args) {
      boolean exitGame = false;
      Game game = new Game();
      Map dungeonMap = new Map(12, 11);
      Scanner scanner = new Scanner(System.in);
      Player MC = new Player(100, "Hero", ((int) Math.random()*12 + 1), ((int) Math.random()*11 + 1));
      MC.addItemToInventory(Item.forceNewItem(3));
      MC.addItemToInventory(Item.forceNewItem(2));
      MC.addItemToInventory(Item.forceNewItem(1));
      MC.addItemToInventory(Item.forceNewItem(8));
      MC.addItemToInventory(Item.forceNewItem(7));
      MC.addItemToInventory(Item.forceNewItem(7));
      
      while (exitGame != true) {
            System.out.print("Enter command: ");
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
               +"\nTake [ITEM] - Take an item that is within the room you are in."
               +"\nDrop [ITEM] - Drop an item from within your inventory onto the floor of the room you are inside"
               +"\nMove [NORTH/EAST/SOUTH/WEST] - Move in a specified direction (Other movement expressions are recognized (e.g. Up/Down/Left/Right))"
               +"\nFight [MONSTER] - Initiate combat with a monster in the same room as you. (Use \"Help - Combat\" for a more in-depth explanation of combat mechanics.)"
               +"\nHealth/Injuries/Wounds - Returns an estimation of your current health & any sustained injuries\n");
            }
            else if (input.equals("HELP - COMBAT") || input.equals("HELP COMBAT") || input.equals("COMBAT HELP")) {
            int initialHealth = MC.getHP();
               System.out.println("\n\n\n\n\n\n\n\n\n\nUpon initiating combat with a monster,\nyou will be presented with the option of either:");
               System.out.println("                                         ----------------------------"
                              +"\n                                         | Attack | Defend |  Item  |"
                              +"\n[ Press [ENTER] to proceed. ]            ----------------------------");
               input = scanner.nextLine();
               if (input.contains("")) {
                  System.out.println("Choosing to attack will deal damage equal to your weapon's current attack power.");
                  System.out.println("                         [ Inspect your weapon and then attack the enemy. ]\n");
                  System.out.print("Enter Command: Inspect");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("Silver Longsword\n----------\n17 Damage\nA heavy blade forged from a silver alloy. Inflicts bonus damage to werewolves.\n");
                  System.out.print("Enter Command: Attack");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("[ You hit [ENEMY] for 17 damage! ]");
                  System.out.println("[ENEMY] has 33 health remaining.\n");
                  System.out.println("Having finished your attack stage, it is now the enemy's turn. Their first attack will always do the same \namount of damage relative to their typing; however subsequent attacks will scale in damage indefinitely.\n[ Press [ENTER] to proceed.]");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("[ Enemy hit you for 15 damage. ]\n\n[ Press [ENTER] to proceed.]");
                  MC.setHP(20);
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("\n\n\n\n\n\n\n\n\n\nThat marks the end of the first combat cycle.\nIn order to check your remaining health, you must use the Check Health command; which will give you a\nrough estimate of your health with the downside of having to forego your ability to see the enemy's health or\ndamage for the remainder of the turn.");
                  System.out.println("                                         ----------------------------"
                              +"\n                                         | Attack | Defend |  Item  |"
                              +"\n[ Check your health. ]                   ----------------------------");
                  System.out.print("Enter Command: Check Health");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.print("[ \""+MC.getHealthPercentage()+"\" ]\n\n");
                  System.out.println("Looks like you're hurt pretty bad. Luckily, you're able to use up to one item at the upkeep of your turn.\nCheck your inventory for any items worth using.\n[ Use a healing potion. ]\n");
                  System.out.print("Enter Command: View Inventory");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("Checking through your bag, you find:\n"+MC.viewInventory());
                  System.out.print("Enter Command: Use Healing Potion");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  MC.useItem("HEALTH POTION");
                  System.out.println("\n\n\n\n\n\n\n\n\n\nGreat! Because you're still badly injured, make sure to defend this turn.\nDefending makes you take reduced damage(0 if you have a powerful enough shield), and allows you to use another\nitem at the upkeep of your turn if you need it.\nUnfortunately, because you checked your health you will not be able to see how much damage the enemy deals,\nbut without any shield a monster will always deal half(50%) damage to a blocking opponent.\n");
                  System.out.println("                                         ----------------------------"                              
                                  +"\n                                         | Attack | Defend |  xxxx  |"
                                  +"\n[ Defend the enemy's incoming attack. ]  ----------------------------");
                  System.out.print("Enter Command: Defend");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("[...]");
                  System.out.println("[ Enemy hit you for ??? damage. ]\n\n[ Press [ENTER] to proceed.]");
                  input = scanner.nextLine();
              } if (input.contains("")) {
                  System.out.println("That covers all the mechanics of combat! Feel free to reopen this guide at any time if you ever feel confused about any combat mechanics.");
              }
              MC.setHP(initialHealth);
            }
            //End Tutorial
            else if (input.equals("LOOK") || input.equals("SEARCH")) {
               if(MC.hasItem("Lit Torch [3]") || MC.hasItem("Lit Torch [2]") || MC.hasItem("Lit Torch [1]")) {
                  System.out.print(MC.look(dungeonMap));
               } else {
                  System.out.println("You attempt to look around and gain your bearings, but it's too dark to see anything...");
                  if((Math.random()*2 > 1) && (dungeonMap.getRoom(MC.getX(), MC.getY()).getItemsInRoom().size() > 1)) {
                     System.out.println("> All you can make out is a single "+dungeonMap.getRoom(MC.getX(), MC.getY()).getItemInRoom(0).toString().toLowerCase()+" on the floor beside your feet...");
                  }
                  System.out.println("Maybe you have something to light the way?..\n");
               }
            }
            else if (input.contains("INVENTORY")) {
               System.out.println("Checking through your bag, you find:\n"+MC.viewInventory());
            }
            else if (input.startsWith("USE ") || input.startsWith("ITEM ")) {
               String chosenItem;
               if(input.startsWith("USE ")) {
                  chosenItem = input.substring(4);
               } else {
                  chosenItem = input.substring(5);
               }
               MC.useItem(chosenItem);
               System.out.println("");
            }
            else if(input.startsWith("TAKE ")) {
               String chosenItem = input.substring(5);
               ArrayList<Item> itemsInRoom = dungeonMap.getRoom(MC.getX(), MC.getY()).getItemsInRoom();
               for(int i = 0; i < itemsInRoom.size(); i++) {
                  if(itemsInRoom.get(i).toString().toUpperCase().contains(chosenItem)) {
                     if(MC.hasItem("Lit Torch [3]") || MC.hasItem("Lit Torch [2]") || MC.hasItem("Lit Torch [1]")) {
                        System.out.println("You hastily grab the "+itemsInRoom.get(i).toString().toLowerCase()+", hoping nothing minds you taking it.");
//have a monster attack you with a 50% chance per item or initiated via fight.
                        System.out.println("> Luckily, it seems your actions have gone unnoticed, as you stow the "+itemsInRoom.get(i).toString().toLowerCase()+" away into your bag.");
                        //Success
                     } else {
                        if(itemsInRoom.get(i).toString().equals("Tattered Map") || itemsInRoom.get(i).toString().equals("Compass") || itemsInRoom.get(i).toString().equals("Rusty Sword")) {
                           System.out.println("> You hastily reclaim your "+itemsInRoom.get(i).toString().toLowerCase()+" from the rough stone floor.");
                        } else {
                           System.out.println("You kneel onto the rough cobblestone floor to steal the "+itemsInRoom.get(i).toString().toLowerCase()+" off whatever creature or corpse previously owned it.");
                           System.out.println("> Amidst the suffocating darkness, you feel as though a pair of vigilant eyes are piercing a hole through the back of your head; although nothing makes a move to stop you... Yet.");
                        }
                        //Success?
//have a monster attack you with a 50% chance per item or initiated via fight.
                        //"Suddenly, you hear a scrape from behind you--[Innitiate combat!]"
                     }
                     MC.addItemToInventory(itemsInRoom.get(i));
                     dungeonMap.getRoom(MC.getX(), MC.getY()).removeItemFromRoom(itemsInRoom.get(i));
                     System.out.println("");
                  }
               }
               //dungeonMap.getRoom(MC.getX(), MC.getY()).getMonstersInRoom(); - Monster arraylist
               //dungeonMap.getRoom(MC.getX(), MC.getY()).getItemsInRoom(); - Item arraylist
            }
            else if (input.startsWith("DROP ")) {
               String chosenItem = input.substring(5);
               if(MC.hasItem(chosenItem)) {
                  String removedItem = MC.getItemFromInventory(chosenItem).toString();
                  if(removedItem.equals("Lit Torch [3]") || removedItem.equals("Lit Torch [2]") || removedItem.equals("Lit Torch [1]")) {
                     System.out.println("As you toss your torch, you watch the flame extinguish itself against the cold cobblestones beneath you.\n> You doubt it will be of use anymore.\n"); 
                     MC.removeItemFromInventory(MC.getItemFromInventory(chosenItem));
                  } else {
                     dungeonMap.getRoom(MC.getX(), MC.getY()).addItemToRoom(MC.getItemFromInventory(chosenItem));
                     MC.removeItemFromInventory(MC.getItemFromInventory(chosenItem));
                     System.out.println("> You throw your "+removedItem.toLowerCase()+" to the ground.\n");
                  }
               } else {
                  System.out.println("You thoroughly search your bag; however cannot find an item by that name. Perhaps you are looking for the wrong thing?..");
                  System.out.println("[ Attempted item: "+chosenItem+" ]\n");
               }
            }
            else if (input.contains("HEALTH") || input.contains("INJURIES") || input.contains("WOUNDS") || input.contains("HP") || input.equals("J")) {
               System.out.print("[ \""+MC.getHealthPercentage()+"\" ]\n\n");
            }  
            else if (input.contains("PRINT DEBUG")) {
               System.out.println(dungeonMap.getRoom(MC.getX(), MC.getY()).getItemsInRoom()+" - Item arraylist\n"
               +dungeonMap.getRoom(MC.getX(), MC.getY()).getMonstersInRoom()+" - Monster arraylist\n"
               +"["+MC.debugGetItemList()+"] - Player inventory list\n"
               +"("+dungeonMap.getNumRows()+", "
               +dungeonMap.getNumCols()+") - Map Dimensions\n"
               +"("+MC.getX()+", "+MC.getY()+") - Player coordinates\n"
               );
            }
            else if (!input.startsWith("MOVE ") && !input.startsWith("WALK ")) {
               System.out.println("[ Action not recognized. Use \"Help\" for a list of applicable commands. ]\n");
            }

            // Movement
            mainloop:
            if (input.startsWith("MOVE ") || input.startsWith("WALK ")) {
               String direction = input.substring(5);
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
                  System.out.println("You find yourself before a flat wall, with no discernable way forward... Perhaps you should try a different direction?\n");
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
                  System.out.println("> As you move, you can feel your torch dimming by the second. You feel this illumination won't last long.");
               } else if(MC.hasItem("Lit Torch [2]")) {
                  MC.removeStringItemFromInventory("Lit Torch [2]");
                  MC.addItemToInventory(Item.forceNewItem(6));
                  System.out.println("> Your torch barely illuminates a thing anymore... It could go out at any second.");
               } else if(MC.hasItem("Lit Torch [1]")) {
                  MC.removeStringItemFromInventory("Lit Torch [1]");
                  System.out.println("> Your torch flickers.. And then goes out completely. As darkness rapidly engulfs you, you get the sense that you are being watched.");
               }
               if(MC.hasItem("Tattered Map")) {
                  System.out.print(". . .\nAfter some time, you think it wise to open your map and try to discern your current location.\n");
                  MC.useItem("MAP");
               } else {
                  System.out.print("[ \"Shit... I really wish I had a map on me.\" ]\n");
               }
               
               System.out.println("");
            }
         // End Movement
       }
   }
}