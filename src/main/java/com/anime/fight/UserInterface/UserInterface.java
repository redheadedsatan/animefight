package com.anime.fight.UserInterface;

import com.anime.fight.Console;
import com.anime.fight.LoadEvents;
import com.anime.fight.event.BasicClassEvent;
import com.anime.fight.event.Data;
import com.anime.fight.eventbus.EventBus;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

public class UserInterface extends Camera{

    @Getter
    private LoadEvents events = new LoadEvents();
    @Getter
    private List<BasicClassEvent> basicClassEvents = new ArrayList<>();

    private final EventBus eventBus = new EventBus();
    private Map<Point, Object> Display = new HashMap<>();
    private final Dimension windowSize = new Dimension(width, height);
    private final Console console;

    public UserInterface()
    {
        Display.put(new Point(1,1), Object.DIRT);
        Display.put(new Point(3,2), Object.DIRT);
        Display.put(new Point(6,3), Object.DIRT);
        console = new Console(windowSize, FOV_X, FOV_Y);
        console.setVisible(true);
        init();
    }

    private final String ClassPath = "com.anime.fight";

    private void init()
    {
        try {
            events.Load(ClassPath);
            basicClassEvents.addAll(events.getBasicClassEvents());
            basicClassEvents.forEach((css) ->
            {
                eventBus.register(css);
            });
        } catch (IOException e) {
            System.out.println("Unable to start" + e);
        }
    }
    int i = 0;
    public void Frame() throws InterruptedException {
        //Call every event that have OnFrame interface in it
        basicClassEvents.forEach((event) -> {
            eventBus.post(new Data(new Point(0,0)));
//            try {
//                ((OnFrame)event).OnFrame();
//            } catch (Exception e) {
//            }
            position.setLocation(i / 20, position.y);
            i++;
            System.out.println(i);
        });

        console.flush(Display, position);
    }
}
