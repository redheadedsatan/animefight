package com.anime.fight.game.event;

import com.anime.fight.api.eventbus.EventBus;
import lombok.Data;

@Data
public class EventBusFinishedInit
{
    private final EventBus eventBus;
}
