package com.anime.fight.event;

import com.anime.fight.annotation.EventActive;
import com.anime.fight.eventbus.EventBus;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.SwingUtilities;
import lombok.Getter;

public class LoadEvents {

    private final List<BasicClassEvent> basicClassEvents = new CopyOnWriteArrayList<>();
    @Getter
    private final EventBus eventBus;

    public LoadEvents(EventBus eventBus)
    {
        this.eventBus = eventBus;
    }

    public void Load(String packageName) throws IOException
    {
//        MutableGraph<classes<? extends BasicClassEvent>> graph = GraphBuilder
//                .directed()
//                .build();

        ClassPath classPath = ClassPath.from(getClass().getClassLoader());
        ImmutableSet<ClassInfo> classes = packageName == null ? classPath.getAllClasses() : classPath.getTopLevelClassesRecursive(packageName);
        for (ClassInfo classInfo : classes)
        {
            Class<?> clazz = classInfo.load();
            EventActive userInterface = clazz.getAnnotation(EventActive.class);
            if (userInterface == null)
            {
                continue;
            }

//            classes<BasicClassEvent> pluginClass = (classes<BasicClassEvent>) clazz;
//            graph.addNode(pluginClass);
            try
            {
                BasicClassEvent classEvent = ((Class<? extends BasicClassEvent>) clazz).newInstance();
                basicClassEvents.add(classEvent);
            } catch (Exception e)
            {
                System.err.println("Got an error: " + e);
            }
        }
    }

    public synchronized void init()
    {
        List<BasicClassEvent> classEvents = new ArrayList<>(basicClassEvents);
        for (BasicClassEvent classEvent : classEvents)
        {
            try
            {
                SwingUtilities.invokeAndWait(() ->
                {
                    try
                    {
                        classEvent.startUp();
                    }
                    catch (Exception ex)
                    {
                        throw new RuntimeException(ex);
                    }
                });
                //eventBus.register(classEvent);
            }
            catch (Exception e) {}
        }
    }

    public Collection<BasicClassEvent> getBasicClassEvents()
    {
        return basicClassEvents;
    }
}
