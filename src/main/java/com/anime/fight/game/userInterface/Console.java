package com.anime.fight.game.userInterface;

import com.anime.fight.game.util.JTextPaneExtend;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import lombok.Getter;

public class Console extends JFrame
{
    @Getter
    private final JTextPaneExtend textArea;
    private int FOV_X;
    private int FOV_Y;
    private Map<Point, CustomObject> oldDisplay;
    private final Style mainStyle;

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
        textArea.setHighlighter(null);
        textArea.setEditable(false);
//        textArea.setEnabled(false);
        add(textArea);
        textArea.setText("");
        mainStyle = textArea.getLogicalStyle();
        StyleConstants.setFontFamily(mainStyle, "Lucida Console");
        StyleConstants.setAlignment(mainStyle, StyleConstants.ALIGN_JUSTIFIED);
        pack();
    }
    public void flush(Map<Point, CustomObject> Display, Point position) throws InterruptedException
    {
//        long time_1 = System.currentTimeMillis();
        if (Display.equals(oldDisplay))
        {
            textAreaChanged();
            return;
        }

        /**
         * 15ms for each render
         */
        StringBuilder SB = new StringBuilder();
        textArea.setEditable(true);
        Queue<Color> colors = new LinkedList();
        List<Color> AllColors = new ArrayList<>();
        for (int y = position.y - FOV_Y; y < position.y + FOV_Y; y++)
        {
            if (y != position.y - FOV_Y)
            {
                SB.append('\n');
                colors.add(Color.WHITE);
                if (!AllColors.contains(Color.WHITE))
                {
                    AllColors.add(Color.WHITE);
                }
            }
            for (int x = position.x - FOV_X; x < position.x + FOV_X; x++)
            {
                Point point = new Point(x, y);
                CustomObject obj = Display.get(point);
                if (obj == null) { continue; }
                SB.append(obj.getChar());
                colors.add(obj.getColor());
                if (!AllColors.contains(obj.getColor()))
                {
                    AllColors.add(obj.getColor());
                }
            }
        }
//        textArea.setText(SB.toString());

        final List<SimpleAttributeSet> aset = new ArrayList<>();
        StyleContext sc = new StyleContext();
        final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        for (Color color : AllColors)
        {
            SimpleAttributeSet Instanceaset = new SimpleAttributeSet();
            StyleConstants.setForeground(Instanceaset, color);
            aset.add(Instanceaset);
        }

        try {
            doc.setLogicalStyle(0, mainStyle);
            doc.insertString(0, SB.toString(), null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        int i = 0;
        for (Color color : colors)
        {
            for (SimpleAttributeSet AS : aset)
            {
                if (AS.getAttribute(StyleConstants.Foreground) != color)
                {
                    continue;
                }
                doc.setCharacterAttributes(i, 1, AS, false);
                break;
            }
            i++;
        }

        textArea.setDocument(doc);

/**
 * 25ms for each render
 */
////        textArea.setEnabled(true);
//        textArea.setEditable(true);
//        textArea.setText("");
////        StringBuilder SB = new StringBuilder("<html>");
//        for (int y = position.y - FOV_Y; y < position.y + FOV_Y; y++)
//        {
//            if (y != position.y - FOV_Y)
//            {
////                SB.append("<br>");
//                textArea.append('\n', Color.white);
//            }
//            for (int x = position.x - FOV_X; x < position.x + FOV_X; x++)
//            {
//                Point point = new Point(x, y);
//                Object obj = Display.get(point);
//                if (obj == null) { continue; }
////                SB.append(CharToHTML(obj.getChar(), obj.getColor()));
//                textArea.append(obj.getChar(), obj.getColor());
//            }
//        }
////        textArea.setText(SB.toString());
////        System.out.println(SB.toString());
//        oldDisplay = Display;
//        textAreaChanged();

//        long time_2 = System.currentTimeMillis();
//
//        System.out.println(time_2 - time_1);

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