package com.anime.fight.game.userInterface.gui;

import com.anime.fight.game.interfaces.Camera;
import com.anime.fight.game.userInterface.Console;
import java.awt.Dimension;
import java.awt.Point;
import lombok.Getter;
import lombok.Setter;

public abstract class baseGui
{
    @Getter
    protected Point location;
    @Getter
    protected Dimension dimension;
    @Getter
    @Setter
    protected Point offSet;

    public void setLocation(int x, int y)
    {
        this.location = new Point(x, y);
    }

    public void setOffSet(int x, int y)
    {
        this.offSet = new Point(x, y);
    }

    protected boolean isContain(Point mousePoint, Point upperLeft, Camera camera, Console console)
    {
//        Point downRight = new Point(location.x + dimension.width,
//                location.y + dimension.height);

        double a = (camera.getFOV_X() * 2);
        double b = camera.getFOV_X() + location.x - offSet.x + dimension.width;
        double c = b / a;
        int d = (int)(c * console.getCanvas().getSize().width + console.getCanvas().getLocationOnScreen().x);

        int x = d;
        a = (camera.getFOV_Y() * 2);
        b = camera.getFOV_Y() + location.y - offSet.y + dimension.height;
        c = b / a;
        d = (int)(c * console.getCanvas().getSize().height + console.getCanvas().getLocationOnScreen().y);

        int y = d;
        Point downRight = new Point(x,
                y);

        if (upperLeft.x > mousePoint.x || upperLeft.y > mousePoint.y)
        {
            return false;
        }

        if (downRight.x < mousePoint.x || downRight.y < mousePoint.y)
        {
            return false;
        }

        return true;
    }
}
