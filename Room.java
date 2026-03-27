import java.util.ArrayList;

public class Room {
   private ArrayList<Item> itemsInRoom;
   private ArrayList<Monster> monstersInRoom;
   private final int maxNumberOfMonsters = 3;
   private final int minNumberOfMonsters = 0;
   private final int maxNumberOfItems = 2;
   private final int minNumberOfItems = 1;
   
   public Room() {
      //Generate a random number of monsters and items between 1 and max (inclusive)
      itemsInRoom = new ArrayList<Item>();
      for(int i = 0; i < ((int) (maxNumberOfItems * Math.random() + minNumberOfItems)); i++) {
         itemsInRoom.add(new Item());
      }
      monstersInRoom = new ArrayList<Monster>();
      for(int i = 0; i < ((int) (maxNumberOfMonsters * Math.random() + minNumberOfMonsters)); i++) {
         monstersInRoom.add(new Monster());
      }
   }
   
   public ArrayList<Monster> getMonstersInRoom() {return monstersInRoom;}
   public ArrayList<Item> getItemsInRoom() {return itemsInRoom;}
   public Monster getMonsterInRoom(int value) {return monstersInRoom.get(value);}
   public Item getItemInRoom(int value) {return itemsInRoom.get(value);}
   
   public void addItemToRoom(Item item) {
      itemsInRoom.add(item);
   }
   public void removeItemFromRoom(Item item) {
      itemsInRoom.remove(item);
   }
   //Testing:/boss summon
   public void addMonsterToRoom(Monster monster) {
      monstersInRoom.add(monster);
   }
   public void removeMonsterFromRoom(Monster monster) {
      monstersInRoom.remove(monster);
   }

   
   public String toString() {
   String returnString;
      if(itemsInRoom.size() == 0 && monstersInRoom.size() == 0) {
         returnString = "Scanning the illuminated room, you notice nothing of note";
      } else {
         returnString = "Through the torchlight, you are able to make out a";
         if(monstersInRoom.size() == 1) {returnString+=" single";}
         for(int i = 0; i < itemsInRoom.size(); i++) {
            String firstChar = itemsInRoom.get(i).toString().substring(0, 1).toLowerCase();
            if(firstChar.equals("a") || firstChar.equals("e") || firstChar.equals("i") || firstChar.equals("o") || firstChar.equals("u")) {
               returnString += "n";
            }
            returnString += " "+itemsInRoom.get(i).toString().toLowerCase();
            if(i < itemsInRoom.size() - 1) {
               if(i < itemsInRoom.size() - 2) {returnString += ", a";}
               else {returnString += ", and a";}
            }
         }
         if(monstersInRoom.size() > 0) {
            if(itemsInRoom.size() > 0) {
               returnString += "; however it is fiercly guarded by a";
            }
               for(int i = 0; i < monstersInRoom.size(); i++) {
                  String firstChar = monstersInRoom.get(i).toString().substring(0, 1).toLowerCase();
                  if(firstChar.equals("a") || firstChar.equals("e") || firstChar.equals("i") || firstChar.equals("o") || firstChar.equals("u")) {
                     returnString += "n";
                  }
                  returnString += " "+monstersInRoom.get(i);
                  if(i < monstersInRoom.size() - 1) {
                     if(i < monstersInRoom.size() - 2) {returnString += ", a";}
                     else {returnString += ", and a";}
                  }
               }
            returnString += "..";
         }
      }
      return returnString+".\n\n";
   }
}