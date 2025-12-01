package net.azurewebsites.amazin_online_bookstore.service;

import net.azurewebsites.amazin_online_bookstore.entity.Book;
import net.azurewebsites.amazin_online_bookstore.entity.JaccardDistance;
import net.azurewebsites.amazin_online_bookstore.entity.Person;
import net.azurewebsites.amazin_online_bookstore.entity.Purchase;
import net.azurewebsites.amazin_online_bookstore.repository.JaccardDistanceRepository;
import net.azurewebsites.amazin_online_bookstore.repository.PersonRepository;
import net.azurewebsites.amazin_online_bookstore.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
/**
 * The PersonService class handles the typical operations for managing users.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-17
 */
@Service
public class PersonService {
	@Autowired
    PersonRepository personRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private JaccardDistanceRepository jaccardRepository;

    public Person findById(Integer userId) {
        return personRepository.findById(userId).orElse(null);
    }

    public HashSet<Book> getPurchasedBooks(Person person) {
        HashSet<Book> purchasedBooks = new HashSet<>();

        for (Purchase purchase : purchaseRepository.findAllByBuyer(person)) {
            purchasedBooks.add(purchase.getPurchasedBook());
        }
        return purchasedBooks;
    }

    public ArrayList<Book> getRecommendedBooks(Person person) {
        ArrayList<Book> recommendedBooks = new ArrayList<>();

        HashSet<Book> purchasedBooks = getPurchasedBooks(person);

        for (JaccardDistance jaccardDistance :
             jaccardRepository.findByPerson1OrPerson2OrderByJaccardIndexDesc(person, person))
        {
            Person similarPerson;

            if (person.getId() == jaccardDistance.getPerson1().getId()) {
                similarPerson = jaccardDistance.getPerson2();
            } else {
                similarPerson = jaccardDistance.getPerson1();
            }

            HashSet<Book> similarBooks = getPurchasedBooks(similarPerson);
            similarBooks.removeAll(purchasedBooks);

            for (Book book : similarBooks) {
                recommendedBooks.add(book);
                if (recommendedBooks.size() >= 3) {
                    return recommendedBooks;
                }
            }
        }

        return recommendedBooks;
    }
}
