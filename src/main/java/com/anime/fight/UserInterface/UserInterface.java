package com.anime.fight.UserInterface;

import com.anime.fight.Console;
import com.anime.fight.LoadEvents;
import com.anime.fight.Util.ColorChar;
import com.anime.fight.event.BasicClassEvent;
import com.anime.fight.event.OnFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;

public class UserInterface {

    @Getter
    private LoadEvents events = new LoadEvents();
    @Getter
    private List<BasicClassEvent> basicClassEvents = new CopyOnWriteArrayList<>();

    private Map<Point, Object> Display = new HashMap<>();
    private Point position;
    private int FOV_X = 20;
    private int FOV_Y = 10;
    final int width = 40;
    final int height = 20;
    private final Dimension windowSize = new Dimension(width, height);
    private final Console console;

    public UserInterface()
    {
        Display.put(new Point(1,1), Object.DIRT);
        Display.put(new Point(3,2), Object.DIRT);
        Display.put(new Point(6,3), Object.DIRT);
        console = new Console(windowSize, FOV_X, FOV_Y);
        console.setVisible(true);

        position = new Point(0, 0);
        init();
    }

    private final String ClassPath = "com.anime.fight.UserInterface";

    private void init()
    {
        try {
            events.Load(ClassPath);
            basicClassEvents.addAll(events.getBasicClassEvents());
        } catch (IOException e) {
            System.out.println("Unable to start" + e);
        }
    }
    int i = 0;
    public void Frame() throws InterruptedException {
        //Call every event that have OnFrame interface in it
        basicClassEvents.forEach((event) -> {
            try {
                ((OnFrame)event).OnFrame();
            } catch (Exception e) {
            }
            position.setLocation(i / 200, position.y);
            i++;
            System.out.println(i);
        });

        console.flush(Display, position);
    }
}
