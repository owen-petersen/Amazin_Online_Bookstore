package net.azurewebsites.amazin_online_bookstore.controller;

import net.azurewebsites.amazin_online_bookstore.service.BookService;
import net.azurewebsites.amazin_online_bookstore.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The BookRestController class
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-02
 */
@RestController
@RequestMapping("/internal/books")
public class BookRestController {
    private BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        return book;
    }
}
