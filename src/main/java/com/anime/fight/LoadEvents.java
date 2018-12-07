package com.anime.fight;

import com.anime.fight.Annotation.UserInterface;
import com.anime.fight.event.BasicClassEvent;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.Graphs;
import com.google.common.graph.MutableGraph;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class LoadEvents {

    private final List<BasicClassEvent> basicClassEvents = new CopyOnWriteArrayList<>();

    public void Load(String packageName) throws IOException
    {
//        MutableGraph<Class<? extends BasicClassEvent>> graph = GraphBuilder
//                .directed()
//                .build();

        ClassPath classPath = ClassPath.from(getClass().getClassLoader());
        ImmutableSet<ClassInfo> classes = packageName == null ? classPath.getAllClasses() : classPath.getTopLevelClassesRecursive(packageName);
        for (ClassInfo classInfo : classes)
        {
            Class<?> clazz = classInfo.load();
            UserInterface userInterface = clazz.getAnnotation(UserInterface.class);
            if (userInterface == null)
            {
                continue;
            }

//            Class<BasicClassEvent> pluginClass = (Class<BasicClassEvent>) clazz;
//            graph.addNode(pluginClass);
            try
            {
                basicClassEvents.add(((Class<? extends BasicClassEvent>) clazz).newInstance());
            } catch (Exception e)
            {
                System.err.println("Got an error: " + e);
            }
        }
    }

    public Collection<BasicClassEvent> getBasicClassEvents()
    {
        return basicClassEvents;
    }
}
