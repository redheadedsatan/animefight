package com.anime.fight.Class.Base;

public interface FighterInterface {
    double critHit();
    void defend(double baseDamage);
    void basicAttack(FighterBase target);
    void logic(FighterBase[] targets);
    void ability(FighterBase target);
}
