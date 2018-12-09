package com.anime.fight.classes.base;

public interface FighterInterface
{
    double critHit();
    void defend(double baseDamage);
    void basicAttack(FighterBase target);
    void logic(FighterBase[] targets);
    void ability(FighterBase target);
}
