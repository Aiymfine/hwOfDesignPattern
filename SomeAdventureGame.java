interface CombatSystem{
    void attack(CombatSystem opponent);
    void receiveDamage(int damage);
    boolean isAlive();
}
    interface InventorySystem{
    void addItem(String item);
    void useItem(String item);
}
    interface LevelSystem{
    void initiateLevel(CombatSystem player);
    void completeLevel(CombatSystem player);
}
        class Player implements CombatSystem, InventorySystem {
    private String playerName;
    private int healthPoints;
    private int xp;
    private Backpack backpack;

    public Player(String playerName, int healthPoints) {
        this.playerName=playerName;
        this.healthPoints=healthPoints;
        this.xp=0;
        this.backpack=new Backpack();
    }
        public void attack(CombatSystem opponent) {
            opponent.receiveDamage(12);}
        public void receiveDamage(int damage) {
        healthPoints-=damage;}
        
        public boolean isAlive() {
        return healthPoints >0;
    }
        
        public void gainXP(int xpPoints) {
        xp+=xpPoints;
    }

        public void addItem(String item) {
        backpack.add(item);
    }

        public void useItem(String item) {
        if (backpack.contains(item)) {
            if (item.equals("health potion")) {
                healthPoints+=25;
                System.out.println(playerName +" health potion was used");
            }
                backpack.remove(item);
        }
    }
            public int getHealth() {
                return healthPoints;
    }
}

        class Enemy implements CombatSystem {
        private int healthPoints;
        public Enemy(int healthPoints) {
        this.healthPoints = healthPoints;
    }

        public void attack(CombatSystem opponent) {
        opponent.receiveDamage(10);
    }

        public void receiveDamage(int damage) {
        healthPoints-=damage;
    }

        public boolean isAlive() {
        return healthPoints>0;
    }

        public int rewardXP() {
        return 25;
    }
}

        class Backpack {
        private java.util.List<String> items;
        public Backpack() {
        this.items = new java.util.ArrayList<>();
    }

        public void add(String item) {
        items.add(item); }
    
        public void remove(String item) {
        items.remove(item);
    }
        public boolean contains(String item) {
        return items.contains(item);
    }
        public java.util.List<String> getItems() {
        return items;
    }
}

        class AdventureLevel implements LevelSystem {
        public void initiateLevel(CombatSystem player) {
        System.out.println("Quest begins, be ready!");
    }

        public void completeLevel(CombatSystem player) {
        if (player.isAlive()) {
            System.out.println("Victory, you won");
        } else {
            System.out.println("Defeat, dont give up");
        }
    }   
}

        class ScoreManager {
        private int totalScore;

        public void addPoints(int points) {
        totalScore+=points;
    }
        public int getTotalScore() {
        return totalScore;
    }
}

        public class SomeAdventureGame {
        public static void main(String[] args) {
        Player hero=new Player("Valiant Knight", 120);
        Enemy troll= new Enemy(40);
        AdventureLevel level= new AdventureLevel();
        ScoreManager scoreManager= new ScoreManager();

        level.initiateLevel(hero);

        while (hero.isAlive() && troll.isAlive()) {
            hero.attack(troll);
            if (troll.isAlive()) {
                troll.attack(hero);
            }
        }

        if (hero.isAlive()) {
            hero.gainXP(troll.rewardXP());
            scoreManager.addPoints(60);
            hero.addItem("Health Potion");
            System.out.println("you won against troll and got potion!");
        }

        level.completeLevel(hero);
        System.out.println("yout points: " +scoreManager.getTotalScore());
    }
}
