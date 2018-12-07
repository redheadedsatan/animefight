package com.anime.fight.Class;

import com.anime.fight.Class.Base.FighterBase;

public class Skeleton extends FighterBase {
    private double abilityRange;
    private double expHp;
    public void ability(FighterBase target) {
        hp = 0;
        target.defend(baseDamage * 2);
        isDead = true;
    }

    public void logic(FighterBase[] targets) {
        FighterBase target = findCloseEnemy(targets);
        double diss = position.distance(target.getPosition());
        if (diss <= baseRange && (System.currentTimeMillis() - lastAttackTime) == attackSpeed)
        {
            if (hp < expHp) {
                ability(target);
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
