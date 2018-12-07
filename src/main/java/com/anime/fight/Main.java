package com.anime.fight;

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

        List<BasicEvents> listeners = new ArrayList<BasicEvents>();

        Reflections reflections = new Reflections("com.anime.fight");
        Set<Class<? extends BasicEvents>> classes = reflections.getSubTypesOf(BasicEvents.class);
        for (Class<? extends BasicEvents> aClass : classes)
        {
            listeners.add(aClass);
            for (Method m : aClass.getMethods())
            {
                for (Method a : BasicEvents.class.getMethods())
                {
                    if (m.getName().equals(a.getName())) {
                        try {
                            m.invoke(m, null);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        System.out.println(m.getName());
                    }
                }
            }
        }
    }
}
