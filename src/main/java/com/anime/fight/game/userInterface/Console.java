package com.anime.fight.game.userInterface;

import com.anime.fight.game.util.JTextPaneExtend;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import lombok.Getter;

public class Console extends JFrame
{
    @Getter
    private final JTextPaneExtend textArea;
    private int FOV_X;
    private int FOV_Y;
    private Map<Point, Object> oldDisplay;

    public Console(int FOV_X, int FOV_Y)
    {
        super();
        this.oldDisplay = new HashMap<>();
        this.FOV_Y = FOV_Y;
        this.FOV_X = FOV_X;
        setResizable(false);
        //setUndecorated(true);
        //setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        textArea = new JTextPaneExtend();
        textArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
//        textArea.setContentType("text/html");
        textArea.setEditable(false);
//        textArea.setEnabled(false);
        add(textArea, BorderLayout.CENTER);
        textArea.setText("");
        pack();
    }
    public void flush(Map<Point, Object> Display, Point position) throws InterruptedException
    {
        if (Display.equals(oldDisplay))
        {
            textAreaChanged();
            return;
        }

//        textArea.setEnabled(true);
        textArea.setEditable(true);
        textArea.setText("");
//        StringBuilder SB = new StringBuilder("<html>");
        for (int y = position.y - FOV_Y; y < position.y + FOV_Y; y++)
        {
            if (y != position.y - FOV_Y)
            {
//                SB.append("<br>");
                textArea.append('\n', Color.white);
            }
            for (int x = position.x - FOV_X; x < position.x + FOV_X; x++)
            {
                Point point = new Point(x, y);
                Object obj = Display.get(point);
                if (obj == null) { continue; }
//                SB.append(CharToHTML(obj.getChar(), obj.getColor()));
                textArea.append(obj.getChar(), obj.getColor());
            }
        }
//        textArea.setText(SB.toString());
//        System.out.println(SB.toString());
        oldDisplay = Display;
        textAreaChanged();
    }

//    private String CharToHTML(String str, Color color)
//    {
//        StringBuilder SB = new StringBuilder();
//        SB.append("<span style=\"color: rgb(");
//        SB.append(color.getRed() + ", ");
//        SB.append(color.getGreen() + ", ");
//        SB.append(color.getBlue());
//        SB.append("); font-family:'MS Gothic'\">" + str + "</span>");
//        return SB.toString();
//    }

    private void textAreaChanged()
    {
//        textArea.select(0, 0);
        textArea.setEditable(false);
//        textArea.setEnabled(false);
//        textArea.requestFocusInWindow();
        pack();
    }
}