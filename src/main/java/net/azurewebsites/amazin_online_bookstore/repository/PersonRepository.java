package net.azurewebsites.amazin_online_bookstore.repository;

import net.azurewebsites.amazin_online_bookstore.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person,Integer> {

    Optional<Person> findByUsername(String username);
    Person findByUsernameAndPassword(String username, String password);
}
