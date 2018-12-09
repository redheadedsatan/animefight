package com.anime.fight.util;

import java.awt.Color;
import lombok.Value;

@Value
public class ColorChar {
    private char Char;
    private Color color;

    public String getString()
    {
        return String.valueOf(Char);
    }
}
