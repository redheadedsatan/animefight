package com.anime.fight.UserInterface;

import com.anime.fight.Console;
import com.anime.fight.LoadEvents;
import com.anime.fight.event.BasicClassEvent;
import com.anime.fight.event.OnFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;

public class UserInterface {

    @Getter
    private LoadEvents events = new LoadEvents();
    @Getter
    private List<BasicClassEvent> basicClassEvents = new CopyOnWriteArrayList<>();

    public UserInterface()
    {
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
    int x = 0;
    public void Frame(Console console) throws InterruptedException {
        //Call every event that have OnFrame interface in it
        basicClassEvents.forEach((event) -> {
            try {
                ((OnFrame) event).OnFrame();
                console.SetLocation(new Point((x / 1000) % 40,(x / 1000) / 20), Object.DIRT);
                x++;
            } catch (Exception e) {
            }
        });

        // Clear Console
        //System.out.print("\033[H\033[2J");
    }
}
