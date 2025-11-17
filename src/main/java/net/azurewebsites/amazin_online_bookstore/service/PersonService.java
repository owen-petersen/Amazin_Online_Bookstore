package net.azurewebsites.amazin_online_bookstore.service;

import net.azurewebsites.amazin_online_bookstore.entity.Person;
import net.azurewebsites.amazin_online_bookstore.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The PersonService class handles the typical operations for managing users.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-17
 */
@Service
public class PersonService {
    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(Integer userId) {
        return personRepository.findById(userId).orElse(null);
    }
}
