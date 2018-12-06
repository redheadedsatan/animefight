package com.anime.fight.UserInterface;

import com.sun.glass.ui.Size;
import lombok.Getter;

public class UserInterface {

    private final int width = 30;
    private final int hieght = 30;
    @Getter
    private Size windowSize = new Size(width, hieght);

    public UserInterface()
    {
        init();
    }

    private void init()
    {

    }
}
