package com.anime.fight.game.util;

import com.anime.fight.api.eventbus.EventBus;
import com.anime.fight.game.userInterface.CustomObject;
import com.anime.fight.game.userInterface.Object;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import lombok.Getter;
import lombok.Setter;

/**
 * Integer - Z Axis
 * Point - Point in the Z Axis
 * Object - Data On The Char
 */
public class TreeMapExtended extends TreeMap<Integer, Map<Point, CustomObject>>
{
    @Getter
    @Setter
    private int seed;

    protected final EventBus eventBus;

    private final int offsetY = 1000000;

    private int calculateSeed(Point point)
    {
        return calculateSeed(point.x, point.y);
    }

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

    protected void addMap(int Z, Map<Point, CustomObject> map)
    {
        Map<Point, CustomObject> pl = get(Z);
        if (pl == null)
        {
            pl = new TreeMap<>();
        }
        pl.putAll(map);
        put(Z, pl);
    }

    public void setPointPlane(int Z, Point point, Object obj)
    {
//        TreeMapExtended p = this;
        CustomObject regObj = new CustomObject(obj);
        Map<Point, CustomObject> pl = get(Z);
        if (pl == null)
        {
            pl = new HashMap<>();
        }
        pl.put(point, regObj);
        put(Z, pl);
//        eventBus.post(new PlaneChange(this, p));
    }

    public void setPointPlane(int Z, Point point, CustomObject obj)
    {
//        TreeMapExtended p = this;
        Map<Point, CustomObject> pl = get(Z);
        if (pl == null)
        {
            pl = new HashMap<>();
        }
        pl.put(point, obj);
        put(Z, pl);
//        eventBus.post(new PlaneChange(this, p));
    }

    public void setPointPlane(int Z, Point point, Dimension dimension, List<CustomObject> obj)
    {
//        TreeMapExtended p = this;
        int index = 0;
        Map<Point, CustomObject> pl = get(Z);
        if (pl == null)
        {
            pl = new HashMap<>();
        }
        for (int y = 0; y < dimension.height; y++)
        {
            for (int x = 0; x < dimension.width; x++)
            {
                Point p = new Point(point.x + x, point.y + y);
                pl.put(p, obj.get(index));
                index++;
            }
        }
        put(Z, pl);
//        eventBus.post(new PlaneChange(this, p));
    }

    public void fillPlane(int Z, Point upperLeft, Point downRight, Object obj)
    {
        CustomObject obj1 = new CustomObject(obj);
        _fillPlane(Z, upperLeft, downRight, obj1, true);
    }

    public void fillPlane(int Z, Point upperLeft, Point downRight, CustomObject obj)
    {
        _fillPlane(Z, upperLeft, downRight, obj, true);
    }

    public void fillPlane(int Z, Point upperLeft, Point downRight, Object obj, boolean override)
    {
        CustomObject obj1 = new CustomObject(obj);
        _fillPlane(Z, upperLeft, downRight, obj1, override);
    }

    public void fillPlane(int Z, Point upperLeft, Point downRight, CustomObject obj, boolean override)
    {
        _fillPlane(Z, upperLeft, downRight, obj, override);
    }

    public void fillPlaneRandom(int Z, Point upperLeft, Point downRight, Weight[] W)
    {
        _fillPlaneRandom(Z, upperLeft, downRight, W, true);
    }

    public void fillPlaneRandom(int Z, Point upperLeft, Point downRight, Weight[] W, boolean override)
    {
        _fillPlaneRandom(Z, upperLeft, downRight, W, override);
    }

    protected void _fillPlane(int Z, Point upperLeft, Point downRight, CustomObject obj, boolean override)
    {
//        TreeMapExtended p = this;
        for (int y = upperLeft.y; y <= downRight.y; y++)
        {
            for (int x = upperLeft.x; x <= downRight.x; x++)
            {
                Point point = new Point(x, y);
                ifNull(Z);
                CustomObject o = get(Z).get(point);
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

    protected void _fillPlaneRandom(int Z, Point upperLeft, Point downRight, Weight[] W, boolean override)
    {
        int max = 1;
        for (Weight weight : W)
        {
            max += weight.getWeight();
        }
//        TreeMapExtended p = this;
        for (int y = upperLeft.y; y <= downRight.y; y++)
        {
            for (int x = upperLeft.x; x <= downRight.x; x++)
            {
                Point point = new Point(x, y);
                ifNull(Z);
                CustomObject o = get(Z).get(point);
                if (o == null) {}
                else if (!override)
                {
                    continue;
                }
                CustomObject obj = getObjFromWeight(W, point, max);
                get(Z).put(point, obj);
            }
        }
//        eventBus.post(new PlaneChange(this, p));
    }

    protected CustomObject getObjFromWeight(Weight[] W, Point point, int max)
    {
        Random r = new Random(calculateSeed(point));
        int randomNum = r.nextInt(max);
        int nowNum = 0;
        for (Weight weight : W)
        {
            nowNum += weight.getWeight();
            if (randomNum <= nowNum)
            {
                return weight.getObj();
            }
        }
        return new CustomObject(Object.NULL);
    }

    protected void ifNull(int Z)
    {
        if (get(Z) == null)
        {
            put(Z, new HashMap<>());
        }
    }
}
