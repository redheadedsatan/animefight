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

        while (!exit)
        {
            SwingUtilities.invokeAndWait(() ->
            {
                try {
                    userInterface.Frame();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        /*BasicEvents a = new BasicEvents() {

        };
        a.OnFrame();*/

        //List<Class<BasicEvents>> listeners = new ArrayList<Class<BasicEvents>>();


    }
}
