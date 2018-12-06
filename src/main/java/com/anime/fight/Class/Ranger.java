package com.anime.fight.Class;

import com.anime.fight.Class.Base.FighterBase;
import lombok.Getter;


public class Ranger extends FighterBase {

    @Getter
    protected double abiltDmgMod;
    /**
     * shoots an arrow with lower dmg and move away from the target
     */
    public void ability(FighterBase target) {
        target.defend(baseDamage * abiltDmgMod);
        int x,y;
        x = 2*(position.getX() - target.position.getX())/(Math.abs(position.getX() - target.position.getX()));
        y = 2*(position.getY() - target.position.getY())/(Math.abs(position.getY() - target.position.getY()));
        int m = (position.getY() - target.position.getY())/Math.abs(position.getX() - target.position.getX());
        
    }
}
