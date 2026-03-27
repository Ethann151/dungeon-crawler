public class Monster {
private String monsterType;
   private final String[] monsterTypes = {
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
            "Basilisk",
            "Doppelgänger",
            "Demon Hound",
            "Cave Serpent",
            "Ancient Dragon"
        };

   public Monster() {
      monsterType = monsterTypes[(int) (monsterTypes.length * Math.random())];
   }
   
   public String getMonsterType() {
      return this.monsterType;
   }
   
   public String toString() {
      return monsterType;
   }
}