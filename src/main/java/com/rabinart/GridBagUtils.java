package com.rabinart;

import java.awt.*;

public class GridBagUtils {

    private GridBagUtils(){}

    public static GridBagConstraints setUpConstrains(int x, int y, int h, int w) {
        var constraints = new GridBagConstraints();
        constraints.fill= GridBagConstraints.HORIZONTAL;
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridheight = h;
        constraints.gridwidth = w;
        return constraints;
    }
}
