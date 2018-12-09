package com.anime.fight.event;

import com.anime.fight.util.Plane;
import lombok.Value;

@Value
public class PlaneChange {
    private final Plane newPlane;
    private final Plane oldPlane;
}
