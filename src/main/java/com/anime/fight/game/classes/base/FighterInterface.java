package com.anime.fight.game.classes.base;

public interface FighterInterface
{
    double critHit();
    void defend(double baseDamage);
    void basicAttack(FighterBase target);
    void logic(FighterBase[] targets);
    void ability(FighterBase target);
}
