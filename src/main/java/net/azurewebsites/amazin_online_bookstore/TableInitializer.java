package net.azurewebsites.amazin_online_bookstore;

import net.azurewebsites.amazin_online_bookstore.entity.Book;
import net.azurewebsites.amazin_online_bookstore.entity.Person;
import net.azurewebsites.amazin_online_bookstore.entity.Purchase;
import net.azurewebsites.amazin_online_bookstore.repository.BookRepository;
import net.azurewebsites.amazin_online_bookstore.repository.PersonRepository;
import net.azurewebsites.amazin_online_bookstore.repository.PurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TableInitializer implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public void run(String... args) throws Exception {
        Person person = new Person();
        person.setEmail("email@email.com");
        person.setUsername("faiazahsan");
        person.setPassword("password123");
        person.setFirstName("Faiaz");
        person.setLastName("Ahsan");
        personRepository.save(person);

        Book book = new Book();
        book.setTitle("The Hobbit");
        book.setAuthor("J.R.R. Tolkien");
        book.setIsbn("123456789");
        book.setPublisher("George Allen & Unwin");
        book.setPublishedYear(1937);
        book.setEdition(1);
        book.setGenre("Fantasy");
        book.setInventory(3);
        book.setNumOfPages(310);
        book.setPrice(25.00);
        bookRepository.save(book);

        Book tale = new Book();
        tale.setTitle("A Tale of Two Cities");
        tale.setAuthor("Charles Dickens");
        tale.setIsbn("987654321");
        tale.setPublisher("Chapman & Hall");
        tale.setPublishedYear(1859);
        tale.setEdition(1);
        tale.setGenre("Historical");
        tale.setInventory(5);
        tale.setNumOfPages(544);
        tale.setPrice(30.00);
        bookRepository.save(tale);

        Purchase purchase = new Purchase();
        purchase.setDateTime("Oct25");
        purchase.setPurchasedBook(book);
        purchase.setQuantity(1);
        purchaseRepository.save(purchase);

    }
}

