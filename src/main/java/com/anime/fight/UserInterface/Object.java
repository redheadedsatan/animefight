package com.anime.fight.UserInterface;

import com.anime.fight.Util.ColorChar;
import lombok.Getter;

import java.awt.Color;
import java.awt.Point;

public enum Object
{
    // Interface
    VER_LINE('║', Color.WHITE),
    HOR_LINE('═', Color.WHITE),
    NW_LINE('╔', Color.WHITE),
    SW_LINE('╚', Color.WHITE),
    NE_LINE('╗', Color.WHITE),
    SE_LINE('╝', Color.WHITE),

    // Environment
    VOID(' ', new Color(0, 0, 0)),
    DIRT(' ', new Color(150, 75, 0)),
    STONE('▒', new Color(128,128,128)),

    // Classes
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
