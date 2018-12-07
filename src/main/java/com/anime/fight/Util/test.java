package com.anime.fight.Util;

import com.anime.fight.Annotation.UserInterface;
import com.anime.fight.event.BasicClassEvent;
import com.anime.fight.event.Data;
import com.anime.fight.eventbus.Subscribe;

@UserInterface
public class test extends BasicClassEvent {

    @Subscribe
    public void onData(Data data)
    {
        System.out.println("hi");
    }
}
