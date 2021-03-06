package Model;

import javafx.scene.image.Image;
import View.Sprite;

/**
 * Created by xingfanxia on 2/27/17.
 */
public class CupCakeWarrior extends MeleeMinion {

    public CupCakeWarrior() {
        this.typeName = "CupCake Warrior";
        this.hp = 500;
        this.atk = 40;
        this.armorType = ArmorType.LightArmor;
        this.moveSpeed = 4;
        this.attackSpeed = 1;
    }

    public CupCakeWarrior(CombatManager manager, String name, PlayerImpl master, double[] coords) {
        this.minionName = name;
        this.master = master;
        this.master.add_Minions(this);
        this.hp = 500;
        this.atk = 40;
        this.armor = -2;
        this.maxhp = 500;
        this.armorType = ArmorType.LightArmor;
        this.atkType = AtkType.Normal;
        this.moveSpeed = 4;
        this.healthRegen = 3;
        this.attackSpeed = 1;
        this.rangeOrMelee = 0;
        this.Coords = coords;
        this.atkRange = 15;
        this.priority = 5;
        this.randomMinConst = 0.90;
        this.randomMaxConst = 1.10;
        this.manager = manager;
        manager.instances.add(this);

        this.sprite = new Sprite();
        if (this.master.getTeam() == 1) {
            this.walk = new Image("file:assets/swordmanT1/goodsmt1wk.gif", 50, 50, true, true);
            this.fight = new Image("file:assets/swordmanT1/goodsmt1atk.gif", 50, 50, true, true);
            this.dead = new Image("file:assets/swordmanT1/goodblood.gif", 50, 50, true, true);
            this.def = this.walk;
        } else if (this.master.getTeam() == 2){
            this.walk = new Image("file:assets/swordmanT1/evilsmt1wk.gif", 50, 50, true, true);
            this.fight = new Image("file:assets/swordmanT1/evilsmt1atk.gif", 50, 50, true, true);
            this.dead = new Image("file:assets/swordmanT1/badblood.gif", 50, 50, true, true);
            this.def = this.walk;
        }

        this.sprite.setImage(def);
        this.portalReward = 12;
        this.killReward = 6;
        //set the Frames
    }
}
