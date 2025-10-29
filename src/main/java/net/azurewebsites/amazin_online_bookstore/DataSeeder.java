package net.azurewebsites.amazin_online_bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final BookRepository repo;
    public DataSeeder(BookRepository repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            Book b = new Book();
            b.setTitle("The Hobbit");
            b.setAuthor("J.R.R. Tolkien");
            b.setPublisher("George Allen & Unwin");
            b.setIsbn("123456789");                 // was setIsbn13(...)
            b.setPublishedYear(1937);               // was setPublishingYear(...)
            b.setEdition(1);                        // Integer, not "1st"
            b.setGenre("Fantasy");
            b.setNumOfPages(310);                   // was setPages(...)
            b.setInventory(3);                      // was setInventoryCount(...)
            b.setPrice(25.00);                      // Double is fine
            b.setPicture(null);                     // was setPictureUrl(...), use a URL string if you have one
            repo.save(b);
        }
    }
}
