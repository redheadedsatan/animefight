package com.anime.fight.game.event;

import com.anime.fight.game.interfaces.Camera;
import lombok.Data;

@Data
public class CameraCreated
{
    private final Camera camera;
}
