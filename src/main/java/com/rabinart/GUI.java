package com.rabinart;

import javax.swing.*;
import java.awt.*;


public class GUI {
    private static GUI INSTANCE;
    private final JPanel panel;
    private JTextField nameField;
    private JTextField numberField;
    private JTextArea text;

    private GUI(){
        JFrame frame = FrameBase.buildDefaultFrame("Phonebook", 650, 450);
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.gray);
        frame.add(panel);
    }

    public static GUI getInstance(){
        if (INSTANCE == null)
            INSTANCE = new GUI();
        return INSTANCE;
    }

    public void addNameField(){
        nameField = new JTextField(10);
        var label = new JLabel(" Name :");
        addField(label,nameField);
    }
    public void addNumberField(){
        numberField = new JTextField(10);
        var label = new JLabel("Number :");
        addField(label,numberField);
    }


    public void addField(JLabel label, JTextField field){
        field.setMinimumSize(new Dimension(100,20));
        panel.add(label, GridBagUtils.setUpConstrains(-1,0,1,1));
        panel.add(field, GridBagUtils.setUpConstrains(-1,0,1,1));
    }

    public void addButtonSet(ActionButtonSet btn){
        var buttons = btn.getButtons();
        for (JButton button : buttons) {
            button.setSize(new Dimension(30, 7));
            panel.add(button, GridBagUtils.setUpConstrains(-1, 0, 1, 1));
        }
    }
    public void configureText(){
        text = new JTextArea(15, 45);
        text.setEditable(false);
        text.setMinimumSize(new Dimension(500, 300));
        text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 17));
        var scrollPane = new JScrollPane(text);
        panel.add(scrollPane,GridBagUtils.setUpConstrains(0,1,0,8));
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextArea getText() {
        return text;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getNumberField() {
        return numberField;
    }
}
