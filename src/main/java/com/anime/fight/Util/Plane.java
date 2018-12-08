package com.anime.fight.Util;

import com.anime.fight.UserInterface.Object;
import com.anime.fight.UserInterface.UserInterface;
import com.sun.glass.ui.Size;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.Getter;

public class Plane extends HashMap<Integer, Map<Point, Object>> {

    //@Getter
    //private Map<Integer, Map<Point, Object>> plane;

    public Map<Integer, Map<Point, Object>> getNearPlane(int Z)
    {
        Map<Integer, Map<Point, Object>> localPlane = new HashMap<>();
        for (int i = -1; i <= 1; i++)
        {
            Map<Point, Object> pl = get(i + Z);
            if (pl == null)
            {
                continue;
            }
            localPlane.put(i + Z, pl);
        }
        return localPlane;
    }

    public void setPointPlane(int Z, Point point, Object obj)
    {
        Map<Point, Object> pl = get(Z);
        if (pl == null)
        {
            pl = new HashMap<>();
        }
        pl.put(point, obj);
        put(Z, pl);
    }

    public void fillPlane(int Z, Point upperLeft, Point downRight, Object obj)
    {
        for (int y = upperLeft.y; y <= downRight.y; y++)
        {
            for (int x = upperLeft.x; x <= downRight.x; x++)
            {
                Point point = new Point(x, y);
                get(Z).put(point, obj);
            }
        }
    }

    public void fillPlane(int Z, Point upperLeft, Point downRight, Object obj, boolean override)
    {
        for (int y = upperLeft.y; y <= downRight.y; y++)
        {
            for (int x = upperLeft.x; x <= downRight.x; x++)
            {
                Point point = new Point(x, y);
                if (override && get(Z).get(point) == null)
                {
                    continue;
                }
                get(Z).put(point, obj);
            }
        }
    }

    public void fillPlaneRandom(int Z, Point upperLeft, Point downRight, Object[] obj)
    {
        for (int y = upperLeft.y; y <= downRight.y; y++)
        {
            for (int x = upperLeft.x; x <= downRight.x; x++)
            {
                Point point = new Point(x, y);
                Random r = new Random();
                int num = r.nextInt(obj.length);
                get(Z).put(point, obj[num]);
            }
        }
    }

    public void fillPlaneRandom(int Z, Point upperLeft, Point downRight, Object[] obj, boolean override)
    {
        for (int y = upperLeft.y; y <= downRight.y; y++)
        {
            for (int x = upperLeft.x; x <= downRight.x; x++)
            {
                Point point = new Point(x, y);
                if (override && get(Z).get(point) == null)
                {
                    continue;
                }
                Random r = new Random();
                int num = r.nextInt(obj.length);
                get(Z).put(point, obj[num]);
            }
        }
    }
}
