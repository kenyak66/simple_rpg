public class Monster extends Character{
    int exp;
    boolean known = false;

    public Monster(String name, int hp_max, int atk, int def, int exp, boolean known) {
        super(name, hp_max, atk, def);
        this.exp = exp;
        this.known = known;
    }

    void attack(Monster m, Player p) {
        int damage_point = Math.max(m.atk-p.def, 0);

        System.out.println("\n" + m.name + "は" + p.name + "を攻撃した！");
        p.hp -= damage_point;
        System.out.println(p.name + "は" + damage_point + "のダメージを受けた！");
    }
}