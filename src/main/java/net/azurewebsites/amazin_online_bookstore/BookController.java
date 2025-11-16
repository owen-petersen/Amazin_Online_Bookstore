package net.azurewebsites.amazin_online_bookstore;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Book book = service.getById(id);
        if (book == null) return "error/404";
        model.addAttribute("book", book);               // <— ADD THIS
        model.addAttribute("related", service.findRelatedByAuthor(book, 5));
        return "books/book";
    }
}
