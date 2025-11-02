package net.azurewebsites.amazin_online_bookstore;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {

    Person findByUsernameAndPassword(String username, String password);
}
