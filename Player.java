import java.util.ArrayList;

public class Player {
   private ArrayList<Item> inventory;
   private String playerName;
   private int health;
   private int currentX;
   private int currentY;
   private Item equippedWeapon;
   private Item equippedArmor;
   private Item equippedShield;
   private Item accessory1;
   private Item accessory2;
   private String itemList = "";
   public final int maxHealth;
   

   public Player(int health, String playerName, int startingX, int startingY, Item equippedWeapon, Item equippedArmor, Item equippedShield, Item accessory1, Item accessory2) {
      this.health = health;
      this.maxHealth = health;
      this.playerName = playerName;
      inventory = new ArrayList<Item>();
      this.currentX = startingX;
      this.currentY = startingY;
      this.equippedWeapon = equippedWeapon;
      this.equippedArmor = equippedArmor;
      this.equippedShield = equippedShield;
      this.accessory1 = accessory1;
      this.accessory2 = accessory2;
   }
   
   public String debugGetTempItemList() {return itemList;}
   public ArrayList<Item> getInventoryList() {return inventory;}

   public void addItemToInventory(Item item) {
      inventory.add(item);
      item.setNewItem(false);
   }  public Item removeItemFromInventory(Item item) {
      inventory.remove(item);
      return item;
   }
   public void removeStringItemFromInventory(String item) {
      for(int i = 0; i < inventory.size(); i++) {
         if(inventory.get(i).toString().toUpperCase().equals(item.toUpperCase())) {
            inventory.remove(i);
         }
      }
   }
   public Item getItemFromInventory(String item) {
      for(int i = 0; i < inventory.size(); i++) {
         if(inventory.get(i).toString().toUpperCase().contains(item.toUpperCase())) {
            return inventory.get(i);
         }
      }
   return null;
   }

   
   public boolean hasItem(String itemName) {
      if(inventory.toString().toUpperCase().contains(itemName.toUpperCase())) {
      return true;
      }
      return false;
   }
   
   public String look(Map map) {
      return map.getRoom(currentX, currentY).toString();
   }
   
   public void moveTo(int x, int y) {
      currentX = x;
      currentY = y;
   }
   
   public Room getRoom(Map map) {
      return map.getRoom(currentX, currentY);
   }
   
   public String getHealthPercentage() {
   if(health < maxHealth*0.15) {return "I can't take another hit.";}
   else if(health < (int) maxHealth*0.35) {return "My injuries are starting to get severe.";}
   else if(health < (int) maxHealth*0.5) {return "I'm wounded, but not severely.";}
   else if(health < (int) maxHealth*0.8) {return "I'm slightly hurt, but I can still keep going.";}
   else if(health < (int) maxHealth*0.95) {return "I'm unscathed for the most part.";}
   else if(health <= maxHealth) {return "I'm uninjured.";}
   return"";
   }
   
   public String viewInventory() {
      for(int i = 0; i < inventory.size(); i++) {
         String equippedStatus = "\n";
         if(inventory.get(i).isEquipped()) {
            String equipped = " [EQUIPPED]\n";
         }
         String itemName = inventory.get(i).toString(); 
         if(itemList.contains(itemName)) {
            int itemPos = itemList.indexOf(itemName);
            int amount = Integer.parseInt(itemList.substring(itemPos-4, itemPos-3));
            amount++;
            itemList = itemList.substring(0, (itemPos-7))+"- ("+amount+"x) "+itemName+equippedStatus+itemList.substring(itemPos+itemName.length()+1);
         } else {
            itemList += "- (1x) "+itemName+equippedStatus;
         }
      }
      String returnList = itemList;
      itemList = "";
      return returnList;
   }
   
