package com.anime.fight;

import com.anime.fight.UserInterface.Camera;
import com.anime.fight.event.BasicEvents;
import org.reflections.Reflections;

import java.util.Set;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        BasicEvents a = new BasicEvents() {

        };
        a.OnFrame();
    }
}
