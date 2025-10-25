package net.azurewebsites.amazin_online_bookstore;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Person,Integer> {
}
