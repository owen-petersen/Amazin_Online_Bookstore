package net.azurewebsites.amazin_online_bookstore;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,Integer> {
    Book findByIsbn(String isbn);
    List<Book> findAll();
}
