package com.anime.fight.game.userInterface;

import java.awt.Color;
import lombok.Getter;

public enum Object
{
    NULL('█', new Color(255,20,147)),

    // Interface
    VER_LINE('║', Color.WHITE, true),
    HOR_LINE('═', Color.WHITE, true),
    NW_LINE('╔', Color.WHITE, true),
    SW_LINE('╚', Color.WHITE, true),
    NE_LINE('╗', Color.WHITE, true),
    SE_LINE('╝', Color.WHITE, true),

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
    private final char Char;
    @Getter
    private final Color color;
    @Getter
    private final boolean gui;

    // TODO : maybe make other Enum with type parameters...

    Object(char Char, Color color)
    {
        this.Char =  Char;
        this.color =  color;
        this.gui = false;
    }

    Object(char Char, Color color, boolean gui)
    {
        this.Char =  Char;
        this.color =  color;
        this.gui =  gui;
    }
}
