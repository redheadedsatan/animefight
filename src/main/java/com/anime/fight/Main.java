package com.anime.fight;

import com.anime.fight.event.BasicEvents;
import org.reflections.Reflections;

import java.util.Set;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections("com.mycompany");
        Set<Class<? extends BasicEvents>> classes = reflections.getSubTypesOf(BasicEvents.class);

        for (Class<? extends BasicEvents> b : classes){
            System.out.println(b);
        }
    }
}
