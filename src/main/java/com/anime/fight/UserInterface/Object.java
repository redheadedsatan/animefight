package com.anime.fight.UserInterface;

import com.anime.fight.Util.ColorChar;
import lombok.Getter;

import java.awt.Color;
import java.awt.Point;

public enum Object
{
    DIRT('░', new Color(150, 75, 0)),
    DIRT2('░', new Color(150, 75, 150)),
    RANGER('R', new Color(0,200,0)),
    SKELETON('S', new Color(200,200,200)),
    CHARGER('C', new Color(100,100,0)),
    NECROMANCER('N', new Color(100,100,100)),
    BESERKER('B', new Color(200,0,0));

    @Getter
    private char Char;
    @Getter
    private Color color;

    Object(char Char, Color color)
    {
        this.Char =  Char;
        this.color =  color;
    }
}
