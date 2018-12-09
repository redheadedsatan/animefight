package com.anime.fight.userInterface;

import com.anime.fight.Console;
import com.anime.fight.annotation.EventActive;
import com.anime.fight.event.BasicClassEvent;
import com.anime.fight.event.ConsoleCreate;
import com.anime.fight.event.Frame;
import com.anime.fight.eventbus.Subscribe;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.inject.Inject;
import lombok.Getter;

@EventActive
public class MouseTrigger extends BasicClassEvent {

    private final int TopOferLayOffset = 25;

    @Inject
    private Console console;

    @Inject
    private Camera camera;

    @Getter
    private Point mousePos;

    @Override
    protected void startUp() throws Exception {
        mousePos = new Point(0, 0);
    }

    @Subscribe
    public void onConsoleCreate(ConsoleCreate console)
    {
        this.console = console.getConsole();
    }

//    @Subscribe
//    public void onPlaneChange(PlaneChange planeChange)
//    {
//        System.out.println(MouseInfo.getPointerInfo().getLocation().x);
//    }

    @Subscribe
    public void onFrame(Frame frame)
    {
        mousePos.setLocation(MouseInfo.getPointerInfo().getLocation().x - console.getLocationOnScreen().x,
                MouseInfo.getPointerInfo().getLocation().y - console.getLocationOnScreen().y - TopOferLayOffset);
        System.out.println(camera.getPosition());
    }
}
