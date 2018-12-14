package com.anime.fight.game.userInterface;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import lombok.Getter;

public class Console extends JFrame
{
    @Getter
    private final Canvas canvas;
    private int FOV_X;
    private int FOV_Y;
    private Map<Point, CustomObject> oldDisplay;
//    private final Style mainStyle;
    private final Graphics graphics;
    private final Font font;

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

        canvas = new Canvas();
        canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        canvas.setBackground(Color.BLACK);
        canvas.setForeground(Color.LIGHT_GRAY);
        canvas.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        add(canvas);

        graphics = canvas.getGraphics();
        font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        graphics.setFont(font);
//        textArea = new JTextPaneExtend();
//        textArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//        textArea.setBackground(Color.BLACK);
//        textArea.setForeground(Color.LIGHT_GRAY);
//        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
////        textArea.setContentType("text/html");
//        textArea.setHighlighter(null);
//        textArea.setEditable(false);
////        textArea.setEnabled(false);
//        add(textArea);
//        textArea.setText("");
//        mainStyle = textArea.getLogicalStyle();
//        StyleConstants.setFontFamily(mainStyle, "Lucida Console");
//        StyleConstants.setAlignment(mainStyle, StyleConstants.ALIGN_JUSTIFIED);
        pack();
    }

    @Override
    public void print(Graphics g) {
        super.print(g);
    }

    public void flush(Map<Point, CustomObject> Display, Point.Double position) throws InterruptedException
    {
//        long time_1 = System.currentTimeMillis();
        if (Display.equals(oldDisplay))
        {
//            textAreaChanged();
            return;
        }

//        graphics.setColor(Color.BLACK);
//        graphics.fillOval(0, 0 , 999, 999);

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 999, 999);
        repaint();

        int xPos = 0;
        int yPos = 10;
        int maxSize = 0;
        for (int y = (int)position.y - FOV_Y; y < (int)position.y + FOV_Y; y++)
        {
            for (int x = (int)position.x - FOV_X; x < (int)position.x + FOV_X; x++)
            {
                Point point = new Point(x, y);
                CustomObject obj = Display.get(point);
                if (obj == null) { continue; }
                graphics.setColor(obj.getColor());
                graphics.drawString(String.valueOf(obj.getChar()), xPos, yPos);
//                colors.add(obj.getColor());
//                if (!AllColors.contains(obj.getColor()))
//                {
//                    AllColors.add(obj.getColor());
//                }
                xPos += 12;
            }
            if (maxSize < xPos)
            {
                maxSize = xPos +8;
            }
            xPos = 0;
            yPos += 12;
        }

        repaint();
        setSize(maxSize, yPos + 20);

        /**
         * 15ms for each render
         */
//        StringBuilder SB = new StringBuilder();
//        textArea.setEditable(true);
//        Queue<Color> colors = new LinkedList();
//        List<Color> AllColors = new ArrayList<>();
//        for (int y = position.y - FOV_Y; y < position.y + FOV_Y; y++)
//        {
//            if (y != position.y - FOV_Y)
//            {
//                SB.append('\n');
//                colors.add(Color.WHITE);
//                if (!AllColors.contains(Color.WHITE))
//                {
//                    AllColors.add(Color.WHITE);
//                }
//            }
//            for (int x = position.x - FOV_X; x < position.x + FOV_X; x++)
//            {
//                Point point = new Point(x, y);
//                CustomObject obj = Display.get(point);
//                if (obj == null) { continue; }
//                SB.append(obj.getChar());
//                colors.add(obj.getColor());
//                if (!AllColors.contains(obj.getColor()))
//                {
//                    AllColors.add(obj.getColor());
//                }
//            }
//        }
////        textArea.setText(SB.toString());
//
//        final List<SimpleAttributeSet> aset = new ArrayList<>();
//        StyleContext sc = new StyleContext();
//        final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
//        for (Color color : AllColors)
//        {
//            SimpleAttributeSet Instanceaset = new SimpleAttributeSet();
//            StyleConstants.setForeground(Instanceaset, color);
//            aset.add(Instanceaset);
//        }
//
//        try {
//            doc.setLogicalStyle(0, mainStyle);
//            doc.insertString(0, SB.toString(), null);
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//        }
//
//        int i = 0;
//        for (Color color : colors)
//        {
//            for (SimpleAttributeSet AS : aset)
//            {
//                if (AS.getAttribute(StyleConstants.Foreground) != color)
//                {
//                    continue;
//                }
//                doc.setCharacterAttributes(i, 1, AS, false);
//                break;
//            }
//            i++;
//        }
//
//        textArea.setDocument(doc);

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
//        textAreaChanged();
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

//    private void textAreaChanged()
//    {
////        textArea.select(0, 0);
//        textArea.setEditable(false);
////        textArea.setEnabled(false);
////        textArea.requestFocusInWindow();
//        pack();
//    }
}