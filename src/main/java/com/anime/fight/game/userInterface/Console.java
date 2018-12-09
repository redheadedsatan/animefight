package com.anime.fight.game.userInterface;

import com.anime.fight.game.util.JTextPaneExtend;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.util.Map;
import javax.swing.JFrame;
import lombok.Getter;

public class Console extends JFrame
{
    @Getter
    private final JTextPaneExtend textArea;
    private int FOV_X;
    private int FOV_Y;

    public Console(int FOV_X, int FOV_Y)
    {
        super();
        this.FOV_Y = FOV_Y;
        this.FOV_X = FOV_X;
        setResizable(false);
        //setUndecorated(true);
        //setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        textArea = new JTextPaneExtend();
        textArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        //textArea.setContentType("text/html");
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        add(textArea, BorderLayout.CENTER);
        pack();
    }
    public void flush(Map<Point, Object> Display, Point position) throws InterruptedException
    {
        textArea.setText("");
        for (int y = position.y - FOV_Y; y < position.y + FOV_Y; y++)
        {
            if (y != position.y - FOV_Y)
            {
                textArea.append("\n", Color.white);
            }
            for (int x = position.x - FOV_X; x < position.x + FOV_X; x++)
            {
                Point point = new Point(x, y);
                Object obj = Display.get(point);
                if (obj == null) { continue; }
                textArea.append(obj.getChar(), obj.getColor());
            }
        }
        textAreaChanged();
    }

    private void textAreaChanged()
    {
        pack();
    }


}