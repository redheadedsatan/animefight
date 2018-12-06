package com.anime.fight.UserInterface;

import lombok.Getter;

import java.awt.Color;
import java.awt.Point;

public enum Object
{
    DIRT('░', new Color(150, 75, 0));

    @Getter
    private char Display;
    @Getter
    private Color color;

    Object(char Display, Color color)
    {
        this.Display =  Display;
        this.color =  color;
    }
}
