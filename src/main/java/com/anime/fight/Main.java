package com.anime.fight;

import com.anime.fight.Annotation.Subscribe;
import com.anime.fight.UserInterface.Camera;
import com.anime.fight.event.BasicEvents;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        /*BasicEvents a = new BasicEvents() {

        };
        a.OnFrame();*/

        //List<Class<BasicEvents>> listeners = new ArrayList<Class<BasicEvents>>();

        Reflections reflections = new Reflections("com.anime.fight");
        Set<Class<? extends BasicEvents>> classes = reflections.getSubTypesOf(BasicEvents.class);
        for (Class<? extends BasicEvents> aClass : classes)
        {
            for (Method m : aClass.getMethods())
            {
                if (m.isAnnotationPresent(Subscribe.class))
                {
                    try {
                        m.invoke(aClass.newInstance());
                    } catch (Exception e) {}
                }
            }
        }
    }
}
