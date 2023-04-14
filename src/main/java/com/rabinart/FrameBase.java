package com.rabinart;

import javax.swing.*;
import java.awt.*;

public final class FrameBase {

    private FrameBase() {}

    public static JFrame buildDefaultFrame(String name, int w, int h){
        var jFrame = new JFrame(name);
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBounds(screenSize.width/2 - 200, screenSize.height/2 - 200, w,h);
        jFrame.setMinimumSize(new Dimension(w, h));
        jFrame.setVisible(true);
        return jFrame;
    }
}
