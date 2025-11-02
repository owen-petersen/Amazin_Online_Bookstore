package net.azurewebsites.amazin_online_bookstore;

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

        Purchase purchase = new Purchase();
        purchase.setDateTime("Oct25");
        purchase.setPurchasedBook(book);
        purchase.setQuantity(1);
        purchaseRepository.save(purchase);

        System.out.println("Users in DB: " + personRepository.count());
    }
}

