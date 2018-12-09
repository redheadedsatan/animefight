package com.anime.fight.util;

import com.anime.fight.event.PlaneChange;
import com.anime.fight.eventbus.EventBus;
import com.anime.fight.userInterface.Object;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

public class Plane extends HashMap<Integer, Map<Point, Object>> {

    @Getter
    @Setter
    private int seed;

    private final EventBus eventBus;

    private final int offsetY = 1000000;

    private int calculateSeed(int x, int y)
    {
        return seed + y * offsetY + x;
    }

    public Plane(int seed, EventBus eventBus)
    {
        super();
        this.seed = seed;
        this.eventBus = eventBus;
    }

    /**
     * Gets the -1 -> +1 Z axis Plans
     * @param Z
     * @return
     */
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
        Plane p = this;
        Map<Point, Object> pl = get(Z);
        if (pl == null)
        {
            pl = new HashMap<>();
        }
        pl.put(point, obj);
        put(Z, pl);
        eventBus.post(new PlaneChange(this, p));
    }

    public void fillPlane(int Z, Point upperLeft, Point downRight, Object obj)
    {
        _fillPlane(Z, upperLeft, downRight, obj, true);
    }

    public void fillPlane(int Z, Point upperLeft, Point downRight, Object obj, boolean override)
    {
        _fillPlane(Z, upperLeft, downRight, obj, override);
    }

    public void fillPlaneRandom(int Z, Point upperLeft, Point downRight, Object[] obj)
    {
        _fillPlaneRandom(Z, upperLeft, downRight, obj, true);
    }

    public void fillPlaneRandom(int Z, Point upperLeft, Point downRight, Object[] obj, boolean override)
    {
        _fillPlaneRandom(Z, upperLeft, downRight, obj, override);
    }

    private void _fillPlane(int Z, Point upperLeft, Point downRight, Object obj, boolean override)
    {
        Plane p = this;
        for (int y = upperLeft.y; y <= downRight.y; y++)
        {
            for (int x = upperLeft.x; x <= downRight.x; x++)
            {
                Point point = new Point(x, y);
                ifNull(Z);
                Object o = get(Z).get(point);
                if (o == null || o.isGui()) {}
                else if (!override && obj != null)
                {
                    continue;
                }
                get(Z).put(point, obj);
            }
        }
        eventBus.post(new PlaneChange(this, p));
    }

    private void _fillPlaneRandom(int Z, Point upperLeft, Point downRight, Object[] obj, boolean override)
    {
        Plane p = this;
        for (int y = upperLeft.y; y <= downRight.y; y++)
        {
            for (int x = upperLeft.x; x <= downRight.x; x++)
            {
                Point point = new Point(x, y);
                ifNull(Z);
                Object o = get(Z).get(point);
                if (o == null || o.isGui()) {}
                else if (!override && obj != null)
                {
                    continue;
                }
                Random r = new Random(calculateSeed(x, y));
                int num = r.nextInt(obj.length);
                get(Z).put(point, obj[num]);
            }
        }
        eventBus.post(new PlaneChange(this, p));
    }

    private void ifNull(int Z)
    {
        if (get(Z) == null)
        {
            put(Z, new HashMap<>());
        }
    }

    public void guiFrame(int Z, Point upperLeft, Point downRight)
    {
        downRight.setLocation(downRight.x - 1, downRight.y - 1);

        fillPlane(Z, upperLeft, new Point(upperLeft.x, downRight.y), Object.VER_LINE);
        fillPlane(Z, new Point(downRight.x, upperLeft.y), downRight, Object.VER_LINE);
        fillPlane(Z, upperLeft, new Point(downRight.x, upperLeft.y), Object.HOR_LINE);
        fillPlane(Z, new Point(upperLeft.x, downRight.y), downRight, Object.HOR_LINE);

        setPointPlane(Z, upperLeft, Object.NW_LINE);
        setPointPlane(Z, new Point(upperLeft.x, downRight.y), Object.SW_LINE);
        setPointPlane(Z, downRight, Object.SE_LINE);
        setPointPlane(Z, new Point(downRight.x, upperLeft.y), Object.NE_LINE);
    }
}
