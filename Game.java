import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Game {
    
    public static void main(String[] args) {
        int encount = 0;
        int count_known = 0;
        ArrayList<Monster> monster_list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(
            "あなたはどこにでもいる普通のHAMAキャン大学生。\n" +
            "とある科目の3000字手書きレポートを提出するため、あなたは夜中のHAMAキャンに訪れた。\n" +
            "しかし、なんだかHAMAキャンの様子が変なのだ...\n" +
            "はたしてあなたは3000字手書きレポートを提出することができるだろうか...\n" +
            "...\n"
        );
        System.out.println("あなたの前は？...");
        Player p = new Player(sc.nextLine());
        p.print_info();

        Monster Goblin = new Monster("Goblin", 10, 8, 3, 4, false);
        Monster Zombi = new Monster("Zombi", 15, 10, 6, 5, false);
        monster_list.add(Goblin);
        monster_list.add(Zombi);

        try {
            while ((count_known < 4) && !(p.dead)) {
                switch (encount) {
                    case 3:
                        Monster Orc = new Monster("Orc", 30, 20, 15, 10, false);
                        monster_list.add(Orc);
                        break;
                    case 10:
                        Monster Centaur = new Monster("Centaur", 50, 40, 30, 20, false);
                        monster_list.add(Centaur);
                        break;
                }

                for(int i = 0; i <= new Random().nextInt(10) + 3; i++) {
                    System.out.println(".");
                }

                Monster m_data = monster_list.get(new Random().nextInt(monster_list.size()));
                Monster m = new Monster(m_data.name, m_data.hp_max, m_data.atk, m_data.def, m_data.exp, m_data.known);
                System.out.println("野生の" + m.name + "が現われた！");
                encount++;
                if (!m_data.known) {
                    m_data.known = true;
                    count_known++;
                }

                while (!(p.dead) && !(m.dead)) {
                    p.battle(p, m);

                    if (m.hp <= 0) {
                        m.dead = true;
                        System.out.println(p.name + "は" + m.name + "を倒した！");
                        if (count_known == 4) {
                            System.out.println(
                                "...邪悪なものの気配がする\n" +
                                "どうする？\n" + 
                                "-0: ここに残る\n" +
                                "-1: 先へ進む"
                                );
                                switch (sc.nextLine()) {
                                    case "0":
                                        count_known = 3;
                                        m_data.known = false;
                                        break;
                                    case "1":
                                        break;
                                    default:
                                        System.err.println("0, 1の内いづれかを入力してください");
                                }
                            break;
                        }
                        p.addexp(m.exp);
                    } else {
                        m.attack(m, p);
                        if (p.hp <= 0) {
                            p.dead = true;
                            System.out.println(p.name + "は" + m.name + "にやられた...");
                            System.out.println("レポート提出失敗...これは落単だねぇ");
                            System.out.println("おしまい");
                        }
                    }
                }
            }

            if (!(p.dead)) {
                while (p.hp < p.hp_max) {
                    p.heal();
                }
                System.out.println(p.name + "は全回復した！");
    
                for(int i = 0; i <= new Random().nextInt(10) + 10; i++) {
                    System.out.println("ゴゴゴゴゴゴゴゴゴゴゴゴ");
                }
    
                Monster m_final = new Monster("Dragon", 100, 50, 40, 100, false);
                System.out.println(m_final.name + "が現われた！");
                m_final.known = true;
                encount++;
                count_known++;
    
                while (!(p.dead) && !(m_final.dead)) {
                    p.battle(p, m_final);
    
                    if (m_final.hp <= 0) {
                        m_final.dead = true;
                        System.out.println(p.name + "は" + m_final.name + "を倒した！");
                        if (count_known == 4) {
                            break;
                        }
                        p.addexp(m_final.exp);
                        System.out.println(
                            "HAMAキャンに平和が戻った！\n" +
                            "あなたは3000字手書きレポートを提出することに無事成功した。\n" +
                            "これでまたレポートを書き続ける楽しいキャンパスライフを送ることができるね！\n" +
                            "無事に卒業できるといいね！\n" +
                            "おしまい"
                        );
                    } else {
                        m_final.attack(m_final, p);
                        if (p.hp <= 0) {
                            p.dead = true;
                            System.out.println(p.name + "は" + m_final.name + "にやられた...");
                            System.out.println("レポート提出失敗...これは落単だねぇ");
                            System.out.println("おしまい");
                        }
                    }
                }
            }

            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./log.csv", true)));
            pw.println(p.name + "," + p.level + "," + count_known + "," + sdf.format(date));
            BufferedReader br = new BufferedReader(new FileReader("./log.csv"));

            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("今までの結果");
            String line;
			while(null != (line = br.readLine())) {
				String[] split = line.split(",");
                System.out.println("------------------------------------------------------------");
				System.out.println(
                    "Name: " + split[0] +
                    " Level: " + split[1] +
                    " Enemies Seen:" +split[2] +
                    " Date: " + split[3]
                );
            }

            br.close();
            pw.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        sc.close();
    }
}