package net.azurewebsites.amazin_online_bookstore.service;

import net.azurewebsites.amazin_online_bookstore.entity.Book;
import net.azurewebsites.amazin_online_bookstore.entity.JaccardDistance;
import net.azurewebsites.amazin_online_bookstore.entity.Person;
import net.azurewebsites.amazin_online_bookstore.repository.JaccardDistanceRepository;
import net.azurewebsites.amazin_online_bookstore.repository.PersonRepository;
import net.azurewebsites.amazin_online_bookstore.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Service
public class JaccardService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    JaccardDistanceRepository jaccardRepository;

    private PersonService personService;

    public double calculateJaccardDistance(Person person1, Person person2) {
        HashSet<Book> person1Books = personService.getPurchasedBooks(person1);
        HashSet<Book> person2Books = personService.getPurchasedBooks(person2);

        HashSet<Book> intersection = new HashSet<Book>(person1Books);
        intersection.retainAll(person2Books);

        HashSet<Book> union = new HashSet<Book>(person1Books);
        union.addAll(person2Books);

        return ((double) intersection.size()) / ((double) union.size());
    }

    public void UpdateJaccardsForUser(Person person) {
        Optional<JaccardDistance> jaccardDistance;

        for (Person otherPerson : personRepository.findAll()) {
            if (person.getId() == otherPerson.getId()) { continue; }

            // Search for already calculated jaccard distance, database is ordered so that person1
            // ID must be less than person 2
            if (person.getId() < otherPerson.getId()) {
                jaccardDistance =
                        jaccardRepository.findByPerson1AndPerson2(person, otherPerson);
            } else {
                jaccardDistance =
                        jaccardRepository.findByPerson1AndPerson2(otherPerson, person);
            }

            double jaccardIndex = calculateJaccardDistance(person, otherPerson);

            if (jaccardDistance.isPresent()) {
                // If it already exists then just update it
                jaccardDistance.get().setJaccardIndex(jaccardIndex);
            } else {
                // Doesn't exist so create new row in table
                jaccardRepository.save(new JaccardDistance(person,
                                                           otherPerson,
                                                           jaccardIndex));
            }
        }
    }
}
