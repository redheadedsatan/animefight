package com.anime.fight.Class;

import com.anime.fight.Class.Base.FighterBase;

public class Necromancer extends FighterBase {
    private int SkeltonNum = 0;
    public void ability(FighterBase target) {
        for (int i = -2; i <= 2;i+=4)
        {
            for (int j = -2; j <= 2; j+=4){
                if(SkeltonNum < 4) {
                    Skeleton a = new Skeleton();
                    SkeltonNum++;
                }
            }
        }

    }

    public void logic(FighterBase[] targets) {
        FighterBase target = findCloseEnemy(targets);
        double diss = position.distance(target.getPosition());
        if (diss <= baseRange && (System.currentTimeMillis() - lastAttackTime) == attackSpeed)
        {
            if (System.currentTimeMillis() - lastAbilltyTime == abiltyColdown && SkeltonNum < 4) {
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
