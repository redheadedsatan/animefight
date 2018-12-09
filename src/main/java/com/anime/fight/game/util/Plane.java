package com.anime.fight.game.util;

import com.anime.fight.api.eventbus.EventBus;
import com.anime.fight.game.event.PlaneChange;
import com.anime.fight.game.userInterface.Object;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public class Plane extends TreeMapExtended {

    @Getter
    private TreeMapExtended gui;

    @Override
    protected void setPointPlane(int Z, Point point, Object obj) {
        clear();
        TreeMapExtended p = this;
        super.setPointPlane(Z, point, obj);
        eventBus.post(new PlaneChange(this, p));
    }

    @Override
    protected void _fillPlane(int Z, Point upperLeft, Point downRight, Object obj, boolean override) {
        clear();
        TreeMapExtended p = this;
        super._fillPlane(Z, upperLeft, downRight, obj, override);
        eventBus.post(new PlaneChange(this, p));
    }

    @Override
    protected void _fillPlaneRandom(int Z, Point upperLeft, Point downRight, Object[] obj, boolean override) {
        clear();
        TreeMapExtended p = this;
        super._fillPlaneRandom(Z, upperLeft, downRight, obj, override);
        eventBus.post(new PlaneChange(this, p));
    }

    public Map<Point, Object> getFlatPlane()
    {
        Map<Point, Object> localPlane = new HashMap<>();
        gui.forEach((ZAxis, plane) -> {
            plane.forEach((point, obj) ->
            {
                localPlane.put(point, obj);
            });
        });

        forEach((ZAxis, plane) -> {
            plane.forEach((point, obj) ->
            {
                if (localPlane.get(point) != null)
                {
                    return;
                }
                localPlane.put(point, obj);
            });
        });

        gui.clear();
        return localPlane;
    }

    public Plane(int seed, EventBus eventBus)
    {
        super(seed, eventBus);
        this.gui = new TreeMapExtended(seed, eventBus);
    }

    public void guiMakeBox(int Z, Point upperLeft, Point downRight)
    {
        downRight.setLocation(downRight.x - 1, downRight.y - 1);

        gui.ifNull(Z);

        gui.fillPlane(Z, upperLeft, new Point(upperLeft.x, downRight.y), Object.VER_LINE);
        gui.fillPlane(Z, new Point(downRight.x, upperLeft.y), downRight, Object.VER_LINE);
        gui.fillPlane(Z, upperLeft, new Point(downRight.x, upperLeft.y), Object.HOR_LINE);
        gui.fillPlane(Z, new Point(upperLeft.x, downRight.y), downRight, Object.HOR_LINE);

        gui.setPointPlane(Z, upperLeft, Object.NW_LINE);
        gui.setPointPlane(Z, new Point(upperLeft.x, downRight.y), Object.SW_LINE);
        gui.setPointPlane(Z, downRight, Object.SE_LINE);
        gui.setPointPlane(Z, new Point(downRight.x, upperLeft.y), Object.NE_LINE);
    }
}
