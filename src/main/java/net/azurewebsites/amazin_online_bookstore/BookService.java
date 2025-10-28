package net.azurewebsites.amazin_online_bookstore;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) { this.repo = repo; }

    public Book getByIdOrIsbn(String idOrIsbn) {
        if (!StringUtils.hasText(idOrIsbn)) return null;
        try {
            Long id = Long.parseLong(idOrIsbn);          // try numeric id first
            return repo.findById(id).orElse(null);
        } catch (NumberFormatException ignored) { }
        return repo.findByIsbn13(idOrIsbn).orElse(null);  // fallback: ISBN-13
    }

    public boolean isInStock(Long bookId, int qty) {
        return repo.findById(bookId)
                .map(b -> b.getInventoryCount() != null && b.getInventoryCount() >= qty)
                .orElse(false);
    }

    public List<Book> findRelatedByAuthor(Book b, int limit) {
        if (b == null || b.getAuthor() == null) return List.of();
        return repo.findTop5ByAuthorAndIdNot(b.getAuthor(), b.getId());
    }
}
