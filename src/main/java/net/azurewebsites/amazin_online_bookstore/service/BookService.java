package net.azurewebsites.amazin_online_bookstore.service;

import net.azurewebsites.amazin_online_bookstore.entity.Book;
import net.azurewebsites.amazin_online_bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
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

    public boolean existsByIsbn(String isbn) {
        return (repo.findByIsbn(isbn).orElse(null) != null);
    }

    public void restockBookInInventory(String isbn, int qty) {
        Book book = repo.findByIsbn(isbn).orElse(null);

        if (book != null) {
            for (int i = 0; i < qty; i++){
                book.incrementInventory();
            }
            repo.save(book);
        }
    }

    public void saveNewBook(Book book) {
        repo.save(book);
    }
}
