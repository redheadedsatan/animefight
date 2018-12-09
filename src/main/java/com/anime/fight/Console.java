package com.anime.fight;

import com.anime.fight.userInterface.Object;
import com.anime.fight.util.JTextPaneExtend;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.Map;
import javax.swing.JFrame;

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

        setSize(this.writeableSize);
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
        textArea.setText("");
        for (int y = position.y - FOV_Y; y < position.y + FOV_Y; y++)
        {
            for (int x = position.x - FOV_X; x < position.x + FOV_X; x++)
            {
                Object obj = Display.get(new Point(x, y));
                if (obj == null) { continue; }
                textArea.append(obj.getChar(), obj.getColor());
            }
            textArea.append("\n", Color.white);
        }
        add(textArea);
    }
}