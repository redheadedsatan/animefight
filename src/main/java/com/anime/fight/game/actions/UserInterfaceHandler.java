package com.anime.fight.game.actions;

import com.anime.fight.api.annotation.EventActive;
import com.anime.fight.api.eventbus.Subscribe;
import com.anime.fight.game.event.BasicActiveClass;
import com.anime.fight.game.event.CameraCreated;
import com.anime.fight.game.event.Frame;
import com.anime.fight.game.interfaces.Camera;
import com.anime.fight.game.userInterface.Console;
import java.awt.Point;
import lombok.Getter;

@EventActive
public class UserInterfaceHandler extends BasicActiveClass
{
    @Getter
    private Point mousePos;
    @Getter
    private Camera camera;
    @Getter
    private Console console;

    @Subscribe
    public void onCameraCreated(CameraCreated camera)
    {
        this.camera = camera.getCamera();
        this.console = camera.getCamera().getConsole();
    }

    @Subscribe
    public void onFrame(Frame frame)
    {
        mousePos = frame.getMousePos();
        int a = (camera.getFOV_X() * 2);
        int b = a - 10;
        double c = (double)b / (double)(camera.getFOV_X() * 2);
        int d = (int)(c * console.getCanvas().getSize().width + this.console.getCanvas().getLocationOnScreen().x);

        int x = d;
        a = (camera.getFOV_Y() * 2);
        b = a - 10;
        c = (double)b / (double)(camera.getFOV_Y() * 2);
        d = (int)(c * console.getCanvas().getSize().height + this.console.getCanvas().getLocationOnScreen().y);

        int y = d;
        Point point = new Point(mousePos.x - x,
                mousePos.y - y);
//        System.out.println(point);
    }
}
