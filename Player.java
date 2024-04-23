import java.util.*;
public class Player extends Character{
    Random random = new Random();
    int level = 1;
    int exp_now = 0;
    int exp_next = 10;
    int atk_bonus = random.nextInt(3) + 1;
    int def_bonus = random.nextInt(3) + 1;

    public Player(String name) {
        super(name);
    }

    public void print_info() {
        super.print_info();
        System.out.println("--level      : " + level);
        System.out.println("--exp_next   :" + this.exp_next);
    }

    void attack(Player p, Monster m) {
        int damage_point;

        System.out.println("\n" + p.name + "は" + m.name + "を攻撃した！");
        if (random.nextInt(20) == 0) {
            System.out.println("クリティカルヒット！");
            damage_point = atk;
        } else {
            damage_point = Math.max(p.atk-m.def, 0);
        }
        m.hp -= damage_point;
        System.out.println(m.name + "は" + damage_point + "のダメージを受けた！");
    }
    
    void heal() {
        int heal_point = Math.min(hp_max / 5, hp_max - hp);

        hp += hp + heal_point;
        hp = Math.min(hp_max , hp);
        System.out.println(name + "は" + heal_point + "回復した");
    }

    void examine(Monster m) {
        m.print_info();
    }

    void battle(Player p, Monster m) {
        Scanner sc = new Scanner(System.in);
        boolean battle_flag = false;

        while (!battle_flag) {
            System.out.println(
                "\n" + 
                "どうする？\n" + 
                "-0: 調べる\n" +
                "-1: 攻撃\n" +
                "-2: 治療"
            );

            switch (sc.nextLine()) {
                case "0":
                    p.examine(m);
                    battle_flag = true;
                    break;
                case "1":
                    p.attack(p, m);
                    battle_flag = true;
                    break;
                case "2":
                    p.heal();
                    battle_flag = true;
                    break;
                default:
                    System.err.println("0, 1, 2の内いづれかを入力してください");
            }
        }
        // sc.close();
    }

    void addexp(int exp) {
        exp_now += exp;
        if(exp_now >= exp_next) {
            this.levelup();
            exp = exp_now - exp_next;
            exp_next += (level-1) * 5; 
            this.addexp(exp);
        }
    }

    void levelup() {
        level ++;
        this.hp_max += random.nextInt(6) + 5;
        this.atk += random.nextInt(6) + atk_bonus;
        this.def += random.nextInt(6) + def_bonus;
        System.out.println("レベルアップ！" + name + "はレベル" + level + "になった");
        this.print_info();
    }
}