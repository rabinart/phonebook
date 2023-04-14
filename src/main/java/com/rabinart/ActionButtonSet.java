package com.rabinart;

import javax.swing.*;
import java.util.List;

public class ActionButtonSet {
    private static ActionButtonSet INSTANCE;
    private final GuiPhonebookAdapter phonebook = GuiPhonebookAdapter.getInstance();
    private final List<JButton> buttons;


    public static ActionButtonSet getInstance(){
        if (INSTANCE == null)
            INSTANCE = new ActionButtonSet();
        return INSTANCE;
    }


    private ActionButtonSet(){
        buttons = List.of(
                new JButton("add"),
                new JButton("delete"),
                new JButton("find"),
                new JButton("list")
        );
        buttons.get(0).addActionListener(e -> phonebook.put());
        buttons.get(1).addActionListener(e -> phonebook.remove());
        buttons.get(2).addActionListener(e -> phonebook.find());
        buttons.get(3).addActionListener(e -> phonebook.list());

    }
    public List<JButton> getButtons(){
        return buttons;
    }

}
