package com.anime.fight.game.userInterface.gui;

import com.anime.fight.game.event.Frame;
import com.anime.fight.game.interfaces.Camera;
import com.anime.fight.game.userInterface.Console;
import com.anime.fight.game.userInterface.CustomObject;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class button extends baseGui
{
    @Getter
    private Point mousePos;
    @Getter
    private String str;
    @Getter
    private Color color;
    @Getter
    private int id;
    @Getter
    private Console console;

    private Cursor cursor;
    private MouseAdapter mouseAdapter;



    public button(int id, String str, Point location, Dimension dimension, Color color, Console console)
    {
        this.id = id;
        this.str = str;
        this.color = color;
        this.location = location;
        this.dimension = dimension;
        this.console = console;
        cursor = new Cursor(Cursor.DEFAULT_CURSOR);
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!onMe())
                {
                    return;
                }
                System.out.println("Clicked On : \"" + str + "\"");
            }
        };
        this.console.getCanvas().addMouseListener(mouseAdapter);
    }

    public boolean onMe()
    {
        if (cursor.getType() == Cursor.DEFAULT_CURSOR)
        {
            return false;
        }
        return true;
    }

    public List<CustomObject> getObjects()
    {
        List<CustomObject> map = new ArrayList<>();
        for (char c : str.toCharArray())
        {
            map.add(new CustomObject(c, color, true));
        }
        return map;
    }

//    public Dimension getDimension(int height)
//    {
//        return new Dimension(str.length(), height);
//    }

    public void onFrame(Frame frame, Camera camera)
    {
        mousePos = frame.getMousePos();
        double a = (camera.getFOV_X() * 2);
        double b = camera.getFOV_X() + location.x - offSet.x ;
        double c = b / a;
        int d = (int)(c * console.getCanvas().getSize().width + console.getCanvas().getLocationOnScreen().x);

        int x = d;
        a = (camera.getFOV_Y() * 2);
        b = camera.getFOV_Y() + location.y - offSet.y;
        c = b / a;
        d = (int)(c * console.getCanvas().getSize().height + console.getCanvas().getLocationOnScreen().y);

        int y = d;
        Point point = new Point(x,
                y);

        if (isContain(mousePos, point, camera, console))
        {
            if (cursor.getType() == Cursor.HAND_CURSOR)
            {
                return;
            }
            cursor = new Cursor(Cursor.HAND_CURSOR);
            console.getCanvas().setCursor(cursor);

        }
        else if (cursor.getType() != Cursor.DEFAULT_CURSOR)
        {
            cursor = new Cursor(Cursor.DEFAULT_CURSOR);
            console.getCanvas().setCursor(cursor);
        }
//        System.out.println(point);
    }
}
