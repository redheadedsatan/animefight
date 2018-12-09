package com.anime.fight.userInterface;

import com.anime.fight.Console;
import com.anime.fight.event.ConsoleCreate;
import com.anime.fight.event.Frame;
import com.anime.fight.event.LoadEvents;
import com.anime.fight.eventbus.EventBus;
import com.anime.fight.util.Plane;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.Field;
import javax.inject.Inject;
import lombok.Getter;

public class UserInterface implements Camera {

    @Getter
    private LoadEvents events;

    @Getter
    Point position = new Point(0,0);
    @Getter
    double verticalSpeed = 0;
    @Getter
    double horizontalSpeed = 0;
    @Getter
    int FOV_X = 40;
    @Getter
    int FOV_Y = 20;
    @Getter
    int width = 580;
    @Getter
    int height = 560;

    private final EventBus eventBus = new EventBus();
    private Plane plane;
    private final Dimension windowSize = new Dimension(width, height);
    private final Console console;
    private int ZAxis = 0;
    private int seed = 0;

    public UserInterface()
    {
        console = new Console(windowSize, FOV_X, FOV_Y);
        console.setVisible(true);
        plane = new Plane(seed, eventBus);
        plane.fillPlaneRandom(ZAxis, new Point(-50, -50), new Point(50, 50),
                new Object[] {Object.DIRT, Object.DIRT, Object.STONE});
        init();
    }

    private final String ClassPath = "com.anime.fight";

    private void init()
    {
        events = new LoadEvents(eventBus);
        try
        {
            events.Load(ClassPath);
            events.init();
        } catch (IOException e)
        {
            System.out.println("Unable to start" + e);
        }
        events.getBasicClassEvents().forEach((event) ->
        {
            for (Field field : event.getClass().getDeclaredFields())
            {
                Inject inj = field.getAnnotation(Inject.class);
                if (inj == null || field.getType() != Camera.class)
                {
                    continue;
                }
                try {
                    field.setAccessible(true);
                    field.set(event, this);
                    field.setAccessible(false);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
            eventBus.register(event);
        });
        eventBus.post(new ConsoleCreate(console));
    }
    int i = 0;
    boolean a = false;
    public void Frame() throws InterruptedException {
        a = !a;
        eventBus.post(new Frame(a));
        position.setLocation(i / 10, position.y);
        i++;
        plane.fillPlaneRandom(ZAxis, new Point(position.x - FOV_X, position.y - FOV_Y),
                               new Point(position.x + FOV_X, position.y + FOV_Y),
                               new Object[] {Object.DIRT, Object.DIRT, Object.STONE}, false);
        plane.guiFrame(ZAxis, new Point(position.x - FOV_X, position.y - FOV_Y),
                new Point(position.x + FOV_X, position.y + FOV_Y));
        console.flush(plane.get(ZAxis), position);
    }
}
