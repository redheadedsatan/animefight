package com.anime.fight.Class;

import com.anime.fight.Class.Base.FighterBase;
import lombok.Getter;


public class Beserker extends FighterBase {

    @Getter
    protected double SelfDmgMinimum = 2;

    public void ability(FighterBase target) {
        double selfDmg = hpMax*0.05;
        if (SelfDmgMinimum > selfDmg) { selfDmg = SelfDmgMinimum; }
        hp -= selfDmg;
        if (hp <= 0)
        {
            hp = 1;
        }
        basicAttack(target);
        basicAttack(target);
    }

    public void logic(FighterBase[] targets) {
        FighterBase target = findCloseEnemy(targets);
        double diss = position.distance(target.getPosition());
        if (diss <= baseRange && (System.currentTimeMillis() - lastAttackTime) == attackSpeed)
        {
            if (System.currentTimeMillis() - lastAbilltyTime == abiltyColdown) {
                ability(target);
                lastAbilltyTime = System.currentTimeMillis();
                lastAttackTime = System.currentTimeMillis();
            }
            else {
                basicAttack(target);
                lastAttackTime = System.currentTimeMillis();
            }
        }
        else {

            }


    }
}
