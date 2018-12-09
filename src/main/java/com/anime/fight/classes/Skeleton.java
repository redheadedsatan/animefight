package com.anime.fight.classes;

import com.anime.fight.classes.base.FighterBase;
import com.anime.fight.userInterface.Object;

public class Skeleton extends FighterBase {
    private double abilityRange;
    private double expHp;
    public Skeleton(){
        super(Object.SKELETON);

    }
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
            double A,B;
            A = position.getX();
            B = position.getY();
            double m = (position.getY() - target.getPosition().getY())/Math.abs(position.getX() - target.getPosition().getX());
            double dissX1 = ((2*m*(m*A + B) + Math.pow(Math.pow(2*m*(m*A + B),2) - 4 * ((m*m + 1)*Math.pow((m+A +B*B),2) - movmentSpeed*movmentSpeed),0.5))/
                    2*(m*m +1));
            double dissX2 = ((2*m*(m*A + B) + Math.pow(Math.pow(2*m*(m*A + B),2) - 4 * ((m*m + 1)*Math.pow((m+A +B*B),2) - movmentSpeed*movmentSpeed),0.5))/
                    2*(m*m +1));
            if (dissX1 - target.getPosition().getX() > dissX2 - target.getPosition().getX())
                position.setLocation(A + dissX1,B + (m*dissX1 - (m*A + B)));
            else
                position.setLocation(A + dissX2,B + (m*dissX2 - (m*A + B)));

        }
    }
}
