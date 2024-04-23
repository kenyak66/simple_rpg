import java.util.Random;

public class Character {
    String name;
    int hp;
    int hp_max;
    int atk;
    int def;
    boolean dead;
    Random random = new Random();

    public Character(String name, int hp_max, int atk, int def) {
        this.name = name;
        this.hp_max = hp_max;
        this.hp = hp_max;
        this.atk = atk;
        this.def = def;
    }

    public Character(String name) {
        this.name = name;
        this.hp_max = random.nextInt(10) + 15;
        this.hp = hp_max;
        this.atk = random.nextInt(3) + 7;
        this.def = random.nextInt(3) + 5;
    }

    void print_info() {
        System.out.println("--------------------------");
        System.out.println("--name      :" + this.name);
        System.out.println("--hp(hp_max):" + this.hp + "(" + this.hp_max + ")");
        System.out.println("--atk       :" + this.atk);
        System.out.println("--def       :" + this.def);
    }
}
