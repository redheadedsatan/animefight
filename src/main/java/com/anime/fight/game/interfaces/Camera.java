package com.anime.fight.game.interfaces;

import com.anime.fight.game.userInterface.Console;
import java.awt.Point;

public interface Camera
{
    Point getPosition();
    double getVerticalSpeed();
    double getHorizontalSpeed();
    Console getConsole();
    int getFOV_X();
    int getFOV_Y();
}
