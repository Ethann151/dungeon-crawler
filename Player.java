import java.util.ArrayList;

public class Player {
   private ArrayList<Item> inventory;
   private String playerName;
   private int health;
   private int currentX;
   private int currentY;
   private String itemList = "";
   public final int maxHealth;
   

   public Player(int health, String playerName, int startingX, int startingY) {
      this.health = health;
      this.maxHealth = health;
      this.playerName = playerName;
      inventory = new ArrayList<Item>();
      this.currentX = startingX;
      this.currentY = startingY;
   }
   
   public String debugGetItemList() {return itemList;}

   public void addItemToInventory(Item item) {
      inventory.add(item);
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
   if(health < 20) {return "I can't take another hit.";}
   else if(health < 40) {return "My injuries are starting to get severe.";}
   else if(health < 60) {return "I'm wounded, but not severely.";}
   else if(health < 80) {return "I'm slightly hurt, but I can still keep going.";}
   else if(health < 95) {return "I'm unscathed for the most part.";}
   else if(health <= 100) {return "I'm uninjured.";}
   return"";
   }
   
   public String viewInventory() {
      for(int i = 0; i < inventory.size(); i++) {
         String itemName = inventory.get(i).toString(); 
         if(itemList.contains(itemName)) {
            int itemPos = itemList.indexOf(itemName);
            int amount = Integer.parseInt(itemList.substring(itemPos-4, itemPos-3));
            amount++;
            String prefix = itemList.substring(0, itemPos-5);
            itemList = itemList.substring(0, (itemPos-7))+"- ("+amount+"x) "+itemName+itemList.substring(itemPos+itemName.length());
         } else {
            itemList += "- (1x) "+itemName+"\n";
         }
      }
      String returnList = itemList;
      itemList = "";
      return returnList;
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
         if(currentY == 0) { System.out.print(currentY+1+"st ");
         } else if(currentY == 1) { System.out.print(currentY+1+"nd ");
         } else if(currentY == 2) { System.out.print(currentY+1+"rd ");
         } else { System.out.print(currentY+1+"th ");
         } System.out.print("room to the North, and the ");
         if(currentX == 0) { System.out.print(currentX+1+"st ");
         } else if(currentX == 1) { System.out.print(currentX+1+"nd ");
         } else if(currentX == 2) { System.out.print(currentX+1+"rd ");
         } else { System.out.print(currentX+1+"th ");
         } System.out.println("room to the East.");
         
      } else if((chosenItem.equals("TORCH") || chosenItem.equals("UNLIT TORCH")) && hasItem("Unlit Torch")) {
         System.out.println("Finding a couple rocks beside your feet, you strike them against eachother repeatedly; eventually generating a spark great enough to ignite your torch.\n> ...You feel this will only last you a few rooms of travel.");
         removeStringItemFromInventory("Unlit Torch");
         addItemToInventory(Item.forceNewItem(4));
         
      } else if((chosenItem.equals("HEALING POTION") || chosenItem.equals("HEALTH POTION") || chosenItem.equals("HEAL")) && hasItem("Healing Potion")) {
         System.out.println("You wrench the cork off your healing potion, immediately downing its' contents.\n> You feel healthier.");
         if(health < maxHealth -25) {
            health+=25;
         } else {
            health = maxHealth;
         }
         removeStringItemFromInventory("Healing Potion");
         //Crystal ball boosts magic damage but breaks when dropped or after 5 uses
         //You firmly grasp your sword within your main hand. Its' presence brings you comfort.
         
      } else if(chosenItem.equals("COMPASS")) {
         System.out.println("[ \"I don't need this right now.\" ]");
      } else {
         System.out.println("You search your bag, but you cannot find an item by that name. Perhaps you are looking for the wrong thing?..");
         System.out.println("[ Attempted item: "+chosenItem+" ]");
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
}
