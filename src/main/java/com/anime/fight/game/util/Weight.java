package com.anime.fight.game.util;

import com.anime.fight.game.userInterface.CustomObject;
import com.anime.fight.game.userInterface.Object;
import lombok.Value;

@Value
public class Weight
{
    private final int weight;
    private final CustomObject obj;

    public Weight(int weight, CustomObject obj)
    {
        this.weight = weight;
        this.obj = obj;
    }

    public Weight(int weight, Object obj)
    {
        this.weight = weight;
        this.obj = new CustomObject(obj);
    }
}
