package com.rabinart;

public class Main {

    public static void main(String[] args) {
        var gui = GUI.getInstance();

        gui.addNumberField();
        gui.addNameField();
        gui.configureText();
        gui.addButtonSet(ActionButtonSet.getInstance());

        gui.getPanel().revalidate();
    }
}