package net.azurewebsites.amazin_online_bookstore.repository;

import net.azurewebsites.amazin_online_bookstore.entity.JaccardDistance;
import net.azurewebsites.amazin_online_bookstore.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JaccardDistanceRepository extends CrudRepository<JaccardDistance,Integer> {

    Optional<JaccardDistance> findByPerson1AndPerson2(Person person1, Person person2);

    List<JaccardDistance> findByPerson1OrPerson2OrderByJaccardIndexDesc(Person person1, Person person2);
}
