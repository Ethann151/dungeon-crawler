public class Item {
   private int damage = 0;
   private int armor = 1;
   private double block = 1;
   private int damageReduction = 0;
   private String details;
   private String type = "N/A";
   private String itemType;
   private String description;
   private boolean isEquipped;
   private boolean newItem = true;
   private static int id;
   private static boolean predetermined;
   private final String[] itemTypes = {
            //Unrollable
            "Tattered Map", //Shows your current location relative to the dungeon grid
            "Compass", //Allows the holder to know which direction they are traveling (NESW)
            "Rusty Sword",
            "Lit Torch [3]",
            "Lit Torch [2]",
            "Lit Torch [1]",
            "Silver Key",  //Allows you to enter the final room at one of the corners of the map, dropped by dragon
            "Dragon Egg", //Basilisk drop - Summons dragon boss @ 10% rate per room entered with the egg in inventory
            "The Hero's Blade", 
            // One time spawn (No monsters or other items in room) & guarentees Basilisk fight immediately after taking; 23 magic dmg w/ 1.5x damage on Giant foes "...A powerful foe is drawn towards your weapon."
            // "This room feels... Different.\nA dull glow eminates from the lone blade sitting before you.
            //Rollable
            "Unlit Torch", "Unlit Torch", "Unlit Torch",
            "Healing Potion", "Healing Potion",
            "Ancient Scroll", //Casts a random spell on use, one time use consumable & stacks with attacking
            "Leather Chestplate",
            "Magic Amulet", //10% boosted magical abilities when worn
            "Goblin Dagger", //7 damage * 1.2 if goblin; attacks twice 
            "Spellbook of Fire", //Casts a fire spell as alternate attack that does 20 damage with 3 turn cooldown & 5 self damage
            "Pouch of Gold Coins", //can be used to distract(stun) an enemy for 1 turn?
            "Mysterious Rune Stone", //
            "Skeleton Bone", //
            "Cursed Ring", //25% bonus damage but 5 self-damage per turn
            "Potion of Invisibility", //Allows you to avoid forced combat for 5 rooms or dodge 3 attacks within combat
            "Iron Shield",
            "Trap Disarming Kit", //
            "Crystal Ball", //Gaze into the ball for 1.2x magic damage bonus lasting 5 turns (or 1 combat encounter, whichever is shorter). Shatters when dropped.
            "Venomous Dagger", //10 damage w/ 3 poision damage for 2 turns (poision stacks seperately for dmg & duration)
            "Silver Longsword", // * 1.2 if werewolf
            "Adamantite Shield", //monster drop?
            "Iron Chestplate",
            "Wooden Shield",
        };
   private int randomID = (int) (((itemTypes.length-9) * Math.random())+9);

   public Item() {
      if(predetermined) {
         predetermined = false;
         randomID = id;
      }
         if(randomID >= itemTypes.length) {randomID = itemTypes.length-1;}
         itemType = itemTypes[randomID];
         setItemStats();
   }
   public static Item forceNewItem(int ID) {
      predetermined = true;
      id = ID-1;
      return new Item();
   }   
   public String getItemType() {
      return this.itemType;
   }
   
   public boolean isNewItem() {return newItem;} 
   public void setNewItem(boolean value) {newItem = value;}
   
   public boolean isEquipped() {return isEquipped;}
   public void equip(boolean value) {isEquipped=value;}
   public int getDamage() {return damage;}
   public int getArmor() {return armor;}
   public double getBlock() {return block;}
   public String getType() {return type;}
   
   public String inspectItem() {
      String returnString = "You can't inspect this item right now.";
      if(type.contains("Weapon")) {
         returnString = this.itemType+"\n----------\n"+this.damage+" Damage\n"+"\" "+this.description+" \"\n";
      } else if(type.contains("Armor")) {
         returnString = this.itemType+"\n----------\n"+this.armor/10+" Armor\n"+"\" "+this.description+" \"\n";
      } else if(type.contains("Shield")) {
         returnString = this.itemType+"\n----------\n"+(1-this.block)+" Blocking Power\n"+"\" "+this.description+" \"\n";
      } else if(type.contains("Accessory")) {
         returnString = this.itemType+"\n----------\n"+this.details+"\n\" "+this.description+" \"\n";
      } else if(type.contains("Tool")) {
         returnString = this.itemType+"\n----------\n"+this.details+"\n\" "+this.description+" \"\n";
      }
      return returnString;
   }
   
   private void setItemStats() {
      // Weapons
      if(this.itemType.equals("Rusty Sword")) {damage=7;type="Weapon";description="A flimsy copper blade riddled with rust. It's something, at least.";}
      else if(this.itemType.equals("The Hero's Blade")) {damage=23;type="Weapon - Magic";description="A lightweight runic blade that luminesces upon your touch. Somehow, you find that you are able to read the runes inscribed on the steel; \"The Prophecised One shall take this blade and slay the greatest of foes, freeing the inhabitants of this dungeon from their bloodthirsty desires. Only that who is destined for salvation may lift this blade from its scabbard.\"";}
      else if(this.itemType.equals("Goblin Dagger")) {damage=7;type="Weapon";description="A small serrated blade commonly seen in robbings within the backstreets of Phaiden. You think it could probably get some use due to the cramped nature of these catacombs.";}
      else if(this.itemType.equals("Venomous Dagger")) {damage=10;type="Weapon - Poison";description="A razor-sharp dagger coated in a substance you'd rather not touch. It definitely works on monsters, though.";}
      else if(this.itemType.equals("Silver Longsword")) {damage=17;type="Heavy Weapon - Silver";description="A long, weighted blade comprised of some sort of silver-alloy. It seems significantly more effective against Werewolves and such, despite not being fully silver.";}
      // Armor
      else if(this.itemType.equals("Leather Chestplate")) {damageReduction=25;type="Armor";description="A lightweight, thin chestplate. It could probably absorb a few blows, but you wouldn't stake your life on it.";}
      else if(this.itemType.equals("Iron Chestplate")) {damageReduction=40;type="Armor";description="A strong chestplate comprised almost entirely of iron; aside from some faint adornment in what seems to be runic gold-- The power is long gone, though.";}
      // Shields
      else if(this.itemType.equals("Wooden Shield")) {block=0.6;type="Shield";description="A slightly-rotten shield comprised of wood and iron. You hope you'll never have to discover if it could actually deflect a blade.";}
      else if(this.itemType.equals("Iron Shield")) {block=0.75;type="Shield";description="A generic iron shield well-known in Phaiden for its reliability.";}
      else if(this.itemType.equals("Adamantite Shield")) {block=0.9;type="Shield";description="A strange magical shield comprised of an extremely precious runic element: Adamantite.";}
      // Amulets
      else if(this.itemType.equals("Magic Amulet")) {type="Accessory - Necklace";details="Improves the wearer's magical abilities by a factor of 20%";description="A small silver pendant radiating lingering magic from a time long before";}
      // Rings
      else if(this.itemType.equals("Cursed Ring")) {type="Accessory - Ring";details="Vastly improves physical prowess at the cost of recieving 5 self-damage every turn of combat.";description="A small crimson ring adorned in numerous runes all across its surface. The presence of it makes you uneasy.";}
      // Items/misc.
      else if(this.itemType.equals("Compass")) {type="Tool";description="A nifty tool that displays the direction that it is facing. It's pretty useful for navigation, so you figure it's best to keep it around.";}
      else if(this.itemType.equals("Tattered Map")) {type="Tool";description="A map of the dungeon. A room near the edge of the map appears to be marked with a door, although it looks to be locked.";}
   }
      
   public String toString() {
      return itemType;
   }
}