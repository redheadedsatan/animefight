package com.anime.fight.game.event;

import com.anime.fight.game.util.TreeMapExtended;
import lombok.Value;

@Value
public class PlaneChange {
    private final TreeMapExtended newPlane;
    private final TreeMapExtended oldPlane;
}
