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
}
