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

    public List<Book> findRelatedByAuthor(Book b, int limit) {
        if (b == null || b.getAuthor() == null) return List.of();
        return repo.findTop5ByAuthorAndIdNot(b.getAuthor(), b.getId());
    }
}
