public class Monster {
   private int health;
   private int damage;
   private String type;
   private String monsterType;
   private static int id;
   private static boolean predetermined;
   private final String[] monsterTypes = {
            // Miniboss
            "Basilisk",
            // Boss
            "Ancient Dragon",
            "Goblin",
            "Skeleton Warrior",
            "Zombie",
            "Orc",
            "Dark Sorcerer",
            "Giant Spider",
            "Vampire Bat",
            "Slime",
            "Mimic",
            "Lich",
            "Troll",
            "Werewolf",
            "Gargoyle",
            "Wraith",
            "Minotaur",
            "Doppelgänger",
            "Demon Hound"
        };
   private int randomID = (int) (((monsterTypes.length-8) * Math.random())+8);

   public Monster() {
      if(predetermined) {
         predetermined = false;
         randomID = id;
      }
         if(randomID >= monsterTypes.length) {randomID = monsterTypes.length-1;}
         monsterType = monsterTypes[randomID];
         setMonsterStats();
   }
   
   public static Monster forceNewMonster(int ID) {
      predetermined = true;
      id = ID-1;
      return new Monster();
   }

   
   public String getMonsterType() {
      return this.monsterType;
   }
   
   private void setMonsterStats() {
      if(randomID == 3) {damage=7;}
      else if(randomID == 8) {damage=23;}
      else if(randomID == 17) {damage=7;}
      else if(randomID == 28) {damage=10;}
      else if(randomID == 29) {damage=17;}
   }
   
   public String toString() {
      return monsterType;
   }
}