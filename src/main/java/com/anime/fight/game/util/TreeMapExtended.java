package com.anime.fight.game.util;

import com.anime.fight.api.eventbus.EventBus;
import com.anime.fight.game.userInterface.Object;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import lombok.Getter;
import lombok.Setter;

public class TreeMapExtended extends TreeMap<Integer, Map<Point, Object>>
{
    @Getter
    @Setter
    private int seed;

    protected final EventBus eventBus;

    private final int offsetY = 1000000;

    private int calculateSeed(int x, int y)
    {
        return seed + y * offsetY + x;
    }

    public TreeMapExtended(int seed, EventBus eventBus)
    {
        super();
        this.seed = seed;
        this.eventBus = eventBus;
    }

    protected void setPointPlane(int Z, Point point, Object obj)
    {
//        TreeMapExtended p = this;
        Map<Point, Object> pl = get(Z);
        if (pl == null)
        {
            pl = new HashMap<>();
        }
        pl.put(point, obj);
        put(Z, pl);
//        eventBus.post(new PlaneChange(this, p));
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

    protected void _fillPlane(int Z, Point upperLeft, Point downRight, Object obj, boolean override)
    {
//        TreeMapExtended p = this;
        for (int y = upperLeft.y; y <= downRight.y; y++)
        {
            for (int x = upperLeft.x; x <= downRight.x; x++)
            {
                Point point = new Point(x, y);
                ifNull(Z);
                Object o = get(Z).get(point);
                if (o == null) {}
                else if (!override && obj != null)
                {
                    continue;
                }
                get(Z).put(point, obj);
            }
        }
//        eventBus.post(new PlaneChange(this, p));
    }

    protected void _fillPlaneRandom(int Z, Point upperLeft, Point downRight, Object[] obj, boolean override)
    {
//        TreeMapExtended p = this;
        for (int y = upperLeft.y; y <= downRight.y; y++)
        {
            for (int x = upperLeft.x; x <= downRight.x; x++)
            {
                Point point = new Point(x, y);
                ifNull(Z);
                Object o = get(Z).get(point);
                if (o == null) {}
                else if (!override && obj != null)
                {
                    continue;
                }
                Random r = new Random(calculateSeed(x, y));
                int num = r.nextInt(obj.length);
                get(Z).put(point, obj[num]);
            }
        }
//        eventBus.post(new PlaneChange(this, p));
    }

    protected void ifNull(int Z)
    {
        if (get(Z) == null)
        {
            put(Z, new HashMap<>());
        }
    }
}
