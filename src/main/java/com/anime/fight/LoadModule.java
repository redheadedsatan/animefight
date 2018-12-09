package com.anime.fight;

import com.anime.fight.api.eventbus.EventBus;
import com.anime.fight.game.userInterface.UserInterface;
import com.google.inject.AbstractModule;

public class LoadModule extends AbstractModule
{
    @Override
    protected void configure() {
        bind(EventBus.class);
        bind(UserInterface.class);
    }
}
