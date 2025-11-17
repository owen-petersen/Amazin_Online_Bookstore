package net.azurewebsites.amazin_online_bookstore.service;

import net.azurewebsites.amazin_online_bookstore.entity.Book;
import net.azurewebsites.amazin_online_bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;


@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {      // Constructor injection (no @Autowired needed)
        this.repo = repo;
    }

    public Book getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public boolean isInStock(Integer bookId, int qty) {
        return repo.findById(bookId)
                .map(b -> b.getInventory() != null && b.getInventory() >= qty)
                .orElse(false);
    }

    /**
     * Retrieves a single book with the bookId from that book's inventory.
     * @param bookId The id of the book to retrieve.
     * @return True if the retrieval could be done false otherwise.
     */
    public boolean takeBookFromInventory(Integer bookId) {
        Book book = repo.findById(bookId).orElse(null);

        if (book != null) {
            book.decrementInventory();
            repo.save(book);
            return true;
        }
        return false;
    }

    public List<Book> findRelatedByAuthor(Book b, int limit) {
        if (b == null || b.getAuthor() == null) return List.of();
        return repo.findTop5ByAuthorAndIdNot(b.getAuthor(), b.getId());
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public List<Book> searchAndFilter(String q, String author, String genre, String publisher) {
        List<Book> all = repo.findAll();

        Stream<Book> stream = all.stream();

        if (q != null && !q.isBlank()) {
            String term = q.toLowerCase();
            stream = stream.filter(b ->
                    (b.getTitle() != null && b.getTitle().toLowerCase().contains(term)) ||
                            (b.getAuthor() != null && b.getAuthor().toLowerCase().contains(term)) ||
                            (b.getGenre() != null && b.getGenre().toLowerCase().contains(term))
            );
        }

        if (author != null && !author.isBlank()) {
            stream = stream.filter(b -> author.equals(b.getAuthor()));
        }

        if (genre != null && !genre.isBlank()) {
            stream = stream.filter(b -> genre.equals(b.getGenre()));
        }

        if (publisher != null && !publisher.isBlank()) {
            stream = stream.filter(b -> publisher.equals(b.getPublisher()));
        }

        return stream.toList();
    }

    public List<String> getAllAuthors() {
        return repo.findAll().stream()
                .map(Book::getAuthor)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getAllGenres() {
        return repo.findAll().stream()
                .map(Book::getGenre)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getAllPublishers() {
        return repo.findAll().stream()
                .map(Book::getPublisher)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
