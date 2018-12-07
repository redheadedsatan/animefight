package com.anime.fight.UserInterface;

import com.anime.fight.Annotation.Subscribe;
import com.anime.fight.event.BasicEvents;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Camera implements BasicEvents {
    @Getter
    private Point position = new Point(0,0);

    @Setter
    private double verticalSpeed = 0;
    @Setter
    private double horizontalSpeed = 0;

    @Subscribe
    public void OnFrame() {
        System.out.println("hi");
    }
}
