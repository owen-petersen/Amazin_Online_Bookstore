package net.azurewebsites.amazin_online_bookstore;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByIsbn13(String isbn13);
    List<Book> findTop5ByAuthorAndIdNot(String author, Long id);
}
