package com.anime.fight.classes;

import com.anime.fight.classes.base.FighterBase;
import com.anime.fight.userInterface.Object;

public class Necromancer extends FighterBase {
    private int SkeltonNum = 0;
    public Necromancer(){
        super(Object.NECROMANCER);

    }
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
            double A,B;
            A = position.getX();
            B = position.getY();
            double m = (position.getY() - target.getPosition().getY())/Math.abs(position.getX() - target.getPosition().getX());
            double dissX1 = ((2*m*(m*A + B) + Math.pow(Math.pow(2*m*(m*A + B),2) - 4 * ((m*m + 1)*Math.pow((m+A +B*B),2) - movmentSpeed*movmentSpeed),0.5))/
                    2*(m*m +1));
            double dissX2 = ((2*m*(m*A + B) + Math.pow(Math.pow(2*m*(m*A + B),2) - 4 * ((m*m + 1)*Math.pow((m+A +B*B),2) - movmentSpeed*movmentSpeed),0.5))/
                    2*(m*m +1));
            if (dissX1 - target.getPosition().getX() < dissX2 - target.getPosition().getX())
                position.setLocation(A + dissX1,B + (m*dissX1 - (m*A + B)));
            else
                position.setLocation(A + dissX2,B + (m*dissX2 - (m*A + B)));
        }
        }
    }

