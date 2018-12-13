package com.anime.fight.game.userInterface;

import com.anime.fight.api.eventbus.EventBus;
import com.anime.fight.api.eventbus.Subscribe;
import com.anime.fight.game.actions.MouseTrigger;
import com.anime.fight.game.event.CameraCreated;
import com.anime.fight.game.event.ConsoleCreate;
import com.anime.fight.game.event.EventBusFinishedInit;
import com.anime.fight.game.event.Frame;
import com.anime.fight.game.event.KeyClicked;
import com.anime.fight.game.event.LoadEvents;
import com.anime.fight.game.interfaces.Camera;
import com.anime.fight.game.userInterface.gui.button;
import com.anime.fight.game.util.Plane;
import com.anime.fight.game.util.Weight;
import com.google.inject.Injector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.inject.Inject;
import lombok.Getter;

public class UserInterface implements Camera {

    @Getter
    private LoadEvents events;

    @Getter
    Point position;
    @Getter
    double verticalSpeed = 0;
    @Getter
    double horizontalSpeed = 0;
    @Getter
    int FOV_X = 40;
    @Getter
    int FOV_Y = 20;
    @Getter
    private Console console;

    @Getter
    @Inject
    private EventBus eventBus;
    @Inject
    private Injector injector;
    @Getter
    private Plane plane;
    @Getter
    private TreeMap<Integer, button> btns;

    private int ZAxis = 0;
    private int seed = 0;

    private final String ClassPath = "com.anime.fight";

    public void startUp()
    {
        btns = new TreeMap<>();
        console = new Console(FOV_X, FOV_Y);
        console.setVisible(true);
        plane = new Plane(seed, eventBus);
//        plane.fillPlaneRandom(ZAxis, new Point(-50, -50), new Point(50, 50),
//                new Object[] {Object.DIRT, Object.DIRT, Object.STONE});
        events = new LoadEvents(eventBus);
        try
        {
            events.Load(ClassPath);
        } catch (IOException e)
        {
            System.out.println("Unable to start" + e);
        }

//        injector.injectMembers(this);
        eventBus = events.getEventBus();
        eventBus.register(this);
//        events.getBasicActiveClasses().forEach((event) ->
//        {
//
//                eventBus.register(event);
//        });
        events.init();

        eventBus.post(new ConsoleCreate(console));
        eventBus.post(new EventBusFinishedInit(eventBus));
        eventBus.post(new CameraCreated(this));
        injector.injectMembers(MouseTrigger.class);
        position = new Point(0,0);

        btns.put(0, new button(0, "cli ck", new Point(0,0), new Dimension("cli ck".length(), 1), Color.RED, console));
        btns.put(1, new button(1, "try me!", new Point(0,0), new Dimension("try me!".length(), 1), Color.BLUE, console));
    }

    boolean firstFrame = false;
    public void Frame() throws InterruptedException {
        position.setLocation(position.getX() + horizontalSpeed,
                position.getY() + verticalSpeed);
        createFrame();


        Map<Point, CustomObject> map = plane.getFlatPlane();
        btns.forEach((id, btn) ->
        {
            btn.onFrame(new Frame(MouseInfo.getPointerInfo().getLocation(), map),
                    this);
        });
        eventBus.post(new Frame(MouseInfo.getPointerInfo().getLocation(), map));
        console.flush(map, position);
    }

    @Subscribe
    public void onKeyClicked(KeyClicked keyClicked)
    {
        verticalSpeed = keyClicked.getVerticalSpeed();
        horizontalSpeed = keyClicked.getHorizontalSpeed();
    }
    private void createFrame()
    {
        plane.fillPlaneRandom(ZAxis, new Point(position.x - FOV_X, position.y - FOV_Y),
                new Point(position.x + FOV_X, position.y + FOV_Y),
                new Weight[] {
                        new Weight(4, Object.DIRT),
                        new Weight(1, Object.STONE)}, false);
        plane.guiMakeBox(ZAxis, topLeft(), bottomRight());

        plane.getGui().fillPlane(ZAxis, new Point(bottomRight().x - 10, bottomRight().y - 10),
                bottomRight(), Object.VOID);
        plane.guiMakeBox(ZAxis, new Point(bottomRight().x - 10, bottomRight().y - 10),
                bottomRight());
        btns.get(0).setLocation(bottomRight().x - 9, bottomRight().y - 9);
        btns.get(1).setLocation(bottomRight().x - 9, bottomRight().y - 3);
        btns.forEach((index, btn) ->
        {
            btn.setOffSet(position);
            plane.getGui().setPointPlane(ZAxis,
                    btn.getLocation(),
                    btn.getDimension(), btn.getObjects());
        });
    }

    private Point topLeft()
    {
        return new Point(position.x - FOV_X, position.y - FOV_Y);
    }
    private Point bottomRight()
    {
        return new Point(position.x + FOV_X, position.y + FOV_Y);
    }
}
