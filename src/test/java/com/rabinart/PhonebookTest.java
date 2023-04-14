package com.rabinart;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PhonebookTest {

    private Phonebook phonebook;
    private final Path file = Path.of("src","test", "resources", "phonebook.out");

    @BeforeEach
    void prepareResources() throws IOException {
        Files.deleteIfExists(file);
    }

    @Test
    @SuppressWarnings("unchecked")
    void ShouldAddAndSave() {
        phonebook = new Phonebook();

        assertTrue(phonebook.put( "Artem", "1234"));
        assertEquals("Artem", phonebook.find("", "1234").get(0).getName());
        assertTrue(phonebook.put( "Ivan", "12345"));
        assertEquals("12345", phonebook.find("", "12345").get(0).getNumber());
        assertTrue(phonebook.put( "Vlada", "123456"));
        assertTrue(phonebook.put( "John", "1234567"));
        assertTrue(phonebook.put( "Michael", "12345678"));

        assertEquals(phonebook.findAll().size(), 5);


        var file = Path.of("src", "test", "resources", "phonebook.out").toFile();
        assertTrue(file.exists());

        try (var stream = new ObjectInputStream(new FileInputStream(file))){
            var savedMap = (Set<UserDto>) stream.readObject();
            assertEquals(savedMap, phonebook.getPhonebook());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void ShouldNotDeleteNothingAndAddTheSame() {
        phonebook = new Phonebook();

        assertFalse(phonebook.remove("1234"));
        assertTrue(phonebook.put("New Entry", "1234"));
        assertFalse(phonebook.put("New Entry", "1234"));
        assertEquals(phonebook.findAll().size(), 1);
        assertEquals(phonebook.find("New Entry","").get(0).getNumber(), "1234");
        assertTrue(phonebook.remove("1234"));
        assertTrue(phonebook.findAll().isEmpty());

        var file = Path.of("src", "test", "resources", "phonebook.out").toFile();
        assertTrue(file.exists());

        try (var stream = new ObjectInputStream(new FileInputStream(file))){
            var savedMap = (Set<UserDto>) stream.readObject();
            assertTrue(savedMap.isEmpty());
            assertEquals(savedMap, phonebook.getPhonebook());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }


}