   public void beginCombat(Monster enemy) {
      int poisionTime = 0;
      int poisionDamage = 0;
      int monsterHealth;
      int monsterDamage;
      String specialType;
      //To be removed ^^^
      String combatGUI = "                                         ----------------------------\n                                         | Attack | Defend |  Item  |\n                                         ----------------------------";
      
      // Entrance text & combat values
      if(enemy.getMonsterType().equals("Goblin")) {
      monsterHealth = 40;
      monsterDamage = 6;
      specialType = "Goblin";
      } else if(enemy.getMonsterType().equals("Skeleton Warrior")) {
      monsterHealth = 40;
      monsterDamage = 9;
      specialType = "Undead";
      } else if(enemy.getMonsterType().equals("Zombie")) {
      monsterHealth = 35;
      monsterDamage = 4;
      specialType = "Undead";
      } else if(enemy.getMonsterType().equals("Orc")) {
      monsterHealth = 60;
      monsterDamage = 12;
      specialType = "Giant";
      } else if(enemy.getMonsterType().equals("Dark Sorcerer")) {
      monsterHealth = 75;
      monsterDamage = 10; // Magic damage, uses spells
      specialType = "Mage";
      } else if(enemy.getMonsterType().equals("Giant Spider")) {
      monsterHealth = 40;
      monsterDamage = 7; // Poison
      specialType = "Giant";
      } else if(enemy.getMonsterType().equals("Vampire Bat")) {
      monsterHealth = 15;
      monsterDamage = 4;
      } else if(enemy.getMonsterType().equals("Slime")) {
      monsterHealth = 10;
      monsterDamage = 3;
      } else if(enemy.getMonsterType().equals("Mimic")) {
      //Subject to change; add mimic function : "Chest..?"
      //1/10 chance for chest room; 1/3 chance a chest is a mimic. Chest rooms are guarenteed 0 items and will spawn 0(66%)-1(33%) large/greedy monsters (e.g. miniboss, Giant, Goblin)-
      monsterHealth = 75;
      monsterDamage = 4;
      } else if(enemy.getMonsterType().equals("Lich")) {
      monsterHealth = 60;
      monsterDamage = 10;
      specialType = "Undead Mage";
      } else if(enemy.getMonsterType().equals("Troll")) {
      monsterHealth = 100;
      monsterDamage = 15;
      specialType = "Giant";
      } else if(enemy.getMonsterType().equals("Werewolf")) {
      monsterHealth = 55;
      monsterDamage = 15;
      specialType = "Giant Beast";
      } else if(enemy.getMonsterType().equals("Gargoyle")) {
      monsterHealth = 60; // 30% dr
      monsterDamage = 8;
      } else if(enemy.getMonsterType().equals("Wraith")) {
      monsterHealth = 50; // 50% chance your attacks miss its' form
      monsterDamage = 6;
      specialType = "Mage";
      } else if(enemy.getMonsterType().equals("Minotaur")) {
      monsterHealth = 150;
      monsterDamage = 12;
      specialType = "Giant Beast";
      } else if(enemy.getMonsterType().equals("Doppelgänger")) {
      // "It's... You."
      monsterHealth = health;
      monsterDamage = 10;
      } else if(enemy.getMonsterType().equals("Demon Hound")) {
      monsterHealth = 60;
      monsterDamage = 7;
      specialType = "Beast";
      } else if(enemy.getMonsterType().equals("Basilisk")) {
      monsterHealth = 300;
      monsterDamage = 15;
      specialType = "Giant";
      //Miniboss
      } else if(enemy.getMonsterType().equals("Ancient Dragon")) {
      monsterHealth = 400;
      monsterDamage = 20;
      specialType = "Giant";
      //Boss
      }
      // End stats
      
      System.out.println(combatGUI);
   }
   
   
   
   
   public void useItem(String chosenItem) {
      if((chosenItem.equals("MAP") || chosenItem.equals("TATTERED MAP")) && hasItem("Tattered Map")) {
         if(hasItem("Lit Torch [3]") || hasItem("Lit Torch [2]")) {
            System.out.print("> With the torchlight illuminating your map, you see that you are within the ");
         } else if(hasItem("Lit Torch [1]")) {
            System.out.print("> With the flickering torchlight dimly illuminating your map, you can see that you are within the ");
         } else {
            System.out.print("> Squinting, you are barely able to make out that you are within the ");
         }
         if(currentY == 0) {System.out.print(currentY+1+"st ");
         } else if(currentY == 1) {System.out.print(currentY+1+"nd ");
         } else if(currentY == 2) {System.out.print(currentY+1+"rd ");
         } else {System.out.print(currentY+1+"th ");
         } System.out.print("room to the North, and the ");
         if(currentX == 0) {System.out.print(currentX+1+"st ");
         } else if(currentX == 1) {System.out.print(currentX+1+"nd ");
         } else if(currentX == 2) {System.out.print(currentX+1+"rd ");
         } else {System.out.print(currentX+1+"th ");
         } System.out.println("room to the East.");
         
      } else if((chosenItem.equals("TORCH") || chosenItem.equals("UNLIT TORCH")) && hasItem("Unlit Torch")) {
         System.out.println("Finding a couple rocks beside your feet, you strike them against eachother repeatedly; eventually generating a spark great enough to ignite your torch.\n> ...You feel this will only last you a few rooms of travel.");
         removeStringItemFromInventory("Unlit Torch");
         addItemToInventory(Item.forceNewItem(4));
         
      } else if((chosenItem.equals("HEALING POTION") || chosenItem.equals("HEALTH POTION") || chosenItem.equals("HEAL")) && hasItem("Healing Potion")) {
         System.out.println("You wrench the cork off your healing potion, immediately downing its' contents.\n> You feel healthier.");
         if(health < maxHealth -50) {
            health+=50;
         } else {
            health = maxHealth;
         }
         removeStringItemFromInventory("Healing Potion");
         //You firmly grasp your sword within your main hand. Its' presence brings you comfort.
         
      } else if(!inventory.contains(chosenItem)) {
         System.out.println("You search your bag, but you cannot find an item by that name. Perhaps you are looking for the wrong thing?..");
         System.out.println("[ Attempted item: "+chosenItem+" ]");
      } else {
         System.out.println("[ \"I don't need this right now.\" ]");
      }
   }

   public String damage(int dmg, Monster source) {
      health-=dmg;
      return "[ "+source.toString()+" hit you for "+dmg+" damage. ]";
   }
   public void setHP(int health) {
      this.health = health;
   }
   public int getX(){return currentX;}
   public int getY(){return currentY;}
   public int getHP(){return health;}
   public Item getEquippedWeapon() {return equippedWeapon;}
   public void setEquippedWeapon(Item weapon) {equippedWeapon = weapon;}
   public Item getEquippedArmor() {return equippedArmor;}
   public void setEquippedArmor(Item armor) {equippedArmor = armor;}
   public Item getEquippedShield() {return equippedShield;}
   public void setEquippedShield(Item shield) {equippedShield = shield;}
   public Item getEquippedAccessory(int ID) {
      if(ID == 1) {return accessory1;}
      else {return accessory2;}
   }
   public void setEquippedAccessory(Item accessory, int ID) {
      if(ID == 1) {accessory1 = accessory;}
      else {accessory2 = accessory;}
   }
}
