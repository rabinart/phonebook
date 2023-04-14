package com.rabinart;

public class GuiPhonebookAdapter implements PhonebookActions{

    private static GuiPhonebookAdapter INSTANCE;
    private final GUI gui = GUI.getInstance();
    private final Phonebook phonebook;

    private GuiPhonebookAdapter(){
        phonebook = new Phonebook();
    }

    public static GuiPhonebookAdapter getInstance(){
        if (INSTANCE == null)
            INSTANCE = new GuiPhonebookAdapter();
        return INSTANCE;
    }

    @Override
    public void put() {
        var number = gui.getNumberField().getText().trim();
        var name = gui.getNameField().getText().trim();
        if (phonebook.put(name, number))
            list();
        else gui.getText().setText("Wrong number format or number already exists");
    }

    @Override
    public void remove() {
        var number = gui.getNumberField().getText().trim();
        if(!phonebook.remove(number))
            gui.getText().setText("Number :" + number + " doesn't exist");
        else list();
    }

    @Override
    public void find() {
        var number = gui.getNumberField().getText().trim();
        var name = gui.getNameField().getText().trim();

        gui.getText().setText("");
        phonebook.find(name,number).forEach(this::print);
    }

    @Override
    public void list() {
        gui.getText().setText("");
        phonebook.findAll().forEach(this::print);
    }

    private void print(UserDto userDto) {
        gui.getText().append(userDto.getNumber() +
                String.format("%" + (30 - userDto.getNumber().length()) + "s", "  ") +
                userDto.getName() + "\n");
    }
}

