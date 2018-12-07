package com.anime.fight.UserInterface;

import com.anime.fight.Annotation.UserInterface;
import com.anime.fight.Annotation.Subscribe;
import com.anime.fight.event.BasicClassEvent;
import com.anime.fight.event.OnFrame;
import java.awt.Point;
import lombok.Getter;
import lombok.Setter;

@UserInterface
public class Camera extends BasicClassEvent implements OnFrame {
    @Getter
    private Point position = new Point(0,0);

    @Setter
    private double verticalSpeed = 0;
    @Setter
    private double horizontalSpeed = 0;

    @Subscribe
    public void OnFrame() {

    }
}
