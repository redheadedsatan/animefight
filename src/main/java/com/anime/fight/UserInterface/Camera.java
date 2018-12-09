package com.anime.fight.userInterface;

import java.awt.Point;

public interface Camera
{
    Point getPosition();
    double getVerticalSpeed();
    double getHorizontalSpeed();
    int getFOV_X();
    int getFOV_Y();
    int getWidth();
    int getHeight();
}
