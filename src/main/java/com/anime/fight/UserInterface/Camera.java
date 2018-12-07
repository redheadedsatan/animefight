package com.anime.fight.UserInterface;

import com.anime.fight.Annotation.UserInterface;
import java.awt.Point;
import lombok.Getter;

public class Camera {

    @Getter
    protected Point position = new Point(0,0);

    protected double verticalSpeed = 0;
    protected double horizontalSpeed = 0;
    protected int FOV_X = 20;
    protected int FOV_Y = 10;
    protected int width = 40;
    protected int height = 20;
}
