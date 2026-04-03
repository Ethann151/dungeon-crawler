public class Monster {
   private int health;
   private int damage;
   private String type;
   private String monsterType;
   //private Item specialDrop;
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
            "Doppelganger",
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
   
   public int getDamage() {return damage;}
   public int getHealth() {return health;}
   public void setHealth(int health) {this.health = health;}
   public String getType() {return type;}
   
   private void setMonsterStats() {
      if(this.monsterType.equals("Goblin")) {damage=6;health=40;type="Goblin";}
      else if(this.monsterType.equals("Skeleton Warrior")) {damage=9;health=40;type="Undead";}
      else if(this.monsterType.equals("Zombie")) {damage=4;health=35;type="Undead";}
      else if(this.monsterType.equals("Orc")) {damage=12;health=60;type="Giant";}
      else if(this.monsterType.equals("Dark Sorcerer")) {damage=10;health=75;type="Mage";} // Magic damage, uses spells
      else if(this.monsterType.equals("Giant Spider")) {damage=7;health=40;type="Giant";} // Poison
      else if(this.monsterType.equals("Vampire Bat")) {damage=4;health=15;type="Vampire";} // Heal 1 on hit
      else if(this.monsterType.equals("Slime")) {damage=3;health=10;} // Splits into 2 slimelings w/ half HP and 1 damage upon death
      else if(this.monsterType.equals("Lich")) {damage=10;health=60;type="Undead - Mage";}
      else if(this.monsterType.equals("Mimic")) {damage=4;health=75;type="Undead";}
      // "Chest..?"
      // 1/10 chance for chest room; 1/3 chance a chest is a mimic. Chest rooms are guarenteed 0 items and will spawn 0(66%)-1(33%) large/greedy monsters (e.g. miniboss, Giant, Goblin)
      else if(this.monsterType.equals("Troll")) {damage=15;health=100;type="Giant";}
      else if(this.monsterType.equals("Werewolf")) {damage=15;health=55;type="Giant - Beast";}
      else if(this.monsterType.equals("Gargoyle")) {damage=8;health=60;type="Stonetouched";} // 30% DR
      else if(this.monsterType.equals("Wraith")) {damage=6;health=30;type="Ghost";} // 50% chance for attacks to miss its' form
      else if(this.monsterType.equals("Minotaur")) {damage=12;health=150;type="Giant - Beast";}
      else if(this.monsterType.equals("Doppelganger")) {damage=10;health=100;} // Set health to player current health at start of battle
      // "It's... You."
      else if(this.monsterType.equals("Demon Hound")) {damage=7;health=60;type="Beast";}
      else if(this.monsterType.equals("Basilisk")) {damage=15;health=300;type="Giant";}
      // Miniboss
      else if(this.monsterType.equals("Ancient Dragon")) {damage=20;health=400;type="Giant";}
      // Boss
   }

   public String toString() {
      return monsterType;
   }
}