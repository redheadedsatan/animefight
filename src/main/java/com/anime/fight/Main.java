package com.anime.fight;

import com.anime.fight.api.eventbus.EventBus;
import com.anime.fight.game.userInterface.UserInterface;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;
import javax.swing.SwingUtilities;
import lombok.Getter;

public class Main
{

    @Getter
    private static Injector injector;
    @Inject
    private UserInterface userInterface;
    @Inject
    private EventBus eventBus;

    private static final String ClassPath = "com.anime.fight.game";
    private static int FrameRate = 0;

    public static void main(String[] args) {

        injector = Guice.createInjector(new LoadModule());

        injector.getInstance(Main.class).start();
    }

    public void start()
    {
        injector.injectMembers(userInterface);
        userInterface.startUp();
        boolean exit = false;

        Timer frame = new Timer();
        frame.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("FrameRate = " + FrameRate);
                FrameRate = 0;
            }
        }, 0, 1000);

        new Thread(() -> {
            while (true)
            {
                if (exit)
                {
                    return;
                }

                try {
                    SwingUtilities.invokeAndWait(() ->
                    {
                        try {
                            userInterface.Frame();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                FrameRate++;
            }
        }).run();
    }
}
