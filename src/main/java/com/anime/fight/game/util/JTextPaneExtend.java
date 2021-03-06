package com.anime.fight.game.util;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class JTextPaneExtend extends JTextPane {
    public void append(String str, Color color)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, color);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment,
                StyleConstants.ALIGN_JUSTIFIED);

        int len = getDocument().getLength();
        setCaretPosition(len);
        setCharacterAttributes(aset, false);
        replaceSelection(str);
    }

    public void append(char str, Color color)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, color);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment,
                StyleConstants.ALIGN_JUSTIFIED);

        int len = getDocument().getLength();
//        try {
//            getDocument().insertString(len, String.valueOf(str), aset);
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//        }
        setCaretPosition(len);
        setCharacterAttributes(aset, false);
        replaceSelection(String.valueOf(str));
    }


}
