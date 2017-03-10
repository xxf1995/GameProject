package Controller;

import Model.CombatManager;
import Model.CupCakeWarrior;
import Model.PlayerImpl;

/**
 * Created by Russll on 3/10/17.
 */
public class GameController {
    private PlayerImpl[] players;
    private int numPlayers;


    public GameController(int numPlayers) {
        this.numPlayers = numPlayers;
        this.players = new PlayerImpl[numPlayers];
    }

    public PlayerImpl[] getPlayers() {
        return players;
    }

    public void setUpGame(CombatManager manager) {
        double[] pos1 = {100, 100};
        double[] pos2 = {100, 110};
        double[] pos3 = {100, 120};
        double[] pos4 = {100, 130};
        double[] pos5 = {100, 140};
        double[] pos6 = {0, 100};
        double[] pos7 = {0,110};
        double[] pos8 = {0, 120};
        double[] pos9 = {0, 130};
        double[] pos10 = {0, 140};

//        how to do variable naming with var in java
//        for (int i = 1; i < 7; i += 1) {
//            CupCakeWarrior new
//        }


        int[] color = {10, 20, 30};
        PlayerImpl yitong = new PlayerImpl(1, "Yitong", color);
        PlayerImpl russell = new PlayerImpl(2, "Russell", color);

        CupCakeWarrior newcup1 = new CupCakeWarrior(manager, "Cup Cake Warrior 1", russell, pos1);
        CupCakeWarrior newcup2 = new CupCakeWarrior(manager, "Cup Cake Warrior 2", russell, pos2);
        CupCakeWarrior newcup3 = new CupCakeWarrior(manager, "Cup Cake Warrior 3", russell, pos3);
        CupCakeWarrior newcup4 = new CupCakeWarrior(manager, "Cup Cake Warrior 4", russell, pos4);
        CupCakeWarrior newcup5 = new CupCakeWarrior(manager, "Cup Cake Warrior 5", russell, pos5);
//        ShieldKnight sk1 = new ShieldKnight(manager, "Shield Knight 2", russell, pos5);
        CupCakeWarrior newcup6 = new CupCakeWarrior(manager, "Cup Cake Warrior 6", yitong, pos6);
        CupCakeWarrior newcup7 = new CupCakeWarrior(manager, "Cup Cake Warrior 7", yitong, pos7);
        CupCakeWarrior newcup8 = new CupCakeWarrior(manager, "Cup Cake Warrior 8", yitong, pos8);
//        ShieldKnight sk2 = new ShieldKnight(manager, "Shield Knight 2", yitong, pos9);
//        ShieldKnight sk3 = new ShieldKnight(manager, "Shield Knight 1", yitong, pos10);
        CupCakeWarrior newcup9 = new CupCakeWarrior(manager, "Cup Cake Warrior 9", yitong, pos9);
        CupCakeWarrior newcup10 = new CupCakeWarrior(manager, "Cup Cake Warrior 10", yitong, pos10);
//        CupCakeBerserker newcupber = new CupCakeBerserker(manager, "Cup Cake Berserker 1", yitong, pos10);

        this.players[0] = yitong;
        this.players[1] = russell;
    }
}
