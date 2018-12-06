package com.anime.fight.Class;

import com.anime.fight.Class.Base.FighterBase;
import jdk.nashorn.internal.objects.annotations.Getter;

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
    public FighterBase findCloseEnemy(FighterBase[] enemy)
    {
        FighterBase close= targets[0];
        int minDistence = position.distance(targets[0].position);
        for (int i = 1; i < targets.length;i++) {
            if (minDistence > position.distance(targets[i].position)) {
                minDistence = position.distance(targets[i].position);
                close = targets[i];
            }
        }
        return close;
    }
    public void logic(FighterBase[] targets) {
        FighterBase target = findCloseEnemy(targets);
        double diss = position.distance(target.position);
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
