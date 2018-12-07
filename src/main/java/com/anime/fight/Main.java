package com.anime.fight;

import com.anime.fight.UserInterface.UserInterface;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {


        UserInterface userInterface = new UserInterface();
        boolean exit = false;

        Timer frame = new Timer();
        frame.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (exit)
                {
                    frame.cancel();
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
            }
        }, 0, 50);
    }
}
