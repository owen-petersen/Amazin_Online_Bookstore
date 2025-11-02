package net.azurewebsites.amazin_online_bookstore;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {


    Optional<Book> findById(Integer id);

    List<Book> findTop5ByAuthorAndIdNot(String author, Integer id);

    Book findByIsbn(String isbn);

    List<Book> findAll();
}
