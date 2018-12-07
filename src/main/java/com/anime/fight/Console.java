package com.anime.fight;

import com.anime.fight.UserInterface.Object;
import com.anime.fight.Util.ColorChar;
import com.anime.fight.Util.JTextPaneExtend;
import com.sun.glass.ui.Size;
import com.sun.istack.internal.Nullable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.Document;

public class Console extends JFrame
{
    private final JTextPaneExtend textArea;
    private final Dimension writeableSize;
    private int FOV_X;
    private int FOV_Y;

    public Console(Dimension writeableSize, int FOV_X, int FOV_Y)
    {
        super();
        this.writeableSize = writeableSize;
        this.FOV_Y = FOV_Y;
        this.FOV_X = FOV_X;

        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        textArea = new JTextPaneExtend();
        //textArea.setContentType("text/html");
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        add(textArea);
    }

    public void flush(Map<Point, Object> Display, Point position) throws InterruptedException
    {
        remove(textArea);
        for (int y = position.y - FOV_Y; y < position.y + FOV_Y; y++)
        {
            for (int x = position.x - FOV_X; x < position.x + FOV_X; x++)
            {
                Point p = new Point(x, y);
                if (Display.get(p) != null)
                {
                    continue;
                }
                Display.put(p, Object.DIRT2);
            }
        }
        textArea.setText("");
        for (int y = position.y - FOV_Y; y < position.y + FOV_Y; y++)
        {
            for (int x = position.x - FOV_X; x < position.x + FOV_X; x++)
            {
                Object obj = Display.get(new Point(x, y));
                textArea.append(obj.getChar(), obj.getColor());
            }
            textArea.append("\n", Color.white);
        }
        add(textArea);
    }
}