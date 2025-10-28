package net.azurewebsites.amazin_online_bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final BookRepository repo;
    public DataSeeder(BookRepository repo) { this.repo = repo; }

    @Override public void run(String... args) {
        if (repo.count() == 0) {
            Book b = new Book();
            b.setTitle("Clean Code");
            b.setAuthor("Robert C. Martin");
            b.setPublisher("Prentice Hall");
            b.setDescription("A Handbook of Agile Software Craftsmanship.");
            b.setIsbn13("9780132350884");
            b.setGenre("Software");
            b.setPublishingYear(2008);
            b.setPages(464);
            b.setEdition("1st");
            b.setPictureUrl("https://via.placeholder.com/200x300?text=Clean+Code");
            b.setPrice(39.99);
            b.setInventoryCount(7);
            repo.save(b);
        }
    }
}
