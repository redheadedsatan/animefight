package com.anime.fight.game.userInterface;

import java.awt.Color;
import lombok.Getter;

public class CustomObject
{
    @Getter
    private final char Char;
    @Getter
    private final Color color;
    @Getter
    private final boolean gui;

    public CustomObject(char Char, Color color)
    {
        this.Char = Char;
        this.color = color;
        this.gui = false;
    }

    public CustomObject(char Char, Color color, boolean gui)
    {
        this.Char = Char;
        this.color = color;
        this.gui = gui;
    }

    public CustomObject(Object obj)
    {
        this.Char = obj.getChar();
        this.color = obj.getColor();
        this.gui = obj.isGui();
    }
}
