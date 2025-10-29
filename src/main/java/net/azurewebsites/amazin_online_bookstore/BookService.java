package net.azurewebsites.amazin_online_bookstore;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    // Get by numeric id OR by ISBN string
    public Book getByIdOrIsbn(String idOrIsbn) {
        if (idOrIsbn == null || idOrIsbn.isBlank()) return null;
        try {
            Integer id = Integer.valueOf(idOrIsbn); // team model uses Integer id
            return repo.findById(id).orElse(null);
        } catch (NumberFormatException ignored) {
            // fall back: ISBN (String)
            return repo.findByIsbn(idOrIsbn).orElse(null);
        }
    }

    // Make the id type Integer to match the entity
    public boolean isInStock(Integer bookId, int qty) {
        return repo.findById(bookId)
                .map(b -> b.getInventory() != null && b.getInventory() >= qty)
                .orElse(false);
    }

    public List<Book> findRelatedByAuthor(Book b, int limit) {
        if (b == null || b.getAuthor() == null) return List.of();
        // assuming your repository has something like: findTop5ByAuthorAndIdNot(String author, Integer id)
        return repo.findTop5ByAuthorAndIdNot(b.getAuthor(), Integer.valueOf(b.getId()));
    }
}
