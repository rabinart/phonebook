package com.rabinart;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Phonebook implements Serializable {
    private Set<UserDto> phonebook;
    private static final String NUMBER_REGEX = "^\\+?[1-9][0-9]{3,14}$";


    public Phonebook(){
        loadPhoneBook();
    }

    /*Loads serialized phonebook or creates new one if it not exists or exception has happened*/
    @SuppressWarnings("unchecked")
    private void loadPhoneBook() {
        File file = setFileLocation();
        if (file.exists()){
            try (var object = new ObjectInputStream(new FileInputStream(file))){
                phonebook = (Set<UserDto>) object.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                phonebook = new HashSet<>();
            }
        }
        else phonebook = new HashSet<>();
    }


    public List<UserDto> findAll(){
        return new ArrayList<>(phonebook);
    }

    public List<UserDto> find(String name, String number){
        return phonebook.stream()
                .filter(userDto -> userDto.getName().equals(name) || userDto.getNumber().equals(number))
                .collect(Collectors.toList());
    }

    public boolean put(String name, String number){
        if (number.matches(NUMBER_REGEX) && phonebook.add(new UserDto(name, number))){
            save();
            return true;
        }
        else return false;
    }

    public boolean remove(String number){
        if (phonebook.remove(new UserDto("", number))){
            save();
            return true;
        }
        return false;
    }

    private void save(){
        var file = setFileLocation();
        try (var outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            outputStream.writeObject(phonebook);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private File setFileLocation(){
        File file;
        try {
            getClass().getClassLoader().loadClass("com.rabinart.PhonebookTest");
            file = Path.of("src","test", "resources","phonebook.out").toFile();
        }catch (ClassNotFoundException e){
            file = Path.of("src","main", "resources","phonebook.out").toFile();
        }
        return file;
    }

    public Set<UserDto> getPhonebook() {
        return phonebook;
    }
}
