package com.anime.fight.game.event;

import lombok.Data;

@Data
public class KeyClicked
{
    private final java.awt.event.KeyEvent KeyEvent;
    private final double verticalSpeed;
    private final double horizontalSpeed;
}
