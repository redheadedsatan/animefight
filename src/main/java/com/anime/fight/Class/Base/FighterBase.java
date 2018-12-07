package com.anime.fight.Class.Base;

import com.anime.fight.UserInterface.Object;
import java.awt.Point;
import java.util.Random;
import lombok.Getter;

public abstract class FighterBase implements FighterInterface
{

    private final double defenceMultiplayer = 10;
    @Getter
    protected Object Look;
    @Getter
    protected double lastAttackTime;
    @Getter
    protected double lastAbilltyTime;
    @Getter
    protected double abiltyColdown;
    @Getter
    protected Point position;
    @Getter
    protected double movmentSpeed;
    @Getter
    protected double attackSpeed;
    @Getter
    protected int team;

    @Getter
    protected double hpMax;
    @Getter
    protected double hp;
    @Getter
    protected double baseDamage;
    @Getter
    protected double baseRange;
    @Getter
    protected double SelfminimumDamage;
    @Getter
    protected double armor;
    /**
     * value between 0 and 1
     */
    @Getter
    protected double critChance;
    @Getter
    protected boolean isDead;
    @Getter
    protected int fighterLevel;
    private int exp;

    public double critHit()
    {
        return baseDamage * 1.5;
    }

    public final void defend(double baseDamage)
    {
        double damage = baseDamage - Math.pow(defenceMultiplayer*armor, 0.5);
        if (damage <= SelfminimumDamage)
            hp -= SelfminimumDamage;
        else
            hp -= damage;
    }
    public final FighterBase findCloseEnemy(FighterBase[] enemy)
    {
        FighterBase close= enemy[0];
        double minDistence = position.distance(enemy[0].position);
        for (int i = 1; i < enemy.length;i++) {
            if (minDistence > position.distance(enemy[i].position)) {
                minDistence = position.distance(enemy[i].position);
                close = enemy[i];
            }
        }
        return close;
    }
    public final void basicAttack(FighterBase target)
    {
        Random r = new Random();
        if (r.nextDouble() <= critChance)
            target.defend(critHit());
        else
            target.defend(baseDamage);
    }
}
