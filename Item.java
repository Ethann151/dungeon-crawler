public class Item {
   private static int id = 1;
   private String itemType;
   private static boolean predetermined;
   private boolean isEquipped;
   private final String[] itemTypes = {
            //Unrollable
            "Tattered Map",
            "Compass",
            "Rusty Sword",
            "Lit Torch [3]",
            "Lit Torch [2]",
            "Lit Torch [1]",
            //Rollable
            "Healing Potion",
            "Unlit Torch",
            "Ancient Scroll",
            "Leather Chestplate",
            "Silver Key",
            "Magic Amulet",
            "Goblin Dagger",
            "Spellbook of Fire",
            "Pouch of Gold Coins",
            "Mysterious Rune Stone",
            "Skeleton Bone",
            "Cursed Ring", //20% bonus damage but 5 self-damage per turn
            "Potion of Invisibility",
            "Iron Shield",
            "Dragon Egg", //Enemy drop? Summons dragon boss & 10% rate per room entered with the egg in inventory
            "Trap Disarming Kit",
            "Crystal Orb",
            "Venomous Dagger",
            "Silver Longsword", //17 dmg * 1.2 if werewolf
        };

   public Item() {
      if(predetermined) {
         predetermined = false;
         itemType = itemTypes[id];
      } else {
         int randomID = (int) (((itemTypes.length-6) * Math.random())+6);
         if(randomID >= itemTypes.length) {randomID = itemTypes.length-1;}
         itemType = itemTypes[randomID];
      }
   }
   
   public static Item forceNewItem(int ID) {
      predetermined = true;
      id = ID-1;
      return new Item();
   }
       
   public String getItemType() {
      return this.itemType;
   }
   
   public String toString() {
      return itemType;
   }
}