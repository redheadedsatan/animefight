package com.anime.fight.game.actions;

import com.anime.fight.api.annotation.EventActive;
import com.anime.fight.api.eventbus.EventBus;
import com.anime.fight.api.eventbus.Subscribe;
import com.anime.fight.game.event.BasicActiveClass;
import com.anime.fight.game.event.CameraCreated;
import com.anime.fight.game.event.ConsoleCreate;
import com.anime.fight.game.event.EventBusFinishedInit;
import com.anime.fight.game.event.KeyClicked;
import com.anime.fight.game.interfaces.Camera;
import com.anime.fight.game.userInterface.Console;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import lombok.Getter;

@EventActive
public class MouseTrigger extends BasicActiveClass {

    @Getter
    private Console console;
    @Getter
    private Point position;
    @Getter
    private EventBus eventBus;
    @Getter
    private Camera camera;

    private double verticalSpeed = 0;
    private double horizontalSpeed = 0;

    @Override
    protected void startUp() throws Exception {
        position = new Point(0, 0);
//        console = userInterface.getConsole();
    }

    @Subscribe
    public void onCameraCreated(CameraCreated camera)
    {
        this.camera = camera.getCamera();
    }

    @Subscribe
    public void onConsoleCreate(ConsoleCreate console)
    {
        this.console = console.getConsole();
//        this.console.getTextArea().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("U"),"UString");
//        this.console.getTextArea().getActionMap().put("UString", a);
        this.console.getTextArea().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
//                System.out.println("hi");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                double dx = 0;
                double dy = 0;
                if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    dy += 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_W)
                {
                    dy -= 1;
                }

                if (e.getKeyCode() == KeyEvent.VK_D)
                {
                    dx += 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_A)
                {
                    dx -= 1;
                }

                if (horizontalSpeed < 1.1 && horizontalSpeed > -1.1)
                {
                    horizontalSpeed += dx;
                }
                if (verticalSpeed < 1.1 && verticalSpeed > -1.1)
                {
                    verticalSpeed += dy;
                }
                eventBus.post(new KeyClicked(e, verticalSpeed, horizontalSpeed));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    verticalSpeed = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_W)
                {
                    verticalSpeed = 0;
                }

                if (e.getKeyCode() == KeyEvent.VK_D)
                {
                    horizontalSpeed = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_A)
                {
                    horizontalSpeed = 0;
                }
                eventBus.post(new KeyClicked(e, verticalSpeed, horizontalSpeed));
            }
        });
    }

//    @Subscribe
//    public void onPlaneChange(PlaneChange planeChange)
//    {
//        System.out.println(MouseInfo.getPointerInfo().getLocation().x);
//    }

    @Subscribe
    public void onEventBusFinishedInit(EventBusFinishedInit event)
    {
        eventBus = event.getEventBus();
    }
//
//    @Subscribe
//    public void onFrame(Frame frame)
//    {
//        int a = (camera.getFOV_X() * 2);
//        int b = a - 10;
//        double c = (double)b / (double)(camera.getFOV_X() * 2);
//        int d = (int)(c * console.getTextArea().getSize().width + this.console.getTextArea().getLocationOnScreen().x);
//
//        int x = d;
//        a = (camera.getFOV_Y() * 2);
//        b = a - 10;
//        c = (double)b / (double)(camera.getFOV_Y() * 2);
//        d = (int)(c * console.getTextArea().getSize().height + this.console.getTextArea().getLocationOnScreen().y);
//
//        int y = d;
//        Point point = new Point(MouseInfo.getPointerInfo().getLocation().x - x,
//                                MouseInfo.getPointerInfo().getLocation().y - y);
//        System.out.println(point);
//    }
}
