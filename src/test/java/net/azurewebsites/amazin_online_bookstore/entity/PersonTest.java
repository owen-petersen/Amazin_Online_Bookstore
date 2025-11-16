package net.azurewebsites.amazin_online_bookstore.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person person;

    @BeforeEach
    public void setUp() {
        // Create a new Person object before each test
        person = new Person();
    }

    @Test
    public void testSetEmail() {
        assertNull(person.getEmail());

        String email1 = "test@example.com";
        person.setEmail(email1);
        assertEquals(email1, person.getEmail());

        String email2 = "new@example.com";
        person.setEmail(email2);
        assertNotEquals(email1, person.getEmail());
        assertEquals(email2, person.getEmail());
    }

    @Test
    public void testSetUsername() {
        assertNull(person.getUsername());

        String username1 = "testUser";
        person.setUsername(username1);
        assertEquals(username1, person.getUsername());

        String username2 = "newUser";
        person.setUsername(username2);
        assertNotEquals(username1, person.getUsername());
        assertEquals(username2, person.getUsername());
    }

    @Test
    public void testSetPassword() {
        assertNull(person.getPassword());

        String password1 = "secret123";
        person.setPassword(password1);
        assertEquals(password1, person.getPassword());

        String password2 = "newSecret456";
        person.setPassword(password2);
        assertNotEquals(password1, person.getPassword());
        assertEquals(password2, person.getPassword());
    }

    @Test
    public void testSetFirstName() {
        assertNull(person.getFirstName());

        String firstName1 = "John";
        person.setFirstName(firstName1);
        assertEquals(firstName1, person.getFirstName());

        String firstName2 = "Jane";
        person.setFirstName(firstName2);
        assertNotEquals(firstName1, person.getFirstName());
        assertEquals(firstName2, person.getFirstName());
    }

    @Test
    public void testSetLastName() {
        assertNull(person.getLastName());

        String lastName1 = "Doe";
        person.setLastName(lastName1);
        assertEquals(lastName1, person.getLastName());

        String lastName2 = "Smith";
        person.setLastName(lastName2);
        assertNotEquals(lastName1, person.getLastName());
        assertEquals(lastName2, person.getLastName());
    }

    @Test
    public void testSetRole() {
        assertEquals(Person.Role.Customer, person.getRole());

        person.setRole(Person.Role.Employee);
        assertEquals(Person.Role.Employee, person.getRole());
    }
}