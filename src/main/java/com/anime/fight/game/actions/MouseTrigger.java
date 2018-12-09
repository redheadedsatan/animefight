package com.anime.fight.game.actions;

import com.anime.fight.api.annotation.EventActive;
import com.anime.fight.api.eventbus.EventBus;
import com.anime.fight.api.eventbus.Subscribe;
import com.anime.fight.game.event.BasicActiveClass;
import com.anime.fight.game.event.ConsoleCreate;
import com.anime.fight.game.event.EventBusFinishedInit;
import com.anime.fight.game.event.KeyClicked;
import com.anime.fight.game.event.PlaneChange;
import com.anime.fight.game.userInterface.Console;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import lombok.Getter;

@EventActive
public class MouseTrigger extends BasicActiveClass {

    @Getter
    private Console console;
    @Getter
    private Point position;
    @Getter
    private EventBus eventBus;

    private double verticalSpeed = 0;
    private double horizontalSpeed = 0;

    @Override
    protected void startUp() throws Exception {
        position = new Point(0, 0);
//        console = userInterface.getConsole();
    }

    @Subscribe
    public void onConsoleCreate(ConsoleCreate console)
    {
        this.console = console.getConsole();
        this.console.getTextArea().addKeyListener(new KeyListener() {
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
                    dy = 0.51;
                }
                if (e.getKeyCode() == KeyEvent.VK_W)
                {
                    dy = -0.51;
                }

                if (e.getKeyCode() == KeyEvent.VK_D)
                {
                    dx = 0.51;
                }
                if (e.getKeyCode() == KeyEvent.VK_A)
                {
                    dx = -0.51;
                }

                if (horizontalSpeed < 2 && horizontalSpeed > -2)
                {
                    horizontalSpeed += dx;
                }
                if (verticalSpeed < 2 && verticalSpeed > -2)
                {
                    verticalSpeed += dy;
                }

                eventBus.post(new KeyClicked(verticalSpeed, horizontalSpeed));
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

                eventBus.post(new KeyClicked(0, 0));
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

    @Subscribe
    public void onPlaneChange(PlaneChange planeChange)
    {
//        System.out.println("hi");
    }
}
