package com.anime.fight.UserInterface;

import com.anime.fight.Util.ColorChar;
import lombok.Getter;

import java.awt.Color;
import java.awt.Point;

public enum Object
{
    DIRT('░', new Color(150, 75, 0)),
    DIRT2('░', new Color(150, 75, 150));

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
