package com.anime.fight.game.event;

import com.anime.fight.api.annotation.EventActive;
import com.anime.fight.api.eventbus.EventBus;
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

    private final List<BasicActiveClass> basicActiveClasses = new CopyOnWriteArrayList<>();
    @Getter
    private final EventBus eventBus;

    public LoadEvents(EventBus eventBus)
    {
        this.eventBus = eventBus;
    }

    public void Load(String packageName) throws IOException
    {
//        MutableGraph<classes<? extends BasicActiveClass>> graph = GraphBuilder
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

//            classes<BasicActiveClass> pluginClass = (classes<BasicActiveClass>) clazz;
//            graph.addNode(pluginClass);
            try
            {
                BasicActiveClass classEvent = ((Class<? extends BasicActiveClass>) clazz).newInstance();
                basicActiveClasses.add(classEvent);
            } catch (Exception e)
            {
                System.err.println("Got an error: " + e);
            }
        }
    }

    public synchronized void init()
    {
        List<BasicActiveClass> classEvents = new ArrayList<>(basicActiveClasses);
        for (BasicActiveClass classEvent : classEvents)
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
                eventBus.register(classEvent);
            }
            catch (Exception e) {}
        }
    }

    public Collection<BasicActiveClass> getBasicActiveClasses()
    {
        return basicActiveClasses;
    }
}
