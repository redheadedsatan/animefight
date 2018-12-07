//package com.anime.fight.Class;
//
//import com.anime.fight.Class.Base.FighterBase;
//import lombok.Getter;
//
//
//public class Ranger extends FighterBase {
//
//    private double abiltyDash;
//    @Getter
//    protected double abiltDmgMod;
//    /**
//     * shoots an arrow with lower dmg and move away from the target
//     */
//    public void ability(FighterBase target) {
//        target.defend(baseDamage * abiltDmgMod);
//        double A,B;
//        A = position.getX();
//        B = position.getY();
//        int m = (position.getY() - target.g.getY())/Math.abs(position.getX() - target.position.getX());
//        double dissX1 = ((2*m*(m*A + B) + Math.pow(Math.pow(2*m*(m*A + B),2) - 4 * ((m*m + 1)*Math.pow((m+A +B*B),2) - abiltyDash*abiltyDash),0.5))/
//                2*(m*m +1));
//        double dissX2 = ((2*m*(m*A + B) + Math.pow(Math.pow(2*m*(m*A + B),2) - 4 * ((m*m + 1)*Math.pow((m+A +B*B),2) - abiltyDash*abiltyDash),0.5))/
//                2*(m*m +1));
//        if (dissX1 - target.getPosition().getX() > dissX2 - target.position.getX())
//            position.setLocation(A + dissX1,B + (m*dissX1 - (m*A + B)));
//        else
//            position.setLocation(A + dissX2,B + (m*dissX2 - (m*A + B)));
//    }
//
//    public void logic(FighterBase[] targets) {
//        FighterBase target = findCloseEnemy(targets);
//        double diss = position.distance(target.position);
//        if (diss <= baseRange && (System.currentTimeMillis() - lastAttackTime) == attackSpeed)
//        {
//            if (System.currentTimeMillis() - lastAbilltyTime == abiltyColdown) {
//                ability(target);
//                lastAbilltyTime = System.currentTimeMillis();
//                lastAttackTime = System.currentTimeMillis();
//            }
//            else {
//                basicAttack(target);
//                lastAttackTime = System.currentTimeMillis();
//            }
//        }
//        else {
//
//        }
//
//    }
//}
