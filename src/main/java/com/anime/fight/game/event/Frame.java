package com.anime.fight.game.event;

import com.anime.fight.game.userInterface.Object;
import java.awt.Point;
import java.util.Map;
import lombok.Data;

@Data
public class Frame {
    private final Map<Point, Object> event;
}
