package net.azurewebsites.amazin_online_bookstore;

import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Optional<Book> findById(Integer id);

    Optional<Book> findByIsbn(String isbn);
    List<Book> findTop5ByAuthorAndIdNot(String author, Integer id);
}
