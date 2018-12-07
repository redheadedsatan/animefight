package com.anime.fight;

import com.anime.fight.UserInterface.Object;
import com.anime.fight.Util.ColorChar;
import com.anime.fight.Util.JTextPaneExtend;
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
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.Document;

public class Console extends JFrame
{
    private final JTextPaneExtend textArea;
    private final Dimension writeableSize;
    private boolean isFull = false;
    //private int textLen = 0;
    private Object[][] Lines;

    public Console(Dimension writeableSize)
    {
        super();
        this.writeableSize = writeableSize;
        Lines = new Object[this.writeableSize.width][this.writeableSize.height];
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
//        frame.pack();
//        frame.setVisible(true);
        textArea = new JTextPaneExtend();
        //textArea.setContentType("text/html");
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
//        System.setOut(new PrintStream(new OutputStream()
//        {
//            @Override
//            public void write(int b) throws IOException
//            {
//                textArea.append(String.valueOf((char) b));
//            }
//        }));
        add(textArea);
    }

    public void flush() throws InterruptedException
    {
        remove(textArea);
        //Document HTMLDocument;
        textArea.setText("");
        isFull = false;
        for (Object[] colorChar : Lines)
        {
            for (Object Char : colorChar)
            {
                if (Char == null)
                {
                    textArea.append(" ", Color.white);
                    continue;
                }
                textArea.append(Char.getChar(), Char.getColor());
            }
            textArea.append("\n", Color.white);
        }
        //HTMLDocument.append("</html>");
        //String a = HTMLDocument.toString();
        //System.out.println(HTMLDocument.length());
        Lines = new Object[this.writeableSize.width][this.writeableSize.height];
        add(textArea);
    }


    public void SetLocation(Point dim, Object obj)
    {
        Lines[dim.x][dim.y] = obj;
    }

    public boolean isFull()
    {
        return isFull;
    }
}