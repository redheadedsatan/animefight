package com.anime.fight;

import com.anime.fight.Annotation.Subscribe;
import com.anime.fight.UserInterface.Camera;
import com.anime.fight.UserInterface.UserInterface;
import com.anime.fight.event.OnFrame;
import com.google.common.reflect.ClassPath;
import java.awt.Dimension;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, InvocationTargetException, InterruptedException {


        UserInterface userInterface = new UserInterface();
        boolean exit = false;
        final int width = 40;
        final int height = 20;
        Dimension windowSize = new Dimension(width, height);
        final Console console;
        console = new Console(windowSize);
        console.setVisible(true);

        while (!exit)
        {
            while (!console.isFull())
            {
                SwingUtilities.invokeAndWait(() ->
                {
                    for (int y = 0; y < height; y++)
                    {
                        for (int x = 0; x < width; x++)
                        {

                            try {
                                userInterface.Frame(console);
                            } catch (Exception e) {
                                System.err.println(e);
                            }

                        }
                    }

                    try {
                        console.flush();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        /*BasicEvents a = new BasicEvents() {

        };
        a.OnFrame();*/

        //List<Class<BasicEvents>> listeners = new ArrayList<Class<BasicEvents>>();


    }
}
