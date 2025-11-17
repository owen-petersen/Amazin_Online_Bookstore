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

        Person person2 = new Person();
        person2.setEmail("manager@email.com");
        person2.setUsername("manager");
        person2.setPassword("1234");
        person2.setFirstName("manager");
        person2.setLastName("manager");
        person2.setRole(Person.Role.Employee);
        personRepository.save(person2);

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

        Book twoCities = new Book();
        twoCities.setTitle("A Tale of Two Cities");
        twoCities.setAuthor("Charles Dickens");
        twoCities.setIsbn("9780582030473");
        twoCities.setPublisher("Charles Dickens");
        twoCities.setPublishedYear(1859);
        twoCities.setEdition(2);
        twoCities.setGenre("Historical");
        twoCities.setInventory(5);
        twoCities.setNumOfPages(350);
        twoCities.setPrice(30.00);
        bookRepository.save(twoCities);

        Book dune = new Book();
        dune.setTitle("Dune");
        dune.setAuthor("Frank Herbert");
        dune.setIsbn("9780441172719");
        dune.setPublisher("Chilton Books");
        dune.setPublishedYear(1965);
        dune.setEdition(1);
        dune.setGenre("Science Fiction");
        dune.setInventory(5);
        dune.setNumOfPages(412);
        dune.setPrice(29.99);
        bookRepository.save(dune);

        Book lotr = new Book();
        lotr.setTitle("The Lord of the Rings");
        lotr.setAuthor("J.R.R. Tolkien");
        lotr.setIsbn("9780544003415");
        lotr.setPublisher("Allen & Unwin");
        lotr.setPublishedYear(1954);
        lotr.setEdition(1);
        lotr.setGenre("Fantasy");
        lotr.setInventory(5);
        lotr.setNumOfPages(1178);
        lotr.setPrice(39.99);
        bookRepository.save(lotr);

        Book hailMary = new Book();
        hailMary.setTitle("Project Hail Mary");
        hailMary.setAuthor("Andy Weir");
        hailMary.setIsbn("9780593135204");
        hailMary.setPublisher("Ballantine Books");
        hailMary.setPublishedYear(2021);
        hailMary.setEdition(1);
        hailMary.setGenre("Science Fiction");
        hailMary.setInventory(5);
        hailMary.setNumOfPages(496);
        hailMary.setPrice(27.00);
        bookRepository.save(hailMary);

        Purchase purchase = new Purchase();
        purchase.setDateTime("Oct25");
        purchase.setPurchasedBook(book);
        purchase.setQuantity(1);
        purchaseRepository.save(purchase);

    }
}

